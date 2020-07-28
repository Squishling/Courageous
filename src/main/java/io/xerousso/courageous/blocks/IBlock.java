package io.xerousso.courageous.blocks;

import net.minecraft.item.ItemGroup;

public interface IBlock {

    /**
     * The item group (creative tab) of this block/item.
     * @return The item group
     */
    ItemGroup getTab();

}
