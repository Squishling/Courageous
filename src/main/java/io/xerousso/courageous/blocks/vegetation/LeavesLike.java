package io.xerousso.courageous.blocks.vegetation;

import io.xerousso.courageous.blocks.BlockBase;
import io.xerousso.courageous.blocks.IBlock;
import io.xerousso.courageous.tabs.WorldTab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class LeavesLike extends BlockBase implements IBlock {

    public LeavesLike(String name) {
        this(name, Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(SoundType.PLANT).doesNotBlockMovement());
    }

    public LeavesLike(String name, Block.Properties properties) {
        super(name, properties);
    }

    public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1;
    }

    public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return false;
    }

    public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    @Override
    public ItemGroup getTab() {
        return WorldTab.WORLD;
    }

}
