//package io.xerousso.courageous.blocks.cutting_board;
//
//import io.xerousso.courageous.blocks.IBlock;
//import io.xerousso.courageous.tabs.GeneralTab;
//import io.xerousso.courageous.util.Util;
//import net.minecraft.block.AbstractBlock.Properties;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockRenderType;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.material.Material;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.inventory.container.INamedContainerProvider;
//import net.minecraft.item.ItemGroup;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.ActionResultType;
//import net.minecraft.util.Direction;
//import net.minecraft.util.Hand;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.BlockRayTraceResult;
//import net.minecraft.util.math.shapes.IBooleanFunction;
//import net.minecraft.util.math.shapes.ISelectionContext;
//import net.minecraft.util.math.shapes.VoxelShape;
//import net.minecraft.util.math.shapes.VoxelShapes;
//import net.minecraft.world.IBlockReader;
//import net.minecraft.world.World;
//import net.minecraftforge.common.ToolType;
//import net.minecraftforge.fml.network.NetworkHooks;
//
//import javax.annotation.Nullable;
//
//public class CuttingBoard extends BlockBase implements IBlock {
//
//    private static final VoxelShape SHAPE = VoxelShapes.combine(Block.makeCuboidShape(3d, 0d, 0d, 13d, 1d, 16d), Block.makeCuboidShape(6d, 0d, 14d, 10d, 1d, 15d), IBooleanFunction.ONLY_FIRST);
//
//    public CuttingBoard() {
//        super("cutting_board", Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
//    }
//
//    @Override
//    public int getHarvestLevel(BlockState state) {
//        return 0;
//    }
//
//    @Nullable
//    @Override
//    public ToolType getHarvestTool(BlockState state) {
//        return ToolType.AXE;
//    }
//
//    @Override
//    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
//        if (Util.isServer(world)) {
//            TileEntity te = world.getTileEntity(pos);
//
//            if (te instanceof INamedContainerProvider) NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) te, te.getPos());
//        }
//
//        return ActionResultType.SUCCESS;
//    }
//
////    @Override
//    public boolean canBeConnectedTo(BlockState state, IBlockReader world, BlockPos pos, Direction facing) {
//        return false;
//    }
//
//    @Override
//    public BlockRenderType getRenderType(BlockState state) {
//        return super.getRenderType(state);
//    }
//
////    @Override
//    public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
//        return false;
//    }
//
//    @Override
//    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
//        return SHAPE;
//    }
//
//    @Override
//    public boolean hasTileEntity(BlockState state) {
//        return true;
//    }
//
//    @Nullable
//    @Override
//    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
//        return new CuttingBoardTileEntity();
//    }
//
//    @Override
//    public ItemGroup getTab() {
//        return GeneralTab.GENERAL;
//    }
//
//}
