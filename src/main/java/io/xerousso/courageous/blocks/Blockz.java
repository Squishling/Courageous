package io.xerousso.courageous.blocks;

import io.xerousso.courageous.Courageous;
import io.xerousso.courageous.items.Itemz;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

public class Blockz {

    public static final Block POTTERY_WHEEL = registerBlockGeneral(new PotteryWheel(), "pottery_wheel", Itemz.POTTERY_GROUP);

    public static final Block MUD = registerBlockGeneral(new MudBlock(), "mud", Itemz.WORLD_GROUP);

    public static final Block REDWOOD_LOG = registerBlockGeneral(createLogBlock(MaterialColor.ORANGE, MaterialColor.ORANGE), "redwood_log", Itemz.WORLD_GROUP);
    public static final Block STRIPPED_REDWOOD_LOG = registerBlockGeneral(createLogBlock(MaterialColor.ORANGE, MaterialColor.ORANGE), "stripped_redwood_log", Itemz.WORLD_GROUP);
    public static final Block REDWOOD_WOOD = registerBlockGeneral(createLogBlock(MaterialColor.ORANGE, MaterialColor.ORANGE), "redwood_wood", Itemz.WORLD_GROUP);
    public static final Block STRIPPED_REDWOOD_WOOD = registerBlockGeneral(createLogBlock(MaterialColor.ORANGE, MaterialColor.ORANGE), "stripped_redwood_wood", Itemz.WORLD_GROUP);

    private static Block registerBlockGeneral(Block block, String name) {
        Registry.register(Registry.ITEM, new Identifier(Courageous.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
        return Registry.register(Registry.BLOCK, new Identifier(Courageous.MOD_ID, name), block);
    }

    private static Block registerBlockGeneral(Block block, String name, ItemGroup group) {
        Registry.register(Registry.ITEM, new Identifier(Courageous.MOD_ID, name), new BlockItem(block, new FabricItemSettings().group(group)));
        return Registry.register(Registry.BLOCK, new Identifier(Courageous.MOD_ID, name), block);
    }

    private static PillarBlock createLogBlock(MaterialColor topMaterialColor, MaterialColor sideMaterialColor) {
        return new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, (blockState) -> {
            return blockState.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMaterialColor : sideMaterialColor;
        }).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    }

    public static void init() {}

}
