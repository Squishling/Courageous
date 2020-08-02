package io.xerousso.courageous.items;

import io.xerousso.courageous.tabs.GeneralTab;
import net.minecraft.item.ItemGroup;

public interface IItem {

    /**
     * The item group (creative tab) of this block/item.
     * @return The item group
     */
    default ItemGroup getTab() {
        return GeneralTab.GENERAL;
    }

}
