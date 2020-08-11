package io.xerousso.courageous.recipes;


import io.xerousso.courageous.recipes.distiller.DistillerRecipe;
import io.xerousso.courageous.util.Util;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Recipez {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Util.MOD_ID);

    //    public static final RegistryObject<IRecipeSerializer<ArchitectsTableRecipe>> ARCHITECT = ModRecipes.RECIPE_SERIALIZERZ.register("architect", () -> new ArchitectsTableRecipe.Serializer<>(ArchitectsTableRecipe::new));
    public static final RegistryObject<IRecipeSerializer<DistillerRecipe>> DISTILLER = Recipez.RECIPE_SERIALIZERS.register("distiller", () -> new DistillerRecipe.Serializer<>(DistillerRecipe::new));

}
