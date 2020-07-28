package io.xerousso.courageous.world.gen.biomes;

import io.xerousso.courageous.world.gen.BiomeRegistry;
import io.xerousso.courageous.world.gen.ModFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class BiomeOasis extends Biome {

    public BiomeOasis() {
        super(new Builder().surfaceBuilder(BiomeRegistry.OASIS_SURFACE_BUILDER, SurfaceBuilder.SAND_CONFIG)
                .category(Category.DESERT)
                .precipitation(RainType.RAIN)
                .downfall(0.4f)

                .temperature(2f)

                .depth(-0.1f)
                .scale(0.075f)

                .waterColor(0x5fcfdf)
                .waterFogColor(0x58bdcc)

                .parent(null));

        DefaultBiomeFeatures.addSedimentDisks(this);
        DefaultBiomeFeatures.addReedsAndPumpkins(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addPlainsTallGrass(this);

        ModFeatures.addUndergroundFeatures(this);
        ModFeatures.addDesertStructures(this);
        ModFeatures.addPillagerOutpost(this);
        ModFeatures.addArabianVillage(this);

        ModFeatures.addPalmTrees(this);
        ModFeatures.addDesertVegetation(this);
        ModFeatures.addGrass(this, 7);
        ModFeatures.addJungleBushes(this, 1, 0.5f, 1);
        ModFeatures.addRocks(this, Blocks.COBBLESTONE.getDefaultState(), 3);

        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.PIG, 10, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.COW, 8, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.RABBIT, 4, 2, 3));
        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(EntityType.BAT, 10, 8, 8));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITCH, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE, 19, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 1, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.HUSK, 80, 4, 4));
    }

    @Override
    public int getGrassColor(double x, double z) {
        return 0x53c11b;
    }
}
