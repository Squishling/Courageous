package co.uk.squishling.courageous.blocks.cutting_board;

import co.uk.squishling.courageous.blocks.IHasTileEntity;
import co.uk.squishling.courageous.blocks.ModBlocks;
import co.uk.squishling.courageous.blocks.ModContainers;
import co.uk.squishling.courageous.items.ModItems;
import co.uk.squishling.courageous.items.sandwich.Sandwich;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.LoomContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class CuttingBoardContainer extends Container implements IHasTileEntity {

    public TileEntity tileEntity;
    private IItemHandler inventory;

    public static final int KNIFE_SLOT = 0;
    public static final int CUTTING_INPUT_SLOT = 1;
    public static final int CUTTING_OUTPUT_SLOT = 2;
    public static final int TOP_BREAD_SLOT = 3;
    public static final int INGREDIENT_SLOT_A = 4;
    public static final int INGREDIENT_SLOT_B = 5;
    public static final int INGREDIENT_SLOT_C = 6;
    public static final int INGREDIENT_SLOT_D = 7;
    public static final int BOTTOM_BREAD_SLOT = 8;
    public static final int SANDWICH_OUTPUT_SLOT = 9;

    private boolean hasKnife = false;

    public CuttingBoardContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory) {
        super(ModContainers.CUTTING_BOARD_CONTAINER, windowId);

        tileEntity = world.getTileEntity(pos);
        inventory = new InvWrapper(playerInventory);

        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
//            addIngredientSlot(handler, KNIFE_SLOT, 41, 45);
            addSlot(new SlotItemHandler(handler, KNIFE_SLOT, 41, 45) {
                @Override
                public void onSlotChanged() {
                    Tag tag = ItemTags.getCollection().get(new ResourceLocation("courageous:knife"));
                    hasKnife = tag == null ? getSlot(KNIFE_SLOT).getStack().getItem() == ModItems.KNIFE : tag.contains(getSlot(KNIFE_SLOT).getStack().getItem());

                    onIngredientsChanged();
                }
            });

            addSlot(handler, CUTTING_INPUT_SLOT, 9, 27);
            addSlot(handler, CUTTING_OUTPUT_SLOT, 9, 65);

            addIngredientSlot(handler, TOP_BREAD_SLOT, 82, 17);
            addIngredientSlot(handler, INGREDIENT_SLOT_A, 73, 36);
            addIngredientSlot(handler, INGREDIENT_SLOT_B, 91, 36);
            addIngredientSlot(handler, INGREDIENT_SLOT_C, 73, 54);
            addIngredientSlot(handler, INGREDIENT_SLOT_D, 91, 54);
            addIngredientSlot(handler, BOTTOM_BREAD_SLOT, 82, 73);

            addSlot(new SlotItemHandler(handler, SANDWICH_OUTPUT_SLOT, 146, 45) {
                @Override
                public ItemStack onTake(PlayerEntity playerEntity, ItemStack stack) {
                    onTakeSandwich();

                    return super.onTake(playerEntity, stack);
                }
            });

            Tag tag = ItemTags.getCollection().get(new ResourceLocation("courageous:knife"));
            hasKnife = tag == null ? getSlot(KNIFE_SLOT).getStack().getItem() == ModItems.KNIFE : tag.contains(getSlot(KNIFE_SLOT).getStack().getItem());
        });
        layoutPlayerInventorySlots(8, 94);
    }

    public void onTakeSandwich() {
        getSlot(TOP_BREAD_SLOT).decrStackSize(1);
        getSlot(INGREDIENT_SLOT_A).decrStackSize(1);
        getSlot(INGREDIENT_SLOT_B).decrStackSize(1);
        getSlot(INGREDIENT_SLOT_C).decrStackSize(1);
        getSlot(INGREDIENT_SLOT_D).decrStackSize(1);
        getSlot(BOTTOM_BREAD_SLOT).decrStackSize(1);

        getSlot(KNIFE_SLOT).getStack().setDamage(getSlot(KNIFE_SLOT).getStack().getDamage() + 1);

        onIngredientsChanged();
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerIn, ModBlocks.CUTTING_BOARD);
    }

    private boolean isValidRecipe() {
        return  getSlot(TOP_BREAD_SLOT).getStack().getItem() == ModItems.BREAD_SLICE &&
                (getSlot(INGREDIENT_SLOT_A).getStack().isEmpty() || getSlot(INGREDIENT_SLOT_A).getStack().isFood()) &&
                (getSlot(INGREDIENT_SLOT_B).getStack().isEmpty() || getSlot(INGREDIENT_SLOT_B).getStack().isFood()) &&
                (getSlot(INGREDIENT_SLOT_C).getStack().isEmpty() || getSlot(INGREDIENT_SLOT_C).getStack().isFood()) &&
                (getSlot(INGREDIENT_SLOT_D).getStack().isEmpty() || getSlot(INGREDIENT_SLOT_D).getStack().isFood()) &&
                getSlot(BOTTOM_BREAD_SLOT).getStack().getItem() == ModItems.BREAD_SLICE;
    }

    private void onIngredientsChanged() {
        if (isValidRecipe() && hasKnife) {
            ItemStack stack = new ItemStack(ModItems.SANDWICH);

            for (int i = INGREDIENT_SLOT_A; i <= INGREDIENT_SLOT_D; i++) {
                if (getSlot(i).getStack().isEmpty()) continue;

                Sandwich.addIngredient(stack, getSlot(i).getStack());
            }

            getSlot(SANDWICH_OUTPUT_SLOT).putStack(stack);
        } else {
            getSlot(SANDWICH_OUTPUT_SLOT).putStack(ItemStack.EMPTY);
        }
    }

    private void addIngredientSlot(IItemHandler handler, int index, int x, int y) {
        addSlot(new SlotItemHandler(handler, index, x, y) {
            @Override
            public void onSlotChanged() {
                onIngredientsChanged();
            }
        });
    }

    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == SANDWICH_OUTPUT_SLOT || index == CUTTING_OUTPUT_SLOT) {
                if (!this.mergeItemStack(itemstack1, 10, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index > 9) {
                if (itemstack1.getItem() == ModItems.KNIFE) {
                    if (!this.mergeItemStack(itemstack1, false, KNIFE_SLOT)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack1.getItem() == ModItems.BREAD_SLICE) {
                    if (!this.mergeItemStack(itemstack1, false, TOP_BREAD_SLOT, BOTTOM_BREAD_SLOT, CUTTING_INPUT_SLOT)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack1.getItem().isFood()) {
                    if (!this.mergeItemStack(itemstack1, false, INGREDIENT_SLOT_A, INGREDIENT_SLOT_B, INGREDIENT_SLOT_C, INGREDIENT_SLOT_D, CUTTING_INPUT_SLOT)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.mergeItemStack(itemstack1, false, CUTTING_INPUT_SLOT)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.mergeItemStack(itemstack1, 10, inventorySlots.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

//    @Override
//    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
//        ItemStack itemstack = ItemStack.EMPTY;
//        Slot slot = this.inventorySlots.get(index);
//        if (slot != null && slot.getHasStack()) {
//            ItemStack itemstack1 = slot.getStack();
//            itemstack = itemstack1.copy();
//            if (index == SANDWICH_OUTPUT_SLOT) {
//                onTakeSandwich();
//            }
//
//            if(index < 10) {
//                onIngredientsChanged();
//                if (!this.mergeItemStack(itemstack1, 10, this.inventorySlots.size(), true)) {
//                    return ItemStack.EMPTY;
//                }
//            } else if (!this.mergeItemStack(itemstack1, 0, 9, false)) {
//                return ItemStack.EMPTY;
//            }
//
//            if (itemstack1.isEmpty()) {
//                slot.putStack(ItemStack.EMPTY);
//            } else {
//                slot.onSlotChanged();
//            }
//        }
//
//        return itemstack;
//    }

    protected boolean mergeItemStack(ItemStack stack, boolean reverseDirection, Integer... ints) {
        boolean flag = false;

        for (int j = 0; j < ints.length; j++) {
            int k = reverseDirection ? ints.length - 1 - j : j;
            int i = ints[k];

            if (stack.isEmpty()) break;

            Slot slot = this.inventorySlots.get(i);
            ItemStack itemstack = slot.getStack();
            if (!itemstack.isEmpty() && areItemsAndTagsEqual(stack, itemstack)) {
                int l = itemstack.getCount() + stack.getCount();
                int maxSize = Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize());
                if (l <= maxSize) {
                    stack.setCount(0);
                    itemstack.setCount(l);
                    slot.onSlotChanged();
                    flag = true;
                } else if (itemstack.getCount() < maxSize) {
                    stack.shrink(maxSize - itemstack.getCount());
                    itemstack.setCount(maxSize);
                    slot.onSlotChanged();
                    flag = true;
                }
            }
        }

        if (!stack.isEmpty()) {
            for (int j = 0; j < ints.length; j++) {
                int k = reverseDirection ? ints.length - 1 - j : j;
                int i = ints[k];

                if (stack.isEmpty()) break;

                Slot slot1 = this.inventorySlots.get(i);
                ItemStack itemstack1 = slot1.getStack();
                if (itemstack1.isEmpty() && slot1.isItemValid(stack)) {
                    if (stack.getCount() > slot1.getSlotStackLimit()) {
                        slot1.putStack(stack.split(slot1.getSlotStackLimit()));
                    } else {
                        slot1.putStack(stack.split(stack.getCount()));
                    }

                    slot1.onSlotChanged();
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(handler, index, x, y);
            x += dx;
            index++;
        }

        return index;
    }

    public int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int i = 0; i < verAmount; i++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }

        return index;
    }

    private void addSlot(IItemHandler handler, int index, int x, int y) {
        addSlot(new SlotItemHandler(handler, index, x, y));
    }

    protected void layoutPlayerInventorySlots(int leftCol, int topRow) {
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }

}
