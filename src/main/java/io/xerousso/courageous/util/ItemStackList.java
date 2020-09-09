package io.xerousso.courageous.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.collection.DefaultedList;

import java.util.List;

public class ItemStackList implements IItemStackInventory {

    private DefaultedList<ItemStack> list;

    public ItemStackList(int size) {
        list = DefaultedList.ofSize(size, ItemStack.EMPTY);
    }

    public ItemStackList(List<ItemStack> list) {
        this.list = (DefaultedList<ItemStack>) list;
    }

    @Override
    public ItemStack getStackCopy(int id) {
        return list.get(id).copy();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < list.size(); i++) {
            if (!getStackCopy(i).isEmpty()) return false;
        }

        return true;
    }

    @Override
    public void clear() {
        list = DefaultedList.ofSize(list.size(), ItemStack.EMPTY);
    }

    /**
     * DEPRECATED: Please use `getStackCopy` instead.  Making changes to this stack will not fire `onContentsChanged`, but will change the stack.
     * @return The stack at the position.  Is NOT a copy and WILL NOT fire `onContentsChanged` when changed.
     */
    @Deprecated
    public ItemStack getStack(int id) {
        return list.get(id);
    }

    @Override
    public void setStack(int id, ItemStack stack) {
        list.set(id, stack);
        onContentsChanged(id);
    }

    @Override
    public ItemStack addStack(ItemStack stack, boolean simulate) {
        for (int i = 0; i < list.size(); i++) {
            stack = insertStack(i, stack, simulate);

            if (stack.getCount() <= 0) break;
        }

        return stack;
    }

    @Override
    public ItemStack removeStack(ItemStack stack, boolean simulate) {
        for (int i = list.size(); i > 0; i--) {
            stack = extractStack(i, stack, simulate);

            if (stack.getCount() <= 0) break;
        }

        return stack;
    }

    @Override
    public ItemStack insertStack(int i, ItemStack stack, boolean simulate) {
        if (list.get(i).isItemEqual(stack) || list.get(i).isEmpty() && isStackValid(i, stack)) {
            int toInsert = Math.min(list.get(i).getMaxCount() - list.get(i).getCount(), stack.getCount());
            if (toInsert > 0) {
                if (!simulate) list.get(i).increment(toInsert);
                stack.decrement(toInsert);
                if (!simulate) onContentsChanged(i);
            }
        }

        return stack;
    }

    @Override
    public ItemStack extractStack(int i, ItemStack stack, boolean simulate) {
        if (list.get(i).isItemEqual(stack)) {
            int toExtract = Math.min(list.get(i).getCount(), stack.getCount());
            if (toExtract > 0) {
                if (!simulate) list.get(i).decrement(toExtract);
                stack.decrement(toExtract);
                if (!simulate) onContentsChanged(i);
            }
        }

        return stack;
    }

    @Override
    public CompoundTag serialize() {
        ListTag stacks = new ListTag();

        for (int i = 0; i < list.size(); i++) {
            CompoundTag itemTag = new CompoundTag();;
            list.get(i).toTag(itemTag);
            stacks.add(i, itemTag);
        }

        CompoundTag tag = new CompoundTag();
        tag.put("stacks", stacks);
        tag.putInt("size", list.size());

        return tag;
    }

    @Override
    public void deserialize(CompoundTag tag) {
        list = DefaultedList.ofSize(tag.contains("size") ? tag.getInt("size") : list.size(), ItemStack.EMPTY);
        if (!tag.contains("stacks")) return;

        ListTag stacks = tag.getList("stacks", 10);

        for (int i = 0; i < stacks.size(); i++) list.set(i, ItemStack.fromTag(stacks.getCompound(i)));
    }

    @Override
    public boolean isStackValid(int slot, ItemStack stack) {
        return true;
    }

    @Override
    public int size() {
        return list.size();
    }

}
