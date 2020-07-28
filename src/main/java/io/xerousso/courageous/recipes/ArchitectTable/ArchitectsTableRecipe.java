package io.xerousso.courageous.recipes.ArchitectTable;

import io.xerousso.courageous.blocks.ModBlocks;
import io.xerousso.courageous.recipes.ModRecipes;
import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class ArchitectsTableRecipe extends SingleItemRecipe {

    public static final IRecipeType<ArchitectsTableRecipe> recipeType = IRecipeType.register("courageous_architect");

    public ArchitectsTableRecipe(ResourceLocation id, String group, Ingredient input, ItemStack output) {
        super(recipeType, ModRecipes.ARCHITECT.get(), id, group, input, output);
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return this.ingredient.test(inv.getStackInSlot(0));
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.ARCHITECTS_TABLE);
    }

    public static class Serializer<T extends ArchitectsTableRecipe> extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {
        final ArchitectsTableRecipe.Serializer.IRecipeFactory<T> factory;

        public Serializer(ArchitectsTableRecipe.Serializer.IRecipeFactory<T> factory) {
            this.factory = factory;
        }

        public T read(ResourceLocation recipeId, JsonObject json) {
            String s = JSONUtils.getString(json, "group", "");
            Ingredient ingredient;
            if (JSONUtils.isJsonArray(json, "ingredient")) {
                ingredient = Ingredient.deserialize(JSONUtils.getJsonArray(json, "ingredient"));
            } else {
                ingredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "ingredient"));
            }

            String s1 = JSONUtils.getString(json, "result");
            int i = JSONUtils.getInt(json, "count");
            ItemStack itemstack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(s1)), i);
            return this.factory.create(recipeId, s, ingredient, itemstack);
        }

        public T read(ResourceLocation recipeId, PacketBuffer buffer) {
            String s = buffer.readString(32767);
            Ingredient ingredient = Ingredient.read(buffer);
            ItemStack itemstack = buffer.readItemStack();
            return this.factory.create(recipeId, s, ingredient, itemstack);
        }

        public void write(PacketBuffer buffer, T recipe) {
            buffer.writeString(recipe.group);
            recipe.ingredient.write(buffer);
            buffer.writeItemStack(recipe.result);
        }

        public interface IRecipeFactory<T extends ArchitectsTableRecipe> {
            T create(ResourceLocation p_create_1_, String p_create_2_, Ingredient p_create_3_, ItemStack p_create_4_);
        }
    }
}
