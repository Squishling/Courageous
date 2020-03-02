//package co.uk.squishling.courageous.blocks.pottery_wheel;
//
//import co.uk.squishling.courageous.blocks.ModBlocks;
//import co.uk.squishling.courageous.blocks.ModContainers;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.inventory.container.Container;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.IWorldPosCallable;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraftforge.items.CapabilityItemHandler;
//import net.minecraftforge.items.IItemHandler;
//import net.minecraftforge.items.SlotItemHandler;
//import net.minecraftforge.items.wrapper.InvWrapper;
//
//public class PotteryWheelContainer extends Container {
//
//    public TileEntity tileEntity;
//    private IItemHandler inventory;
//
//    public PotteryWheelContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory) {
//        super(ModContainers.POTTERY_WHEEL_CONTAINER, windowId);
//
//        tileEntity = world.getTileEntity(pos);
//        inventory = new InvWrapper(playerInventory);
//
//        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
//            addSlot(handler, 0, 47, 30);
//            addSlot(handler, 1, 113, 30);
//        });
//        layoutPlayerInventorySlots(8, 84);
//    }
//
//    @Override
//    public boolean canInteractWith(PlayerEntity playerIn) {
//        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerIn, ModBlocks.POTTERY_WHEEL);
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
//    protected void layoutPlayerInventorySlots(int leftCol, int topRow) {
//        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);
//
//        topRow += 58;
//        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
//    }
//
//}
