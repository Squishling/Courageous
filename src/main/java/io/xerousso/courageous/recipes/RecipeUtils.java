//package io.xerousso.courageous.recipes;
//
//import io.xerousso.courageous.util.pseudofluids.PseudoFluidStack;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonSyntaxException;
//import net.minecraft.fluid.Fluid;
//import net.minecraft.util.JSONUtils;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fluids.FluidStack;
//import net.minecraftforge.registries.ForgeRegistries;
//
//public class RecipeUtils {
//
//    public static FluidStack getFluidStack(JsonObject json, String id) {
//        JsonObject fluidJson = JSONUtils.getJsonObject(json, id);
//        ResourceLocation fluidRS = ResourceLocation.create(JSONUtils.getString(fluidJson, "fluid", "minecraft:empty"), ':');
//        int amount = JSONUtils.getInt(fluidJson, "amount", 1000);
//        Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidRS);
//
//        if (fluid == null) {
//            throw new JsonSyntaxException("Unknown fluid '" + fluidRS + "'");
//        }
//
//        return new FluidStack(fluid, amount);
//    }
//
//    public static PseudoFluidStack getPseudoFluidStack(JsonObject json, String id) {
//        JsonObject fluidJson = JSONUtils.getJsonObject(json, id);
//        ResourceLocation fluidRS = ResourceLocation.create(JSONUtils.getString(fluidJson, "fluid", "minecraft:empty"), ':');
//        int amount = JSONUtils.getInt(fluidJson, "amount", 1000);
//
//        return new PseudoFluidStack(fluidRS, amount);
//    }
//}
