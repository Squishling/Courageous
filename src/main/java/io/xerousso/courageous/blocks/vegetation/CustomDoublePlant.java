package io.xerousso.courageous.blocks.vegetation;

import io.xerousso.courageous.blocks.IBlock;
import io.xerousso.courageous.tabs.WorldTab;
import io.xerousso.courageous.util.lib.DefaultBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class CustomDoublePlant extends DoublePlantBlock implements IBlock {

    public CustomDoublePlant(String name) {
        super(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT));
        DefaultBlockProperties.defaults(this, name);
    }

    @Override
    public ItemGroup getTab() {
        return WorldTab.WORLD;
    }

}
