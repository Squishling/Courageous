package io.xerousso.courageous.items.pottery;

import io.xerousso.courageous.tabs.PotteryTab;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class Amphora extends Item {

    public Amphora() {
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

        if (!world.getFluidState(pos).isEmpty()) {
            if (contents.getAmount() >= getCapacity(stack)) return new ActionResult<>(ActionResultType.PASS, stack);
            FluidStack fluidToFill = new FluidStack(world.getFluidState(pos).getFluid(), FluidAttributes.BUCKET_VOLUME);

            if (!canAcceptFluid(stack, fluidToFill)) return new ActionResult<>(ActionResultType.PASS, stack);
            if (!world.isRemote) {
                fill(stack, new FluidStack(world.getFluidState(pos).getFluid(), FluidAttributes.BUCKET_VOLUME));
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
            SoundEvent soundevent = contents.getFluid().getAttributes().getEmptySound();
            if (soundevent == null)
                soundevent = contents.getFluid().isIn(FluidTags.LAVA) ? SoundEvents.ITEM_BUCKET_FILL_LAVA : SoundEvents.ITEM_BUCKET_FILL;
            player.playSound(soundevent, 1.0F, 1.0F);

            player.addStat(Stats.ITEM_USED.get(this));
            return new ActionResult<>(ActionResultType.SUCCESS, stack);
        } else {
            if (contents.getAmount() < 1000) return new ActionResult<>(ActionResultType.PASS, stack);
            BlockPos pos1 = pos.offset(blockraytraceresult.getFace());
            BlockState targetState = world.getBlockState(pos1);
            if (targetState.isAir(world, pos1) || !targetState.getMaterial().isSolid() || targetState.getMaterial().isReplaceable()) {
                if (!world.isRemote) {
                    world.setBlockState(pos1, contents.getFluid().getDefaultState().getBlockState());
                    drain(stack, 1000);
                }

                SoundEvent soundevent = contents.getFluid().getAttributes().getEmptySound();
                if (soundevent == null)
                    soundevent = contents.getFluid().isIn(FluidTags.LAVA) ? SoundEvents.ITEM_BUCKET_EMPTY_LAVA : SoundEvents.ITEM_BUCKET_EMPTY;
                player.playSound(soundevent, 1.0F, 1.0F);

                player.addStat(Stats.ITEM_USED.get(this));
                return new ActionResult<>(ActionResultType.SUCCESS, stack);
            }
        }

        return new ActionResult<>(ActionResultType.PASS, stack);
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
                return cap == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY ? (LazyOptional<T>) LazyOptional.of(() -> new FluidHandlerItemStack(stack, 3000))
                        : LazyOptional.empty();
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
