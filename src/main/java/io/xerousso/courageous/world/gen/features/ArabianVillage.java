package io.xerousso.courageous.world.gen.features;

import io.xerousso.courageous.util.Util;
import io.xerousso.courageous.world.gen.ModFeatures;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.structure.VillagePieces;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;
import java.util.function.Function;

public class ArabianVillage extends Structure<NoFeatureConfig> {

    public ArabianVillage(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);

        this.setRegistryName(Util.MOD_ID, "arabian_village");
        ModFeatures.FEATURES.add(this);
    }

    @Override
    protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ) {
        int maxDistance = 12;
        int minDistance = (int) (maxDistance * 0.75f);
        if (minDistance == 0) {
            minDistance = 1;
        }
        int k = x + maxDistance * spacingOffsetsX;
        int l = z + maxDistance * spacingOffsetsZ;
        int i1 = k < 0 ? k - maxDistance + 1 : k;
        int j1 = l < 0 ? l - maxDistance + 1 : l;
        int targetChunkX = i1 / maxDistance;
        int targetChunkZ = j1 / maxDistance;
        ((SharedSeedRandom) random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), targetChunkX, targetChunkZ, 14983234);
        targetChunkX = targetChunkX * maxDistance;
        targetChunkZ = targetChunkZ * maxDistance;
        targetChunkX = targetChunkX + random.nextInt(maxDistance - minDistance);
        targetChunkZ = targetChunkZ + random.nextInt(maxDistance - minDistance);
        return new ChunkPos(targetChunkX, targetChunkZ);
    }


    @Override
    public boolean canBeGenerated(BiomeManager biomeManager, ChunkGenerator<?> chunkGenerator, Random random, int chunkPosX, int chunkPosZ, Biome biome) {
        if (biome == Biomes.JUNGLE_EDGE) return false;

        ChunkPos chunkpos = this.getStartPositionForPosition(chunkGenerator, random, chunkPosX, chunkPosZ, 0, 0);
        if (chunkPosX == chunkpos.x && chunkPosZ == chunkpos.z && chunkGenerator.hasStructure(biome, this)) {
            if (!chunkGenerator.hasStructure(biomeManager.getBiome(new BlockPos(chunkPosX * 16, 60, chunkPosZ * 16)), this)) {
                return false;
            }

            return true;
        }
        return false;
    }

    public Structure.IStartFactory getStartFactory() {
        return ArabianVillage.Start::new;
    }


    public String getStructureName() {
        return Util.MOD_ID + ":arabian_village";
    }


    public int getSize() {
        return 18;
    }

    public static class Start extends MarginedStructureStart {
        public Start(Structure<?> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biomeIn) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            VillagePieces.addPieces(generator, templateManager, blockpos, this.components, this.rand, new VillageConfig("courageous:village/arabian/town_centers", 12));
            this.recalculateStructureSize();
        }
    }

}
