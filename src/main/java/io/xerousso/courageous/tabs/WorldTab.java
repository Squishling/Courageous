package io.xerousso.courageous.tabs;

import io.xerousso.courageous.items.Itemz;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class WorldTab extends ItemGroup {

    public static final ItemGroup WORLD = new WorldTab();

    public WorldTab() {
        super("courageous_world");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Itemz.MUD_BALL.get());
    }

}
