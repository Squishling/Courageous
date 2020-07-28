package io.xerousso.courageous.blocks.vegetation;

import io.xerousso.courageous.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class HarvestableLeaves extends CustomLeaves {

    public static final BooleanProperty GROWN = BooleanProperty.create("grown");
    private Item item;

    private int min = 1;
    private int max = 2;

    public HarvestableLeaves(String name, Item item) {
        super(name);
        this.setDefaultState(this.stateContainer.getBaseState().with(GROWN, false));
        this.item = item;
    }

    public boolean ticksRandomly(BlockState state) {
        return !state.get(PERSISTENT) || !state.get(GROWN);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);

        if (Util.isServer(world)) if (!state.get(GROWN) && random.nextInt(20) == 0 && world.getLightSubtracted(pos.up(), 0) >= 9) {
            world.setBlockState(pos, state.with(GROWN, true), 2);
        }
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (Util.isServer(worldIn)) if (state.get(GROWN)) {
            spawnAsEntity(worldIn, pos, new ItemStack(this.item, worldIn.getRandom().nextInt(max - min) + min));
            worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
            worldIn.setBlockState(pos, state.with(GROWN, false), 2);
            return ActionResultType.SUCCESS;
        }

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(GROWN);
        super.fillStateContainer(builder);
    }

    public HarvestableLeaves setMinMax(int min, int max) {
        this.min = min;
        this.max = max;

        return this;
    }

    @Override
    public BlockState getStateForPlacement(BlockState state, Direction facing, BlockState state2, IWorld world, BlockPos pos1, BlockPos pos2, Hand hand) {
        return getDefaultState().with(GROWN, false).with(PERSISTENT, true);
    }
}
