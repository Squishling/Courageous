package io.xerousso.courageous.tabs;

import io.xerousso.courageous.items.Itemz;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class PotteryTab extends ItemGroup {

    public static final ItemGroup POTTERY = new PotteryTab();

    public PotteryTab() {
        super("courageous_pottery");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Itemz.AMPHORA.get());
    }

}
