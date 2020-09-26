package io.xerousso.courageous.networking;

import io.netty.buffer.Unpooled;
import io.xerousso.courageous.Courageous;
import io.xerousso.courageous.blocks.block_entities.PotteryWheelBlockEntity;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PotteryPacket implements Packet {

    @Override
    public void handle(PacketContext context, PacketByteBuf byteBuf) {
        String selectedRecipe = byteBuf.readString();
        BlockPos blockEntityPos = byteBuf.readBlockPos();

        context.getTaskQueue().execute(() -> {
            World world = context.getPlayer().getEntityWorld();
            BlockEntity blockEntity = world.getBlockEntity(blockEntityPos);

            if (blockEntity instanceof PotteryWheelBlockEntity && world.getRecipeManager().get(new Identifier(selectedRecipe)).isPresent())
                ((PotteryWheelBlockEntity) blockEntity).setSelectedRecipe(new Identifier(selectedRecipe));
        });
    }

    @Override
    public Identifier getID() {
        return new Identifier(Courageous.MOD_ID, "pottery_wheel_sync");
    }

    public void sendToServer(Identifier recipe, BlockPos pos) {
        PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
        data.writeString(recipe.toString());
        data.writeBlockPos(pos);

        sendToServer(data);
    }

    public void sendToClient(Identifier recipe, World world, BlockPos pos) {
        PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
        data.writeString(recipe.toString());
        data.writeBlockPos(pos);

        sendToClients(world, pos, data);
    }

}
