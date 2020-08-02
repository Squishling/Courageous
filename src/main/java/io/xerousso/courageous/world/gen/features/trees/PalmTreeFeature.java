//package io.xerousso.courageous.world.gen.features.trees;
//
//import io.xerousso.courageous.Courageous;
//import io.xerousso.courageous.blocks.ModBlocks;
//import io.xerousso.courageous.world.gen.ModFeatures;
//import com.mojang.datafixers.Dynamic;
//import io.xerousso.courageous.util.Util;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.LeavesBlock;
//import net.minecraft.util.Mirror;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.Rotation;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.IWorld;
//import net.minecraft.world.IWorldWriter;
//import net.minecraft.world.gen.ChunkGenerator;
//import net.minecraft.world.gen.GenerationSettings;
//import net.minecraft.world.gen.feature.AbstractTreeFeature;
//import net.minecraft.world.gen.feature.Feature;
//import net.minecraft.world.gen.feature.NoFeatureConfig;
//import net.minecraft.world.gen.feature.template.*;
//import net.minecraft.world.server.ServerWorld;
//import net.minecraftforge.common.Tags;
//import net.minecraftforge.common.Tags.Blocks;
//
//import java.util.Random;
//import java.util.function.Function;
//
//public class PalmTreeFeature extends Feature<NoFeatureConfig> {
//
//    private static final BlockState TRUNK = ModBlocks.PALM_LOG.getDefaultState();
//    private static final BlockState LEAF = ModBlocks.PALM_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, 1);
//
//    private static final ResourceLocation PALM_LEAVES_STRUCTURE = new ResourceLocation(Util.MOD_ID, "vegetation/date_palm_leaves");
//
//    private static Template PALM_LEAVES_TEMPLATE = null;
//    private static PlacementSettings SETTINGS = new PlacementSettings().setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(true)
//            .setChunk(null).setCenterOffset(new BlockPos(4, 3, 4));
//
//    public PalmTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
//        super(configFactoryIn);
//
//        this.setRegistryName(Util.MOD_ID, "palm_tree");
//        ModFeatures.FEATURES.add(this);
//    }
//
//    @Override
//    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random random, BlockPos position, NoFeatureConfig config) {
//        int min = 10;
//        int max = 16;
//
//        int trunkHeight = random.nextInt(max - min) + min;
//        if (!canCreate(worldIn, position, trunkHeight)) return false;
//
//        if (PALM_LEAVES_TEMPLATE == null) {
//            TemplateManager templatemanager = ((ServerWorld) worldIn.getWorld()).getSaveHandler().getStructureTemplateManager();
//            PALM_LEAVES_TEMPLATE = templatemanager.getTemplate(PALM_LEAVES_STRUCTURE);
//        }
//
//        if (PALM_LEAVES_TEMPLATE == null) {
//            Courageous.LOGGER.warn("the nbt doesnt exist you fuckwit");
//            return false;
//        }
//
//        for (int i = 0; i <= trunkHeight; i++) placeLog(worldIn, position.up(i));
//
//        PALM_LEAVES_TEMPLATE.addBlocksToWorld(worldIn, position.up(trunkHeight).subtract(SETTINGS.getCenterOffset()), SETTINGS);
//
//        return true;
//    }
//
//    private boolean canCreate(IWorld world, BlockPos position, int height) {
//        if (!(world.hasBlockState(position.down(), (blockState) -> {
//            Block block = blockState.getBlock();
//            return Tags.Blocks.DIRT.contains(block) || Blocks.SAND.contains(block);
//        }))) return false;
//
//        for (int i = 0; i <= height; i++) {
//            if (!world.getBlockState(position).isAir(world, position.up(i))) return false;
//        }
//
//        for (int x = -4; x < 4; x++) {
//            for (int z = -4; z < 4; z++) {
//                for (int y = -3; y < 5; y++) {
//                    if (!world.getBlockState(position).isAir(world, position.add(x, y + height, z))) return false;
//                }
//            }
//        }
//
//        return true;
//    }
//
//
//    private void placeLog(IWorldWriter world, BlockPos pos) {
//        this.setBlockState(world, pos, TRUNK);
//    }
//
//}
