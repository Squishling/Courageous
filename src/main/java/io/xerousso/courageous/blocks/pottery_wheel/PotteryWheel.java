package io.xerousso.courageous.blocks.pottery_wheel;

import io.xerousso.courageous.blocks.BlockBase;
import io.xerousso.courageous.blocks.IBlock;
import io.xerousso.courageous.tabs.PotteryTab;
import io.xerousso.courageous.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class PotteryWheel extends BlockBase implements IBlock {

    private static final VoxelShape SHAPE = VoxelShapes.or(VoxelShapes.or(Block.makeCuboidShape(2d, 13d, 2d, 14d, 15d, 14d),
            Block.makeCuboidShape(4d, 12d, 4d, 12d, 13d, 12d)),
            Block.makeCuboidShape(0d, 0d, 0d, 16d, 12d, 16d));

    public PotteryWheel() {
        super("pottery_wheel", Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5f));
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return 1;
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(BlockState state) {
        return ToolType.PICKAXE;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if (Util.isServer(world)) {
            TileEntity te = world.getTileEntity(pos);

            if (te instanceof INamedContainerProvider) NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) te, te.getPos());
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean canBeConnectedTo(BlockState state, IBlockReader world, BlockPos pos, Direction facing) {
        return facing != Direction.UP;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return super.getRenderType(state);
    }

    @Override
    public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return false;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PotteryWheelTileEntity();
    }

    @Override
    public ItemGroup getTab() {
        return PotteryTab.POTTERY;
    }

}
