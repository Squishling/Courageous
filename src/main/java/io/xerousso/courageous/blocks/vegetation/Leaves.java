package io.xerousso.courageous.blocks.vegetation;

import io.xerousso.courageous.items.IItem;
import io.xerousso.courageous.tabs.WorldTab;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class Leaves extends LeavesBlock implements IItem {

    public Leaves() {
        super(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(SoundType.PLANT).tickRandomly().notSolid());
    }

    @Override
    public ItemGroup getTab() {
        return WorldTab.WORLD;
    }

}