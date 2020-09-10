package io.xerousso.courageous.blocks.block_entities;

import io.xerousso.courageous.client.PotteryWheelScreenDescription;
import io.xerousso.courageous.util.ItemStackList;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import org.jetbrains.annotations.Nullable;

public class PotteryWheelBlockEntity extends BlockEntity implements Tickable, BlockEntityClientSerializable, Inventory, NamedScreenHandlerFactory {

    private ItemStackList INVENTORY = new ItemStackList(3);

    public PotteryWheelBlockEntity() {
        super(BlockEntities.POTTERY_WHEEL_BLOCK_ENTITY);
    }

    @Override
    public void tick() {

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText("gui.courageous.pottery_wheel");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new PotteryWheelScreenDescription(syncId, inv, ScreenHandlerContext.create(world, pos));
    }

    // NBT
    // Server
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);

        tag.put("inventory", INVENTORY.serialize());

        return tag;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);

        if (tag.contains("inventory")) INVENTORY.deserialize((CompoundTag) tag.get("inventory"));
    }

    // Client
    @Override
    public CompoundTag toClientTag(CompoundTag compoundTag) {
        return new CompoundTag();
    }

    @Override
    public void fromClientTag(CompoundTag compoundTag) {

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

}
