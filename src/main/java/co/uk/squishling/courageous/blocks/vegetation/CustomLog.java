package co.uk.squishling.courageous.blocks.vegetation;

import co.uk.squishling.courageous.blocks.ModBlocks;
import co.uk.squishling.courageous.items.ModItems;
import co.uk.squishling.courageous.tabs.Tab;
import co.uk.squishling.courageous.util.Reference;
import co.uk.squishling.courageous.util.lib.DefaultBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class CustomLog extends LogBlock {

    public CustomLog(String name, MaterialColor color) {
        super(color, Block.Properties.create(Material.WOOD, color).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
        DefaultBlockProperties.defaults(this, name);
    }

}
