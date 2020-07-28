package io.xerousso.courageous;

import io.xerousso.courageous.blocks.ModBlocks;
import io.xerousso.courageous.blocks.ModContainers;
import io.xerousso.courageous.blocks.ModTileEntities;
import io.xerousso.courageous.blocks.architects_table.ArchitectsTableScreen;
import io.xerousso.courageous.blocks.cutting_board.CuttingBoardScreen;
import io.xerousso.courageous.blocks.cutting_board.CuttingBoardTER;
import io.xerousso.courageous.blocks.planter_box.PlanterBoxTER;
import io.xerousso.courageous.blocks.pottery_wheel.PotteryWheelScreen;
import io.xerousso.courageous.blocks.pottery_wheel.PotteryWheelTESR;
import io.xerousso.courageous.blocks.pottery_wheel.PotteryWheelTileEntity;
import io.xerousso.courageous.blocks.vegetation.LeavesLike;
import io.xerousso.courageous.items.ModItems;
import io.xerousso.courageous.recipes.ModRecipes;
import io.xerousso.courageous.tiles.ModTiles;
import io.xerousso.courageous.tiles.renderers.FaucetRenderer;
import io.xerousso.courageous.tiles.renderers.FluidPotRenderer;
import co.uk.squishling.courageous.util.*;
import io.xerousso.courageous.util.*;
import io.xerousso.courageous.util.config.ConfigHandler;
import io.xerousso.courageous.util.networking.ModPacketHandler;
import io.xerousso.courageous.util.rendering.FallingFluidParticle;
import io.xerousso.courageous.util.rendering.FallingWaterParticle;
import io.xerousso.courageous.util.rendering.ModParticles;
import io.xerousso.courageous.world.gen.ModFeatures;
import io.xerousso.courageous.world.gen.features.ArabianVillagePools;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.BushBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.resources.ClientResourcePackInfo;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleType;
import net.minecraft.resources.*;
import net.minecraft.resources.ResourcePackInfo.IFactory;
import net.minecraft.resources.ResourcePackInfo.Priority;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.loading.moddiscovery.ModFile;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

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

        ModRecipes.RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlocks.DEFFERED_BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModTiles.TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Preinit
    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Common setup");

        ModPacketHandler.registerPackets();

        ModFeatures.addTree(Biomes.FOREST, ModFeatures.APPLE_TREE, 0, 0.2f, 1);

        /* Arabian Village */ {
            ArabianVillagePools.init();

            for (Biome biome : ForgeRegistries.BIOMES) {
                biome.addFeature(Decoration.SURFACE_STRUCTURES, ModFeatures.ARABIAN_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
            }

            Biomes.JUNGLE_EDGE.addStructure(ModFeatures.ARABIAN_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
//            Biomes.JUNGLE_EDGE.func_235063_a_(ModFeatures.ARABIAN_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));

            List<Structure<?>> tempList = new ArrayList<>(Feature.ILLAGER_STRUCTURES);
            tempList.add(ModFeatures.ARABIAN_VILLAGE);
            Feature.ILLAGER_STRUCTURES = ImmutableList.copyOf(tempList);
        }

        EventHandler.STRIPPED_LOG_MAP.put((RotatedPillarBlock) ModBlocks.MAPLE_LOG, (RotatedPillarBlock) ModBlocks.STRIPPED_MAPLE_LOG);
        EventHandler.STRIPPED_LOG_MAP.put((RotatedPillarBlock) ModBlocks.MAPLE_LOG_SYRUP, (RotatedPillarBlock) ModBlocks.STRIPPED_MAPLE_LOG);
        EventHandler.STRIPPED_LOG_MAP.put((RotatedPillarBlock) ModBlocks.MAPLE_WOOD, (RotatedPillarBlock) ModBlocks.STRIPPED_MAPLE_WOOD);

        EventHandler.STRIPPED_LOG_MAP.put((RotatedPillarBlock) ModBlocks.PALM_LOG, (RotatedPillarBlock) ModBlocks.STRIPPED_PALM_LOG);
        EventHandler.STRIPPED_LOG_MAP.put((RotatedPillarBlock) ModBlocks.PALM_WOOD, (RotatedPillarBlock) ModBlocks.STRIPPED_PALM_WOOD);

        EventHandler.STRIPPED_LOG_MAP.put((RotatedPillarBlock) ModBlocks.REDWOOD_LOG, (RotatedPillarBlock) ModBlocks.STRIPPED_REDWOOD_LOG);
        EventHandler.STRIPPED_LOG_MAP.put((RotatedPillarBlock) ModBlocks.REDWOOD_WOOD, (RotatedPillarBlock) ModBlocks.STRIPPED_REDWOOD_WOOD);

        PotteryWheelTileEntity.POTTERY_PIECES.add(ModItems.UNFIRED_AMPHORA);
        PotteryWheelTileEntity.POTTERY_PIECES.add(ModItems.UNFIRED_WATERING_CAN);
        PotteryWheelTileEntity.POTTERY_PIECES.add(ModItems.UNFIRED_FLUID_POT.get());
    }

    private void clientRegistry(final FMLClientSetupEvent event) {
        LOGGER.info("Client setup");

//        trySetRandomPanorama();
        injectResourcePack();

        for (Block block : ModBlocks.BLOCKS) {
            if (block instanceof BushBlock || block instanceof LeavesBlock || block instanceof LeavesLike) RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
        }
        RenderTypeLookup.setRenderLayer(ModBlocks.FLUID_POT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.DISTILLER.get(), RenderType.getCutout());

        ClientRegistry.bindTileEntityRenderer(ModTileEntities.POTTERY_WHEEL, PotteryWheelTESR::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.CUTTING_BOARD, CuttingBoardTER::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.PLANTER_BOX, PlanterBoxTER::new);


        ClientRegistry.bindTileEntityRenderer(ModTiles.FLUID_POT.get(), FluidPotRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTiles.FAUCET.get(), FaucetRenderer::new);

        ScreenManager.registerFactory(ModContainers.POTTERY_WHEEL_CONTAINER, PotteryWheelScreen::new);
        ScreenManager.registerFactory(ModContainers.CUTTING_BOARD_CONTAINER, CuttingBoardScreen::new);
        ScreenManager.registerFactory(ModContainers.ARCHITECTS_TABLE_CONTAINER, ArchitectsTableScreen::new);

        ModBlockColors.registerBlockColors();
        ModItemColors.registerItemColors();
    }

    @EventBusSubscriber(bus=Bus.MOD)
    public static class Registry {

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            LOGGER.info("Items registry");

            for (Item item : ModItems.ITEMS_ARRAY) {
                event.getRegistry().register(item);
            }
        }

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            LOGGER.info("Blocks registry");

            event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[ModBlocks.BLOCKS.size()]));
        }

        @SubscribeEvent
        public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
            LOGGER.info("Tile entities registry");

            event.getRegistry().registerAll(ModTileEntities.TILE_ENTITIES.toArray(new TileEntityType[ModTileEntities.TILE_ENTITIES.size()]));
        }

        @SubscribeEvent
        public static void registerFeatures(final RegistryEvent.Register<Feature<?>> event) {
            LOGGER.info("Features registry");

            event.getRegistry().registerAll(ModFeatures.FEATURES.toArray(new Feature[ModFeatures.FEATURES.size()]));
        }

        @SubscribeEvent
        public static void registerContainerTypes(final RegistryEvent.Register<ContainerType<?>> event) {
            LOGGER.info("Container types registry");

            event.getRegistry().register(ModContainers.POTTERY_WHEEL_CONTAINER);
            event.getRegistry().register(ModContainers.CUTTING_BOARD_CONTAINER);
            event.getRegistry().register(ModContainers.ARCHITECTS_TABLE_CONTAINER);
        }

        @SubscribeEvent
        public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
            Minecraft.getInstance().particles.registerFactory(ModParticles.FALLING_WATER_PARTICLE, FallingWaterParticle.Factory::new);
            Minecraft.getInstance().particles.registerFactory(ModParticles.FALLING_FLUID_PARTICLE, FallingFluidParticle.FallingFluidParticleFactory::new);
        }

        @SubscribeEvent
        public static void registerParticleTypes(RegistryEvent.Register<ParticleType<?>> event) {
            event.getRegistry().register(ModParticles.FALLING_WATER_PARTICLE.setRegistryName(Util.MOD_ID, "falling_water"));
            event.getRegistry().register(ModParticles.FALLING_FLUID_PARTICLE.setRegistryName(Util.MOD_ID, "falling_fluid"));
        }

        @SubscribeEvent
        public static void registerSounds(final RegistryEvent.Register<SoundEvent> event) {
            LOGGER.info("Sounds registry");

            event.getRegistry().register(ModSounds.POTTERY_WHEEL_SPIN);
        }

    }

    public void injectResourcePack() {
        Minecraft mc = Minecraft.getInstance();
        if (mc == null) return;

        try {
            File resourcesFolder = FMLLoader.getLoadingModList().getModFileById(Util.MOD_ID).getFile().findResource("overrides").toFile();

            if (!resourcesFolder.exists() && !resourcesFolder.mkdirs()) return;
            if (!resourcesFolder.exists() || !resourcesFolder.isDirectory()) return;

            final String id = "courageous_mc_override";
            final ITextComponent name = new StringTextComponent("Courageous' Panorama");
            final ITextComponent description = new StringTextComponent("Custom main menu panorama.");

            final IResourcePack pack = new FolderPack(resourcesFolder) {
                String prefix = "assets/minecraft/";

                @Override
                protected InputStream getInputStream(String resourcePath) throws IOException {
                    if ("pack.mcmeta".equals(resourcePath)) return new ByteArrayInputStream(("{\"pack\":{\"description\": \"dummy\",\"pack_format\": 4}}").getBytes(StandardCharsets.UTF_8));
                    if (!resourcePath.startsWith(prefix)) throw new FileNotFoundException(resourcePath);

                    return super.getInputStream(resourcePath);
                }

                @Override
                public boolean resourceExists(String resourcePath) {
                    if ("pack.mcmeta".equals(resourcePath)) return true;
                    if (!resourcePath.startsWith(prefix)) return false;

                    return super.resourceExists(resourcePath);
                }

                @Override
                public Set<String> getResourceNamespaces(ResourcePackType type) {
                    return Collections.singleton("minecraft");
                }
            };

            Minecraft.getInstance().getResourcePackList().addPackFinder(new IPackFinder() {
                @Override
                public <T extends ResourcePackInfo> void addPackInfosToMap(Map<String, T> nameToPackMap, IFactory<T> packInfoFactory) {
                    nameToPackMap.put(id, (T) new ClientResourcePackInfo(id, true, () ->
                            pack, name, description, PackCompatibility.COMPATIBLE, Priority.TOP, true, null, false
                    ));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @OnlyIn(Dist.CLIENT)
//    public static void trySetRandomPanorama() {
//        //Get this mod's resource pack
//        Optional<ModFileResourcePack> optionalResourcePack = ResourcePackLoader.getResourcePackFor(Util.MOD_ID);
//        //If found, try replacing the panorama
//        if (optionalResourcePack.isPresent()) {
//            //First of all, get the actual resource pack
//            ModFileResourcePack resourcePack = optionalResourcePack.get();
//            //Then, get a set of subfolders from the panoramas directory
//            Set<String> folders = getSubfoldersFromDirectory(resourcePack.getModFile(), "assets/" + Util.MOD_ID + "/panoramas");
//            //If there's at least 1 such folder, replace the panorama
//            if (folders.size() > 0) {
//                //Get a random panorama from the list of folders
//                String chosenPanorama = (String) folders.toArray()[new Random().nextInt(folders.size())];
//                //Generate a base resource location for it
//                ResourceLocation panoramaLoc = new ResourceLocation(Util.MOD_ID, "panoramas/" + chosenPanorama + "/panorama");
//                //Generate the array of resource locations
//                ResourceLocation[] ResourceLocationsArray = new ResourceLocation[6];
//                for (int i = 0; i < 6; ++i) {
//                    ResourceLocationsArray[i] = new ResourceLocation(panoramaLoc.getNamespace(), panoramaLoc.getPath() + '_' + i + ".png");
//                }
//                //Replace the resource locations in the main menu's skybox field using reflection
//                ObfuscationReflectionHelper.setPrivateValue(RenderSkyboxCube.class, MainMenuScreen.PANORAMA_RESOURCES, ResourceLocationsArray, "field_209143_a");
//            }
//        }
//    }

    /**
     * Get a list of the subfolder names in a specific directory from a specific mod file
     *
     * @param modFile       the {@link ModFile} where the directory is located
     * @param directoryName the path to search from in string form, format "assets/modid/folder" or "data/modid/folder"
     * @return a set of strings being the names of the folders found
     */
    private static Set<String> getSubfoldersFromDirectory(ModFile modFile, String directoryName) {
        try {
            Path root = modFile.getLocator().findPath(modFile, directoryName).toAbsolutePath();
            return Files.walk(root, 1)
                    .map(path -> root.relativize(path.toAbsolutePath()))
                    .filter(path -> path.getNameCount() > 0)
                    .map(p -> p.toString().replaceAll("/$", ""))
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            return Collections.emptySet();
        }
    }

}
