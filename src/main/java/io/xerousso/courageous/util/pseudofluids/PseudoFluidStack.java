package io.xerousso.courageous.util.pseudofluids;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;


public class PseudoFluidStack extends FluidStack {

    public static final PseudoFluidStack EMPTY = new PseudoFluidStack(Fluids.EMPTY.getRegistryName(), 0);

    private boolean isFake = false; //Assume fluid is real until proven otherwise
    private ResourceLocation name;

    public PseudoFluidStack(ResourceLocation location, int amount) {
        super(createFluid(location), amount);
        name = location;
        if (PseudoFluidUtil.fluidRegistryToAttributes.containsKey(location)) {
            isFake = true;
        }
    }

    public PseudoFluidStack(ResourceLocation name, int amount, CompoundNBT tag) {
        this(name, amount);
        if (!(isEmpty() || tag == null)) this.setTag(tag);
    }

    public PseudoFluidStack(FluidStack stack) {
        this(stack.getFluid().getRegistryName(), stack.getAmount(), stack.getTag());
        if (stack instanceof PseudoFluidStack) {
            isFake = true;
            name = ((PseudoFluidStack) stack).getPseudoFluid();
        }
    }

    //@Override //Compiler doesn't want to believe this is an override
    public static PseudoFluidStack loadFluidStackFromNBT(CompoundNBT nbt) {
        if (nbt == null) {
            return EMPTY;
        }
        if (!nbt.contains("FluidName", Constants.NBT.TAG_STRING)) {
            return EMPTY;
        }
        ResourceLocation fluidName = new ResourceLocation(nbt.getString("FluidName"));
        PseudoFluidStack stack = new PseudoFluidStack(fluidName, nbt.getInt("Amount"));

        if (nbt.contains("Tag", Constants.NBT.TAG_COMPOUND)) {
            stack.setTag(nbt.getCompound("Tag"));
        }

        return stack;
    }

    public boolean isFake() {
        return isFake;
    }

    @Override
    public CompoundNBT writeToNBT(CompoundNBT nbt) {
        nbt = super.writeToNBT(nbt);
        if (isFake) {
            nbt.putString("FluidName", name.toString());
        }
        return nbt;
    }

    @Override
    public void writeToPacket(PacketBuffer buf) {
        buf.writeBoolean(isFake);
        if (isFake) {
            buf.writeResourceLocation(name);
            buf.writeVarInt(getAmount());
            buf.writeCompoundTag(getTag());
        } else {
            super.writeToPacket(buf);
        }
    }

    //@Override //Turns out it is because you can't override static classes. Thanks, java.
    public static PseudoFluidStack readFromPacket(PacketBuffer buf) {
        boolean fake = buf.readBoolean();
        if (fake) {
            ResourceLocation name = buf.readResourceLocation();
            int amount = buf.readVarInt();
            CompoundNBT tag = buf.readCompoundTag();
            return new PseudoFluidStack(name, amount, tag);
        } else {
            return new PseudoFluidStack(FluidStack.readFromPacket(buf));
        }
    }

    public ResourceLocation getPseudoFluid() {
        return isEmpty() ? Fluids.EMPTY.getRegistryName() : name;
    }

    private static Fluid createFluid(ResourceLocation location) {
        if (PseudoFluidUtil.fluidRegistryToAttributes.containsKey(location)) {
            return Fluids.WATER;
        } else if (ForgeRegistries.FLUIDS.containsKey(location)) {
            return ForgeRegistries.FLUIDS.getValue(location);
        } else {
            return null;
        }
    }

    @Override
    public PseudoFluidStack copy() {
        return new PseudoFluidStack(getPseudoFluid(), getAmount(), getTag());
    }

    @Override
    public boolean isFluidEqual(@Nonnull FluidStack other) {
        if (other instanceof PseudoFluidStack) {
            return getPseudoFluid().equals(((PseudoFluidStack) other).getPseudoFluid());
        } else {
            if (isFake) return false; //A fake and a real fluid can never be equal. Such are the rules.
            return super.isFluidEqual(other);
        }
    }

    protected boolean isFluidStackTagEqual(FluidStack other) {
        return getTag() == null ? other.getTag() == null : other.getTag() != null && getTag().equals(other.getTag());
    }

    public FluidAttributes getAttributes() {
        if (isFake) {
            return PseudoFluidUtil.fluidRegistryToAttributes.get(name);
        } else {
            return getFluid().getAttributes();
        }
    }
}
