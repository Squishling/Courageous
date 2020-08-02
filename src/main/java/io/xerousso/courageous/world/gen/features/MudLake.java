//package io.xerousso.courageous.world.gen.features;
//
//import io.xerousso.courageous.blocks.ModBlocks;
//import io.xerousso.courageous.util.Util;
//import io.xerousso.courageous.world.gen.ModFeatures;
//import com.mojang.datafixers.Dynamic;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Blocks;
//import net.minecraft.block.material.Material;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.ChunkPos;
//import net.minecraft.world.IWorld;
//import net.minecraft.world.LightType;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.chunk.ChunkStatus;
//import net.minecraft.world.gen.ChunkGenerator;
//import net.minecraft.world.gen.GenerationSettings;
//import net.minecraft.world.gen.feature.Feature;
//import net.minecraft.world.gen.feature.NoFeatureConfig;
//
//import java.util.Random;
//import java.util.function.Function;
//
//public class MudLake extends Feature<NoFeatureConfig> {
//    private static final BlockState AIR;
//    private static final BlockState MUD = ModBlocks.MUD.getDefaultState();
//    private static final BlockState WATER = Blocks.WATER.getDefaultState();
//
//    public MudLake(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51485_1_) {
//        super(p_i51485_1_);
//        this.setRegistryName(Util.MOD_ID, "mud_lake");
//
//        ModFeatures.FEATURES.add(this);
//    }
//
//    @Override
//    public boolean place(IWorld p_212245_1_, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random p_212245_3_, BlockPos p_212245_4_, NoFeatureConfig config) {
//        while(p_212245_4_.getY() > 5 && p_212245_1_.isAirBlock(p_212245_4_)) {
//            p_212245_4_ = p_212245_4_.down();
//        }
//
//        if (p_212245_4_.getY() <= 4) {
//            return false;
//        } else {
//            p_212245_4_ = p_212245_4_.down(4);
//            ChunkPos lvt_6_1_ = new ChunkPos(p_212245_4_);
//            if (!p_212245_1_.getChunk(lvt_6_1_.x, lvt_6_1_.z, ChunkStatus.STRUCTURE_REFERENCES).getStructureReferences(Feature.VILLAGE.getStructureName()).isEmpty()) {
//                return false;
//            } else {
//                boolean[] lvt_7_1_ = new boolean[2048];
//                int lvt_8_1_ = p_212245_3_.nextInt(4) + 4;
//
//                int lvt_9_5_;
//                for(lvt_9_5_ = 0; lvt_9_5_ < lvt_8_1_; ++lvt_9_5_) {
//                    double lvt_10_1_ = p_212245_3_.nextDouble() * 6.0D + 3.0D;
//                    double lvt_12_1_ = p_212245_3_.nextDouble() * 4.0D + 2.0D;
//                    double lvt_14_1_ = p_212245_3_.nextDouble() * 6.0D + 3.0D;
//                    double lvt_16_1_ = p_212245_3_.nextDouble() * (16.0D - lvt_10_1_ - 2.0D) + 1.0D + lvt_10_1_ / 2.0D;
//                    double lvt_18_1_ = p_212245_3_.nextDouble() * (8.0D - lvt_12_1_ - 4.0D) + 2.0D + lvt_12_1_ / 2.0D;
//                    double lvt_20_1_ = p_212245_3_.nextDouble() * (16.0D - lvt_14_1_ - 2.0D) + 1.0D + lvt_14_1_ / 2.0D;
//
//                    for(int lvt_22_1_ = 1; lvt_22_1_ < 15; ++lvt_22_1_) {
//                        for(int lvt_23_1_ = 1; lvt_23_1_ < 15; ++lvt_23_1_) {
//                            for(int lvt_24_1_ = 1; lvt_24_1_ < 7; ++lvt_24_1_) {
//                                double lvt_25_1_ = ((double)lvt_22_1_ - lvt_16_1_) / (lvt_10_1_ / 2.0D);
//                                double lvt_27_1_ = ((double)lvt_24_1_ - lvt_18_1_) / (lvt_12_1_ / 2.0D);
//                                double lvt_29_1_ = ((double)lvt_23_1_ - lvt_20_1_) / (lvt_14_1_ / 2.0D);
//                                double lvt_31_1_ = lvt_25_1_ * lvt_25_1_ + lvt_27_1_ * lvt_27_1_ + lvt_29_1_ * lvt_29_1_;
//                                if (lvt_31_1_ < 1.0D) {
//                                    lvt_7_1_[(lvt_22_1_ * 16 + lvt_23_1_) * 8 + lvt_24_1_] = true;
//                                }
//                            }
//                        }
//                    }
//                }
//
//                int lvt_11_4_;
//                int lvt_10_5_;
//                boolean lvt_12_4_;
//                for(lvt_9_5_ = 0; lvt_9_5_ < 16; ++lvt_9_5_) {
//                    for(lvt_10_5_ = 0; lvt_10_5_ < 16; ++lvt_10_5_) {
//                        for(lvt_11_4_ = 0; lvt_11_4_ < 8; ++lvt_11_4_) {
//                            lvt_12_4_ = !lvt_7_1_[(lvt_9_5_ * 16 + lvt_10_5_) * 8 + lvt_11_4_] && (lvt_9_5_ < 15 && lvt_7_1_[((lvt_9_5_ + 1) * 16 + lvt_10_5_) * 8 + lvt_11_4_] || lvt_9_5_ > 0 && lvt_7_1_[((lvt_9_5_ - 1) * 16 + lvt_10_5_) * 8 + lvt_11_4_] || lvt_10_5_ < 15 && lvt_7_1_[(lvt_9_5_ * 16 + lvt_10_5_ + 1) * 8 + lvt_11_4_] || lvt_10_5_ > 0 && lvt_7_1_[(lvt_9_5_ * 16 + (lvt_10_5_ - 1)) * 8 + lvt_11_4_] || lvt_11_4_ < 7 && lvt_7_1_[(lvt_9_5_ * 16 + lvt_10_5_) * 8 + lvt_11_4_ + 1] || lvt_11_4_ > 0 && lvt_7_1_[(lvt_9_5_ * 16 + lvt_10_5_) * 8 + (lvt_11_4_ - 1)]);
//                            if (lvt_12_4_) {
//                                Material lvt_13_1_ = p_212245_1_.getBlockState(p_212245_4_.add(lvt_9_5_, lvt_11_4_, lvt_10_5_)).getMaterial();
//                                if (lvt_11_4_ >= 4 && lvt_13_1_.isLiquid()) {
//                                    return false;
//                                }
//
//                                if (lvt_11_4_ < 4 && !lvt_13_1_.isSolid() && p_212245_1_.getBlockState(p_212245_4_.add(lvt_9_5_, lvt_11_4_, lvt_10_5_)) != WATER) {
//                                    return false;
//                                }
//                            }
//                        }
//                    }
//                }
//
//                for(lvt_9_5_ = 0; lvt_9_5_ < 16; ++lvt_9_5_) {
//                    for(lvt_10_5_ = 0; lvt_10_5_ < 16; ++lvt_10_5_) {
//                        for(lvt_11_4_ = 0; lvt_11_4_ < 8; ++lvt_11_4_) {
//                            if (lvt_7_1_[(lvt_9_5_ * 16 + lvt_10_5_) * 8 + lvt_11_4_]) {
//                                p_212245_1_.setBlockState(p_212245_4_.add(lvt_9_5_, lvt_11_4_, lvt_10_5_), lvt_11_4_ >= 4 ? AIR : WATER, 2);
//                            }
//                        }
//                    }
//                }
//
//                BlockPos lvt_12_5_;
//                for(lvt_9_5_ = 0; lvt_9_5_ < 16; ++lvt_9_5_) {
//                    for(lvt_10_5_ = 0; lvt_10_5_ < 16; ++lvt_10_5_) {
//                        for(lvt_11_4_ = 4; lvt_11_4_ < 8; ++lvt_11_4_) {
//                            if (lvt_7_1_[(lvt_9_5_ * 16 + lvt_10_5_) * 8 + lvt_11_4_]) {
//                                lvt_12_5_ = p_212245_4_.add(lvt_9_5_, lvt_11_4_ - 1, lvt_10_5_);
//                                if (p_212245_1_.getBlockState(lvt_12_5_).getBlock() == Blocks.DIRT && p_212245_1_.getLightFor(LightType.SKY, p_212245_4_.add(lvt_9_5_, lvt_11_4_, lvt_10_5_)) > 0) {
//                                    Biome lvt_13_2_ = p_212245_1_.getBiome(lvt_12_5_);
//                                    if (lvt_13_2_.getSurfaceBuilderConfig().getTop().getBlock() == Blocks.MYCELIUM) {
//                                        p_212245_1_.setBlockState(lvt_12_5_, Blocks.MYCELIUM.getDefaultState(), 2);
//                                    } else {
//                                        p_212245_1_.setBlockState(lvt_12_5_, Blocks.GRASS_BLOCK.getDefaultState(), 2);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//
//                for(lvt_9_5_ = 0; lvt_9_5_ < 16; ++lvt_9_5_) {
//                    for(lvt_10_5_ = 0; lvt_10_5_ < 16; ++lvt_10_5_) {
//                        for(lvt_11_4_ = 0; lvt_11_4_ < 8; ++lvt_11_4_) {
//                            lvt_12_4_ = !lvt_7_1_[(lvt_9_5_ * 16 + lvt_10_5_) * 8 + lvt_11_4_] && (lvt_9_5_ < 15 && lvt_7_1_[((lvt_9_5_ + 1) * 16 + lvt_10_5_) * 8 + lvt_11_4_] || lvt_9_5_ > 0 && lvt_7_1_[((lvt_9_5_ - 1) * 16 + lvt_10_5_) * 8 + lvt_11_4_] || lvt_10_5_ < 15 && lvt_7_1_[(lvt_9_5_ * 16 + lvt_10_5_ + 1) * 8 + lvt_11_4_] || lvt_10_5_ > 0 && lvt_7_1_[(lvt_9_5_ * 16 + (lvt_10_5_ - 1)) * 8 + lvt_11_4_] || lvt_11_4_ < 7 && lvt_7_1_[(lvt_9_5_ * 16 + lvt_10_5_) * 8 + lvt_11_4_ + 1] || lvt_11_4_ > 0 && lvt_7_1_[(lvt_9_5_ * 16 + lvt_10_5_) * 8 + (lvt_11_4_ - 1)]);
//                            if (lvt_12_4_ && (lvt_11_4_ < 4 || p_212245_3_.nextInt(2) != 0) && p_212245_1_.getBlockState(p_212245_4_.add(lvt_9_5_, lvt_11_4_, lvt_10_5_)).getMaterial().isSolid()) {
//                                p_212245_1_.setBlockState(p_212245_4_.add(lvt_9_5_, lvt_11_4_, lvt_10_5_), MUD, 2);
//                            }
//                        }
//                    }
//                }
//
//                return true;
//            }
//        }
//    }
//
//    static {
//        AIR = Blocks.CAVE_AIR.getDefaultState();
//    }
//}
