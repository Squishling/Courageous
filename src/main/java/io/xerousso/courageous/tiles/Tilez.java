package io.xerousso.courageous.tiles;

import io.xerousso.courageous.blocks.Blockz;
import io.xerousso.courageous.util.Util;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Tilez {
    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Util.MOD_ID);

    public static final RegistryObject<TileEntityType<TileFluidPot>> FLUID_POT = TILES.register("fluid_pot",
            () -> TileEntityType.Builder.create(TileFluidPot::new, Blockz.FLUID_POT.get()).build(null)
    );
    public static final RegistryObject<TileEntityType<TileDistiller>> DISTILLER = TILES.register("distiller",
            () -> TileEntityType.Builder.create(TileDistiller::new, Blockz.DISTILLER.get()).build(null)
    );
    public static final RegistryObject<TileEntityType<TileFaucet>> FAUCET = TILES.register("faucet",
            () -> TileEntityType.Builder.create(TileFaucet::new, Blockz.FAUCET.get()).build(null)
    );
}
