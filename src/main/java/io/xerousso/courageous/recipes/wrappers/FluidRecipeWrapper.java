package io.xerousso.courageous.recipes.wrappers;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class FluidRecipeWrapper extends RecipeWrapper {
    protected final IFluidHandler fluidinv;

    public FluidRecipeWrapper(IFluidHandler fluidinv, IItemHandlerModifiable inv) {
        super(inv);
        this.fluidinv = fluidinv;
    }

    @Override
    public boolean isEmpty() {
        for (int tank = 0; tank < fluidinv.getTanks(); tank++) {
            if (!fluidinv.getFluidInTank(tank).isEmpty()) return false;
        }
        return super.isEmpty();
    }

    public FluidStack getFluidInTank(int tank) {
        return fluidinv.getFluidInTank(tank);
    }
}
