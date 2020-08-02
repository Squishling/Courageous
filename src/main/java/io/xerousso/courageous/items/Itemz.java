package io.xerousso.courageous.items;

import io.xerousso.courageous.blocks.Blockz;
import io.xerousso.courageous.items.pottery.Amphora;
import io.xerousso.courageous.items.pottery.WateringCan;
import io.xerousso.courageous.items.sandwich.Sandwich;
import io.xerousso.courageous.tabs.FoodTab;
import io.xerousso.courageous.tabs.PotteryTab;
//import io.xerousso.courageous.tabs.WorldTab;
import io.xerousso.courageous.tabs.WorldTab;
import io.xerousso.courageous.util.Util;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food.Builder;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;

public class Itemz {

    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Util.MOD_ID);

    public static RegistryObject<Item> KNIFE = ITEMS.register("knife", Knife::new);

    public static RegistryObject<Item> BREAD_SLICE = ITEMS.register("bread_slice", () -> new CustomFood(1, 0.12f));
    public static RegistryObject<Item> SANDWICH = ITEMS.register("sandwich", Sandwich::new);

    public static RegistryObject<Item> PEAR = ITEMS.register("pear", () -> new CustomFood(4, 0.4f));
    public static RegistryObject<Item> ORANGE = ITEMS.register("orange", () -> new CustomFood(3, 0.3f));
    public static RegistryObject<Item> PLUM = ITEMS.register("plum", () -> new CustomFood(3, 0.3f));
    public static RegistryObject<Item> LEMON = ITEMS.register("lemon", () -> new CustomFood(1, 0.15f));
    public static RegistryObject<Item> AVOCADO = ITEMS.register("avocado", () -> new CustomFood(2, 0.1f));
    public static RegistryObject<Item> KIWI_FRUIT = ITEMS.register("kiwi_fruit", () -> new CustomFood(3, 0.35f));

    public static RegistryObject<Item> MAPLE_SYRUP = ITEMS.register("maple_syrup", () -> new CustomFood(new Builder().hunger(1).saturation(0.4f).setAlwaysEdible().effect(new EffectInstance(Effects.SPEED, 60), 0).build(), Items.GLASS_BOTTLE).drink());

    public static RegistryObject<Item> MUD_BALL = ITEMS.register("mud_ball", () -> new Item(new Properties().group(WorldTab.WORLD)));

    public static RegistryObject<Item> UNFIRED_AMPHORA            = ITEMS.register("unfired_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> BLACK_UNFIRED_AMPHORA      = ITEMS.register("unfired_black_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> BLUE_UNFIRED_AMPHORA       = ITEMS.register("unfired_blue_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> BROWN_UNFIRED_AMPHORA      = ITEMS.register("unfired_brown_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> CYAN_UNFIRED_AMPHORA       = ITEMS.register("unfired_cyan_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> GRAY_UNFIRED_AMPHORA       = ITEMS.register("unfired_gray_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> GREEN_UNFIRED_AMPHORA      = ITEMS.register("unfired_green_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> LIGHT_BLUE_UNFIRED_AMPHORA = ITEMS.register("unfired_light_blue_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> LIGHT_GRAY_UNFIRED_AMPHORA = ITEMS.register("unfired_light_gray_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> LIME_UNFIRED_AMPHORA       = ITEMS.register("unfired_lime_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> MAGENTA_UNFIRED_AMPHORA    = ITEMS.register("unfired_magenta_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> ORANGE_UNFIRED_AMPHORA     = ITEMS.register("unfired_orange_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> PINK_UNFIRED_AMPHORA       = ITEMS.register("unfired_pink_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> PURPLE_UNFIRED_AMPHORA     = ITEMS.register("unfired_purple_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> RED_UNFIRED_AMPHORA        = ITEMS.register("unfired_red_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> WHITE_UNFIRED_AMPHORA      = ITEMS.register("unfired_white_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> YELLOW_UNFIRED_AMPHORA     = ITEMS.register("unfired_yellow_amphora", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));

    public static RegistryObject<Item> AMPHORA            = ITEMS.register("amphora"           , Amphora::new);
    public static RegistryObject<Item> BLACK_AMPHORA      = ITEMS.register("black_amphora"     , Amphora::new);
    public static RegistryObject<Item> BLUE_AMPHORA       = ITEMS.register("blue_amphora"      , Amphora::new);
    public static RegistryObject<Item> BROWN_AMPHORA      = ITEMS.register("brown_amphora"     , Amphora::new);
    public static RegistryObject<Item> CYAN_AMPHORA       = ITEMS.register("cyan_amphora"      , Amphora::new);
    public static RegistryObject<Item> GRAY_AMPHORA       = ITEMS.register("gray_amphora"      , Amphora::new);
    public static RegistryObject<Item> GREEN_AMPHORA      = ITEMS.register("green_amphora"     , Amphora::new);
    public static RegistryObject<Item> LIGHT_BLUE_AMPHORA = ITEMS.register("light_blue_amphora", Amphora::new);
    public static RegistryObject<Item> LIGHT_GRAY_AMPHORA = ITEMS.register("light_gray_amphora", Amphora::new);
    public static RegistryObject<Item> LIME_AMPHORA       = ITEMS.register("lime_amphora"      , Amphora::new);
    public static RegistryObject<Item> MAGENTA_AMPHORA    = ITEMS.register("magenta_amphora"   , Amphora::new);
    public static RegistryObject<Item> ORANGE_AMPHORA     = ITEMS.register("orange_amphora"    , Amphora::new);
    public static RegistryObject<Item> PINK_AMPHORA       = ITEMS.register("pink_amphora"      , Amphora::new);
    public static RegistryObject<Item> PURPLE_AMPHORA     = ITEMS.register("purple_amphora"    , Amphora::new);
    public static RegistryObject<Item> RED_AMPHORA        = ITEMS.register("red_amphora"       , Amphora::new);
    public static RegistryObject<Item> WHITE_AMPHORA      = ITEMS.register("white_amphora"     , Amphora::new);
    public static RegistryObject<Item> YELLOW_AMPHORA     = ITEMS.register("yellow_amphora"    , Amphora::new);


    public static RegistryObject<Item> UNFIRED_WATERING_CAN            = ITEMS.register("unfired_watering_can"           , () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> BLACK_UNFIRED_WATERING_CAN      = ITEMS.register("unfired_black_watering_can"     , () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> BLUE_UNFIRED_WATERING_CAN       = ITEMS.register("unfired_blue_watering_can"      , () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> BROWN_UNFIRED_WATERING_CAN      = ITEMS.register("unfired_brown_watering_can"     , () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> CYAN_UNFIRED_WATERING_CAN       = ITEMS.register("unfired_cyan_watering_can"      , () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> GRAY_UNFIRED_WATERING_CAN       = ITEMS.register("unfired_gray_watering_can"      , () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> GREEN_UNFIRED_WATERING_CAN      = ITEMS.register("unfired_green_watering_can"     , () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> LIGHT_BLUE_UNFIRED_WATERING_CAN = ITEMS.register("unfired_light_blue_watering_can", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> LIGHT_GRAY_UNFIRED_WATERING_CAN = ITEMS.register("unfired_light_gray_watering_can", () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> LIME_UNFIRED_WATERING_CAN       = ITEMS.register("unfired_lime_watering_can"      , () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> MAGENTA_UNFIRED_WATERING_CAN    = ITEMS.register("unfired_magenta_watering_can"   , () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> ORANGE_UNFIRED_WATERING_CAN     = ITEMS.register("unfired_orange_watering_can"    , () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> PINK_UNFIRED_WATERING_CAN       = ITEMS.register("unfired_pink_watering_can"      , () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> PURPlE_UNFIRED_WATERING_CAN     = ITEMS.register("unfired_purple_watering_can"    , () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> RED_UNFIRED_WATERING_CAN        = ITEMS.register("unfired_red_watering_can"       , () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> WHITE_UNFIRED_WATERING_CAN      = ITEMS.register("unfired_white_watering_can"     , () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));
    public static RegistryObject<Item> YELLOW_UNFIRED_WATERING_CAN     = ITEMS.register("unfired_yellow_watering_can"    , () -> new Item(new Item.Properties().group(PotteryTab.POTTERY)));

    public static RegistryObject<Item> WATERING_CAN            = ITEMS.register("watering_can"           , WateringCan::new);
    public static RegistryObject<Item> BLACK_WATERING_CAN      = ITEMS.register("black_watering_can"     , WateringCan::new);
    public static RegistryObject<Item> BLUE_WATERING_CAN       = ITEMS.register("blue_watering_can"      , WateringCan::new);
    public static RegistryObject<Item> BROWN_WATERING_CAN      = ITEMS.register("brown_watering_can"     , WateringCan::new);
    public static RegistryObject<Item> CYAN_WATERING_CAN       = ITEMS.register("cyan_watering_can"      , WateringCan::new);
    public static RegistryObject<Item> GRAY_WATERING_CAN       = ITEMS.register("gray_watering_can"      , WateringCan::new);
    public static RegistryObject<Item> GREEN_WATERING_CAN      = ITEMS.register("green_watering_can"     , WateringCan::new);
    public static RegistryObject<Item> LIGHT_BLUE_WATERING_CAN = ITEMS.register("light_blue_watering_can", WateringCan::new);
    public static RegistryObject<Item> LIGHT_GRAY_WATERING_CAN = ITEMS.register("light_gray_watering_can", WateringCan::new);
    public static RegistryObject<Item> LIME_WATERING_CAN       = ITEMS.register("lime_watering_can"      , WateringCan::new);
    public static RegistryObject<Item> MAGENTA_WATERING_CAN    = ITEMS.register("magenta_watering_can"   , WateringCan::new);
    public static RegistryObject<Item> ORANGE_WATERING_CAN     = ITEMS.register("orange_watering_can"    , WateringCan::new);
    public static RegistryObject<Item> PINK_WATERING_CAN       = ITEMS.register("pink_watering_can"      , WateringCan::new);
    public static RegistryObject<Item> PURPLE_WATERING_CAN     = ITEMS.register("purple_watering_can"    , WateringCan::new);
    public static RegistryObject<Item> RED_WATERING_CAN        = ITEMS.register("red_watering_can"       , WateringCan::new);
    public static RegistryObject<Item> WHITE_WATERING_CAN      = ITEMS.register("white_watering_can"     , WateringCan::new);
    public static RegistryObject<Item> YELLOW_WATERING_CAN     = ITEMS.register("yellow_watering_can"    , WateringCan::new);

    public static void registerItemBlocks() {
        for (RegistryObject<Block> block : Blockz.BLOCKS.getEntries()) {
            ITEMS.register(block.get().getRegistryName().getPath(), () -> {
                Item.Properties properties = new Item.Properties();
                if (block.get() instanceof IItem) properties.group(((IItem) block.get()).getTab());

                return new BlockItem(block.get(), properties);
            });
        }
    }

//    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Util.MOD_ID);

//    public static final RegistryObject<Item> FLUID_POT = ITEMS.register("fluid_pot", () -> new BlockItem(ModBlocks.FLUID_POT.get(), new BlockItem.Properties().group(PotteryTab.POTTERY).maxStackSize(1)));
//    public static final RegistryObject<Item> FAUCET = ITEMS.register("bamboo_faucet", () -> new BlockItem(ModBlocks.FAUCET.get(), new BlockItem.Properties().group(GeneralTab.GENERAL)));
//    public static final RegistryObject<Item> UNFIRED_FLUID_POT = ITEMS.register("unfired_fluid_pot", () -> new BlockItem(ModBlocks.UNFIRED_FLUID_POT.get(), new BlockItem.Properties().group(PotteryTab.POTTERY)));
}
