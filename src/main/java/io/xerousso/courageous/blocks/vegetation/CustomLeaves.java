package io.xerousso.courageous.blocks.vegetation;

import io.xerousso.courageous.blocks.IBlock;
import io.xerousso.courageous.tabs.WorldTab;
import io.xerousso.courageous.util.lib.DefaultBlockProperties;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class CustomLeaves extends LeavesBlock implements IBlock {

    public CustomLeaves(String name) {
        super(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(SoundType.PLANT).tickRandomly().notSolid());
        DefaultBlockProperties.defaults(this, name);
    }

    @Override
    public ItemGroup getTab() {
        return WorldTab.WORLD;
    }

}