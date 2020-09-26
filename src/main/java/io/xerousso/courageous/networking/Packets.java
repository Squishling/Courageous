package io.xerousso.courageous.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;

public class Packets {

    private static Packet pottery = new PotteryPacket();

    @Environment(EnvType.CLIENT)
    public static void registerPacketsClient() {
        registerPacketClient(pottery);
    }

    public static void registerPacketsCommon() {
        registerPacketServer(pottery);
    }

    private static void registerPacketServer(Packet packet) {
        ServerSidePacketRegistry.INSTANCE.register(packet.getID(), packet.getConsumer());
    }

    private static void registerPacketClient(Packet packet) {
        ClientSidePacketRegistry.INSTANCE.register(packet.getID(), packet.getConsumer());
    }

}
