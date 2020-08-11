package io.xerousso.courageous.blocks;

//import io.xerousso.courageous.tiles.TileFaucet;
import io.xerousso.courageous.items.IItem;
import io.xerousso.courageous.tabs.GeneralTab;
import io.xerousso.courageous.tiles.TileFaucet;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.extensions.IForgeBlock;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nullable;

public class BlockBambooFaucet extends HorizontalBlock implements IForgeBlock, IItem {
    private final static VoxelShape SHAPE_NORTH = Block.makeCuboidShape(6.5, 3, 10, 9.5, 6, 16);
    private final static VoxelShape SHAPE_EAST = Block.makeCuboidShape(0, 3, 6.5, 6, 6, 9.5);
    private final static VoxelShape SHAPE_SOUTH = Block.makeCuboidShape(6.5, 3, 0, 9.5, 6, 6);
    private final static VoxelShape SHAPE_WEST = Block.makeCuboidShape(10, 3, 6.5, 16, 6, 9.5);

    public BlockBambooFaucet() {
        super(Block.Properties.create(Material.BAMBOO)
                .notSolid()
                .sound(SoundType.BAMBOO)
                .harvestLevel(-1)
                .hardnessAndResistance(1.0F)
        );
        this.setDefaultState(this.getStateContainer().getBaseState().with(HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileFaucet();
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader reader, BlockPos pos) {
        return this.canAttachTo(reader, pos.offset(state.get(HORIZONTAL_FACING).getOpposite()), state.get(HORIZONTAL_FACING));
    }

    private boolean canAttachTo(IBlockReader reader, BlockPos pos, Direction direction) {
        if (reader.getTileEntity(pos) == null) return false;
        else
            return reader.getTileEntity(pos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, direction).isPresent();
    }


    @Override
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState p_196271_3_, IWorld world, BlockPos currentPos, BlockPos p_196271_6_) {
        if (facing.getOpposite() == state.get(HORIZONTAL_FACING) && !state.isValidPosition(world, currentPos))
            return Blocks.AIR.getDefaultState();
        return super.updatePostPlacement(state, facing, p_196271_3_, world, currentPos, p_196271_6_);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        if (!context.replacingClickedOnBlock()) {
            BlockState blockstate = context.getWorld().getBlockState(context.getPos().offset(context.getFace().getOpposite()));
            if (blockstate.getBlock() == this && blockstate.get(HORIZONTAL_FACING) == context.getFace()) {
                return null;
            }
        }

        BlockState blockstate1 = this.getDefaultState();

        for (Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockstate1 = blockstate1.with(HORIZONTAL_FACING, direction.getOpposite());
                if (blockstate1.isValidPosition(context.getWorld(), context.getPos())) {
                    return blockstate1;
                }
            }
        }

        return null;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        VoxelShape shape = null;
        switch (state.get(HORIZONTAL_FACING)) {
            case NORTH:
                shape = SHAPE_NORTH;
                break;
            case EAST:
                shape = SHAPE_EAST;
                break;
            case SOUTH:
                shape = SHAPE_SOUTH;
                break;
            case WEST:
                shape = SHAPE_WEST;
                break;
        }
        return shape;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(HORIZONTAL_FACING);
    }

    @Override
    public ItemGroup getTab() {
        return GeneralTab.GENERAL;
    }

}
