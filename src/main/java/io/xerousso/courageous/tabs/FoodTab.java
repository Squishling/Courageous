package io.xerousso.courageous.tabs;

import io.xerousso.courageous.items.Itemz;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class FoodTab extends ItemGroup {

    public static final ItemGroup FOOD = new FoodTab();

    public FoodTab() {
        super("courageous_food");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Itemz.AVOCADO.get());
    }

}
