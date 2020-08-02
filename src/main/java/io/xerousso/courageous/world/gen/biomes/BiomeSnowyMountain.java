//package io.xerousso.courageous.world.gen.biomes;
//
//import io.xerousso.courageous.world.gen.surface.SnowyMountainSurfaceBuilder;
//import io.xerousso.courageous.world.gen.BiomeRegistry;
//import io.xerousso.courageous.world.gen.ModFeatures;
//import net.minecraft.entity.EntityClassification;
//import net.minecraft.entity.EntityType;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.biome.DefaultBiomeFeatures;
//import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
//
//public class BiomeSnowyMountain extends Biome {
//
//    public BiomeSnowyMountain() {
//        super(new Builder().surfaceBuilder(
//                new ConfiguredSurfaceBuilder(BiomeRegistry.MOUNTAIN_SURFACE_BUILDER, SnowyMountainSurfaceBuilder.SNOW_SNOW_GRAVEL_CONFIG)
//        )
//                .category(Category.EXTREME_HILLS)
//                .precipitation(RainType.SNOW)
//                .downfall(0.3f)
//
//                .temperature(0.0f)
//
//                .depth(5f)
//                .scale(0.5f)
//
////                .waterColor(0x5fcfdf)
////                .waterFogColor(0x58bdcc)
//
//                .parent((String) null));
//
//        DefaultBiomeFeatures.addLakes(this);
//        DefaultBiomeFeatures.addSedimentDisks(this);
//        DefaultBiomeFeatures.addReedsAndPumpkins(this);
//        DefaultBiomeFeatures.addFreezeTopLayer(this);
//        DefaultBiomeFeatures.addSprings(this);
//        DefaultBiomeFeatures.addSprings(this);
////        DefaultBiomeFeatures.addStructures(this);
//
//        ModFeatures.addUndergroundFeatures(this);
//
//        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
//        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.PIG, 10, 4, 4));
//        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
//        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.WOLF, 8, 4, 4));
//        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.RABBIT, 4, 2, 3));
//        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.FOX, 8, 2, 4));
//        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(EntityType.BAT, 10, 8, 8));
//        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
//        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
//        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
//        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
//        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
//        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
//        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITCH, 5, 1, 1));
//    }
//
//}
