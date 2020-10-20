package io.xerousso.courageous.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;

public class Packets {

    public static final PotteryPacket POTTERY = new PotteryPacket();

    @Environment(EnvType.CLIENT)
    public static void registerPacketsClient() {
        registerPacketClient(POTTERY);
    }

    public static void registerPacketsCommon() {
        registerPacketServer(POTTERY);
    }

    private static void registerPacketServer(Packet packet) {
        ServerSidePacketRegistry.INSTANCE.register(packet.getID(), packet.getConsumer());
    }

    private static void registerPacketClient(Packet packet) {
        ClientSidePacketRegistry.INSTANCE.register(packet.getID(), packet.getConsumer());
    }

}
