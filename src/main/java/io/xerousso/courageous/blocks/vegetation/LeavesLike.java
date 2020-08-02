package io.xerousso.courageous.blocks.vegetation;

import io.xerousso.courageous.items.IItem;
import io.xerousso.courageous.tabs.WorldTab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class LeavesLike extends Block implements IItem {

    public LeavesLike() {
        this(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(SoundType.PLANT).doesNotBlockMovement());
    }

    public LeavesLike(Block.Properties properties) {
        super(properties);
    }

    public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1;
    }

    @Override
    public ItemGroup getTab() {
        return WorldTab.WORLD;
    }

}
