package io.xerousso.courageous.blocks;

import io.xerousso.courageous.blocks.architects_table.ArchitectsTable;
import io.xerousso.courageous.blocks.cutting_board.CuttingBoard;
import io.xerousso.courageous.blocks.planter_box.PlanterBoxBlock;
import io.xerousso.courageous.blocks.pot.BlockDistiller;
import io.xerousso.courageous.blocks.pot.BlockFluidPot;
import io.xerousso.courageous.blocks.pot.BlockFluidPotBase;
import io.xerousso.courageous.blocks.pottery_wheel.PotteryWheel;
import co.uk.squishling.courageous.blocks.vegetation.*;
import io.xerousso.courageous.items.ModItems;
import co.uk.squishling.courageous.trees.*;
import io.xerousso.courageous.trees.*;
import io.xerousso.courageous.util.Util;
import io.xerousso.courageous.blocks.vegetation.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;

public class ModBlocks {

    public static ArrayList<Block> BLOCKS = new ArrayList<Block>();

    public static final Block PALM_SAPLING = new CustomSapling("palm_sapling", new PalmTree());
    public static final Block PALM_LEAVES = new DirectionLeavesBlock("palm_leaves");
    public static final Block PALM_LOG = new CustomLog("palm_log", MaterialColor.SAND);
    public static final Block STRIPPED_PALM_LOG = new CustomLog("stripped_palm_log", MaterialColor.SAND);
    public static final Block PALM_WOOD = new CustomWood("palm_wood", MaterialColor.SAND);
    public static final Block STRIPPED_PALM_WOOD = new CustomWood("stripped_palm_wood", MaterialColor.SAND);
    public static final Block PALM_PLANKS = new CustomPlanks("palm_planks");

    public static final Block PEAR_SAPLING = new CustomSapling("pear_sapling", new PearTree());
    public static final Block PEAR_LEAVES = new HarvestableLeaves("pear_leaves", ModItems.PEAR).setMinMax(1, 2);

    public static final Block APPLE_SAPLING = new CustomSapling("apple_sapling", new AppleTree());
    public static final Block APPLE_LEAVES = new HarvestableLeaves("apple_leaves", Items.APPLE).setMinMax(1, 2);

    public static final Block KIWI_FRUIT_SAPLING = new CustomSapling("kiwi_fruit_sapling", new KiwiFruitTree());
    public static final Block KIWI_FRUIT_LEAVES = new HarvestableLeaves("kiwi_fruit_leaves", ModItems.KIWI_FRUIT).setMinMax(1, 2);

    public static final Block LEMON_SAPLING = new CustomSapling("lemon_sapling", new LemonTree());
    public static final Block LEMON_LEAVES = new HarvestableLeaves("lemon_leaves", ModItems.LEMON).setMinMax(1, 2);

    public static final Block AVOCADO_SAPLING = new CustomSapling("avocado_sapling", new AvocadoTree());
    public static final Block AVOCADO_LEAVES = new HarvestableLeaves("avocado_leaves", ModItems.AVOCADO).setMinMax(1, 2);

    public static final Block PLUM_SAPLING = new CustomSapling("plum_sapling", new PlumTree());
    public static final Block PLUM_LEAVES = new HarvestableLeaves("plum_leaves", ModItems.PLUM).setMinMax(1, 2);

    public static final Block ORANGE_SAPLING = new CustomSapling("orange_sapling", new OrangeTree());
    public static final Block ORANGE_LEAVES = new HarvestableLeaves("orange_leaves", ModItems.ORANGE).setMinMax(1, 2);

    public static final Block MAPLE_SAPLING = new CustomSapling("maple_sapling", new MapleTree());
    public static final Block MAPLE_LEAVES = new CustomLeaves("maple_leaves");
    public static final Block MAPLE_LOG = new CustomLog("maple_log", MaterialColor.BROWN);
    public static final Block MAPLE_LOG_SYRUP = new MapleLog("maple_log_syrup", MaterialColor.BROWN);
    public static final Block STRIPPED_MAPLE_LOG = new CustomLog("stripped_maple_log", MaterialColor.WOOD);
    public static final Block MAPLE_WOOD = new CustomWood("maple_wood", MaterialColor.BROWN);
    public static final Block STRIPPED_MAPLE_WOOD = new CustomWood("stripped_maple_wood", MaterialColor.WOOD);
    public static final Block MAPLE_PLANKS = new CustomPlanks("maple_planks");

    public static final Block REDWOOD_SAPLING = new CustomSapling("redwood_sapling", new RedwoodTree()).setShape(Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D));
    public static final Block REDWOOD_LEAVES = new CustomLeaves("redwood_leaves");
    public static final Block REDWOOD_LOG = new CustomLog("redwood_log", MaterialColor.ADOBE);
    public static final Block STRIPPED_REDWOOD_LOG = new CustomLog("stripped_redwood_log", MaterialColor.ADOBE);
    public static final Block REDWOOD_WOOD = new CustomWood("redwood_wood", MaterialColor.ADOBE);
    public static final Block STRIPPED_REDWOOD_WOOD = new CustomWood("stripped_redwood_wood", MaterialColor.ADOBE);
    public static final Block REDWOOD_PLANKS = new CustomPlanks("redwood_planks");

    public static final Block ALPINE_SAPLING = new CustomSapling("alpine_sapling", new AlpineTree()).setShape(Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D));
    public static final Block ALPINE_LEAVES = new CustomLeaves("alpine_leaves");

    public static final Block DOUGLAS_FIR_SAPLING = new CustomSapling("douglas_fir_sapling", new DouglasFirTree()).setShape(Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D));
    public static final Block DOUGLAS_FIR_LEAVES = new CustomLeaves("douglas_fir_leaves");

    public static final Block DESERT_SHRUB = new CustomBush("desert_shrub", Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D), Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.SAND, Blocks.RED_SAND);
    public static final Block STEPPE_GRASS = new CustomBush("steppe_grass", Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D), Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.SAND, Blocks.RED_SAND);
    public static final Block BULRUSHES = new Bulrushes();

    public static final Block FALLEN_LEAVES = new FallenLeaves();

    public static final Block POTTERY_WHEEL = new PotteryWheel();
    public static final Block CUTTING_BOARD = new CuttingBoard();
    public static final Block ARCHITECTS_TABLE = new ArchitectsTable();

    public static final Block MUD = new MudBlock();
    public static final Block TANNED_SAND = new CustomSand("tanned_sand", 0xc9a875);

    public static final Block PLANTER_BOX = new PlanterBoxBlock();

    public static final DeferredRegister<Block> DEFFERED_BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Util.MOD_ID);

    public static final RegistryObject<Block> FLUID_POT = DEFFERED_BLOCKS.register("fluid_pot", BlockFluidPot::new);
    public static final RegistryObject<Block> UNFIRED_FLUID_POT = DEFFERED_BLOCKS.register("unfired_fluid_pot", () -> new BlockFluidPotBase(Block.Properties.create(Material.CLAY).sound(SoundType.GROUND).hardnessAndResistance(0.6F)));
    public static final RegistryObject<Block> DISTILLER = DEFFERED_BLOCKS.register("distiller", BlockDistiller::new);
    public static final RegistryObject<Block> FAUCET = DEFFERED_BLOCKS.register("bamboo_faucet", BlockBambooFaucet::new);

}
