package io.xerousso.courageous.items;

import io.xerousso.courageous.Courageous;
import io.xerousso.courageous.blocks.Blockz;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class Itemz {

    public static final ItemGroup WORLD_GROUP = registerItemGroup("world", () -> new ItemStack(Blockz.MUD.asItem()));
    public static final ItemGroup POTTERY_GROUP = registerItemGroup("pottery", () -> new ItemStack(Blockz.POTTERY_WHEEL.asItem()));

    public static final Item UNFIRED_AMPHORA                 = registerPotteryItem("unfired_amphora"                );
    public static final Item UNFIRED_BLACK_AMPHORA           = registerPotteryItem("unfired_black_amphora"          );
    public static final Item UNFIRED_WHITE_AMPHORA           = registerPotteryItem("unfired_white_amphora"          );
    public static final Item UNFIRED_RED_AMPHORA             = registerPotteryItem("unfired_red_amphora"            );
    public static final Item UNFIRED_YELLOW_AMPHORA          = registerPotteryItem("unfired_yellow_amphora"         );
    public static final Item UNFIRED_MAGENTA_AMPHORA         = registerPotteryItem("unfired_magenta_amphora"        );
    public static final Item UNFIRED_PURPLE_AMPHORA          = registerPotteryItem("unfired_purple_amphora"         );
    public static final Item UNFIRED_ORANGE_AMPHORA          = registerPotteryItem("unfired_orange_amphora"         );
    public static final Item UNFIRED_LIME_AMPHORA            = registerPotteryItem("unfired_lime_amphora"           );
    public static final Item UNFIRED_GREEN_AMPHORA           = registerPotteryItem("unfired_green_amphora"          );
    public static final Item UNFIRED_BLUE_AMPHORA            = registerPotteryItem("unfired_blue_amphora"           );
    public static final Item UNFIRED_LIGHT_BLUE_AMPHORA      = registerPotteryItem("unfired_light_blue_amphora"     );
    public static final Item UNFIRED_GRAY_AMPHORA            = registerPotteryItem("unfired_gray_amphora"           );
    public static final Item UNFIRED_LIGHT_GRAY_AMPHORA      = registerPotteryItem("unfired_light_gray_amphora"     );
    public static final Item UNFIRED_PINK_AMPHORA            = registerPotteryItem("unfired_pink_amphora"           );
    public static final Item UNFIRED_BROWN_AMPHORA           = registerPotteryItem("unfired_brown_amphora"          );
    public static final Item UNFIRED_CYAN_AMPHORA            = registerPotteryItem("unfired_cyan_amphora"           );

    public static final Item UNFIRED_WATERING_CAN            = registerPotteryItem("unfired_watering_can"           );
    public static final Item UNFIRED_BLACK_WATERING_CAN      = registerPotteryItem("unfired_black_watering_can"     );
    public static final Item UNFIRED_WHITE_WATERING_CAN      = registerPotteryItem("unfired_white_watering_can"     );
    public static final Item UNFIRED_RED_WATERING_CAN        = registerPotteryItem("unfired_red_watering_can"       );
    public static final Item UNFIRED_YELLOW_WATERING_CAN     = registerPotteryItem("unfired_yellow_watering_can"    );
    public static final Item UNFIRED_MAGENTA_WATERING_CAN    = registerPotteryItem("unfired_magenta_watering_can"   );
    public static final Item UNFIRED_PURPLE_WATERING_CAN     = registerPotteryItem("unfired_purple_watering_can"    );
    public static final Item UNFIRED_ORANGE_WATERING_CAN     = registerPotteryItem("unfired_orange_watering_can"    );
    public static final Item UNFIRED_LIME_WATERING_CAN       = registerPotteryItem("unfired_lime_watering_can"      );
    public static final Item UNFIRED_GREEN_WATERING_CAN      = registerPotteryItem("unfired_green_watering_can"     );
    public static final Item UNFIRED_BLUE_WATERING_CAN       = registerPotteryItem("unfired_blue_watering_can"      );
    public static final Item UNFIRED_LIGHT_BLUE_WATERING_CAN = registerPotteryItem("unfired_light_blue_watering_can");
    public static final Item UNFIRED_GRAY_WATERING_CAN       = registerPotteryItem("unfired_gray_watering_can"      );
    public static final Item UNFIRED_LIGHT_GRAY_WATERING_CAN = registerPotteryItem("unfired_light_gray_watering_can");
    public static final Item UNFIRED_PINK_WATERING_CAN       = registerPotteryItem("unfired_pink_watering_can"      );
    public static final Item UNFIRED_BROWN_WATERING_CAN      = registerPotteryItem("unfired_brown_watering_can"     );
    public static final Item UNFIRED_CYAN_WATERING_CAN       = registerPotteryItem("unfired_cyan_watering_can"      );

    private static Item registerPotteryItem(String name) {
        return registerItem(new Item(new FabricItemSettings().group(POTTERY_GROUP)), name);
    }

    private static Item registerItem(Item item, String name) {
        return Registry.register(Registry.ITEM, new Identifier(Courageous.MOD_ID, name), item);
    }

    private static ItemGroup registerItemGroup(String name, Supplier<ItemStack> icon) {
        return FabricItemGroupBuilder.build(new Identifier(Courageous.MOD_ID, name), icon);
    }

    public static void init() {}

}
