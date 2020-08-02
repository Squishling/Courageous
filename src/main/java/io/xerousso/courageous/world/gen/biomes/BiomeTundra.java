//package io.xerousso.courageous.world.gen.biomes;
//
//import io.xerousso.courageous.world.gen.BiomeRegistry;
//import io.xerousso.courageous.world.gen.ModFeatures;
//import net.minecraft.entity.EntityClassification;
//import net.minecraft.entity.EntityType;
//import net.minecraft.util.SharedSeedRandom;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.biome.DefaultBiomeFeatures;
//import net.minecraft.world.gen.PerlinNoiseGenerator;
//import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
//import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
//
//public class BiomeTundra extends Biome {
//
////    private static PerlinNoiseGenerator noiseGen = new PerlinNoiseGenerator(new SharedSeedRandom(6378l), 6, 0);
//
//    public BiomeTundra() {
//        super(new Builder().surfaceBuilder(
//                new ConfiguredSurfaceBuilder(BiomeRegistry.TUNDRA_SURFACE_BUILDER, SurfaceBuilder.PODZOL_DIRT_GRAVEL_CONFIG)
//        )
//                .category(Category.ICY)
//                .precipitation(RainType.RAIN)
//                .downfall(0.3f)
//
//                .temperature(0.3f)
//
//                .depth(0.2f)
//                .scale(0.05f)
//
////                .waterColor(0x02367e)
////                .waterFogColor(0x2158a5)
//
//                .parent((String) null));
//
//        DefaultBiomeFeatures.addLakes(this);
//        DefaultBiomeFeatures.addSedimentDisks(this);
//        DefaultBiomeFeatures.addReedsAndPumpkins(this);
//        DefaultBiomeFeatures.addFreezeTopLayer(this);
//        DefaultBiomeFeatures.addSprings(this);
////        DefaultBiomeFeatures.addStructures(this);
//
//        ModFeatures.addUndergroundFeatures(this);
//        ModFeatures.addFallenLeaves(this);
//        ModFeatures.addFallenSnow(this);
//
//        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
//        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
//        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.WOLF, 8, 4, 4));
//        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(EntityType.BAT, 10, 8, 8));
//        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
//        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
//        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
//        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
//        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
//        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SLIME, 100, 4, 4));
//        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
//        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITCH, 5, 1, 1));
//    }
//
//    @Override
//    public int getGrassColor(double x, double z) {
////        if (noiseGen.noiseAt(x, z, false) >= 5) return 0xab552f;
//        return 0xab7a2f;
//    }
//
//    @Override
//    public int getFoliageColor() {
//        return 0xb4411a;
//    }
//}
