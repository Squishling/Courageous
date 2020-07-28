package io.xerousso.courageous.tiles;

import io.xerousso.courageous.blocks.pot.BlockFluidPot;
import io.xerousso.courageous.util.pseudofluids.IPseudoFluidHandler;
import io.xerousso.courageous.util.pseudofluids.PseudoFluidStack;
import io.xerousso.courageous.util.pseudofluids.PseudoFluidTank;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.extensions.IForgeTileEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileFluidPot extends TileEntity implements IForgeTileEntity, IPseudoFluidHandler {
    protected PseudoFluidTank tank;
    protected final LazyOptional<IFluidHandler> fluidHandler = LazyOptional.of(() -> this).cast();


    public TileFluidPot() {
        super(ModTiles.FLUID_POT.get());
        tank = new PseudoFluidTank(4000);
    }

    public TileFluidPot(TileEntityType type) {
        super(type);
        tank = new PseudoFluidTank(4000);
    }

    public void copyPotInfo(TileFluidPot pot) {
        if (!world.isRemote) {
            this.tank = pot.tank;
        }
        SynchroniseTile();
    }

    @Override
    public int getTanks() {
        return 2;
    }

    @Nonnull
    @Override
    public PseudoFluidStack getFluidInTank(int index) {
        return tank.getFluid();
    }

    @Override
    public int getTankCapacity(int index) {
        return tank.getCapacity();
    }

    @Override
    public boolean isFluidValid(int index, @Nonnull FluidStack stack) {
        return true;
    } //All fluids accepted

    public boolean canBeVanillaDrained() {
        return !tank.getFluid().isFake();
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if (resource instanceof PseudoFluidStack) {
            return fill((PseudoFluidStack) resource, action);
        }
        return fill(new PseudoFluidStack(resource), action);
    }

    public int fill(PseudoFluidStack resource, FluidAction action) {
        SynchroniseTile();
        return tank.fill(resource, action);
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        SynchroniseTile();
        return tank.drain(resource, action);
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        SynchroniseTile();
        return tank.drain(maxDrain, action);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.put("tank", tank.writeToNBT(new CompoundNBT()));
        return compound;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        tank.readFromNBT(compound.getCompound("tank"));
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && ((facing == Direction.UP && getBlockState().get(BlockFluidPot.OPEN)) || facing == null)) {
            return fluidHandler.cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public net.minecraft.nbt.CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        this.read(tag);
    }

    //Sync and replace this tile with the new blockstate, will sync info to client
    protected void SynchroniseTile() {
        markDirty();
        if (world != null) {
            world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 3);
            world.markBlockRangeForRenderUpdate(pos, getBlockState(), getBlockState());
            markDirty();
        }
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, this.getType().hashCode(), this.write(new CompoundNBT()));
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        CompoundNBT compound = packet.getNbtCompound();
        this.read(compound);
    }
}
