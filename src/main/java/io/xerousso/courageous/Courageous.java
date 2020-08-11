package io.xerousso.courageous;

import io.xerousso.courageous.blocks.Blockz;
import io.xerousso.courageous.items.Itemz;
import io.xerousso.courageous.recipes.Recipez;
import io.xerousso.courageous.tiles.Tilez;
import io.xerousso.courageous.util.*;
import io.xerousso.courageous.util.config.ConfigHandler;
import io.xerousso.courageous.util.networking.ModPacketHandler;
import io.xerousso.courageous.world.gen.ModFeatures;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.particles.ParticleType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod(Util.MOD_ID)
public class Courageous {

    public static Courageous instance;
    public static final Logger LOGGER = LogManager.getLogger(Util.MOD_ID);

    public Courageous() {
        instance = this;

        ModLoadingContext.get().registerConfig(Type.COMMON, ConfigHandler.COMMON_SPEC);
        ModLoadingContext.get().registerConfig(Type.CLIENT, ConfigHandler.CLIENT_SPEC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistry);

        ConfigHandler.loadConfig(ConfigHandler.COMMON_SPEC, FMLPaths.CONFIGDIR.get().resolve("courageous-common.toml"));
        ConfigHandler.loadConfig(ConfigHandler.CLIENT_SPEC, FMLPaths.CONFIGDIR.get().resolve("courageous-client.toml"));

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new EventHandler());

        Blockz.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        Itemz.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        Tilez.TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        Recipez.RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Preinit
    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Common setup");

        ModPacketHandler.registerPackets();

//        ModFeatures.addTree(Biomes.FOREST, ModFeatures.APPLE_TREE, 0, 0.2f, 1);

        /* Arabian Village */ {
//            ArabianVillagePools.init();

            for (Biome biome : ForgeRegistries.BIOMES) {
//                biome.addFeature(Decoration.SURFACE_STRUCTURES, ModFeatures.ARABIAN_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
            }

//            Biomes.JUNGLE_EDGE.func_235063_a_(ModFeatures.ARABIAN_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
//            Biomes.JUNGLE_EDGE.func_235063_a_(ModFeatures.ARABIAN_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));

            List<Structure<?>> tempList = new ArrayList<>(Structure.field_236384_t_);
//            tempList.add(ModFeatures.ARABIAN_VILLAGE);
//            Structure.field_236384_t_ = ImmutableList.copyOf(tempList);
        }

        EventHandler.STRIPPED_LOG_MAP.put((RotatedPillarBlock) Blockz.MAPLE_LOG.get(), (RotatedPillarBlock) Blockz.STRIPPED_MAPLE_LOG.get());
        EventHandler.STRIPPED_LOG_MAP.put((RotatedPillarBlock) Blockz.MAPLE_LOG_SYRUP.get(), (RotatedPillarBlock) Blockz.STRIPPED_MAPLE_LOG.get());
        EventHandler.STRIPPED_LOG_MAP.put((RotatedPillarBlock) Blockz.MAPLE_WOOD.get(), (RotatedPillarBlock) Blockz.STRIPPED_MAPLE_WOOD.get());

        EventHandler.STRIPPED_LOG_MAP.put((RotatedPillarBlock) Blockz.PALM_LOG.get(), (RotatedPillarBlock) Blockz.STRIPPED_PALM_LOG.get());
        EventHandler.STRIPPED_LOG_MAP.put((RotatedPillarBlock) Blockz.PALM_WOOD.get(), (RotatedPillarBlock) Blockz.STRIPPED_PALM_WOOD.get());

        EventHandler.STRIPPED_LOG_MAP.put((RotatedPillarBlock) Blockz.REDWOOD_LOG.get(), (RotatedPillarBlock) Blockz.STRIPPED_REDWOOD_LOG.get());
        EventHandler.STRIPPED_LOG_MAP.put((RotatedPillarBlock) Blockz.REDWOOD_WOOD.get(), (RotatedPillarBlock) Blockz.STRIPPED_REDWOOD_WOOD.get());

//        PotteryWheelTileEntity.POTTERY_PIECES.add(ModItems.UNFIRED_AMPHORA);
//        PotteryWheelTileEntity.POTTERY_PIECES.add(ModItems.UNFIRED_WATERING_CAN);
//        PotteryWheelTileEntity.POTTERY_PIECES.add(ModItems.UNFIRED_FLUID_POT.get());
    }

    private void clientRegistry(final FMLClientSetupEvent event) {
        LOGGER.info("Client setup");

        Blockz.setRenderLayers();

//        ClientRegistry.bindTileEntityRenderer(ModTileEntities.POTTERY_WHEEL, PotteryWheelTESR::new);
//        ClientRegistry.bindTileEntityRenderer(ModTileEntities.CUTTING_BOARD, CuttingBoardTER::new);
//        ClientRegistry.bindTileEntityRenderer(ModTileEntities.PLANTER_BOX, PlanterBoxTER::new);


//        ClientRegistry.bindTileEntityRenderer(ModTiles.FLUID_POT.get(), FluidPotRenderer::new);
//        ClientRegistry.bindTileEntityRenderer(ModTiles.FAUCET.get(), FaucetRenderer::new);

//        ScreenManager.registerFactory(ModContainers.POTTERY_WHEEL_CONTAINER, PotteryWheelScreen::new);
//        ScreenManager.registerFactory(ModContainers.CUTTING_BOARD_CONTAINER, CuttingBoardScreen::new);
//        ScreenManager.registerFactory(ModContainers.ARCHITECTS_TABLE_CONTAINER, ArchitectsTableScreen::new);

        ModBlockColors.registerBlockColors();
        ModItemColors.registerItemColors();
    }

    @EventBusSubscriber(bus=Bus.MOD)
    public static class Registry {

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Block> event) {
            Itemz.registerItemBlocks();
        }

        @SubscribeEvent
        public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
            LOGGER.info("Tile entities registry");

//            event.getRegistry().registerAll(ModTileEntities.TILE_ENTITIES.toArray(new TileEntityType[ModTileEntities.TILE_ENTITIES.size()]));
        }

        @SubscribeEvent
        public static void registerFeatures(final RegistryEvent.Register<Feature<?>> event) {
            LOGGER.info("Features registry");

            event.getRegistry().registerAll(ModFeatures.FEATURES.toArray(new Feature[ModFeatures.FEATURES.size()]));
        }

        @SubscribeEvent
        public static void registerContainerTypes(final RegistryEvent.Register<ContainerType<?>> event) {
            LOGGER.info("Container types registry");

//            event.getRegistry().register(ModContainers.POTTERY_WHEEL_CONTAINER);
//            event.getRegistry().register(ModContainers.CUTTING_BOARD_CONTAINER);
//            event.getRegistry().register(ModContainers.ARCHITECTS_TABLE_CONTAINER);
        }

        @SubscribeEvent
        public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
//            Minecraft.getInstance().particles.registerFactory(ModParticles.FALLING_WATER_PARTICLE, FallingWaterParticle.Factory::new);
//            Minecraft.getInstance().particles.registerFactory(ModParticles.FALLING_FLUID_PARTICLE, FallingFluidParticle.FallingFluidParticleFactory::new);
        }

        @SubscribeEvent
        public static void registerParticleTypes(RegistryEvent.Register<ParticleType<?>> event) {
//            event.getRegistry().register(ModParticles.FALLING_WATER_PARTICLE.setRegistryName(Util.MOD_ID, "falling_water"));
//            event.getRegistry().register(ModParticles.FALLING_FLUID_PARTICLE.setRegistryName(Util.MOD_ID, "falling_fluid"));
        }

        @SubscribeEvent
        public static void registerSounds(final RegistryEvent.Register<SoundEvent> event) {
            LOGGER.info("Sounds registry");

            event.getRegistry().register(ModSounds.POTTERY_WHEEL_SPIN);
        }

    }

}
