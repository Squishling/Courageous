package io.xerousso.courageous.items.pottery;

import io.xerousso.courageous.tabs.PotteryTab;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class WateringCan extends Item {

    public WateringCan() {
        super(new Item.Properties().group(PotteryTab.POTTERY));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (!getFluid(stack).isEmpty()) {
//            String text = TextFormatting.GOLD + new TranslationTextComponent(getFluid(stack).getTranslationKey()).getFormattedText() + TextFormatting.RESET
//                    + " x " + getFluid(stack).getAmount();
//            tooltip.add(new StringTextComponent(text));
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand handIn) {
        ItemStack stack = player.getHeldItem(handIn);

        FluidStack contents = getFluid(stack);

        RayTraceResult raytraceresult = rayTrace(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
        if (raytraceresult.getType() != Type.BLOCK) return new ActionResult<>(ActionResultType.PASS, stack);
        BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
        BlockPos pos = blockraytraceresult.getPos();

        if (!world.getFluidState(pos).isEmpty() && player.isCrouching()) {
            if (contents.getAmount() >= getCapacity(stack) || !world.getFluidState(pos).getFluid().equals(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("minecraft:water")))) return new ActionResult<>(ActionResultType.PASS, stack);
            FluidStack fluidToFill = new FluidStack(world.getFluidState(pos).getFluid(), FluidAttributes.BUCKET_VOLUME);

            if (!canAcceptFluid(stack, fluidToFill)) return new ActionResult<>(ActionResultType.PASS, stack);
            if (!world.isRemote) {
                fill(stack, new FluidStack(world.getFluidState(pos).getFluid(), FluidAttributes.BUCKET_VOLUME));
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }

            player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);

            player.addStat(Stats.ITEM_USED.get(this));
            return new ActionResult<>(ActionResultType.SUCCESS, stack);
        } else {
            if (contents.getAmount() < 50) return new ActionResult<>(ActionResultType.PASS, stack);
            BlockPos pos1 = pos.offset(blockraytraceresult.getFace());
            BlockState targetState = world.getBlockState(pos1);
            if (targetState.isAir(world, pos1) || !targetState.getMaterial().isSolid() || targetState.getMaterial().isReplaceable()) {
                if (!world.isRemote) {
                    if (random.nextInt(7) == 0) applyBonemeal(stack, world, pos, player);
                    drain(stack, 50);
                }

                if (world.getBlockState(pos).getBlock() instanceof CropsBlock && ((IGrowable) world.getBlockState(pos).getBlock()).canGrow(world, pos, world.getBlockState(pos), world.isRemote))
                    world.addParticle(ParticleTypes.COMPOSTER, pos.getX() + 0.4f + random.nextFloat() / 5, pos.getY() + 0.1f + random.nextFloat() / 5, pos.getZ() + 0.4f + random.nextFloat() / 5, 0f, 0f, 0f);

                player.playSound(SoundEvents.WEATHER_RAIN_ABOVE, 0.2F, 1.5F);

//                for (int i = 0; i < 2; i++) world.addParticle(ModParticles.FALLING_WATER_PARTICLE_DATA, player.getPosX(), player.getPosY() + 1.25, player.getPosZ(), (player.getLookVec().getX() * 0.6f - 0.05f) + random.nextFloat() / 10f, 0f, (player.getLookVec().getZ() * 0.6f - 0.05f) + random.nextFloat() / 10f);
                for (int i = 0; i < 4; i++) world.addParticle(ParticleTypes.SPLASH, pos.getX() + 0.4f + random.nextFloat() / 5, pos.getY() + 1.1f + random.nextFloat() / 5, pos.getZ() + random.nextFloat(), 0f, 0f, 0f);

                player.addStat(Stats.ITEM_USED.get(this));
                return new ActionResult<>(ActionResultType.SUCCESS, stack);
            }
        }

        return new ActionResult<>(ActionResultType.PASS, stack);
    }

    public static boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos pos, PlayerEntity player) {
        BlockState blockstate = worldIn.getBlockState(pos);
        int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, worldIn, pos, blockstate, stack);
        if (hook != 0) return hook > 0;
        if (blockstate.getBlock() instanceof CropsBlock) {
            IGrowable igrowable = (IGrowable)blockstate.getBlock();
            if (igrowable.canGrow(worldIn, pos, blockstate, worldIn.isRemote)) {
                if (!worldIn.isRemote) {
                    if (igrowable.canUseBonemeal(worldIn, worldIn.rand, pos, blockstate)) {
                        igrowable.grow((ServerWorld) worldIn, worldIn.rand, pos, blockstate);
                    }
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        FluidStack fluid = getFluid(stack);
        if (fluid.isEmpty()) return 0f;
        return 1 - (float) fluid.getAmount() / getCapacity(stack);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return !getFluid(stack).isEmpty();
    }


    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new ICapabilityProvider() {
            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
                return cap == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY ? (LazyOptional<T>) LazyOptional.of(() -> new FluidHandlerItemStack(stack, 1000) {
                    @Override
                    public boolean canFillFluidType(FluidStack fluid) {
                        return fluid.getFluid().equals(Fluids.WATER);
                    }
                }) : LazyOptional.empty();
            }
        };
    }

    public FluidStack getFluid(ItemStack stack) {
        final LazyOptional<IFluidHandlerItem> cap = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY);
        if (cap.isPresent()) {
            final FluidHandlerItemStack handler = (FluidHandlerItemStack) cap.orElseThrow(NullPointerException::new);
            return handler.getFluid();
        }
        return FluidStack.EMPTY;
    }

    public int getCapacity(ItemStack stack) {
        final LazyOptional<IFluidHandlerItem> cap = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY);
        if (cap.isPresent()) {
            final FluidHandlerItemStack handler = (FluidHandlerItemStack) cap.orElseThrow(NullPointerException::new);
            return handler.getTankCapacity(0);
        }
        return 0;
    }

    public void fill(ItemStack stack, FluidStack resource) {
        final LazyOptional<IFluidHandlerItem> cap = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY);
        if (cap.isPresent()) {
            final FluidHandlerItemStack handler = (FluidHandlerItemStack) cap.orElseThrow(NullPointerException::new);
            handler.fill(resource, IFluidHandler.FluidAction.EXECUTE);
        }
    }

    public void drain(ItemStack stack, int amount) {
        final LazyOptional<IFluidHandlerItem> cap = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY);
        if (cap.isPresent()) {
            final FluidHandlerItemStack handler = (FluidHandlerItemStack) cap.orElseThrow(NullPointerException::new);
            handler.drain(amount, IFluidHandler.FluidAction.EXECUTE);
        }
    }

    public boolean canAcceptFluid(ItemStack stack, FluidStack resource) {
        final LazyOptional<IFluidHandlerItem> cap = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY);
        if (cap.isPresent()) {
            final FluidHandlerItemStack handler = (FluidHandlerItemStack) cap.orElseThrow(NullPointerException::new);
            return handler.canFillFluidType(resource);
        }
        return false;
    }

}
