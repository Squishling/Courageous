package io.xerousso.courageous.generators;

import io.xerousso.courageous.blocks.BlockBambooFaucet;
import io.xerousso.courageous.blocks.ModBlocks;
import io.xerousso.courageous.blocks.pot.BlockDistiller;
import io.xerousso.courageous.blocks.pot.BlockFluidPot;
import io.xerousso.courageous.util.Util;
import net.minecraft.block.SixWayBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;

public class BlockStateGenerators extends BlockStateProvider {
    public BlockStateGenerators(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Util.MOD_ID, exFileHelper);
    }

    public MultiPartBlockStateBuilder FluidPot(BlockFluidPot block, ModelFile open, ModelFile closed, ModelFile supports) {
//        VariantBlockStateBuilder builder =  getVariantBuilder(block);
//        builder.partialState().with(BlockFluidPot.OPEN, true).modelForState().modelFile(open).addModel()
//                .partialState().with(BlockFluidPot.OPEN, false).modelForState().modelFile(closed).addModel();

        MultiPartBlockStateBuilder builder = getMultipartBuilder(block)
                .part().modelFile(open).addModel().end();
        builder.part().modelFile(closed).addModel().condition(BlockFluidPot.OPEN, false).end();
        builder.part().modelFile(supports).addModel().condition(BlockFluidPot.CAMPFIREATTACHED, true).end();
        return builder;
    }

    public MultiPartBlockStateBuilder Distiller(BlockDistiller block, ModelFile spout, ModelFile open, ModelFile closed, ModelFile supports) {
        MultiPartBlockStateBuilder builder = FluidPot(block, open, closed, supports);
        SixWayBlock.FACING_TO_PROPERTY_MAP.entrySet().forEach(e -> {
            Direction dir = e.getKey();
            if (dir.getAxis().isHorizontal()) {
                builder.part().modelFile(spout).rotationY((((int) dir.getHorizontalAngle()) + 180) % 360).uvLock(true).addModel()
                        .condition(BlockDistiller.HORIZONTAL_FACING, dir);
            }
        });
        return builder;
    }

    public VariantBlockStateBuilder Faucet(BlockBambooFaucet block, ModelFile faucet) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        return builder.partialState().with(BlockBambooFaucet.HORIZONTAL_FACING, Direction.NORTH).modelForState().modelFile(faucet).rotationY(0).addModel()
                .partialState().with(BlockBambooFaucet.HORIZONTAL_FACING, Direction.EAST).modelForState().modelFile(faucet).rotationY(90).addModel()
                .partialState().with(BlockBambooFaucet.HORIZONTAL_FACING, Direction.SOUTH).modelForState().modelFile(faucet).rotationY(180).addModel()
                .partialState().with(BlockBambooFaucet.HORIZONTAL_FACING, Direction.WEST).modelForState().modelFile(faucet).rotationY(270).addModel();
    }

    @Override
    protected void registerStatesAndModels() {
        registeredBlocks.put(ModBlocks.FLUID_POT.get(),
                FluidPot((BlockFluidPot) ModBlocks.FLUID_POT.get(),
                        models().getExistingFile(new ResourceLocation(Util.MOD_ID, "block/pot/empty")),
                        models().getExistingFile(new ResourceLocation(Util.MOD_ID, "block/pot/covered")),
                        models().getExistingFile(new ResourceLocation(Util.MOD_ID, "block/pot/support"))
                )
        );
        registeredBlocks.put(ModBlocks.DISTILLER.get(),
                Distiller((BlockDistiller) ModBlocks.DISTILLER.get(),
                        models().getExistingFile(new ResourceLocation(Util.MOD_ID, "block/pot/distiller")),
                        models().getExistingFile(new ResourceLocation(Util.MOD_ID, "block/pot/empty")),
                        models().getExistingFile(new ResourceLocation(Util.MOD_ID, "block/pot/covered")),
                        models().getExistingFile(new ResourceLocation(Util.MOD_ID, "block/pot/support"))
                )
        );
        registeredBlocks.put(ModBlocks.FAUCET.get(), Faucet((BlockBambooFaucet) ModBlocks.FAUCET.get(),
                models().getExistingFile(new ResourceLocation(Util.MOD_ID, "block/bamboo_faucet"))));
    }
}
