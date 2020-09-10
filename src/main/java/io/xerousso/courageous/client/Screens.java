package io.xerousso.courageous.client;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.xerousso.courageous.Courageous;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry.SimpleClientHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class Screens {

    public static ScreenHandlerType<? extends PotteryWheelScreenDescription> POTTERY_WHEEL_SCREEN;

    public static void registerScreens() {
//        POTTERY_WHEEL_SCREEN = registerScreen((syncID, inventory) -> new PotteryWheelScreenDescription(syncID, inventory, ScreenHandlerContext.EMPTY), "pottery_wheel");
    }

    @Environment(EnvType.CLIENT)
    public static void registerScreensClient() {
//        ScreenRegistry.<PotteryWheelScreenDescription, PotteryWheelScreen>register(POTTERY_WHEEL_SCREEN, (gui, inventory, title) -> new PotteryWheelScreen(gui, inventory.player, title));
    }

    private static <T extends ScreenHandler> ScreenHandlerType<T> registerScreen(SimpleClientHandlerFactory<T> screenHandlerType, String name) {
        return ScreenHandlerRegistry.registerSimple(new Identifier(Courageous.MOD_ID, name), screenHandlerType);
    }

}

