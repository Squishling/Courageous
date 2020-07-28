package io.xerousso.courageous.util.pseudofluids;

import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class PseudoFluidInteractionResult {
    public final boolean fillMode;
    public SoundEvent sound;
    public ItemStack resultItem;
    public PseudoFluidStack fluidStack;

    public PseudoFluidInteractionResult(SoundEvent sound, ItemStack resultItem, PseudoFluidStack fluidStack, boolean fillsContainer) {
        this.sound = sound;
        this.resultItem = resultItem;
        this.fluidStack = fluidStack;
        this.fillMode = fillsContainer;
    }
}
