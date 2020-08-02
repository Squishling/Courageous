package io.xerousso.courageous.blocks;

import io.xerousso.courageous.items.IItem;
import io.xerousso.courageous.tabs.WorldTab;
import net.minecraft.block.Block;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;

public class CustomSand extends SandBlock implements IItem {

    public CustomSand(int dustColorIn) {
        super(dustColorIn, Block.Properties.create(Material.SAND, MaterialColor.ADOBE).hardnessAndResistance(0.5F).sound(SoundType.SAND));
    }

    @Override
    public ItemGroup getTab() {
        return WorldTab.WORLD;
    }
}
