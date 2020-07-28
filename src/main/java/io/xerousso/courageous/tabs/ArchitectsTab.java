package io.xerousso.courageous.tabs;

import io.xerousso.courageous.blocks.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ArchitectsTab extends ItemGroup {

    public static final ItemGroup ARCHITECT = new ArchitectsTab();

    public ArchitectsTab() {
        super("courageous_architect");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModBlocks.ARCHITECTS_TABLE);
    }

}
