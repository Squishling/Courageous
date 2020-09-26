package io.xerousso.courageous.client;

import io.xerousso.courageous.blocks.block_entities.BlockEntities;
import io.xerousso.courageous.client.screens.Screens;
import io.xerousso.courageous.networking.Packets;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class CourageousClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntities.registerBlockEntitiesClient();
        Screens.registerScreensClient();
        Packets.registerPacketsClient();
    }

}
