package co.uk.squishling.courageous.blocks;

import co.uk.squishling.courageous.items.ModItems;
import co.uk.squishling.courageous.tabs.Tab;
import co.uk.squishling.courageous.util.Reference;
import co.uk.squishling.courageous.util.lib.DefaultBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BlockBase extends Block {

    public BlockBase(String name, Material material) {
        this(name, Block.Properties.create(material));
    }

    public BlockBase(String name, Block.Properties props) {
        super(props);
        DefaultBlockProperties.defaults(this, name);
    }

}
