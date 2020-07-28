package io.xerousso.courageous.blocks;

import io.xerousso.courageous.tabs.WorldTab;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;

public class CustomPlanks extends BlockBase implements IBlock {

    public CustomPlanks(String name) {
        this(name, MaterialColor.WOOD);
    }

    public CustomPlanks(String name, MaterialColor color) {
        super(name, Block.Properties.create(Material.WOOD, color).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    }

    @Override
    public ItemGroup getTab() {
        return WorldTab.WORLD;
    }

}
