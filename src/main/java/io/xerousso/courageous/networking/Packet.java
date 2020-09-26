package io.xerousso.courageous.networking;

import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.fabric.api.server.PlayerStream;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.stream.Stream;

public interface Packet {

    void handle(PacketContext context, PacketByteBuf byteBuf);

    Identifier getID();

    default PacketConsumer getConsumer() {
        return this::handle;
    }

    default void sendToClients(World world, BlockPos pos, PacketByteBuf packetByteBuf) {
        PlayerStream.watching(world, pos).forEach(playerEntity -> ServerSidePacketRegistry.INSTANCE.sendToPlayer(playerEntity, getID(), packetByteBuf));
    }

    default void sendToServer(PacketByteBuf packetByteBuf) {
        ClientSidePacketRegistry.INSTANCE.sendToServer(getID(), packetByteBuf);
    }

}
