package io.xerousso.courageous.blocks.vegetation;

import io.xerousso.courageous.blocks.IBlock;
import io.xerousso.courageous.tabs.WorldTab;
import io.xerousso.courageous.util.lib.DefaultBlockProperties;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.OakTree;
import net.minecraft.block.trees.Tree;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class CustomSapling extends SaplingBlock implements IBlock {

    private Tree tree;

    private VoxelShape shape;

    public CustomSapling(String name, Tree tree) {
        super(tree, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.PLANT).hardnessAndResistance(0));
        DefaultBlockProperties.defaults(this, name);

        this.tree = tree;
    }

    public Block setShape(VoxelShape shape) {
        this.shape = shape;

        return this;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (shape != null) return shape;
        return super.getShape(state, worldIn, pos, context);
    }

    public CustomSapling(String name, AbstractTreeFeature tree) {
        this(name, new OakTree());
    }

    @Override
    public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
//        tree.func_225545_a_(world, world.getChunkProvider().getChunkGenerator(), pos, state, rand);
        placeTree(world, pos, state, rand);
    }

    @Override
    public ItemGroup getTab() {
        return WorldTab.WORLD;
    }

}
