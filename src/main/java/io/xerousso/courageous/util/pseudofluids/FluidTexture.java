package io.xerousso.courageous.util.pseudofluids;

import net.minecraft.util.ResourceLocation;

public class FluidTexture {
    ResourceLocation texture;
    int colour;
    int luminosity;

    public FluidTexture(ResourceLocation texture, int colour, int luminosity) {
        this.texture = texture;
        this.colour = colour;
        this.luminosity = luminosity;
    }

    public int getColour() {
        return colour;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public int getLuminosity() {
        return luminosity;
    }
}
