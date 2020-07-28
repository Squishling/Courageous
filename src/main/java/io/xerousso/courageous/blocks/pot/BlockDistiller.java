package io.xerousso.courageous.blocks.pot;

import io.xerousso.courageous.blocks.ModBlocks;
import io.xerousso.courageous.items.ModItems;
import io.xerousso.courageous.tiles.TileDistiller;
import io.xerousso.courageous.tiles.TileFluidPot;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockDistiller extends BlockFluidPot {
    public static final DirectionProperty HORIZONTAL_FACING = HorizontalBlock.HORIZONTAL_FACING;

    public BlockDistiller() {
        super();
        setDefaultState(getDefaultState().with(BlockFluidPot.OPEN, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder.add(HORIZONTAL_FACING));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        if (player.getHeldItemMainhand().isEmpty()) {
            convertToPot(state, world, pos, player, trace);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }


    //Turn back into a normal pot by removing the cover
    void convertToPot(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockRayTraceResult trace) {
        if (player.getHeldItemMainhand().isEmpty()) {
            TileDistiller fluidPot = (TileDistiller) world.getTileEntity(pos);

            if (!world.isRemote) {
                //If still working recipe, damage player from heat and cancel
                if (fluidPot.isRecipePresent()) {
                    player.attackEntityFrom(DamageSource.IN_FIRE, 1);
                    return;
                }
                world.setBlockState(pos, ModBlocks.FLUID_POT.get().getDefaultState().with(OPEN, true).with(CAMPFIREATTACHED, state.get(CAMPFIREATTACHED)), 3);
                ((TileFluidPot) world.getTileEntity(pos)).copyPotInfo(fluidPot);
            }

            //Play sound on both sides if a recipe is not present
            if (!fluidPot.isRecipePresent()) {
                world.playSound(player, pos, SoundEvents.BLOCK_WET_GRASS_BREAK, SoundCategory.BLOCKS, 0.5f, 1);
                world.playSound(player, pos, SoundEvents.BLOCK_BAMBOO_BREAK, SoundCategory.BLOCKS, 0.5f, 1);
            }
        }
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.with(HORIZONTAL_FACING, direction.rotate(state.get(HORIZONTAL_FACING)));
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileDistiller();
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return new ItemStack(ModItems.FLUID_POT.get());
    }
}
