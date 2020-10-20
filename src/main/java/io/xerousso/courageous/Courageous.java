package io.xerousso.courageous;

import io.xerousso.courageous.blocks.PotteryWheel;
import io.xerousso.courageous.blocks.block_entities.BlockEntities;
import io.xerousso.courageous.blocks.block_entities.recipes.Recipes;
import io.xerousso.courageous.client.screens.Screens;
import io.xerousso.courageous.items.Itemz;
import io.xerousso.courageous.networking.Packets;
import io.xerousso.courageous.util.Triad;
import net.fabricmc.api.ModInitializer;
import io.xerousso.courageous.blocks.Blockz;

public class Courageous implements ModInitializer {

    public static final String MOD_ID = "courageous";

    @Override
    public void onInitialize() {
        Blockz.init();
        Itemz.init();
        BlockEntities.registerBlockEntities();
        Screens.init();
        Recipes.init();
        Packets.registerPacketsCommon();
    }

}
