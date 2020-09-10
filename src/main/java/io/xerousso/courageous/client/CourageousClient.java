package io.xerousso.courageous.client;

import io.xerousso.courageous.blocks.Blockz;
import io.xerousso.courageous.blocks.block_entities.BlockEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class CourageousClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntities.registerBlockEntitiesClient();
        Screens.registerScreensClient();
    }

}
