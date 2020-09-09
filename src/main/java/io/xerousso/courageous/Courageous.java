package io.xerousso.courageous;

import com.google.common.reflect.Reflection;
import io.xerousso.courageous.blocks.block_entities.BlockEntities;
import net.fabricmc.api.ModInitializer;
import io.xerousso.courageous.blocks.Blockz;

public class Courageous implements ModInitializer {

    public static final String MOD_ID = "courageous";

    @Override
    public void onInitialize() {
        Blockz.init();
        BlockEntities.registerBlockEntities();
    }

}
