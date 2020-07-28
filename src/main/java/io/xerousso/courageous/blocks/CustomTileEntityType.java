package io.xerousso.courageous.blocks;

import io.xerousso.courageous.util.Util;
import com.mojang.datafixers.types.Type;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.function.Supplier;

public class CustomTileEntityType {

    public static TileEntityType create(Supplier<? extends TileEntity> factoryIn, Type<?> dataFixerType, String name, Block... blocks) {
        TileEntityType type = TileEntityType.Builder.create(factoryIn, blocks).build(dataFixerType);
        type.setRegistryName(Util.MOD_ID, name);

        ModTileEntities.TILE_ENTITIES.add(type);
        return type;
    }

    public static TileEntityType create(Supplier<? extends TileEntity> factoryIn, String name, Block... blocks) {
        return create(factoryIn, null, name, blocks);
    }

}
