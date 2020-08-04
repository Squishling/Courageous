package io.xerousso.courageous.blocks;

import io.xerousso.courageous.blocks.pot.BlockFluidPotBase;
import io.xerousso.courageous.blocks.vegetation.*;
import io.xerousso.courageous.items.IItem;
import io.xerousso.courageous.items.Itemz;
import io.xerousso.courageous.trees.*;
import io.xerousso.courageous.util.Util;
import io.xerousso.courageous.world.gen.ModFeatures;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.trees.Tree;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Random;

public class Blockz {

    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Util.MOD_ID);

//    public static final Block PALM_SAPLING = new CustomSapling("palm_sapling", new PalmTree());
    public static RegistryObject<Block> PALM_LEAVES = BLOCKS.register("palm_leaves", DirectionLeavesBlock::new);
    public static RegistryObject<Block> PALM_LOG = BLOCKS.register("palm_log", () -> new LogBlock(MaterialColor.SAND, MaterialColor.SAND));
    public static RegistryObject<Block> STRIPPED_PALM_LOG = BLOCKS.register("stripped_palm_log", () -> new LogBlock(MaterialColor.SAND, MaterialColor.SAND));
    public static RegistryObject<Block> PALM_WOOD = BLOCKS.register("palm_wood", () -> new LogBlock(MaterialColor.SAND, MaterialColor.SAND));
    public static RegistryObject<Block> STRIPPED_PALM_WOOD = BLOCKS.register("stripped_palm_wood", () -> new LogBlock(MaterialColor.SAND, MaterialColor.SAND));
    public static RegistryObject<Block> PALM_PLANKS = BLOCKS.register("palm_planks", () -> new Planks(MaterialColor.ADOBE));

    public static RegistryObject<Block> PEAR_SAPLING = BLOCKS.register("pear_sapling", () -> new Sapling(PearTree::new));
    public static RegistryObject<Block> PEAR_LEAVES = BLOCKS.register("pear_leaves", () -> new HarvestableLeaves(Itemz.PEAR::get).setMinMax(1, 2));

    public static RegistryObject<Block> APPLE_SAPLING = BLOCKS.register("apple_sapling", () -> new Sapling(AppleTree::new));
    public static RegistryObject<Block> APPLE_LEAVES = BLOCKS.register("apple_leaves", () -> new HarvestableLeaves(() -> Items.APPLE).setMinMax(1, 2));

    public static RegistryObject<Block> KIWI_FRUIT_SAPLING = BLOCKS.register("kiwi_fruit_sapling", () -> new Sapling(KiwiFruitTree::new));
    public static RegistryObject<Block> KIWI_FRUIT_LEAVES = BLOCKS.register("kiwi_fruit_leaves", () -> new HarvestableLeaves(Itemz.KIWI_FRUIT::get).setMinMax(1, 2));

    public static RegistryObject<Block> LEMON_SAPLING = BLOCKS.register("lemon_sapling", () -> new Sapling(LemonTree::new));
    public static RegistryObject<Block> LEMON_LEAVES = BLOCKS.register("lemon_leaves", () -> new HarvestableLeaves(Itemz.LEMON::get).setMinMax(1, 2));

    public static RegistryObject<Block> AVOCADO_SAPLING = BLOCKS.register("avocado_sapling", () -> new Sapling(AvocadoTree::new));
    public static RegistryObject<Block> AVOCADO_LEAVES = BLOCKS.register("avocado_leaves", () -> new HarvestableLeaves(Itemz.AVOCADO::get).setMinMax(1, 2));

    public static RegistryObject<Block> PLUM_SAPLING = BLOCKS.register("plum_sapling", () -> new Sapling(PlumTree::new));
    public static RegistryObject<Block> PLUM_LEAVES = BLOCKS.register("plum_leaves", () -> new HarvestableLeaves(Itemz.PLUM::get).setMinMax(1, 2));

    public static RegistryObject<Block> ORANGE_SAPLING = BLOCKS.register("orange_sapling", () -> new Sapling(OrangeTree::new));
    public static RegistryObject<Block> ORANGE_LEAVES = BLOCKS.register("orange_leaves", () -> new HarvestableLeaves(Itemz.ORANGE::get).setMinMax(1, 2));

//    public static final Block MAPLE_SAPLING = new CustomSapling("maple_sapling", new MapleTree());






    public static RegistryObject<Block> GREEN_MAPLE_SAPLING = BLOCKS.register("green_maple_sapling", () -> new Sapling(() -> new Tree() {
        @Nullable
        @Override
        protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
            return Feature.field_236291_c_.withConfiguration(ModFeatures.GREEN_MAPLE_TREE_CONFIG);
        }
    }));
    public static RegistryObject<Block> GREEN_MAPLE_LEAVES = BLOCKS.register("green_maple_leaves", Leaves::new);
    public static RegistryObject<Block> FALLEN_GREEN_MAPLE_LEAVES = BLOCKS.register("fallen_green_maple_leaves", FallenLeaves::new);

    public static RegistryObject<Block> YELLOW_MAPLE_SAPLING = BLOCKS.register("yellow_maple_sapling", () -> new Sapling(() -> new Tree() {
        @Nullable
        @Override
        protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
            return Feature.field_236291_c_.withConfiguration(ModFeatures.YELLOW_MAPLE_TREE_CONFIG);
        }
    }));
    public static RegistryObject<Block> YELLOW_MAPLE_LEAVES = BLOCKS.register("yellow_maple_leaves", Leaves::new);
    public static RegistryObject<Block> FALLEN_YELLOW_MAPLE_LEAVES = BLOCKS.register("fallen_yellow_maple_leaves", FallenLeaves::new);

    public static RegistryObject<Block> ORANGE_MAPLE_SAPLING = BLOCKS.register("orange_maple_sapling", () -> new Sapling(() -> new Tree() {
        @Nullable
        @Override
        protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
            return Feature.field_236291_c_.withConfiguration(ModFeatures.ORANGE_MAPLE_TREE_CONFIG);
        }
    }));
    public static RegistryObject<Block> ORANGE_MAPLE_LEAVES = BLOCKS.register("orange_maple_leaves", Leaves::new);
    public static RegistryObject<Block> FALLEN_ORANGE_MAPLE_LEAVES = BLOCKS.register("fallen_orange_maple_leaves", FallenLeaves::new);

    public static RegistryObject<Block> RED_MAPLE_SAPLING = BLOCKS.register("red_maple_sapling", () -> new Sapling(() -> new Tree() {
        @Nullable
        @Override
        protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
            return Feature.field_236291_c_.withConfiguration(ModFeatures.RED_MAPLE_TREE_CONFIG);
        }
    }));
    public static RegistryObject<Block> RED_MAPLE_LEAVES = BLOCKS.register("red_maple_leaves", Leaves::new);
    public static RegistryObject<Block> FALLEN_RED_MAPLE_LEAVES = BLOCKS.register("fallen_red_maple_leaves", FallenLeaves::new);

    public static RegistryObject<Block> BROWN_MAPLE_SAPLING = BLOCKS.register("brown_maple_sapling", () -> new Sapling(() -> new Tree() {
        @Nullable
        @Override
        protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
            return Feature.field_236291_c_.withConfiguration(ModFeatures.BROWN_MAPLE_TREE_CONFIG);
        }
    }));
    public static RegistryObject<Block> BROWN_MAPLE_LEAVES = BLOCKS.register("brown_maple_leaves", Leaves::new);
    public static RegistryObject<Block> FALLEN_BROWN_MAPLE_LEAVES = BLOCKS.register("fallen_brown_maple_leaves", FallenLeaves::new);

    public static RegistryObject<Block> MAPLE_LOG = BLOCKS.register("maple_log", () -> new LogBlock(MaterialColor.DIRT, MaterialColor.IRON));
    public static RegistryObject<Block> MAPLE_LOG_SYRUP = BLOCKS.register("maple_log_with_slit", MapleLog::new);
    public static RegistryObject<Block> STRIPPED_MAPLE_LOG = BLOCKS.register("stripped_maple_log", () -> new LogBlock(MaterialColor.DIRT, MaterialColor.DIRT));
    public static RegistryObject<Block> MAPLE_WOOD = BLOCKS.register("maple_wood", () -> new LogBlock(MaterialColor.IRON, MaterialColor.IRON));
    public static RegistryObject<Block> STRIPPED_MAPLE_WOOD = BLOCKS.register("stripped_maple_wood", () -> new LogBlock(MaterialColor.DIRT, MaterialColor.DIRT));
    public static RegistryObject<Block> MAPLE_PLANKS = BLOCKS.register("maple_planks", () -> new Planks(MaterialColor.DIRT));

//    public static final Block REDWOOD_SAPLING = new CustomSapling("redwood_sapling", new RedwoodTree()).setShape(Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D));
    public static RegistryObject<Block> REDWOOD_LEAVES = BLOCKS.register("redwood_leaves", () -> new Leaves());
    public static RegistryObject<Block> REDWOOD_LOG = BLOCKS.register("redwood_log", () -> new LogBlock(MaterialColor.ADOBE, MaterialColor.ADOBE));
    public static RegistryObject<Block> STRIPPED_REDWOOD_LOG = BLOCKS.register("stripped_redwood_log", () -> new LogBlock(MaterialColor.ADOBE, MaterialColor.ADOBE));
    public static RegistryObject<Block> REDWOOD_WOOD = BLOCKS.register("redwood_wood", () -> new LogBlock(MaterialColor.ADOBE, MaterialColor.ADOBE));
    public static RegistryObject<Block> STRIPPED_REDWOOD_WOOD = BLOCKS.register("stripped_redwood_wood", () -> new LogBlock(MaterialColor.ADOBE, MaterialColor.ADOBE));
    public static RegistryObject<Block> REDWOOD_PLANKS = BLOCKS.register("redwood_planks", () -> new Planks(MaterialColor.ADOBE));

//    public static final Block ALPINE_SAPLING = new CustomSapling("alpine_sapling", new AlpineTree()).setShape(Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D));
    public static RegistryObject<Block> ALPINE_LEAVES = BLOCKS.register("alpine_leaves", () -> new Leaves());

//    public static final Block DOUGLAS_FIR_SAPLING = new CustomSapling("douglas_fir_sapling", new DouglasFirTree()).setShape(Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D));
    public static RegistryObject<Block> DOUGLAS_FIR_LEAVES = BLOCKS.register("douglas_fir_leaves", () -> new Leaves());

    public static RegistryObject<Block> DESERT_SHRUB = BLOCKS.register("desert_shrub", () -> new Bush(state -> {
        return state.getBlock() == Blocks.GRASS_BLOCK;
    }, Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D)));
    public static RegistryObject<Block> STEPPE_GRASS = BLOCKS.register("steppe_grass", () -> new Bush(state -> {
        return true;
    }, Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D)));

    public static RegistryObject<Block> BULRUSHES = BLOCKS.register("bulrushes", Bulrushes::new);

    public static RegistryObject<Block> FALLEN_LEAVES = BLOCKS.register("fallen_leaves", FallenLeaves::new);

//    public static RegistryObject<Block> POTTERY_WHEEL = BLOCKS.register("pottery_wheel", PotteryWheel::new);
//    public static RegistryObject<Block> CUTTING_BOARD = BLOCKS.register("cutting_board", CuttingBoard::new);
//    public static RegistryObject<Block> ARCHITECTS_TABLE = BLOCKS.register("architects_table", ArchitectsTable::new);

    public static RegistryObject<Block> MUD = BLOCKS.register("mud", MudBlock::new);
    public static RegistryObject<Block> TANNED_SAND = BLOCKS.register("tanned_sand", () -> new CustomSand(0xc9a875));

//    public static RegistryObject<Block> PLANTER_BOX = BLOCKS.register("planter_box", PlanterBoxBlock::new);

//    public static final RegistryObject<Block> FLUID_POT = BLOCKS.register("fluid_pot", BlockFluidPot::new);
    public static final RegistryObject<Block> UNFIRED_FLUID_POT = BLOCKS.register("unfired_fluid_pot", () -> new BlockFluidPotBase(Block.Properties.create(Material.CLAY).sound(SoundType.GROUND).hardnessAndResistance(0.6F)));
//    public static final RegistryObject<Block> DISTILLER = BLOCKS.register("distiller", BlockDistiller::new);
    public static final RegistryObject<Block> FAUCET = BLOCKS.register("bamboo_faucet", BlockBambooFaucet::new);

    public static void setRenderLayers() {
        for (RegistryObject<Block> block : Blockz.BLOCKS.getEntries()) {
            if (block.get() instanceof Sapling || block.get() instanceof Leaves || block.get() instanceof LeavesLike || block.get() instanceof Bush) {
                RenderTypeLookup.setRenderLayer(block.get(), RenderType.getCutout());
            }
        }
    }

}
