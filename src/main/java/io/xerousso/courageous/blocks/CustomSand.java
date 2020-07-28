package io.xerousso.courageous.blocks;

import io.xerousso.courageous.util.lib.DefaultBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class CustomSand extends SandBlock {

    public CustomSand(String name, int dustColorIn) {
        super(dustColorIn, Block.Properties.create(Material.SAND, MaterialColor.ADOBE).hardnessAndResistance(0.5F).sound(SoundType.SAND));

        DefaultBlockProperties.defaults(this, name);
    }

}
