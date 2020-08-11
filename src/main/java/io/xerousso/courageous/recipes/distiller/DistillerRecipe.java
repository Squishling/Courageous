package io.xerousso.courageous.recipes.distiller;

import com.google.gson.JsonObject;
import io.xerousso.courageous.recipes.RecipeUtils;
import io.xerousso.courageous.recipes.Recipez;
import io.xerousso.courageous.recipes.wrappers.FluidRecipeWrapper;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class DistillerRecipe implements IRecipe<FluidRecipeWrapper> {
    public static final IRecipeType<DistillerRecipe> recipeType = IRecipeType.register("courageous_distiller");

    public final String group;
    public final ResourceLocation id;
    public final FluidStack input;
    public final FluidStack outputFlow;
    public final FluidStack outputResidue;
    public final int recipeCycles;

    public DistillerRecipe(ResourceLocation id, String group, FluidStack input, FluidStack outputFlow, FluidStack outputResidue, int recipeCycles) {
        this.group = group;
        this.id = id;
        this.input = input;
        this.outputFlow = outputFlow;
        this.outputResidue = outputResidue;
        this.recipeCycles = recipeCycles;
    }

    @Override
    public boolean matches(FluidRecipeWrapper inv, World worldIn) {
        return inv.getFluidInTank(0).isFluidEqual(input);
    }

    public FluidStack getOutputFlowPerCycle(FluidRecipeWrapper inv, World worldIn) {
        Fluid fluid = outputFlow.getFluid();
        float multiplier = (float) outputFlow.getAmount() / input.getAmount() / recipeCycles;
        int amount = (int) (multiplier * inv.getFluidInTank(0).getAmount());
        return new FluidStack(fluid, amount);
    }

    public FluidStack getOutputResidue(FluidRecipeWrapper inv, World worldIn) {
        Fluid fluid = outputResidue.getFluid();
        float multiplier = (float) outputResidue.getAmount() / input.getAmount();
        int amount = (int) (multiplier * inv.getFluidInTank(0).getAmount());
        return new FluidStack(fluid, amount);
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return Recipez.DISTILLER.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return recipeType;
    }

    public static class Serializer<T extends DistillerRecipe> extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {
        final DistillerRecipe.Serializer.IRecipeFactory<T> factory;

        public Serializer(DistillerRecipe.Serializer.IRecipeFactory<T> factory) {
            this.factory = factory;
        }

        public T read(ResourceLocation recipeId, JsonObject json) {
            String group = JSONUtils.getString(json, "group", "");
            FluidStack input = RecipeUtils.getFluidStack(json, "input");
            FluidStack outputFlow = RecipeUtils.getFluidStack(json, "outputFlow");
            FluidStack outputResidue = RecipeUtils.getFluidStack(json, "outputResidue");
            int cycles = JSONUtils.getInt(json, "cycles");
            return this.factory.create(recipeId, group, input, outputFlow, outputResidue, cycles);
        }

        public T read(ResourceLocation recipeId, PacketBuffer buffer) {
            String group = buffer.readString();

            FluidStack input = FluidStack.readFromPacket(buffer);
            FluidStack outputFlow = FluidStack.readFromPacket(buffer);
            FluidStack outputResidue = FluidStack.readFromPacket(buffer);
            int cycles = buffer.readInt();
            return this.factory.create(recipeId, group, input, outputFlow, outputResidue, cycles);
        }

        public void write(PacketBuffer buffer, T recipe) {
            buffer.writeString(recipe.group);
            buffer.writeFluidStack(recipe.input);
            buffer.writeFluidStack(recipe.outputFlow);
            buffer.writeFluidStack(recipe.outputResidue);
            buffer.writeInt(recipe.recipeCycles);
        }

        public interface IRecipeFactory<T extends DistillerRecipe> {
            T create(ResourceLocation id, String group, FluidStack input, FluidStack outputFlow, FluidStack outputResidue, int cycles);
        }
    }

    //Unused item recipe methods

    @Override
    public ItemStack getCraftingResult(FluidRecipeWrapper inv) {
        return null;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }
}
