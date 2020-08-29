package io.xerousso.courageous.blocks.pottery_wheel;

import io.xerousso.courageous.blocks.IHasButton;
import io.xerousso.courageous.tiles.Tilez;
import io.xerousso.courageous.util.ModSounds;
import io.xerousso.courageous.util.Util;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;

public class PotteryWheelTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider, IHasButton {

    private LazyOptional<ItemStackHandler> handler = LazyOptional.of(() -> getHandler()).cast();

    private HashMap<Item, Integer> CLAY_VALUES_MAP = new HashMap<Item, Integer>();

    public static ArrayList<Item> POTTERY_PIECES = new ArrayList<Item>();

    public int workingTicks = 0;
    public boolean working = false;

    public int selectedIndex = 0;
    public HashMap<Item, Integer> POTTERY_MAP = new HashMap<Item, Integer>();

    public static int requiredClay = 4;
    public static int tickTime = 100;

    public PotteryWheelTileEntity() {
        super(Tilez.POTTERY_WHEEL.get());

        CLAY_VALUES_MAP.put(Items.CLAY, 4);
        CLAY_VALUES_MAP.put(Items.CLAY_BALL, 1);
    }

    @Override
    public void tick() {
        // Common
        if (working) workingTicks++;

        if (Util.isServer(world)) {
            // Server
            handler.ifPresent(h -> {
                if (working && getClayValue(h.getStackInSlot(1)) < requiredClay) {
                    working = false;
                    workingTicks = 0;
                    notifyUpdate();
                }

                if (working && workingTicks >= tickTime) onFinish();
                if (world.isBlockPowered(getPos()) && !working && getClayValue(h.getStackInSlot(1)) >= requiredClay) {
                    working = true;
                    world.playSound(null, getPos(), ModSounds.POTTERY_WHEEL_SPIN, SoundCategory.BLOCKS, 0.5f, 1f);
                    notifyUpdate();
                }
            });
        }
    }

    @Override
    public void remove() {
        handler.ifPresent(h -> {
            Block.spawnAsEntity(world, pos, h.getStackInSlot(0));
            Block.spawnAsEntity(world, pos, h.getStackInSlot(1));
        });

        super.remove();
    }

    private void onFinish() {
        workingTicks = 0;
        working = false;

        notifyUpdate();

        handler.ifPresent(h -> {
            ItemStack in = h.getStackInSlot(1);
            ItemStack out = h.getStackInSlot(0);

            if (getClayValue(in) < requiredClay) return;

            if (out.getItem() == POTTERY_PIECES.get(selectedIndex) && out.getCount() < out.getMaxStackSize()) out.setCount(out.getCount() + 1);
            else if(out.isEmpty()) h.setStackInSlot(0, new ItemStack(POTTERY_PIECES.get(selectedIndex), 1));
            else return;

            in.setCount(in.getCount() - requiredClay / CLAY_VALUES_MAP.get(in.getItem()));
        });
    }

    // ---- NBT ----
//    @Override
    public void read(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");

        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));
        selectedIndex = tag.getInt("index");

        workingTicks = tag.getInt("ticks");
        working = tag.getBoolean("working");

//        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        handler.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
            tag.put("inv", compound);
        });

        tag.putInt("index", selectedIndex);

        tag.putInt("ticks", workingTicks);
        tag.putBoolean("working", working);

        return super.write(tag);
    }


    @Override
    public CompoundNBT getUpdateTag() {
        return write(super.getUpdateTag());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        write(nbt);

        return new SUpdateTileEntityPacket(getPos(), 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        read(pkt.getNbtCompound());
    }

    // ---- Capabilities ----
    private ItemStackHandler getHandler() {
        return new ItemStackHandler(2) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return slot != 0 && CLAY_VALUES_MAP.containsKey(stack.getItem());
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) return stack;
                return super.insertItem(slot, stack, simulate);
            }

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return handler.cast();
        return super.getCapability(cap, side);
    }

    // --- Container stuff ---
    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("block.courageous.pottery_wheel");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new PotteryWheelContainer(i, world, pos, playerInventory);
    }

    @Override
    public void select(int index) {
        selectedIndex = index;
    }

    private int getClayValue(ItemStack stack) {
        int clayValue = 0;

        if (!CLAY_VALUES_MAP.containsKey(stack.getItem())) return 0;
        clayValue += CLAY_VALUES_MAP.get(stack.getItem()) * stack.getCount();

        return clayValue;
    }

    private void notifyUpdate() {
        world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 0);
    }

}
