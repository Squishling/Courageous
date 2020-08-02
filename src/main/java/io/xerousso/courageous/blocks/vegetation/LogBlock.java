package io.xerousso.courageous.blocks.vegetation;

import io.xerousso.courageous.items.IItem;
import io.xerousso.courageous.tabs.WorldTab;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Direction.Axis;

public class LogBlock extends RotatedPillarBlock implements IItem {

    public LogBlock(MaterialColor top, MaterialColor bark) {
        super(Block.Properties.create(Material.WOOD, blockState -> {
            return blockState.get(AXIS) == Axis.Y ? top : bark;
        }).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
    }

    public LogBlock() {
        this(MaterialColor.WOOD, MaterialColor.OBSIDIAN);
    }

    @Override
    public ItemGroup getTab() {
        return WorldTab.WORLD;
    }
}
