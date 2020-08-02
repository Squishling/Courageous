//package io.xerousso.courageous.world.gen.features.trees;
//
//import io.xerousso.courageous.blocks.ModBlocks;
//import io.xerousso.courageous.util.Util;
//import io.xerousso.courageous.world.gen.ModFeatures;
//import com.mojang.datafixers.Dynamic;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Blocks;
//import net.minecraft.block.LeavesBlock;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.MutableBoundingBox;
//import net.minecraft.world.IWorldWriter;
//import net.minecraft.world.gen.IWorldGenerationReader;
//import net.minecraft.world.gen.feature.AbstractTreeFeature;
//import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
//
//import java.util.ArrayList;
//import java.util.Random;
//import java.util.Set;
//import java.util.function.Function;
//
//public class DouglasFirTreeFeature extends AbstractTreeFeature<BaseTreeFeatureConfig> {
//
//    private static final BlockState TRUNK = Blocks.SPRUCE_LOG.getDefaultState();
//    private static final BlockState LEAF = ModBlocks.DOUGLAS_FIR_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, 1);
//
//    private static final ArrayList<ArrayList<BlockPos>> BLOBS_LIST = new ArrayList<ArrayList<BlockPos>>();
//
//    public DouglasFirTreeFeature(Function<Dynamic<?>, ? extends BaseTreeFeatureConfig> config) {
//        super(config);
//
//        BLOBS_LIST.add(new ArrayList<BlockPos>());
//        BLOBS_LIST.get(0).add(new BlockPos(0, 0, 0));
//
//        BLOBS_LIST.get(0).add(new BlockPos(1, 0, 0));
//        BLOBS_LIST.get(0).add(new BlockPos(-1, 0, 0));
//        BLOBS_LIST.get(0).add(new BlockPos(0, 0, 1));
//        BLOBS_LIST.get(0).add(new BlockPos(0, 0, -1));
//
//        BLOBS_LIST.get(0).add(new BlockPos(1, 1, 0));
//        BLOBS_LIST.get(0).add(new BlockPos(-1, 1, 0));
//        BLOBS_LIST.get(0).add(new BlockPos(0, 1, 1));
//        BLOBS_LIST.get(0).add(new BlockPos(0, 1, -1));
//
//        BLOBS_LIST.get(0).add(new BlockPos(0, 1, 0));
//        BLOBS_LIST.get(0).add(new BlockPos(0, 2, 0));
//        BLOBS_LIST.get(0).add(new BlockPos(0, 3, 0));
//        BLOBS_LIST.get(0).add(new BlockPos(0, 4, 0));
//
//        BLOBS_LIST.add(new ArrayList<BlockPos>());
//        BLOBS_LIST.get(1).add(new BlockPos(0, -2, 0));
//
//        BLOBS_LIST.get(1).add(new BlockPos(1, 0, 0));
//        BLOBS_LIST.get(1).add(new BlockPos(1, 0, 1));
//        BLOBS_LIST.get(1).add(new BlockPos(0, 0, 1));
//        BLOBS_LIST.get(1).add(new BlockPos(-1, 0, 0));
//        BLOBS_LIST.get(1).add(new BlockPos(0, 0, -1));
//        BLOBS_LIST.get(1).add(new BlockPos(-1, 0, -1));
//        BLOBS_LIST.get(1).add(new BlockPos(1, 0, -1));
//        BLOBS_LIST.get(1).add(new BlockPos(-1, 0, 1));
//
//        BLOBS_LIST.get(1).add(new BlockPos(1, 1, 0));
//        BLOBS_LIST.get(1).add(new BlockPos(1, 1, 1));
//        BLOBS_LIST.get(1).add(new BlockPos(0, 1, 1));
//        BLOBS_LIST.get(1).add(new BlockPos(-1, 1, 0));
//        BLOBS_LIST.get(1).add(new BlockPos(0, 1, -1));
//        BLOBS_LIST.get(1).add(new BlockPos(-1, 1, -1));
//        BLOBS_LIST.get(1).add(new BlockPos(1, 1, -1));
//        BLOBS_LIST.get(1).add(new BlockPos(-1, 1, 1));
//
//
//        BLOBS_LIST.add(new ArrayList<BlockPos>());
//        BLOBS_LIST.get(2).add(new BlockPos(0, -4, 0));
//
//        BLOBS_LIST.get(2).add(new BlockPos(1, 0, 0));
//        BLOBS_LIST.get(2).add(new BlockPos(1, 0, 1));
//        BLOBS_LIST.get(2).add(new BlockPos(0, 0, 1));
//        BLOBS_LIST.get(2).add(new BlockPos(-1, 0, 0));
//        BLOBS_LIST.get(2).add(new BlockPos(0, 0, -1));
//        BLOBS_LIST.get(2).add(new BlockPos(-1, 0, -1));
//        BLOBS_LIST.get(2).add(new BlockPos(1, 0, -1));
//        BLOBS_LIST.get(2).add(new BlockPos(-1, 0, 1));
//
//        BLOBS_LIST.get(2).add(new BlockPos(1, 1, 0));
//        BLOBS_LIST.get(2).add(new BlockPos(1, 1, 1));
//        BLOBS_LIST.get(2).add(new BlockPos(0, 1, 1));
//        BLOBS_LIST.get(2).add(new BlockPos(-1, 1, 0));
//        BLOBS_LIST.get(2).add(new BlockPos(0, 1, -1));
//        BLOBS_LIST.get(2).add(new BlockPos(-1, 1, -1));
//        BLOBS_LIST.get(2).add(new BlockPos(1, 1, -1));
//        BLOBS_LIST.get(2).add(new BlockPos(-1, 1, 1));
//
//        BLOBS_LIST.get(2).add(new BlockPos(2, 0, 0));
//        BLOBS_LIST.get(2).add(new BlockPos(-2, 0, 0));
//        BLOBS_LIST.get(2).add(new BlockPos(0, 0, 2));
//        BLOBS_LIST.get(2).add(new BlockPos(0, 0, -2));
//
//        BLOBS_LIST.get(2).add(new BlockPos(2, 1, 0));
//        BLOBS_LIST.get(2).add(new BlockPos(-2, 1, 0));
//        BLOBS_LIST.get(2).add(new BlockPos(0, 1, 2));
//        BLOBS_LIST.get(2).add(new BlockPos(0, 1, -2));
//
//
//        BLOBS_LIST.add(new ArrayList<BlockPos>());
//        BLOBS_LIST.get(3).add(new BlockPos(0, -6, 0));
//
//        BLOBS_LIST.get(3).add(new BlockPos(1, 0, 0));
//        BLOBS_LIST.get(3).add(new BlockPos(1, 0, 1));
//        BLOBS_LIST.get(3).add(new BlockPos(0, 0, 1));
//        BLOBS_LIST.get(3).add(new BlockPos(-1, 0, 0));
//        BLOBS_LIST.get(3).add(new BlockPos(0, 0, -1));
//        BLOBS_LIST.get(3).add(new BlockPos(-1, 0, -1));
//        BLOBS_LIST.get(3).add(new BlockPos(1, 0, -1));
//        BLOBS_LIST.get(3).add(new BlockPos(-1, 0, 1));
//
//        BLOBS_LIST.get(3).add(new BlockPos(1, 1, 0));
//        BLOBS_LIST.get(3).add(new BlockPos(1, 1, 1));
//        BLOBS_LIST.get(3).add(new BlockPos(0, 1, 1));
//        BLOBS_LIST.get(3).add(new BlockPos(-1, 1, 0));
//        BLOBS_LIST.get(3).add(new BlockPos(0, 1, -1));
//        BLOBS_LIST.get(3).add(new BlockPos(-1, 1, -1));
//        BLOBS_LIST.get(3).add(new BlockPos(1, 1, -1));
//        BLOBS_LIST.get(3).add(new BlockPos(-1, 1, 1));
//
//        BLOBS_LIST.get(3).add(new BlockPos(2, 0, 0));
//        BLOBS_LIST.get(3).add(new BlockPos(-2, 0, 0));
//        BLOBS_LIST.get(3).add(new BlockPos(0, 0, 2));
//        BLOBS_LIST.get(3).add(new BlockPos(0, 0, -2));
//
//        BLOBS_LIST.get(3).add(new BlockPos(2, 1, 0));
//        BLOBS_LIST.get(3).add(new BlockPos(-2, 1, 0));
//        BLOBS_LIST.get(3).add(new BlockPos(0, 1, 2));
//        BLOBS_LIST.get(3).add(new BlockPos(0, 1, -2));
//
//        BLOBS_LIST.get(3).add(new BlockPos(2, 0, 1));
//        BLOBS_LIST.get(3).add(new BlockPos(1, 0, 2));
//        BLOBS_LIST.get(3).add(new BlockPos(-2, 0, 1));
//        BLOBS_LIST.get(3).add(new BlockPos(1, 0, -2));
//        BLOBS_LIST.get(3).add(new BlockPos(-2, 0, -1));
//        BLOBS_LIST.get(3).add(new BlockPos(-1, 0, -2));
//        BLOBS_LIST.get(3).add(new BlockPos(2, 0, -1));
//        BLOBS_LIST.get(3).add(new BlockPos(-1, 0, 2));
//
//        BLOBS_LIST.get(3).add(new BlockPos(2, 1, 1));
//        BLOBS_LIST.get(3).add(new BlockPos(1, 1, 2));
//        BLOBS_LIST.get(3).add(new BlockPos(-2, 1, 1));
//        BLOBS_LIST.get(3).add(new BlockPos(1, 1, -2));
//        BLOBS_LIST.get(3).add(new BlockPos(-2, 1, -1));
//        BLOBS_LIST.get(3).add(new BlockPos(-1, 1, -2));
//        BLOBS_LIST.get(3).add(new BlockPos(2, 1, -1));
//        BLOBS_LIST.get(3).add(new BlockPos(-1, 1, 2));
//
//
//        this.setRegistryName(Util.MOD_ID, "douglas_fir_tree");
//        ModFeatures.FEATURES.add(this);
//    }
//
//    @Override
//    protected boolean place(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set<BlockPos> set, Set<BlockPos> set1, MutableBoundingBox boundsIn, BaseTreeFeatureConfig treeFeatureConfig) {
//        int trunkHeight = rand.nextInt(3) + 10;
//        if (!canCreate(worldIn, position, trunkHeight)) return false;
//
//        buildTree(worldIn, position, trunkHeight);
//
//        return true;
//    }
//
//    private void buildTree(IWorldGenerationReader world, BlockPos pos, int height) {
//        // Place logs
//        for (int i = 0; i < height; i++) {
//            placeLog(world, pos.up(i));
//        }
//
//        ArrayList<ArrayList<BlockPos>> BLOBS = (ArrayList<ArrayList<BlockPos>>) BLOBS_LIST.clone();
//
//        // Place leaves
//        while(BLOBS.size() > 0) {
//            ArrayList<BlockPos> BLOB = BLOBS.get(0);
//
//            if (BLOB.size() == 0) break;
//            if (BLOB.get(0).getY() < Math.floor(height * -0.8)) break;
//
//            int y = BLOB.get(0).getY();
//
//            for (int j = 1; j < BLOB.size(); j++) {
//                BlockPos pos2 = BLOB.get(j);
//                placeLeaves(world, new BlockPos(pos.getX() + pos2.getX(), pos.getY() + y + height + pos2.getY() - 2, pos.getZ() + pos2.getZ()));
//            }
//            BLOBS.remove(0);
//        }
//    }
//
//    private boolean canCreate(IWorldGenerationReader world, BlockPos position, int height) {
//        if (!isDirtOrGrassBlock(world, position.down())) return false;
//
//        for (int i = 0; i < height; i++) {
//            if (!isAirOrLeaves(world, position.up(i)) && !isTallPlants(world, position.up(i))) return false;
//        }
//
//        return true;
//    }
//
//    private void placeLog(IWorldWriter world, BlockPos pos) {
//        this.setBlockState(world, pos, TRUNK);
//    }
//
//    private void placeLeaves(IWorldGenerationReader world, BlockPos pos) {
//        if (isAirOrLeaves(world, pos)) this.setBlockState(world, pos, LEAF);
//    }
//
//}
