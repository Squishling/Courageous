package io.xerousso.courageous.util;

import io.xerousso.courageous.blocks.Blockz;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.biome.BiomeColors;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ModBlockColors implements IBlockColor {

    public static final IBlockColor INSTANCE = new ModBlockColors();
    public static Random rand = new Random();

    private static ArrayList<Block> LEAVES = new ArrayList<Block>();
    private static HashMap<Block, Integer> BLOCKS = new HashMap<Block, Integer>();

    @Override
    public int getColor(BlockState blockState, @Nullable IBlockDisplayReader blockDisplayReader, @Nullable BlockPos blockPos, int p_getColor_4_) {
        for (Block block : BLOCKS.keySet()) if (blockState.getBlock().equals(block)) return BLOCKS.get(block);
        for (Block block : LEAVES) if (blockState.getBlock().equals(block)) return BiomeColors.getFoliageColor(blockDisplayReader, blockPos);

        return 0;
    }

    public static void registerFoliage(Block block) {
        LEAVES.add(block);
    }

    public static void registerAllFoliage(Block... blocks) {
        for (Block block : blocks) registerFoliage(block);
    }

    public static void registerBlock(Block block, int color) {
        BLOCKS.put(block, color);
    }

    public static void registerBlockColors() {
        registerBlock(Blockz.PALM_LEAVES.get(), 0xe1ff43);

        registerBlock(Blockz.GREEN_MAPLE_LEAVES.get(), 0x85b82a);
        registerBlock(Blockz.YELLOW_MAPLE_LEAVES.get(), 0xffe33d);
        registerBlock(Blockz.ORANGE_MAPLE_LEAVES.get(), 0xff8826);
        registerBlock(Blockz.RED_MAPLE_LEAVES.get(), 0xce4d2d);
        registerBlock(Blockz.BROWN_MAPLE_LEAVES.get(), 0x9f4924);

        registerAllFoliage (
            Blockz.ALPINE_LEAVES.get(), Blockz.PEAR_LEAVES.get(),    Blockz.ORANGE_LEAVES.get(),
            Blockz.APPLE_LEAVES.get(),  Blockz.AVOCADO_LEAVES.get(), Blockz.KIWI_FRUIT_LEAVES.get(),
            Blockz.LEMON_LEAVES.get(),  Blockz.PLUM_LEAVES.get(),    Blockz.FALLEN_LEAVES.get(),
            Blockz.DOUGLAS_FIR_LEAVES.get()
        );

        for (Block block : BLOCKS.keySet()) Minecraft.getInstance().getBlockColors().register(INSTANCE, block);
        for (Block block : LEAVES) Minecraft.getInstance().getBlockColors().register(INSTANCE, block);
    }

}
