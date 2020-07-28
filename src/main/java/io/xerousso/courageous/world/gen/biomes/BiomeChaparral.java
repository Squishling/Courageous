package io.xerousso.courageous.world.gen.biomes;

import io.xerousso.courageous.blocks.ModBlocks;
import io.xerousso.courageous.world.gen.BiomeRegistry;
import io.xerousso.courageous.world.gen.ModFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class BiomeChaparral extends Biome {

    public BiomeChaparral() {
        super(new Builder().surfaceBuilder(
                new ConfiguredSurfaceBuilder(BiomeRegistry.CHAPARRAL_SURFACE_BUILDER, SurfaceBuilder.GRASS_DIRT_SAND_CONFIG)
        )
                .category(Category.SAVANNA)
                .precipitation(RainType.NONE)
                .downfall(0.1f)

                .temperature(2f)

                .depth(1.2f)
                .scale(0.6f)

                .waterColor(0x5fcfdf)
                .waterFogColor(0x58bdcc)

                .parent((String) null));

        DefaultBiomeFeatures.addSedimentDisks(this);
        DefaultBiomeFeatures.addStructures(this);

        ModFeatures.addUndergroundFeatures(this);
        ModFeatures.addSavannaStructures(this);

        ModFeatures.addPlant(this, ModBlocks.DESERT_SHRUB.getDefaultState(), 2);
        ModFeatures.addPlant(this, ModBlocks.STEPPE_GRASS.getDefaultState(), 1);

        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.PIG, 10, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.COW, 8, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.HORSE, 1, 2, 6));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.DONKEY, 1, 1, 1));
        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(EntityType.BAT, 10, 8, 8));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITCH, 5, 1, 1));
    }

    @Override
    public int getGrassColor(double x, double y) {
        return 0xab8e35;
    }

}