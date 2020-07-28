package io.xerousso.courageous.util.pseudofluids;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;

public class PseudoFluidTank implements IFluidTank, IFluidHandler {
    protected PseudoFluidStack fluid;
    protected int capacity;

    public PseudoFluidTank(int capacity) {
        this.capacity = capacity;
        fluid = new PseudoFluidStack(FluidStack.EMPTY);
    }


    @Nonnull
    @Override
    public PseudoFluidStack getFluid() {
        return fluid;
    }

    @Override
    public int getFluidAmount() {
        return fluid.getAmount();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean isFluidValid(FluidStack stack) {
        return true;
    }

    @Override
    public int getTanks() {
        return 1;
    }

    @Nonnull
    @Override
    public PseudoFluidStack getFluidInTank(int tank) {
        return fluid;
    }

    @Override
    public int getTankCapacity(int tank) {
        return capacity;
    }

    @Override
    public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
        return true;
    }

    @Override
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
        return fill(new PseudoFluidStack(resource), action);
    }

    public int fill(PseudoFluidStack resource, IFluidHandler.FluidAction action) {
        if (resource.isEmpty() || !isFluidValid(resource)) {
            return 0;
        }
        if (action.simulate()) {
            if (fluid.isEmpty()) {
                return Math.min(capacity, resource.getAmount());
            }
            if (!fluid.isFluidEqual(resource)) {
                return 0;
            }
            return Math.min(capacity - fluid.getAmount(), resource.getAmount());
        }
        if (fluid.isEmpty()) {
            fluid = new PseudoFluidStack(resource.getPseudoFluid(), Math.min(capacity, resource.getAmount()));
            return fluid.getAmount();
        }
        if (!fluid.isFluidEqual(resource)) {
            return 0;
        }
        int filled = capacity - fluid.getAmount();

        if (resource.getAmount() < filled) {
            fluid.grow(resource.getAmount());
            filled = resource.getAmount();
        } else {
            fluid.setAmount(capacity);
        }

        return filled;
    }

    @Nonnull
    @Override
    public PseudoFluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
        int drained = maxDrain;
        if (fluid.getAmount() < drained) {
            drained = fluid.getAmount();
        }
        PseudoFluidStack stack = new PseudoFluidStack(fluid.getPseudoFluid(), drained);
        if (action.execute() && drained > 0) {
            fluid.shrink(drained);
        }

        return stack;
    }

    @Nonnull
    @Override
    public PseudoFluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {
        if (resource.isEmpty() || !resource.isFluidEqual(fluid)) {
            return new PseudoFluidStack(FluidStack.EMPTY);
        }
        return drain(resource.getAmount(), action);
    }

    public INBT writeToNBT(CompoundNBT nbt) {
        fluid.writeToNBT(nbt);
        return nbt;
    }

    public PseudoFluidTank readFromNBT(CompoundNBT nbt) {
        PseudoFluidStack fluid = PseudoFluidStack.loadFluidStackFromNBT(nbt);
        setFluid(fluid);
        return this;
    }

    public void setFluid(PseudoFluidStack fluid) {
        this.fluid = fluid;
    }

    public boolean isEmpty() {
        return fluid.isEmpty();
    }
}
