package io.xerousso.courageous.world.gen.biomes;

import io.xerousso.courageous.world.gen.BiomeRegistry;
import io.xerousso.courageous.world.gen.ModFeatures;
import io.xerousso.courageous.world.gen.surface.OasisSurfaceBuilder;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

import javax.annotation.Nullable;

public class BiomeLushDesert extends Biome {

    public BiomeLushDesert() {
        super(new Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, OasisSurfaceBuilder.TANNED_SAND_DIRT_CONFIG)
                .category(Category.DESERT)
                .precipitation(RainType.RAIN)
                .downfall(0.1f)

                .temperature(2f)

                .depth(0.2f)
                .scale(0.2f)

                .waterColor(0x5fcfdf)
                .waterFogColor(0x58bdcc)

                .parent((String) null));

        DefaultBiomeFeatures.addSedimentDisks(this);
        DefaultBiomeFeatures.addReedsAndPumpkins(this);
        DefaultBiomeFeatures.addStructures(this);

        ModFeatures.addUndergroundFeatures(this);
        ModFeatures.addDesertStructures(this);
        ModFeatures.addPillagerOutpost(this);
//        ModFeatures.addArabianVillage(this);

        ModFeatures.addDesertVegetation(this);

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

    @Nullable
    @Override
    public Biome getHill(INoiseRandom rand) {
        return BiomeRegistry.OASIS;
    }

    @Override
    public int getGrassColor(double x, double z) {
        return 0x53c11b;
    }
}
