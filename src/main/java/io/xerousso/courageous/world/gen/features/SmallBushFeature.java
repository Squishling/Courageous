package io.xerousso.courageous.world.gen.features;

import io.xerousso.courageous.Courageous;
import io.xerousso.courageous.util.Util;
import io.xerousso.courageous.world.gen.ModFeatures;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags.Blocks;

import java.util.Random;
import java.util.function.Function;

public class SmallBushFeature extends Feature<NoFeatureConfig> {

    private static final ResourceLocation SMALL_BUSH_STRUCTURE = new ResourceLocation(Util.MOD_ID, "vegetation/small_bush_01");

    private static Template PALM_LEAVES_TEMPLATE = null;
    private static PlacementSettings SETTINGS = new PlacementSettings().setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(true)
            .setChunk(null).setCenterOffset(new BlockPos(1, 0, 1));

    public SmallBushFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);

        this.setRegistryName(Util.MOD_ID, "small_bush");
        ModFeatures.FEATURES.add(this);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random random, BlockPos position, NoFeatureConfig config) {
        if (!canCreate(worldIn, position)) return false;

        if (PALM_LEAVES_TEMPLATE == null) {
            TemplateManager templatemanager = ((ServerWorld) worldIn.getWorld()).getSaveHandler().getStructureTemplateManager();
            PALM_LEAVES_TEMPLATE = templatemanager.getTemplate(SMALL_BUSH_STRUCTURE);
        }

        if (PALM_LEAVES_TEMPLATE == null) {
            Courageous.LOGGER.warn("the nbt doesnt exist you fuckwit");
            return false;
        }

        PALM_LEAVES_TEMPLATE.addBlocksToWorld(worldIn, position.subtract(SETTINGS.getCenterOffset()), SETTINGS);

        return true;
    }

    private boolean canCreate(IWorld world, BlockPos position) {
        if (!(world.hasBlockState(position.down(), (blockState) -> {
            Block block = blockState.getBlock();
            return Blocks.DIRT.contains(block) || Blocks.SAND.contains(block);
        }))) return false;

        for (int x = -1; x < 1; x++) {
            for (int z = -1; z < 1; z++) {
                for (int y = 0; y < 2; y++) {
                    if (!world.getBlockState(position).isAir(world, position.add(x, y, z))) return false;
                }
            }
        }

        return true;
    }

}
