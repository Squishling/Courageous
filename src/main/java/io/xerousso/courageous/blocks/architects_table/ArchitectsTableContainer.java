//package io.xerousso.courageous.blocks.architects_table;
//
//import io.xerousso.courageous.blocks.Blockz;
//import io.xerousso.courageous.blocks.ModContainers;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.inventory.container.Container;
//import net.minecraft.inventory.container.Slot;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.IWorldPosCallable;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraftforge.items.CapabilityItemHandler;
//import net.minecraftforge.items.IItemHandler;
//import net.minecraftforge.items.SlotItemHandler;
//import net.minecraftforge.items.wrapper.InvWrapper;
//
//public class ArchitectsTableContainer extends Container {
//
//    private IItemHandler inventory;
//    public TileEntity tileEntity;
//
//    public ArchitectsTableContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory) {
//        super(ModContainers.ARCHITECTS_TABLE_CONTAINER, windowId);
//
//        tileEntity = world.getTileEntity(pos);
//        inventory = new InvWrapper(playerInventory);
//
//        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
//            addSlot(handler, 0, 14, 51);
//            addOutputSlot(handler, 1, 175, 51);
//        });
//
//        layoutPlayerInventorySlots(24, 117);
//    }
//
//    @Override
//    public boolean canInteractWith(PlayerEntity playerIn) {
//        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerIn, Blockz.ARCHITECTS_TABLE);
//    }
//
//    protected boolean mergeItemStack(ItemStack stack, boolean reverseDirection, Integer... ints) {
//        boolean flag = false;
//
//        for (int j = 0; j < ints.length; j++) {
//            int k = reverseDirection ? ints.length - 1 - j : j;
//            int i = ints[k];
//
//            if (stack.isEmpty()) break;
//
//            Slot slot = this.inventorySlots.get(i);
//            ItemStack itemstack = slot.getStack();
//            if (!itemstack.isEmpty() && areItemsAndTagsEqual(stack, itemstack)) {
//                int l = itemstack.getCount() + stack.getCount();
//                int maxSize = Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize());
//                if (l <= maxSize) {
//                    stack.setCount(0);
//                    itemstack.setCount(l);
//                    slot.onSlotChanged();
//                    flag = true;
//                } else if (itemstack.getCount() < maxSize) {
//                    stack.shrink(maxSize - itemstack.getCount());
//                    itemstack.setCount(maxSize);
//                    slot.onSlotChanged();
//                    flag = true;
//                }
//            }
//        }
//
//        if (!stack.isEmpty()) {
//            for (int j = 0; j < ints.length; j++) {
//                int k = reverseDirection ? ints.length - 1 - j : j;
//                int i = ints[k];
//
//                if (stack.isEmpty()) break;
//
//                Slot slot1 = this.inventorySlots.get(i);
//                ItemStack itemstack1 = slot1.getStack();
//                if (itemstack1.isEmpty() && slot1.isItemValid(stack)) {
//                    if (stack.getCount() > slot1.getSlotStackLimit()) {
//                        slot1.putStack(stack.split(slot1.getSlotStackLimit()));
//                    } else {
//                        slot1.putStack(stack.split(stack.getCount()));
//                    }
//
//                    slot1.onSlotChanged();
//                    flag = true;
//                    break;
//                }
//            }
//        }
//
//        return flag;
//    }
//
//    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
//        for (int i = 0; i < amount; i++) {
//            addSlot(handler, index, x, y);
//            x += dx;
//            index++;
//        }
//
//        return index;
//    }
//
//    public int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
//        for (int i = 0; i < verAmount; i++) {
//            index = addSlotRange(handler, index, x, y, horAmount, dx);
//            y += dy;
//        }
//
//        return index;
//    }
//
//    private void addSlot(IItemHandler handler, int index, int x, int y) {
//        addSlot(new SlotItemHandler(handler, index, x, y));
//    }
//
//    private int addOutputSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
//        for (int i = 0; i < amount; i++) {
//            addSlot(new SlotItemHandler(handler, index, x, y) {
//                @Override
//                public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
//                    getSlot(0).decrStackSize(1);
//                    return stack;
//                }
//            });
//            x += dx;
//            index++;
//        }
//
//        return index;
//    }
//
//    public int addOutputSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
//        for (int i = 0; i < verAmount; i++) {
//            index = addOutputSlotRange(handler, index, x, y, horAmount, dx);
//            y += dy;
//        }
//
//        return index;
//    }
//
//    private void addOutputSlot(IItemHandler handler, int index, int x, int y) {
//        addSlot(new SlotItemHandler(handler, index, x, y) {
//            @Override
//            public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
//                getSlot(0).decrStackSize(1);
//                return stack;
//            }
//        });
//    }
//
//    protected void layoutPlayerInventorySlots(int leftCol, int topRow) {
//        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);
//
//        topRow += 58;
//        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
//    }
//
//}
