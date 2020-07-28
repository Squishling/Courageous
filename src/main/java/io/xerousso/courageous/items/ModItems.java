package io.xerousso.courageous.items;

import io.xerousso.courageous.blocks.ModBlocks;
import io.xerousso.courageous.items.pottery.Amphora;
import io.xerousso.courageous.items.pottery.WateringCan;
import io.xerousso.courageous.items.sandwich.Sandwich;
import io.xerousso.courageous.tabs.FoodTab;
import io.xerousso.courageous.tabs.GeneralTab;
import io.xerousso.courageous.tabs.PotteryTab;
import io.xerousso.courageous.tabs.WorldTab;
import io.xerousso.courageous.util.Util;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food.Builder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;

public class ModItems {

    public static ArrayList<Item> ITEMS_ARRAY = new ArrayList<Item>();

    public static final Item KNIFE = new Knife("knife");

    public static final Item BREAD_SLICE = new CustomFood("bread_slice", 1, 0.12f, FoodTab.FOOD);
    public static final Item SANDWICH = new Sandwich();

    public static final Item PEAR = new CustomFood("pear", 4, 0.4f, FoodTab.FOOD);
    public static final Item ORANGE = new CustomFood("orange",3, 0.3f, FoodTab.FOOD);
    public static final Item PLUM = new CustomFood("plum",3, 0.3f, FoodTab.FOOD);
    public static final Item LEMON = new CustomFood("lemon", 1, 0.15f, FoodTab.FOOD);
    public static final Item AVOCADO = new CustomFood("avocado", 2, 0.1f, FoodTab.FOOD);
    public static final Item KIWI_FRUIT = new CustomFood("kiwi_fruit", 3, 0.35f, FoodTab.FOOD);

    public static final Item MAPLE_SYRUP = new CustomFood("maple_syrup", new Builder().hunger(1).saturation(0.4f).setAlwaysEdible().effect(new EffectInstance(Effects.SPEED, 60), 0).build(), Items.GLASS_BOTTLE, FoodTab.FOOD).drink();

    public static final Item MUD_BALL = new ItemBase("mud_ball", WorldTab.WORLD);

    public static final Item UNFIRED_AMPHORA            = new ItemBase("unfired_amphora", PotteryTab.POTTERY);
    public static final Item BLACK_UNFIRED_AMPHORA      = new ItemBase("unfired_black_amphora", PotteryTab.POTTERY);
    public static final Item BLUE_UNFIRED_AMPHORA       = new ItemBase("unfired_blue_amphora", PotteryTab.POTTERY);
    public static final Item BROWN_UNFIRED_AMPHORA      = new ItemBase("unfired_brown_amphora", PotteryTab.POTTERY);
    public static final Item CYAN_UNFIRED_AMPHORA       = new ItemBase("unfired_cyan_amphora", PotteryTab.POTTERY);
    public static final Item GRAY_UNFIRED_AMPHORA       = new ItemBase("unfired_gray_amphora", PotteryTab.POTTERY);
    public static final Item GREEN_UNFIRED_AMPHORA      = new ItemBase("unfired_green_amphora", PotteryTab.POTTERY);
    public static final Item LIGHT_BLUE_UNFIRED_AMPHORA = new ItemBase("unfired_light_blue_amphora", PotteryTab.POTTERY);
    public static final Item LIGHT_GRAY_UNFIRED_AMPHORA = new ItemBase("unfired_light_gray_amphora", PotteryTab.POTTERY);
    public static final Item LIME_UNFIRED_AMPHORA       = new ItemBase("unfired_lime_amphora", PotteryTab.POTTERY);
    public static final Item MAGENTA_UNFIRED_AMPHORA    = new ItemBase("unfired_magenta_amphora", PotteryTab.POTTERY);
    public static final Item ORANGE_UNFIRED_AMPHORA     = new ItemBase("unfired_orange_amphora", PotteryTab.POTTERY);
    public static final Item PINK_UNFIRED_AMPHORA       = new ItemBase("unfired_pink_amphora", PotteryTab.POTTERY);
    public static final Item PURPLE_UNFIRED_AMPHORA     = new ItemBase("unfired_purple_amphora", PotteryTab.POTTERY);
    public static final Item RED_UNFIRED_AMPHORA        = new ItemBase("unfired_red_amphora", PotteryTab.POTTERY);
    public static final Item WHITE_UNFIRED_AMPHORA      = new ItemBase("unfired_white_amphora", PotteryTab.POTTERY);
    public static final Item YELLOW_UNFIRED_AMPHORA     = new ItemBase("unfired_yellow_amphora", PotteryTab.POTTERY);

    public static final Item AMPHORA            = new Amphora("amphora"           );
    public static final Item BLACK_AMPHORA      = new Amphora("black_amphora"     );
    public static final Item BLUE_AMPHORA       = new Amphora("blue_amphora"      );
    public static final Item BROWN_AMPHORA      = new Amphora("brown_amphora"     );
    public static final Item CYAN_AMPHORA       = new Amphora("cyan_amphora"      );
    public static final Item GRAY_AMPHORA       = new Amphora("gray_amphora"      );
    public static final Item GREEN_AMPHORA      = new Amphora("green_amphora"     );
    public static final Item LIGHT_BLUE_AMPHORA = new Amphora("light_blue_amphora");
    public static final Item LIGHT_GRAY_AMPHORA = new Amphora("light_gray_amphora");
    public static final Item LIME_AMPHORA       = new Amphora("lime_amphora"      );
    public static final Item MAGENTA_AMPHORA    = new Amphora("magenta_amphora"   );
    public static final Item ORANGE_AMPHORA     = new Amphora("orange_amphora"    );
    public static final Item PINK_AMPHORA       = new Amphora("pink_amphora"      );
    public static final Item PURPLE_AMPHORA     = new Amphora("purple_amphora"    );
    public static final Item RED_AMPHORA        = new Amphora("red_amphora"       );
    public static final Item WHITE_AMPHORA      = new Amphora("white_amphora"     );
    public static final Item YELLOW_AMPHORA     = new Amphora("yellow_amphora"    );


    public static final Item UNFIRED_WATERING_CAN            = new ItemBase("unfired_watering_can"           , PotteryTab.POTTERY);
    public static final Item BLACK_UNFIRED_WATERING_CAN      = new ItemBase("unfired_black_watering_can"     , PotteryTab.POTTERY);
    public static final Item BLUE_UNFIRED_WATERING_CAN       = new ItemBase("unfired_blue_watering_can"      , PotteryTab.POTTERY);
    public static final Item BROWN_UNFIRED_WATERING_CAN      = new ItemBase("unfired_brown_watering_can"     , PotteryTab.POTTERY);
    public static final Item CYAN_UNFIRED_WATERING_CAN       = new ItemBase("unfired_cyan_watering_can"      , PotteryTab.POTTERY);
    public static final Item GRAY_UNFIRED_WATERING_CAN       = new ItemBase("unfired_gray_watering_can"      , PotteryTab.POTTERY);
    public static final Item GREEN_UNFIRED_WATERING_CAN      = new ItemBase("unfired_green_watering_can"     , PotteryTab.POTTERY);
    public static final Item LIGHT_BLUE_UNFIRED_WATERING_CAN = new ItemBase("unfired_light_blue_watering_can", PotteryTab.POTTERY);
    public static final Item LIGHT_GRAY_UNFIRED_WATERING_CAN = new ItemBase("unfired_light_gray_watering_can", PotteryTab.POTTERY);
    public static final Item LIME_UNFIRED_WATERING_CAN       = new ItemBase("unfired_lime_watering_can"      , PotteryTab.POTTERY);
    public static final Item MAGENTA_UNFIRED_WATERING_CAN    = new ItemBase("unfired_magenta_watering_can"   , PotteryTab.POTTERY);
    public static final Item ORANGE_UNFIRED_WATERING_CAN     = new ItemBase("unfired_orange_watering_can"    , PotteryTab.POTTERY);
    public static final Item PINK_UNFIRED_WATERING_CAN       = new ItemBase("unfired_pink_watering_can"      , PotteryTab.POTTERY);
    public static final Item PURPlE_UNFIRED_WATERING_CAN     = new ItemBase("unfired_purple_watering_can"    , PotteryTab.POTTERY);
    public static final Item RED_UNFIRED_WATERING_CAN        = new ItemBase("unfired_red_watering_can"       , PotteryTab.POTTERY);
    public static final Item WHITE_UNFIRED_WATERING_CAN      = new ItemBase("unfired_white_watering_can"     , PotteryTab.POTTERY);
    public static final Item YELLOW_UNFIRED_WATERING_CAN     = new ItemBase("unfired_yellow_watering_can"    , PotteryTab.POTTERY);

    public static final Item WATERING_CAN            = new WateringCan("watering_can");
    public static final Item BLACK_WATERING_CAN      = new WateringCan("black_watering_can");
    public static final Item BLUE_WATERING_CAN       = new WateringCan("blue_watering_can");
    public static final Item BROWN_WATERING_CAN      = new WateringCan("brown_watering_can");
    public static final Item CYAN_WATERING_CAN       = new WateringCan("cyan_watering_can");
    public static final Item GRAY_WATERING_CAN       = new WateringCan("gray_watering_can");
    public static final Item GREEN_WATERING_CAN      = new WateringCan("green_watering_can");
    public static final Item LIGHT_BLUE_WATERING_CAN = new WateringCan("light_blue_watering_can");
    public static final Item LIGHT_GRAY_WATERING_CAN = new WateringCan("light_gray_watering_can");
    public static final Item LIME_WATERING_CAN       = new WateringCan("lime_watering_can");
    public static final Item MAGENTA_WATERING_CAN    = new WateringCan("magenta_watering_can");
    public static final Item ORANGE_WATERING_CAN     = new WateringCan("orange_watering_can");
    public static final Item PINK_WATERING_CAN       = new WateringCan("pink_watering_can");
    public static final Item PURPLE_WATERING_CAN     = new WateringCan("purple_watering_can");
    public static final Item RED_WATERING_CAN        = new WateringCan("red_watering_can");
    public static final Item WHITE_WATERING_CAN      = new WateringCan("white_watering_can");
    public static final Item YELLOW_WATERING_CAN     = new WateringCan("yellow_watering_can");

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Util.MOD_ID);

    public static final RegistryObject<Item> FLUID_POT = ITEMS.register("fluid_pot", () -> new BlockItem(ModBlocks.FLUID_POT.get(), new BlockItem.Properties().group(PotteryTab.POTTERY).maxStackSize(1)));
    public static final RegistryObject<Item> FAUCET = ITEMS.register("bamboo_faucet", () -> new BlockItem(ModBlocks.FAUCET.get(), new BlockItem.Properties().group(GeneralTab.GENERAL)));
    public static final RegistryObject<Item> UNFIRED_FLUID_POT = ITEMS.register("unfired_fluid_pot", () -> new BlockItem(ModBlocks.UNFIRED_FLUID_POT.get(), new BlockItem.Properties().group(PotteryTab.POTTERY)));
}
