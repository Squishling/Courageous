package io.xerousso.courageous.trees;

import io.xerousso.courageous.blocks.ModBlocks;
import io.xerousso.courageous.world.gen.ModFeatures;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import java.util.Random;

public class RedwoodTree extends BigTree {

    @Nullable
    @Override
    protected ConfiguredFeature<HugeTreeFeatureConfig, ?> getHugeTreeFeature(Random random) {
        return Feature.MEGA_SPRUCE_TREE.withConfiguration(new HugeTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.REDWOOD_LOG.getDefaultState()), new SimpleBlockStateProvider(ModBlocks.REDWOOD_LEAVES.getDefaultState())).baseHeight(15).heightInterval(15).crownHeight(13).setSapling((IPlantable) ModBlocks.REDWOOD_SAPLING).build());
    }

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean b) {
        return ModFeatures.REDWOOD_TREE.withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG);
    }
}
