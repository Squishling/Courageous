package io.xerousso.courageous.util.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import org.apache.commons.lang3.tuple.Pair;

import java.nio.file.Path;

public class ConfigHandler {

    public static final ClientConfig CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    public static final CommonConfig COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        final Pair<ClientConfig, ForgeConfigSpec> specPair = new Builder().configure(ClientConfig::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    static {
        final Pair<CommonConfig, ForgeConfigSpec> specPair = new Builder().configure(CommonConfig::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static class ClientConfig {

        public final BooleanValue itemsInVanillaTabs;

        ClientConfig(Builder builder) {
            builder.push("Creative Tabs");
            itemsInVanillaTabs = builder
                    .comment("Whether to place Courageous' items in vanilla tabs")
                    .define("itemsInVanillaTabs", false);
            builder.pop();
        }

    }

    public static class CommonConfig {

        public final BooleanValue spawnBiomes;
        public final BooleanValue spawnAutumnalForest;
        public final BooleanValue spawnFruitfulForest;
        public final BooleanValue spawnLushDesert;
        public final BooleanValue spawnAlpineForest;
        public final BooleanValue spawnSparseAlpineForest;
        public final BooleanValue spawnTundra;

        public final BooleanValue spawnEntities;

        public final BooleanValue removeFantasy;
        public final BooleanValue removeBlocks;
        public final BooleanValue removeItems;
        public final BooleanValue removeEntities;
        public final BooleanValue removeDimensions;
        public final BooleanValue removeStructures;

        public final ForgeConfigSpec.IntValue distillerProgressChance;
        public final ForgeConfigSpec.IntValue distillerMaxDrainPerTick;
        public final ForgeConfigSpec.IntValue bambooFaucetCapacity;
        public final ForgeConfigSpec.IntValue bambooFaucetSpeed;


        CommonConfig(Builder builder) {
            builder.push("Main");
            spawnBiomes = builder
                    .comment("Whether to spawn Courageous' biomes in the overworld.")
                    .define("spawnBiomes", true);
            spawnEntities = builder
                    .comment("Whether to spawn Courageous' animals in the overworld.")
                    .define("spawnEntities", true);
            removeFantasy = builder
                    .comment("Whether to remove fantasy or magic things in Minecraft such as monsters, dimensions, and more.")
                    .define("removeFantasy", false);
            builder.pop();

            builder.push("Biomes");
            spawnAlpineForest = builder
                    .comment("Whether to spawn the alpine forest biome.  Only active if `spawnBiomes` is.")
                    .define("spawnAlpineForest", true);
            spawnSparseAlpineForest = builder
                    .comment("Whether to spawn the sparse alpine forest biome.  Only active if `spawnBiomes` is.")
                    .define("spawnSparseAlpineForest", true);
            spawnAutumnalForest = builder
                    .comment("Whether to spawn the autumnal forest biome.  Only active if `spawnBiomes` is.")
                    .define("spawnAutumnalForest", true);
            spawnFruitfulForest = builder
                    .comment("Whether to spawn the fruitful forest biome.  Only active if `spawnBiomes` is.")
                    .define("spawnFruitfulForest", true);
            spawnLushDesert = builder
                    .comment("Whether to spawn the lush desrt biome.  Only active if `spawnBiomes` is.")
                    .define("spawnLushDesert", true);
            spawnTundra = builder
                    .comment("Whether to spawn the tundra biome.  Only active if `spawnBiomes` is.")
                    .define("spawnTundra", true);
            builder.pop();

            builder.push("Entities");
            builder.pop();

            builder.push("Removals");
            removeBlocks = builder
                    .comment("Whether to remove fantasy/magic blocks from Minecraft, like Purpur blocks, prismarine, and more.  Only active if `removeFantasy` is.")
                    .define("removeBlocks", true);
            removeItems = builder
                    .comment("Whether to remove fantasy/magic items from Minecraft, like the totem of undying, golden apples, and more.  Only active if `removeFantasy` is.")
                    .define("removeItems", true);
            removeDimensions = builder
                    .comment("Whether to remove the nether and end dimensions from Minecraft.  Only active if `removeFantasy` is.")
                    .define("removeDimensions", true);
            removeEntities = builder
                    .comment("Whether to remove fantasy/magic entities from Minecraft, like monsters, mooshrooms, and more.  Only active if `removeFantasy` is.")
                    .define("removeEntities", true);
            removeStructures = builder
                    .comment("Whether to remove fantasy/magic structures from Minecraft, like strongholds, underwater ruins, and more.  Only active if `removeFantasy` is.")
                    .define("removeStructures", true);
            builder.pop();
            builder.push("Machine Settings");
            distillerProgressChance = builder
                    .comment("Chance per tick for the distiller to make recipe progress, 1/n [1..32767|default:60]")
                    .defineInRange("distillerProgressChance", 60, 1, 32767);
            distillerMaxDrainPerTick = (ForgeConfigSpec.IntValue) builder
                    .comment("Max amount of fluid output in mb per tick on the distiller. Beware: setting this too low will result in overpressure and exploding distillation devices. [0..32767|default:100]")
                    .defineInRange("distillerMaxDrainPerTick", 100, 0, 32767);
            bambooFaucetCapacity = (ForgeConfigSpec.IntValue) builder
                    .comment("Max amount of fluid output in mb stored at any moment in the bamboo faucet [0..32767|default:100]")
                    .defineInRange("bambooFaucetCapacity", 100, 0, 32767);
            bambooFaucetSpeed = (ForgeConfigSpec.IntValue) builder
                    .comment("Max speed of fluid output in mb per tick from the bamboo faucet [0..32767|default:10]")
                    .defineInRange("bambooFaucetSpeed", 10, 0, 32767);
            builder.pop();
        }

    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();

        configData.load();
        spec.setConfig(configData);
    }

}
