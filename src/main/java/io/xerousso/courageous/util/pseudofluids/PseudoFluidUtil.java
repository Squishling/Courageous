//package io.xerousso.courageous.util.pseudofluids;
//
//import io.xerousso.courageous.util.rendering.ModParticles;
//import com.google.common.base.Preconditions;
//import net.minecraft.block.Blocks;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.fluid.Fluids;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.Items;
//import net.minecraft.particles.BlockParticleData;
//import net.minecraft.particles.IParticleData;
//import net.minecraft.util.*;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraftforge.common.extensions.IForgePacketBuffer;
//import net.minecraftforge.common.util.LazyOptional;
//import net.minecraftforge.fluids.FluidAttributes;
//import net.minecraftforge.fluids.FluidStack;
//import net.minecraftforge.fluids.FluidUtil;
//import net.minecraftforge.fluids.capability.IFluidHandler;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//import java.util.HashMap;
//
//public class PseudoFluidUtil {
//    private static final ResourceLocation
//            milk = new ResourceLocation("minecraft", "milk"),
//            cheese = new ResourceLocation("courageous", "cheese");
//    public static HashMap<ResourceLocation, FluidAttributes> fluidRegistryToAttributes = new HashMap<ResourceLocation, FluidAttributes>() {{
//        put(milk,
//                FluidAttributes.builder(Fluids.WATER.getAttributes().getStillTexture(), Fluids.WATER.getAttributes().getFlowingTexture())
//                        .color(0xFFffFFff)
//                        .build(Fluids.EMPTY)
//        );
//        put(cheese,
//                FluidAttributes.builder(new ResourceLocation("block/white_wool"), new ResourceLocation("block/white_wool"))
//                        .color(0xFFffFFff)
//                        .build(Fluids.EMPTY)
//        );
//    }};
//
//    public static HashMap<ResourceLocation, IParticleData> fluidToDrip = new HashMap<ResourceLocation, IParticleData>() {{
//        put(milk, new BlockParticleData(ModParticles.FALLING_FLUID_PARTICLE, Blocks.WHITE_WOOL.getDefaultState()));
//        put(cheese, new BlockParticleData(ModParticles.FALLING_FLUID_PARTICLE, Blocks.WHITE_WOOL.getDefaultState()));
//    }};
//
//    public static HashMap<Item, PseudoFluidInteractionResult> itemsThatFillPseudo = new HashMap<Item, PseudoFluidInteractionResult>() {{
//        put(Items.MILK_BUCKET,
//                new PseudoFluidInteractionResult(
//                        SoundEvents.ITEM_BUCKET_EMPTY,
//                        new ItemStack(Items.BUCKET, 1),
//                        new PseudoFluidStack(milk, 1000),
//                        true
//                )
//        );
//    }};
//
//    //One item might drain several types of fluids. This is a map of items and their map of interactions
//    public static HashMap<Item, HashMap<ResourceLocation, PseudoFluidInteractionResult>> itemsThatEmptyPseudo = new HashMap<Item, HashMap<ResourceLocation, PseudoFluidInteractionResult>>() {{
//        put(Items.BUCKET,
//                new HashMap<ResourceLocation, PseudoFluidInteractionResult>() {{
//                    put(milk,
//                            new PseudoFluidInteractionResult(
//                                    SoundEvents.ITEM_BUCKET_FILL,
//                                    new ItemStack(Items.MILK_BUCKET, 1),
//                                    new PseudoFluidStack(milk, 1000),
//                                    false
//                            )
//                    );
//                }}
//        );
//        put(Items.AIR,
//                new HashMap<ResourceLocation, PseudoFluidInteractionResult>() {{
//                    put(cheese,
//                            new PseudoFluidInteractionResult(
//                                    SoundEvents.ENTITY_ITEM_PICKUP,
//                                    new ItemStack(Items.WHITE_WOOL, 1),
//                                    new PseudoFluidStack(cheese, 1000),
//                                    false
//                            )
//                    );
//                }}
//        );
//    }};
//
//    public static boolean interactWithFluidHandler(@Nonnull PlayerEntity player, @Nonnull Hand hand, @Nonnull World world, @Nonnull BlockPos pos, @Nullable Direction side) {
//        Preconditions.checkNotNull(world);
//        Preconditions.checkNotNull(pos);
//        Preconditions.checkNotNull(player);
//
//        ItemStack held = player.getHeldItem(hand);
//
//        LazyOptional<IFluidHandler> optionalFh = FluidUtil.getFluidHandler(world, pos, side);
//        if (!optionalFh.isPresent()) return false;
//        IFluidHandler iFluidHandler = optionalFh.orElseThrow(UnsupportedOperationException::new);
//
//        FluidStack probe = iFluidHandler.getFluidInTank(0); //Let's assume we can only ever interact with tank 0 for now
//
//        //Conditions for possible fill
//        if (probe.isEmpty() || isFluidFake(probe)) {
//            if (tryFillWithPseudo(iFluidHandler, player, hand, held)) return true;
//        }
//        //Conditions for possible drainage
//        if (!probe.isEmpty() && isFluidFake(probe)) {
//            if (tryEmptyPseudo(iFluidHandler, player, hand, held, probe)) return true;
//        }
//
//        if (probe.isEmpty() || !isFluidFake(probe))
//            return FluidUtil.interactWithFluidHandler(player, hand, world, pos, side);
//        return false;
//    }
//
//    public static boolean tryFillWithPseudo(IFluidHandler iFluidHandler, PlayerEntity player, Hand hand, ItemStack held) {
//        if (itemsThatFillPseudo.containsKey(held.getItem())) {
//            PseudoFluidInteractionResult result = itemsThatFillPseudo.get(held.getItem());
//            iFluidHandler.fill(result.fluidStack, IFluidHandler.FluidAction.EXECUTE);
//            player.world.playSound(null, player.getPosX(), player.getPosY() + 0.5, player.getPosZ(), result.sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
//            consumeAndGivePlayerItem(player, hand, held, result.resultItem.copy());
//            return true;
//        }
//        return false;
//    }
//
//    public static boolean tryEmptyPseudo(IFluidHandler iFluidHandler, PlayerEntity player, Hand hand, ItemStack held, FluidStack probe) {
//        if (itemsThatEmptyPseudo.containsKey(held.getItem())) {
//            HashMap<ResourceLocation, PseudoFluidInteractionResult> possibleInteractions = itemsThatEmptyPseudo.get(held.getItem());
//
//            ResourceLocation fluid = probe instanceof PseudoFluidStack ? ((PseudoFluidStack) probe).getPseudoFluid() : probe.getFluid().getRegistryName();
//
//            if (!possibleInteractions.containsKey((fluid))) return false; //Undrainable fluid for us
//
//            PseudoFluidInteractionResult result = possibleInteractions.get(fluid);
//            //Now see if we can actually drain that
//            FluidStack drained = iFluidHandler.drain(result.fluidStack, IFluidHandler.FluidAction.SIMULATE);
//
//            if (drained.isFluidStackIdentical(result.fluidStack)) {
//                //We can! Let's go bois
//                iFluidHandler.drain(result.fluidStack, IFluidHandler.FluidAction.EXECUTE);
//                player.world.playSound(null, player.getPosX(), player.getPosY() + 0.5, player.getPosZ(), result.sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
//                consumeAndGivePlayerItem(player, hand, held, result.resultItem.copy());
//            }
//
//            return true;
//        }
//
//        return false;
//    }
//
//    public static boolean isFluidFake(FluidStack fluid) {
//        return fluid instanceof PseudoFluidStack && ((PseudoFluidStack) fluid).isFake();
//    }
//
//    //Consumes one of itemstack and gives player itemstack1, provided they aren't in creative
//    private static void consumeAndGivePlayerItem(PlayerEntity player, Hand handIn, ItemStack itemstack, ItemStack itemstack1) {
//        if (!player.abilities.isCreativeMode) {
//            itemstack.shrink(1);
//            if (itemstack.isEmpty()) {
//                player.setHeldItem(handIn, itemstack1);
//            } else if (!player.addItemStackToInventory(itemstack1)) {
//                player.dropItem(itemstack1, false);
//            }
//        }
//    }
//
//    /**
//     * Reads a FluidStack from this buffer.
//     */
//    public static PseudoFluidStack readFluidStack(IForgePacketBuffer buffer) {
//        return !buffer.getBuffer().readBoolean() ? PseudoFluidStack.EMPTY : PseudoFluidStack.readFromPacket(buffer.getBuffer());
//    }
//
//    public static FluidAttributes getFluidAttributes(FluidStack stack) {
//        return stack instanceof PseudoFluidStack ? ((PseudoFluidStack) stack).getAttributes() : stack.getFluid().getAttributes();
//    }
//}
//
