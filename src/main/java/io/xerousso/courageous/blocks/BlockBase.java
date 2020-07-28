package io.xerousso.courageous.blocks;

import io.xerousso.courageous.util.lib.DefaultBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {

    public BlockBase(String name, Material material) {
        this(name, Block.Properties.create(material));
    }

    public BlockBase(String name, Block.Properties props) {
        super(props);
        DefaultBlockProperties.defaults(this, name);
    }

}
