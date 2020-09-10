package io.xerousso.courageous.blocks.block_entities;

import io.xerousso.courageous.Courageous;
import io.xerousso.courageous.blocks.Blockz;
import io.xerousso.courageous.client.PotteryWheelBER;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class BlockEntities {

    public static BlockEntityType<PotteryWheelBlockEntity> POTTERY_WHEEL_BLOCK_ENTITY;

    public static void registerBlockEntities() {
        POTTERY_WHEEL_BLOCK_ENTITY = registerBlockEntity(PotteryWheelBlockEntity::new, Blockz.POTTERY_WHEEL, "pottery_wheel");
    }

    @Environment(EnvType.CLIENT)
    public static void registerBlockEntitiesClient() {
        BlockEntityRendererRegistry.INSTANCE.register(POTTERY_WHEEL_BLOCK_ENTITY, PotteryWheelBER::new);
    }

    private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(Supplier<T> blockEntitySupplier, Block block, String name) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Courageous.MOD_ID, name), BlockEntityType.Builder.create(blockEntitySupplier, block).build(null));
    }

}
