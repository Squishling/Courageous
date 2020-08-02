//package io.xerousso.courageous.blocks.planter_box;
//
//import io.xerousso.courageous.blocks.ModTileEntities;
//import net.minecraft.block.Block;
//import net.minecraft.entity.item.ItemEntity;
//import net.minecraft.item.BlockItem;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.CompoundNBT;
//import net.minecraft.network.NetworkManager;
//import net.minecraft.network.play.server.SUpdateTileEntityPacket;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.Direction;
//import net.minecraftforge.common.capabilities.Capability;
//import net.minecraftforge.common.util.INBTSerializable;
//import net.minecraftforge.common.util.LazyOptional;
//import net.minecraftforge.items.CapabilityItemHandler;
//import net.minecraftforge.items.ItemStackHandler;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//public class PlanterBoxTileEntity extends TileEntity
//{
//    private LazyOptional<ItemStackHandler> handler = LazyOptional.of(() -> getHandler()).cast();
//
////    public PlanterBoxTileEntity() { super(ModTileEntities.PLANTER_BOX); }
//    //public Block containBlock;
//
//    @Override
//    public void remove()
//    {
//        this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
//            //Block block = Block.getBlockFromItem(handler.getStackInSlot(0).getItem());
//            //LootTable loottable = this.world.getServer().getLootTableManager().getLootTableFromLocation(Block.Properties. );
//            world.addEntity(new ItemEntity(world, pos.getX() + 0.5 , pos.getY() + 0.5, pos.getZ() + 0.5 , new ItemStack(Block.getBlockFromItem(handler.getStackInSlot(0).getItem()), 1)));
//        });
//        super.remove();
//    }
//
//    // ---- NBT ----
////    @Override
//    public void read(CompoundNBT tag) {
//        CompoundNBT invTag = tag.getCompound("inv");
//
//        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));
//
////        super.read(tag);
//    }
//
//    @Override
//    public CompoundNBT write(CompoundNBT tag) {
//        handler.ifPresent(h -> {
//            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
//            tag.put("inv", compound);
//        });
//
//        return super.write(tag);
//    }
//
//
//    @Override
//    public CompoundNBT getUpdateTag() {
//        return write(super.getUpdateTag());
//    }
//
//    @Nullable
//    @Override
//    public SUpdateTileEntityPacket getUpdatePacket() {
//        CompoundNBT nbt = new CompoundNBT();
//        write(nbt);
//
//        return new SUpdateTileEntityPacket(getPos(), 1, nbt);
//    }
//
//    @Override
//    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
//        read(pkt.getNbtCompound());
//    }
//
//    // ---- Capabilities ----
//    private ItemStackHandler getHandler() {
//        return new ItemStackHandler(1) {
//            @Override
//            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
//                if (slot == 0) return stack.getItem() instanceof BlockItem;
//                return false;
//            }
//
//            @Nonnull
//            @Override
//            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
//                if (!isItemValid(slot, stack)) return stack;
//                return super.insertItem(slot, stack, simulate);
//            }
//
//            @Override
//            protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
//                return 1;
//            }
//        };
//    }
//
//    @Nonnull
//    @Override
//    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
//        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return handler.cast();
//        return super.getCapability(cap, side);
//    }
//}
