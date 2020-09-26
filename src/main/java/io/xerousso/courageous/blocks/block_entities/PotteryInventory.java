package io.xerousso.courageous.blocks.block_entities;

import io.xerousso.courageous.util.ItemStackList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class PotteryInventory implements Inventory {

    private ItemStackList INVENTORY = new ItemStackList(3);

    @Override
    public int size() {
        return INVENTORY.size();
    }

    @Override
    public boolean isEmpty() {
        return INVENTORY.isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        return INVENTORY.getStack(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack stack = INVENTORY.getStackCopy(slot);
        stack.setCount(amount);
        return INVENTORY.extractStack(slot, stack, false);
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack stack = INVENTORY.getStackCopy(slot);
        INVENTORY.setStack(slot, ItemStack.EMPTY);
        System.out.println(stack);
        return stack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        INVENTORY.setStack(slot, stack);
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        INVENTORY.clear();
    }

    public void deserialize(CompoundTag tag) {
        INVENTORY.deserialize(tag);
    }

    public CompoundTag serialize() {
        return INVENTORY.serialize();
    }

}
