//package io.xerousso.courageous.blocks.pot;
//
//import io.xerousso.courageous.blocks.ModBlocks;
//import io.xerousso.courageous.items.ModItems;
//import io.xerousso.courageous.tiles.TileDistiller;
//import io.xerousso.courageous.tiles.TileFluidPot;
//import io.xerousso.courageous.util.Util;
//import io.xerousso.courageous.util.pseudofluids.PseudoFluidUtil;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Blocks;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.material.MaterialColor;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.*;
//import net.minecraft.state.BooleanProperty;
//import net.minecraft.state.StateContainer;
//import net.minecraft.state.properties.BlockStateProperties;
//import net.minecraft.tags.ItemTags;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.*;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.BlockRayTraceResult;
//import net.minecraft.util.math.shapes.IBooleanFunction;
//import net.minecraft.util.math.shapes.ISelectionContext;
//import net.minecraft.util.math.shapes.VoxelShape;
//import net.minecraft.util.math.shapes.VoxelShapes;
//import net.minecraft.world.IBlockReader;
//import net.minecraft.world.IWorld;
//import net.minecraft.world.World;
//import net.minecraftforge.common.ToolType;
//
//import javax.annotation.Nullable;
//
//public class BlockFluidPot extends BlockFluidPotBase {
//    public static final ResourceLocation coverTag = new ResourceLocation(Util.MOD_ID, "pot_dirt_covers");
//    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
//    public static final BooleanProperty CAMPFIREATTACHED = BlockStateProperties.ATTACHED;
//
//    private final static float PX = 1 / 16f;
//    private final static VoxelShape coverShape = makeCuboidShape(1, 5, 1, 15, 14, 15);
//
//    public BlockFluidPot() {
//        super(Block.Properties.create(Material.PISTON, MaterialColor.ADOBE)
//                .notSolid()
//                .hardnessAndResistance(1.25F)
//                .harvestTool(ToolType.PICKAXE)
//        );
//        setDefaultState(getStateContainer().getBaseState().with(OPEN, true).with(CAMPFIREATTACHED, false));
//    }
//
//    @Override
//    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
//        builder
//                .add(OPEN)
//                .add(CAMPFIREATTACHED);
//    }
//
//    @Override
//    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
//        ItemStack heldItem = player.getHeldItem(hand);
//        if (state.get(OPEN)) {
//            //Try a vanilla fluid transfer
////            if (((TileFluidPot)world.getTileEntity(pos)).canBeVanillaDrained() && FluidUtil.interactWithFluidHandler(player, hand, world, pos, Direction.UP)) {
////                return ActionResultType.SUCCESS;
////            }
//            if (PseudoFluidUtil.interactWithFluidHandler(player, hand, world, pos, Direction.UP)) {
//                return ActionResultType.SUCCESS;
//            }
//            if (ItemTags.getCollection().getOrCreate(coverTag).contains(heldItem.getItem())) {
//                world.setBlockState(pos, state.with(OPEN, false));
//                heldItem.setCount(heldItem.getCount() - 1);
//                player.setHeldItem(hand, heldItem);
//                world.playSound(player, pos, SoundEvents.BLOCK_WET_GRASS_PLACE, SoundCategory.BLOCKS, 0.5f, 1);
//                return ActionResultType.SUCCESS;
//            }
//        } else {
//            if (heldItem.getItem() == Items.BAMBOO) {
//                this.convertToDistiller(state, world, pos, trace, player, hand, heldItem);
//                heldItem.setCount(heldItem.getCount() - 1);
//                player.setHeldItem(hand, heldItem);
//                return ActionResultType.SUCCESS;
//            } else if (player.getHeldItemMainhand().isEmpty()) {
//                world.setBlockState(pos, state.with(BlockFluidPot.OPEN, true));
//                world.playSound(player, pos, SoundEvents.BLOCK_WET_GRASS_BREAK, SoundCategory.BLOCKS, 0.5f, 1);
//                return ActionResultType.SUCCESS;
//            } else if (heldItem.getItem() == ModItems.FAUCET.get()) {
//                this.convertToDistiller(state, world, pos, trace, player, hand, heldItem);
//                ((BlockItem) heldItem.getItem()).tryPlace(new BlockItemUseContext(new ItemUseContext(player, hand, trace)));
//                return ActionResultType.SUCCESS;
//            }
//        }
//        return super.onBlockActivated(state, world, pos, player, hand, trace);
//    }
//
//    private void convertToDistiller(BlockState state, World world, BlockPos pos, BlockRayTraceResult trace, PlayerEntity player, Hand hand, ItemStack heldItem) {
//        if (!world.isRemote) {
//            Direction face = trace.getFace();
//            if (face == Direction.DOWN || face == Direction.UP) return; //Can't place the bamboo on top or bottom
//
//            TileFluidPot fluidPot = (TileFluidPot) world.getTileEntity(pos);
//
//            world.setBlockState(pos, ModBlocks.DISTILLER.get().getDefaultState().with(BlockDistiller.HORIZONTAL_FACING, trace.getFace()).with(CAMPFIREATTACHED, state.get(CAMPFIREATTACHED)), 3);
//            ((TileDistiller) world.getTileEntity(pos)).copyPotInfo(fluidPot);
//        }
//        world.playSound(player, pos, SoundEvents.BLOCK_BAMBOO_PLACE, SoundCategory.BLOCKS, 0.5f, 1);
//    }
//
//    @Override
//    public boolean hasTileEntity(BlockState state) {
//        return true;
//    }
//
//    @Override
//    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
//        return new TileFluidPot();
//    }
//
//    @Override
//    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext selectionContext) {
//        if (state.get(OPEN)) {
//            return super.getShape(state, reader, pos, selectionContext);
//        } else {
//            return VoxelShapes.combineAndSimplify(super.getShape(state, reader, pos, selectionContext), coverShape, IBooleanFunction.OR);
//        }
//    }
//
//    @Override
//    public BlockState updatePostPlacement(BlockState state, Direction dir, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
//        if (dir == Direction.DOWN) {
//            if (facingState.getBlock() == Blocks.CAMPFIRE) {
//                state = state.with(CAMPFIREATTACHED, true);
//            } else {
//                state = state.with(CAMPFIREATTACHED, false);
//            }
//        }
//        return super.updatePostPlacement(state, dir, facingState, world, currentPos, facingPos);
//    }
//
//    @Nullable
//    @Override
//    public BlockState getStateForPlacement(BlockItemUseContext context) {
//        return super.getStateForPlacement(context).with(CAMPFIREATTACHED, context.getWorld().getBlockState(context.getPos().down()).getBlock() == Blocks.CAMPFIRE);
//    }
//}
