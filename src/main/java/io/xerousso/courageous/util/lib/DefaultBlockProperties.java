package io.xerousso.courageous.util.lib;

import io.xerousso.courageous.blocks.IBlock;
import io.xerousso.courageous.blocks.ModBlocks;
import io.xerousso.courageous.items.ModItems;
import io.xerousso.courageous.tabs.GeneralTab;
import io.xerousso.courageous.util.Util;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

public class DefaultBlockProperties {

    public static void defaults(Block block, String name) {
        block.setRegistryName(Util.MOD_ID, name);

        ItemGroup group = GeneralTab.GENERAL;
        if (block instanceof IBlock) group = ((IBlock) block).getTab();

        ModBlocks.BLOCKS.add(block);
        ModItems.ITEMS_ARRAY.add(new BlockItem(block, new Item.Properties().group(group)).setRegistryName(new ResourceLocation(Util.MOD_ID, name)));
    }

}
