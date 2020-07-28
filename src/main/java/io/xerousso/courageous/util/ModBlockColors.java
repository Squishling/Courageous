package io.xerousso.courageous.util;

import io.xerousso.courageous.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILightReader;
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
    public int getColor(BlockState blockState, @Nullable ILightReader iLightReader, @Nullable BlockPos blockPos, int i) {
        for (Block block : BLOCKS.keySet()) if (blockState.getBlock().equals(block)) return BLOCKS.get(block);
        for (Block block : LEAVES) if (blockState.getBlock().equals(block)) return BiomeColors.getFoliageColor(iLightReader, blockPos);

        if (blockState.getBlock() == ModBlocks.MAPLE_LEAVES) {
            int rgb = BiomeColors.getFoliageColor(iLightReader, blockPos);
            int green = (rgb >> 8) & 0xFF;
            int blue = rgb & 0xFF;

            green *= 0.4;
            blue *= 0.4;

            rgb = 0xFF;
            rgb = (rgb << 8) + green;
            rgb = (rgb << 8) + blue;

            return rgb;
        }

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
        registerBlock(ModBlocks.PALM_LEAVES, 0xe1ff43);

        registerAllFoliage (
            ModBlocks.ALPINE_LEAVES, ModBlocks.PEAR_LEAVES,    ModBlocks.ORANGE_LEAVES,
            ModBlocks.APPLE_LEAVES,  ModBlocks.AVOCADO_LEAVES, ModBlocks.KIWI_FRUIT_LEAVES,
            ModBlocks.LEMON_LEAVES,  ModBlocks.PLUM_LEAVES,    ModBlocks.FALLEN_LEAVES,
            ModBlocks.DOUGLAS_FIR_LEAVES
        );

        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.MAPLE_LEAVES);

        for (Block block : BLOCKS.keySet()) Minecraft.getInstance().getBlockColors().register(INSTANCE, block);
        for (Block block : LEAVES) Minecraft.getInstance().getBlockColors().register(INSTANCE, block);
    }

}
