package io.xerousso.courageous.blocks.block_entities.recipes;

import io.xerousso.courageous.Courageous;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Recipes {

    public static final RecipeType<PotteryRecipe> POTTERY_TYPE = registerRecipeType("pottery");
    public static final RecipeSerializer<PotteryRecipe> POTTERY_SERIALIZER = registerRecipeSerializer("pottery", PotteryRecipe.Serializer.INSTANCE);

    private static <T extends Recipe<?>> RecipeType<T> registerRecipeType(String name) {
        return Registry.register(Registry.RECIPE_TYPE, new Identifier(Courageous.MOD_ID, name), new RecipeType<T>() {
            @Override
            public String toString() {
                return new Identifier(Courageous.MOD_ID, name).toString();
            }
        });
    }

    private static <T extends Recipe<?>> RecipeSerializer<T> registerRecipeSerializer(String name, RecipeSerializer<T> type) {
        return Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Courageous.MOD_ID, name), type);
    }

    public static void init() {}

}
