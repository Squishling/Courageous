package io.xerousso.courageous.world.gen.surface;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class TundraSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public static final PerlinNoiseGenerator noiseGen = new PerlinNoiseGenerator(new SharedSeedRandom(8544l), 4, 0);

    public TundraSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> deserializer) {
        super(deserializer);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        if (noiseGen.noiseAt(x, z, false) > 5) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRASS_DIRT_GRAVEL_CONFIG);
        } else {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, PODZOL_DIRT_GRAVEL_CONFIG);
        }
    }

}
