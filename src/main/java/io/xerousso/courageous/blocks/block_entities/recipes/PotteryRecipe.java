package io.xerousso.courageous.blocks.block_entities.recipes;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import io.xerousso.courageous.blocks.block_entities.PotteryInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;

public class PotteryRecipe implements Recipe<PotteryInventory> {

    private final int clay;

    private final HashMap<Item, Ingredient> outputs;
    private final int time;

    private final Identifier id;

    public PotteryRecipe(int clay, int time, HashMap<Item, Ingredient> outputs, Identifier id) {
        this.clay = clay;

        this.outputs = outputs;
        this.time = time;

        this.id = id;
    }

    public Ingredient getOutput(Item dye) {
        if (outputs.containsKey(dye)) return outputs.get(dye);
        if (outputs.containsKey(Items.AIR)) return outputs.get(Items.AIR);
        return Ingredient.EMPTY;
    }

    public HashMap<Item, Ingredient> getOutputs() {
        return outputs;
    }

    public int getClay() {
        return clay;
    }

    public int getTime() {
        return time;
    }


    @Override
    public boolean matches(PotteryInventory inv, World world) {
        return inv.size() >= 2;
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    public static class Serializer implements RecipeSerializer<PotteryRecipe> {

        @Override
        public PotteryRecipe read(Identifier id, JsonObject json) {
            PotteryRecipeJSONFormat recipeJSON = new Gson().fromJson(json, PotteryRecipeJSONFormat.class);

            if (recipeJSON.results == null) throw new JsonSyntaxException("Pottery recipe \"" + id.toString() + "\" missing \"results\" JSON attribute.");
            if (recipeJSON.revolutions <= 0) throw new JsonSyntaxException("Pottery recipe \"" + id.toString() + "\" missing \"revolutions\" JSON attribute or \"revolutions\" JSON attribute is set to 0 or less.");
            if (recipeJSON.clay <= 0) throw new JsonSyntaxException("Pottery recipe \"" + id.toString() + "\" missing \"clay\" JSON attribute or \"clay\" JSON attribute is set to 0 or less.");

            HashMap<Item, Ingredient> outputs = new HashMap<>();

            boolean containsEmpty = false;
            for (Entry<String, JsonElement> object : recipeJSON.results.entrySet()) {
                AtomicReference<Item> item = new AtomicReference<>(Items.AIR);
                Registry.ITEM.getOrEmpty(new Identifier(object.getKey())).ifPresent(item::set);

                if (item.get() == Items.AIR) containsEmpty = true;
                outputs.put(item.get(), Ingredient.fromJson(object.getValue()));
            }
            if (!containsEmpty) throw new JsonSyntaxException("Pottery recipe \"" + id.toString() + "\" missing empty \"\" result asstribute.");

            return new PotteryRecipe(recipeJSON.clay, recipeJSON.revolutions, outputs, id);
        }

        @Override
        public void write(PacketByteBuf buf, PotteryRecipe recipe) {
            buf.writeInt(recipe.getClay());
            buf.writeInt(recipe.getTime());

            buf.writeInt(recipe.getOutputs().size());
            for (Entry<Item, Ingredient> entry : recipe.getOutputs().entrySet()) {
                buf.writeItemStack(new ItemStack(entry.getKey()));
                entry.getValue().write(buf);
            }
        }

        @Override
        public PotteryRecipe read(Identifier id, PacketByteBuf buf) {
            int clay = buf.readInt();
            int time = buf.readInt();

            int size = buf.readInt();
            HashMap<Item, Ingredient> map = new HashMap<>();
            for (int i = 0; i < size; i++) {
                map.put(buf.readItemStack().getItem(), Ingredient.fromPacket(buf));
            }

            return new PotteryRecipe(clay, time, map, id);
        }

        class PotteryRecipeJSONFormat {
            int clay;
            int revolutions;
            JsonObject results;
        }

        public static final Serializer INSTANCE = new Serializer();

    }

    @Override
    public RecipeType<?> getType() {
        return Recipes.POTTERY_TYPE;
    }

    @Override
    public Identifier getId() {
        return id;
    }


    // Unused
    @Override
    public ItemStack getOutput() {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack craft(PotteryInventory inv) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

}
