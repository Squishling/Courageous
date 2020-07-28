package io.xerousso.courageous.generators;

import io.xerousso.courageous.blocks.ModBlocks;
import net.minecraft.data.DataGenerator;

public class BlockLootTableGenerator extends BaseLootTableProvider {
    public BlockLootTableGenerator(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(ModBlocks.FAUCET.get(), createStandardTable("bamboo_faucet", ModBlocks.FAUCET.get()));
        lootTables.put(ModBlocks.DISTILLER.get(), createStandardTable("distiller", ModBlocks.DISTILLER.get()));
        lootTables.put(ModBlocks.FLUID_POT.get(), createStandardTable("fluid_pot", ModBlocks.FLUID_POT.get()));
    }
}
