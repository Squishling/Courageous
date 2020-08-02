package io.xerousso.courageous.items;

import io.xerousso.courageous.tabs.FoodTab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class Knife extends Item implements IItem {

    public Knife() {
        super(new Item.Properties().group(FoodTab.FOOD).maxDamage(70).containerItem(Items.STONE_SWORD));
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack stack = itemStack.copy();
        stack.setDamage(itemStack.getDamage() + 1);
        return stack;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return stack.getDamage() < stack.getMaxDamage() + 1;
    }

    @Override
    public ItemGroup getTab() {
        return FoodTab.FOOD;
    }

}
