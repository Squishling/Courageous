package io.xerousso.courageous.tabs;

import io.xerousso.courageous.blocks.Blockz;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class GeneralTab extends ItemGroup {

    public static final ItemGroup GENERAL = new GeneralTab();

    public GeneralTab() {
        super("courageous_general");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Blockz.STRIPPED_MAPLE_LOG.get());
    }

}
