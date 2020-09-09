package io.xerousso.courageous.blocks.block_entities;

import io.xerousso.courageous.util.ItemStackList;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;

public class PotteryWheelBlockEntity extends BlockEntity implements Tickable, BlockEntityClientSerializable, Inventory {

    private ItemStackList INVENTORY = new ItemStackList(3);

    public PotteryWheelBlockEntity() {
        super(BlockEntities.POTTERY_WHEEL_BLOCK_ENTITY);
    }

    @Override
    public void tick() {

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return false;
    }

    // Inventory
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
        return stack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        INVENTORY.setStack(slot, stack);
    }

    @Override
    public void clear() {
        INVENTORY.clear();
    }

    // NBT
    // Server
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        return super.toTag(tag);
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
    }

    // Client
    @Override
    public CompoundTag toClientTag(CompoundTag compoundTag) {
        return new CompoundTag();
    }

    @Override
    public void fromClientTag(CompoundTag compoundTag) {

    }

}
