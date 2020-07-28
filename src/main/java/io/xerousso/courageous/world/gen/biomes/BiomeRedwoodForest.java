package io.xerousso.courageous.world.gen.biomes;

import io.xerousso.courageous.blocks.ModBlocks;
import io.xerousso.courageous.world.gen.ModFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.common.IPlantable;

public class BiomeRedwoodForest extends Biome {

    public BiomeRedwoodForest() {
        super(new Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
                .category(Category.FOREST)

                .precipitation(RainType.RAIN)
                .downfall(0.8f)

                .temperature(0.6f)

                .depth(0.5f)
                .scale(0.1f)

                .waterColor(0x02367e)
                .waterFogColor(0x2158a5)

                .parent((String) null));

        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addSedimentDisks(this);
        DefaultBiomeFeatures.addReedsAndPumpkins(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addStructures(this);

        ModFeatures.addUndergroundFeatures(this);
        ModFeatures.addVegetation(this);
        ModFeatures.addVegetation(this);
        ModFeatures.addPillagerOutpost(this);
        ModFeatures.addVilage(this, "taiga");

//        ModFeatures.addTree(this, ModFeatures.GIANT_REDWOOD_TREE, 8, 0.1f, 1);
        ModFeatures.addGiantSpruceTree(this, ModBlocks.REDWOOD_LOG.getDefaultState(), ModBlocks.REDWOOD_LEAVES.getDefaultState(), (IPlantable) ModBlocks.REDWOOD_SAPLING, 3, 0.1f, 1);
        ModFeatures.addTree(this, ModFeatures.ALPINE_TREE, 3, 0.1f, 1);
        ModFeatures.addSparseTrees(this);

        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.PIG, 10, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.COW, 8, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.WOLF, 8, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.RABBIT, 4, 2, 3));
        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(EntityType.BAT, 10, 8, 8));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITCH, 5, 1, 1));
    }

    @Override
    public int getGrassColor(double p_225528_1_, double p_225528_3_) {
        return super.getGrassColor(p_225528_1_, p_225528_3_);
        // return 0x2d8137;
    }


    @Override
    public int getFoliageColor() {
        return 0x748541;
    }

}
