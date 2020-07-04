package co.uk.squishling.courageous.world.gen;

import co.uk.squishling.courageous.blocks.ModBlocks;
import co.uk.squishling.courageous.world.gen.features.MudLake;
import co.uk.squishling.courageous.world.gen.features.BulrushesFeature;
import co.uk.squishling.courageous.world.gen.features.trees.*;
import co.uk.squishling.courageous.world.gen.features.trees.fruit.*;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig.Builder;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraftforge.common.IPlantable;

import java.util.ArrayList;

public class ModFeatures {

    public static final ArrayList<Feature> FEATURES = new ArrayList<Feature>();

    public static final AbstractTreeFeature ALPINE_TREE = new AlpineTreeFeature(TreeFeatureConfig::deserialize);
    public static final AbstractTreeFeature REDWOOD_TREE = new RedwoodTreeFeature(TreeFeatureConfig::deserialize);

    public static final AbstractTreeFeature DOUGLAS_FIR_TREE = new DouglasFirTreeFeature(TreeFeatureConfig::deserialize);

//    public static final AbstractTreeFeature GIANT_REDWOOD_TREE = new GiantRedwoodTreeFeature(TreeFeatureConfig::deserialize);

    public static final Feature PALM_TREE = new PalmTreeFeature(NoFeatureConfig::deserialize);
    public static final AbstractTreeFeature MAPLE_TREE = new MapleTreeFeature(TreeFeatureConfig::deserialize);

    public static final AbstractTreeFeature PEAR_TREE = new PearTreeFeature(TreeFeatureConfig::deserialize);
    public static final AbstractTreeFeature ORANGE_TREE = new OrangeTreeFeature(TreeFeatureConfig::deserialize);
    public static final AbstractTreeFeature LEMON_TREE = new LemonTreeFeature(TreeFeatureConfig::deserialize);
    public static final AbstractTreeFeature PLUM_TREE = new PlumTreeFeature(TreeFeatureConfig::deserialize);
    public static final AbstractTreeFeature AVOCADO_TREE = new AvocadoTreeFeature(TreeFeatureConfig::deserialize);
    public static final AbstractTreeFeature KIWI_FRUIT_TREE = new KiwiFruitTreeFeature(TreeFeatureConfig::deserialize);
    public static final AbstractTreeFeature APPLE_TREE = new AppleTreeFeature(TreeFeatureConfig::deserialize);

    public static final Feature MUD_LAKE = new MudLake(NoFeatureConfig::deserialize);
    public static final Feature BULRUSHES = new BulrushesFeature(SeaGrassConfig::deserialize);

    public static void addAlpineTrees(Biome biome) {
        addTree(biome, ALPINE_TREE, 5, 0.1f, 1);
    }

    public static void addSparseAlpineTrees(Biome biome) {
        addTree(biome, ALPINE_TREE, 2, 0.1f, 1);
    }

    public static void addSparseTrees(Biome biome) {
        addDefaultTrees(biome, 1, 0.1f, 1);
    }

    // -1761374426436900763
    // 42266 -1533
    public static void addGiantSpruceTree(Biome biome, BlockState LOG, BlockState LEAVES, IPlantable sapling, int perChunk, float extraChance, int extra) {
        biome.addFeature(Decoration.VEGETAL_DECORATION, Feature.MEGA_SPRUCE_TREE.withConfiguration(new HugeTreeFeatureConfig.Builder(new SimpleBlockStateProvider(LOG), new SimpleBlockStateProvider(LEAVES)).baseHeight(15).heightInterval(15).crownHeight(13).setSapling(sapling).build()).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(perChunk, extraChance, extra))));
    }

    public static void addFruitForestTrees(Biome biome) {
        addDefaultTrees(biome, 4, 0.1f, 2);
        addTree(biome, PEAR_TREE, 1, 0.1f, 1);
        addTree(biome, ORANGE_TREE, 1, 0.1f, 1);
        addTree(biome, LEMON_TREE, 1, 0.1f, 1);
        addTree(biome, PLUM_TREE, 1, 0.1f, 1);
        addTree(biome, AVOCADO_TREE, 1, 0.1f, 1);
        addTree(biome, APPLE_TREE, 1, 0.1f, 1);
        addTree(biome, KIWI_FRUIT_TREE, 1, 0.1f, 1);
    }

    public static void addAutumnalTrees(Biome biome) {
        addDefaultTrees(biome, 4, 0.1f, 1);
        addTree(biome, MAPLE_TREE, 4, 0.1f, 1);
    }

    public static void addPalmTrees(Biome biome) {
        biome.addFeature(Decoration.VEGETAL_DECORATION, PALM_TREE.withConfiguration(new NoFeatureConfig()).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.75f, 1))));
    }

    public static void addVegetation(Biome biome) {
        DefaultBiomeFeatures.addExtraDefaultFlowers(biome);
        DefaultBiomeFeatures.addDenseGrass(biome);
        DefaultBiomeFeatures.addTaigaLargeFerns(biome);
    }

    public static void addDesertVegetation(Biome biome) {
        addPlant(biome, ModBlocks.DESERT_SHRUB.getDefaultState(), 1);
    }

    public static void addFallenLeaves(Biome biome) {
        addPlant(biome, ModBlocks.FALLEN_LEAVES.getDefaultState(), 1);
    }

    public static void addFallenSnow(Biome biome) {
        addPlant(biome, Blocks.SNOW.getDefaultState(), 3);
    }

    public static void addUndergroundFeatures(Biome biome) {
        DefaultBiomeFeatures.addCarvers(biome);
        DefaultBiomeFeatures.addMonsterRooms(biome);
        DefaultBiomeFeatures.addStoneVariants(biome);
        DefaultBiomeFeatures.addOres(biome);
    }

    public static void addDesertStructures(Biome biome) {
        addVilage(biome, "desert");
        biome.addFeature(Decoration.SURFACE_STRUCTURES, Feature.DESERT_PYRAMID.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
    }

    public static void addSavannaStructures(Biome biome) {
        addVilage(biome, "savanna");
        addPillagerOutpost(biome);
    }

    public static void addSparseSavannaTrees(Biome biome) {
        biome.addFeature(Decoration.VEGETAL_DECORATION, Feature.ACACIA_TREE.withConfiguration(DefaultBiomeFeatures.ACACIA_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.05F, 1))));
    }

    public static void addPillagerOutpost(Biome biome) {
        biome.addFeature(Decoration.SURFACE_STRUCTURES, Feature.PILLAGER_OUTPOST.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
    }

    public static void addVilage(Biome biome, String type) {
        biome.addFeature(Decoration.SURFACE_STRUCTURES, Feature.VILLAGE.withConfiguration(new VillageConfig("village/" + type + "/town_centers", 6)).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
    }

    public static void addTree(Biome biome, AbstractTreeFeature tree, int perChunk, float extraChance, int extra) {
        biome.addFeature(Decoration.VEGETAL_DECORATION, tree.withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(perChunk, extraChance, extra))));
    }

    public static void addDefaultTrees(Biome biome, int perChunk, float extraChance, int extra) {
        biome.addFeature(Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.field_230129_h_).withChance(0.2F), Feature.FANCY_TREE.withConfiguration(DefaultBiomeFeatures.field_230131_m_).withChance(0.1F)), Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.field_230132_o_))).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(perChunk, extraChance, extra))));
    }

    public static void addPlant(Biome biome, BlockState state, int frequency) {
        biome.addFeature(Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(state), new SimpleBlockPlacer())).tries(32).build()).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(frequency))));
    }

    public static void addDoublePlant(Biome biome, BlockState state, int frequency) {
        biome.addFeature(Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration((new Builder(new SimpleBlockStateProvider(state), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build()).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(frequency))));
    }

    public static void addMudLake(Biome biome, int chance) {
        biome.addFeature(Decoration.LOCAL_MODIFICATIONS, MUD_LAKE.withConfiguration(new NoFeatureConfig()).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(chance))));
    }

    public static void addSeaPlant(Biome biome, Feature feature) {
        biome.addFeature(Decoration.VEGETAL_DECORATION, feature.withConfiguration(new SeaGrassConfig(22, 0.6D)).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
    }

    public static void addSwampDecoration(Biome biome) {
        addSeaPlant(biome, Feature.SEAGRASS);
        DefaultBiomeFeatures.addSwampVegetation(biome);
        DefaultBiomeFeatures.addSwampClayDisks(biome);
    }

}
