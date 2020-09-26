package io.xerousso.courageous.client.screens;

import io.xerousso.courageous.Courageous;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry.SimpleClientHandlerFactory;
import net.fabricmc.fabric.impl.screenhandler.ExtendedScreenHandlerType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class Screens {

    public static final ScreenHandlerType<PotteryWheelScreenHandler> POTTERY_WHEEL_SCREEN = registerScreen(PotteryWheelScreenHandler::new, "pottery_wheel");

    @Environment(EnvType.CLIENT)
    public static void registerScreensClient() {
        ScreenRegistry.register(POTTERY_WHEEL_SCREEN, PotteryWheelScreen::new);
    }

    private static <T extends ScreenHandler> ScreenHandlerType<T> registerScreen(SimpleClientHandlerFactory<T> screenHandlerType, String name) {
        return ScreenHandlerRegistry.registerSimple(new Identifier(Courageous.MOD_ID, name), screenHandlerType);
    }

    public static void init() {}

}

