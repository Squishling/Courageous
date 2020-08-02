package io.xerousso.courageous.blocks.vegetation;

import io.xerousso.courageous.items.IItem;
import io.xerousso.courageous.tabs.WorldTab;
import net.minecraft.block.Block;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class DoublePlant extends DoublePlantBlock implements IItem {

    public DoublePlant() {
        super(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT));
    }

    @Override
    public ItemGroup getTab() {
        return WorldTab.WORLD;
    }

}
