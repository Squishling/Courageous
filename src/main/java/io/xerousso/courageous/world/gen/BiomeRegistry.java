package io.xerousso.courageous.world.gen;

import co.uk.squishling.courageous.world.gen.biomes.*;
import io.xerousso.courageous.world.gen.surface.OasisSurfaceBuilder;
import io.xerousso.courageous.world.gen.surface.SnowyMountainSurfaceBuilder;
import io.xerousso.courageous.util.Util;
import io.xerousso.courageous.world.gen.surface.ChaparralSurfaceBuilder;
import io.xerousso.courageous.world.gen.surface.TundraSurfaceBuilder;
import io.xerousso.courageous.world.gen.biomes.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;

@EventBusSubscriber(bus= Bus.MOD)
public class BiomeRegistry {

    public static Biome FRUIT_FOREST;
    public static Biome AUTUMNAL_FOREST;

    public static Biome ALPINE_FOREST;
    public static Biome SPARSE_ALPINE_FOREST;
    public static Biome TEMPERATE_RAINFOREST;

    public static Biome REDWOOD_FOREST;
    public static Biome DOUGLAS_FIR_FOREST;

    public static Biome TUNDRA;

    public static Biome LUSH_DESERT;
    public static Biome OASIS;
    public static Biome STEPPE;
    public static Biome CHAPARRAL;

    public static Biome WETLANDS;

    public static Biome SNOWY_MOUNTAIN;

    public static Biome BUNGLE_BUNGLE_LOWLANDS;
    public static Biome BUNGLE_BUNGLES;

    public static SurfaceBuilder<SurfaceBuilderConfig> TUNDRA_SURFACE_BUILDER = new TundraSurfaceBuilder(SurfaceBuilderConfig::deserialize);
    public static SurfaceBuilder<SurfaceBuilderConfig> MOUNTAIN_SURFACE_BUILDER = new SnowyMountainSurfaceBuilder(SurfaceBuilderConfig::deserialize);

    public static SurfaceBuilder<SurfaceBuilderConfig> CHAPARRAL_SURFACE_BUILDER = new ChaparralSurfaceBuilder(SurfaceBuilderConfig::deserialize);
    public static SurfaceBuilder<SurfaceBuilderConfig> OASIS_SURFACE_BUILDER = new OasisSurfaceBuilder(SurfaceBuilderConfig::deserialize);

    @SubscribeEvent
    public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
        FRUIT_FOREST = registerStandardBiome(new BiomeFruitForest(), BiomeType.COOL, "fruitful_forest", 6, Type.FOREST, Type.COLD, Type.DENSE);
        AUTUMNAL_FOREST = registerStandardBiome(new BiomeAutumnalForest(), BiomeType.COOL, "autumnal_forest", 16, Type.FOREST, Type.COLD, Type.DENSE);

        ALPINE_FOREST = registerStandardBiome(new BiomeAlpineForest(), BiomeType.COOL, "alpine_forest", 11, Type.FOREST, Type.COLD, Type.CONIFEROUS, Type.HILLS);
        SPARSE_ALPINE_FOREST = registerStandardBiome(new BiomeSparseAlpineForest(), BiomeType.COOL, "sparse_alpine_forest", 8, Type.FOREST, Type.COLD, Type.CONIFEROUS, Type.HILLS);
        TEMPERATE_RAINFOREST = registerStandardBiome(new BiomeTemperateRainforest(), BiomeType.COOL, "temperate_rainforest", 13, Type.FOREST, Type.CONIFEROUS, Type.HILLS, Type.DENSE);

        REDWOOD_FOREST = registerStandardBiome(new BiomeRedwoodForest(), BiomeType.COOL, "redwood_forest", 11, Type.FOREST, Type.CONIFEROUS, Type.WET, Type.DENSE);
        DOUGLAS_FIR_FOREST = registerStandardBiome(new BiomeDouglasFirForest(), BiomeType.COOL, "douglas_fir_forest", 12, Type.FOREST, Type.CONIFEROUS, Type.DENSE, Type.COLD);

        TUNDRA = registerStandardBiome(new BiomeTundra(), BiomeType.ICY, "tundra", 1, Type.COLD, Type.SPARSE, Type.DRY, Type.PLAINS, Type.SNOWY);

        LUSH_DESERT = registerStandardBiome(new BiomeLushDesert(), BiomeType.DESERT, "lush_desert", 10, Type.LUSH, Type.DRY, Type.HILLS, Type.HOT, Type.SANDY);
        OASIS = registerHillBiome(new BiomeOasis(), "oasis", Type.LUSH, Type.DRY, Type.HOT, Type.SANDY);

        BUNGLE_BUNGLE_LOWLANDS = registerStandardBiome(new BiomeBungleBungleLowlands(), BiomeType.WARM, "bungle_bungle_lowlands", 10, Type.DRY, Type.SPARSE, Type.HOT, Type.SANDY, Type.PLAINS, Type.SAVANNA);
        BUNGLE_BUNGLES = registerHillBiome(new BiomeBungleBungles(), "bungle_bungles", Type.DRY, Type.SPARSE, Type.HOT, Type.MESA, Type.SANDY, Type.HILLS);

        STEPPE = registerStandardBiome(new BiomeSteppe(), BiomeType.DESERT, "steppe", 12, Type.PLAINS, Type.DRY, Type.SPARSE, Type.HOT, Type.SAVANNA, Type.SANDY);
        CHAPARRAL = registerStandardBiome(new BiomeChaparral(), BiomeType.DESERT, "chaparral", 13, Type.HILLS, Type.HOT, Type.SPARSE, Type.SANDY, Type.PLAINS);

        WETLANDS = registerStandardBiome(new BiomeWetlands(), BiomeType.COOL, "wetlands", 15, Type.SPARSE, Type.SWAMP, Type.WET, Type.WATER);

        SNOWY_MOUNTAIN = registerStandardBiome(new BiomeSnowyMountain(), BiomeType.ICY, "snowy_mountain", 22, Type.SPARSE, Type.WET, Type.HILLS, Type.SNOWY, Type.COLD);
    }

    public static Biome registerHillBiome(Biome biome, String name, Type... types) {
        Biome biome1 = registerBiome(biome, name, types);
        BiomeManager.addSpawnBiome(biome);

        return biome1;
    }

    public static Biome registerStandardBiome(Biome biome, BiomeType biomeType, String name, int weight, Type... types) {
        Biome biome1 = registerBiome(biome, name, types);

        BiomeManager.addSpawnBiome(biome);
        BiomeManager.addBiome(biomeType, new BiomeEntry(biome, weight));

        return biome1;
    }

    public static Biome registerBiome(Biome biome, String name, Type... types) {
        biome.setRegistryName(Util.MOD_ID, name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        BiomeDictionary.addTypes(biome, Type.OVERWORLD);

        return biome;

//        if (!ConfigHandler.COMMON.spawnBiomes.get() ||
//                (name == "fruitful_forest"      && !ConfigHandler.COMMON.spawnFruitfulForest    .get()) ||
//                (name == "autumnal_forest"      && !ConfigHandler.COMMON.spawnAutumnalForest    .get()) ||
//                (name == "alpine_forest"        && !ConfigHandler.COMMON.spawnAlpineForest      .get()) ||
//                (name == "sparse_alpine_forest" && !ConfigHandler.COMMON.spawnSparseAlpineForest.get()) ||
//                (name == "tundra"               && !ConfigHandler.COMMON.spawnTundra            .get()) ||
//                (name == "lush_desert"          && !ConfigHandler.COMMON.spawnLushDesert        .get())
//        ) return biome;

//        BiomeManager.addSpawnBiome(biome);
//        BiomeManager.addBiome(biomeType, new BiomeEntry(biome, weight));
    }

    public static HashMap<SpawnListEntry, EntityClassification> getSpawns(Biome target) {
        HashMap<SpawnListEntry, EntityClassification> entries = new HashMap<SpawnListEntry, EntityClassification>();

        for (SpawnListEntry entry : ((Biome) target).getSpawns(EntityClassification.AMBIENT)) entries.put(entry, EntityClassification.AMBIENT);
        for (SpawnListEntry entry : ((Biome) target).getSpawns(EntityClassification.WATER_CREATURE)) entries.put(entry, EntityClassification.WATER_CREATURE);
        for (SpawnListEntry entry : ((Biome) target).getSpawns(EntityClassification.CREATURE)) entries.put(entry, EntityClassification.CREATURE);
        for (SpawnListEntry entry : ((Biome) target).getSpawns(EntityClassification.MISC)) entries.put(entry, EntityClassification.MISC);
        for (SpawnListEntry entry : ((Biome) target).getSpawns(EntityClassification.MONSTER)) entries.put(entry, EntityClassification.MONSTER);

        return entries;
    }

}
