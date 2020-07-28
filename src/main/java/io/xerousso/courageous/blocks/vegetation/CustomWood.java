package io.xerousso.courageous.blocks.vegetation;

import io.xerousso.courageous.blocks.IBlock;
import io.xerousso.courageous.tabs.WorldTab;
import io.xerousso.courageous.util.lib.DefaultBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;

public class CustomWood extends RotatedPillarBlock implements IBlock {

    public CustomWood(String name, MaterialColor color) {
        super(Block.Properties.create(Material.WOOD, MaterialColor.SAND).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
        DefaultBlockProperties.defaults(this, name);
    }

    public CustomWood(String name) {
        this(name, MaterialColor.WOOD);
    }

    @Override
    public ItemGroup getTab() {
        return WorldTab.WORLD;
    }

}
