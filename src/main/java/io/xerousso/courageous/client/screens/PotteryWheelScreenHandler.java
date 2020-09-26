package io.xerousso.courageous.client.screens;

import io.xerousso.courageous.blocks.block_entities.PotteryInventory;
import io.xerousso.courageous.blocks.block_entities.recipes.PotteryRecipe;
import io.xerousso.courageous.blocks.block_entities.recipes.Recipes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

import java.util.ArrayList;

public class PotteryWheelScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PlayerInventory playerInventory;

    public PotteryWheelScreenHandler(int syncID, PlayerInventory playerInventory) {
        this(syncID, playerInventory, new PotteryInventory());
    }

    public PotteryWheelScreenHandler(int syncID, PlayerInventory playerInventory, Inventory inventory) {
        super(Screens.POTTERY_WHEEL_SCREEN, syncID);
        checkSize(inventory, 3);

        this.inventory = inventory;
        this.playerInventory = playerInventory;
        inventory.onOpen(playerInventory.player);

        addSlot(new Slot(inventory, 0, 33, 27));
        addSlot(new Slot(inventory, 1, 59, 27));
        addSlot(new Slot(inventory, 2, 117, 27));
        layoutPlayerInventory(playerInventory, 8, 84);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }

    private void layoutPlayerInventory(PlayerInventory playerInventory, int x, int y) {
        for (int m = 0; m < 3; ++m) for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, x + l * 18, y + m * 18));
        }

        for (int m = 0; m < 9; ++m) this.addSlot(new Slot(playerInventory, m, x + m * 18, y + 58));
    }

    public ArrayList<PotteryRecipe> getRecipes() {
        World world = playerInventory.player.world;
        if (world != null) {
            return new ArrayList<>(world.getRecipeManager().getAllMatches(Recipes.POTTERY_TYPE, (PotteryInventory) inventory, world));
        }

        return new ArrayList<>();
    }

}
