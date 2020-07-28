package io.xerousso.courageous.items;

import io.xerousso.courageous.util.Util;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item {

    public ItemBase(String name, ItemGroup group) {
        super(new Item.Properties().group(group));
        this.setRegistryName(Util.MOD_ID, name);

        ModItems.ITEMS_ARRAY.add(this);
    }

    public ItemBase(String name, Item.Properties properties) {
        super(properties);
        this.setRegistryName(Util.MOD_ID, name);

        ModItems.ITEMS_ARRAY.add(this);
    }

}
