package io.xerousso.courageous.blocks.vegetation;

import io.xerousso.courageous.blocks.IBlock;
import io.xerousso.courageous.blocks.ModBlocks;
import io.xerousso.courageous.tabs.WorldTab;
import io.xerousso.courageous.util.lib.DefaultBlockProperties;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Bulrushes extends ShearableDoublePlantBlock implements IWaterLoggable, IBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public Bulrushes() {
        super(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.WET_GRASS));

        setDefaultState(getDefaultState().with(WATERLOGGED, false));

        DefaultBlockProperties.defaults(this, "bulrushes");
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        boolean water = context.getWorld().getFluidState(context.getPos()).isTagged(FluidTags.WATER) && context.getWorld().getFluidState(context.getPos()).getLevel() == 8;
        BlockState state = getDefaultState();
        if (water) state = state.with(WATERLOGGED, true);
        return blockpos.getY() < context.getWorld().getDimension().getHeight() - 1 && context.getWorld().getBlockState(blockpos.up()).isReplaceable(context) ? state : null;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        boolean water = worldIn.getFluidState(pos.up()).isTagged(FluidTags.WATER) && worldIn.getFluidState(pos.up()).getLevel() == 8;
        BlockState newState = this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER);
        if (water) newState = newState.with(WATERLOGGED, true);
        worldIn.setBlockState(pos.up(), newState, 3);
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction direction, BlockState state2, IWorld world, BlockPos pos, BlockPos pos2) {
        if (state.get(WATERLOGGED)) world.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));

        return super.updatePostPlacement(state, direction, state2, world, pos, pos2);
    }

    public IFluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    protected void fillStateContainer(Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
        super.fillStateContainer(builder);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock() == ModBlocks.MUD;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        if (state.get(HALF) != DoubleBlockHalf.UPPER) {
            BlockPos blockpos = pos.down();

            return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
        } else {
            BlockState blockstate = worldIn.getBlockState(pos.down());
            if (state.getBlock() != this) {
                BlockPos blockpos = pos.down();

                return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            }

            return blockstate.getBlock() == this && blockstate.get(HALF) == DoubleBlockHalf.LOWER;
        }
    }

    @Override
    public ItemGroup getTab() {
        return WorldTab.WORLD;
    }
}
