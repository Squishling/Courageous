package io.xerousso.courageous.world.gen.surface;

import io.xerousso.courageous.blocks.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class OasisSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public static final PerlinNoiseGenerator noiseGen = new PerlinNoiseGenerator(new SharedSeedRandom(13378l), 5, 0);
    public static final SurfaceBuilderConfig TANNED_SAND_DIRT_CONFIG = new SurfaceBuilderConfig(ModBlocks.TANNED_SAND.getDefaultState(), ModBlocks.TANNED_SAND.getDefaultState(), Blocks.DIRT.getDefaultState());

    public OasisSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> deserializer) {
        super(deserializer);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        if (noiseGen.noiseAt(x, z, false) > 0.05f) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRASS_DIRT_SAND_CONFIG);
        } else {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, TANNED_SAND_DIRT_CONFIG);
        }
    }

}
