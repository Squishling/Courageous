package io.xerousso.courageous.blocks.block_entities;

import io.xerousso.courageous.client.screens.PotteryWheelScreenHandler;
import io.xerousso.courageous.networking.Packets;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Tickable;
import org.jetbrains.annotations.Nullable;

public class PotteryWheelBlockEntity extends BlockEntity implements Tickable, BlockEntityClientSerializable, NamedScreenHandlerFactory {

    private Identifier selectedRecipe;

    private PotteryInventory inventory = new PotteryInventory();

    public PotteryWheelBlockEntity() {
        super(BlockEntities.POTTERY_WHEEL_BLOCK_ENTITY);
    }

    @Override
    public void tick() {

    }

    public void setSelectedRecipe(Identifier selectedRecipe) {
        this.selectedRecipe = selectedRecipe;

        if (world.isClient) Packets.POTTERY.sendToServer(selectedRecipe, pos);
        else Packets.POTTERY.sendToClient(selectedRecipe, world, pos);
    }

    public Identifier getSelectedRecipe() {
        return selectedRecipe;
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText("gui.courageous.pottery_wheel");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new PotteryWheelScreenHandler(syncId, inv, inventory);
    }

    // NBT
    // Server
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);

        tag.put("inventory", inventory.serialize());
        tag.putString("selectedRecipe", selectedRecipe.toString());

        return tag;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);

        if (tag.contains("inventory")) inventory.deserialize((CompoundTag) tag.get("inventory"));
        if (tag.contains("selectedRecipe")) selectedRecipe = new Identifier(tag.get("selectedRecipe").asString());
    }

    // Client
    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
//        tag.putString("selectedRecipe", selectedRecipe.toString());

        return tag;
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        if (tag.contains("selectedRecipe")) selectedRecipe = new Identifier(tag.get("selectedRecipe").asString());
    }

}
