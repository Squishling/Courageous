package io.xerousso.courageous.trees;

import io.xerousso.courageous.blocks.Blockz;
import io.xerousso.courageous.blocks.vegetation.HarvestableLeaves;
import io.xerousso.courageous.world.gen.ModFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

import javax.annotation.Nullable;
import java.util.Random;

public class AppleTree extends Tree {

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return Feature.field_236291_c_.withConfiguration(ModFeatures.FRUIT_TREE_CONFIG.apply(() -> (HarvestableLeaves) Blockz.APPLE_LEAVES.get()));
    }

}
