package io.xerousso.courageous.util;

import io.xerousso.courageous.blocks.Blockz;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

public class ModItemColors implements IItemColor {

    public static final IItemColor INSTANCE = new ModItemColors();

    private static HashMap<Item, Integer> ITEMS = new HashMap<Item, Integer>();
    private static HashMap<Block, Integer> BLOCKS = new HashMap<Block, Integer>();

    @Override
    public int getColor(ItemStack itemStack, int i) {
        for (Item item : ITEMS.keySet()) if (itemStack.getItem().equals(item)) return ITEMS.get(item);
        for (Block item : BLOCKS.keySet()) if (itemStack.getItem() instanceof BlockItem && ((BlockItem) itemStack.getItem()).getBlock().equals(item)) return BLOCKS.get(item);

        return 0;
    }

    public static void registerItem(Item item, int color) {
        ITEMS.put(item, color);
    }

    public static void registerItem(Block item, int color) {
        BLOCKS.put(item, color);
    }

    public static void registerItemColors() {
        registerItem(Blockz.ALPINE_LEAVES.get(), 0x446852);
        registerItem(Blockz.DOUGLAS_FIR_LEAVES.get(), 0x748541);

        registerItem(Blockz.PALM_LEAVES.get(), 0xe1ff43);

        registerItem(Blockz.PEAR_LEAVES.get(), 0x59ae30);
        registerItem(Blockz.ORANGE_LEAVES.get(), 0x59ae30);
        registerItem(Blockz.LEMON_LEAVES.get(), 0x59ae30);
        registerItem(Blockz.PLUM_LEAVES.get(), 0x59ae30);
        registerItem(Blockz.AVOCADO_LEAVES.get(), 0x59ae30);
        registerItem(Blockz.KIWI_FRUIT_LEAVES.get(), 0x59ae30);
        registerItem(Blockz.APPLE_LEAVES.get(), 0x59ae30);

        registerItem(Blockz.GREEN_MAPLE_LEAVES.get(), 0x85b82a);
        registerItem(Blockz.YELLOW_MAPLE_LEAVES.get(), 0xffe33d);
        registerItem(Blockz.ORANGE_MAPLE_LEAVES.get(), 0xff8826);
        registerItem(Blockz.RED_MAPLE_LEAVES.get(), 0xce4d2d);
        registerItem(Blockz.BROWN_MAPLE_LEAVES.get(), 0x9f4924);

        registerItem(Blockz.FALLEN_GREEN_MAPLE_LEAVES.get(), 0x85b82a);
        registerItem(Blockz.FALLEN_YELLOW_MAPLE_LEAVES.get(), 0xffe33d);
        registerItem(Blockz.FALLEN_ORANGE_MAPLE_LEAVES.get(), 0xff8826);
        registerItem(Blockz.FALLEN_RED_MAPLE_LEAVES.get(), 0xce4d2d);
        registerItem(Blockz.FALLEN_BROWN_MAPLE_LEAVES.get(), 0x9f4924);

        registerItem(Blockz.FALLEN_LEAVES.get(), 0x59ae30);

        for (Item item : ITEMS.keySet()) Minecraft.getInstance().getItemColors().register(INSTANCE, item);
        for (Block item : BLOCKS.keySet()) Minecraft.getInstance().getItemColors().register(INSTANCE, item);
    }

}
