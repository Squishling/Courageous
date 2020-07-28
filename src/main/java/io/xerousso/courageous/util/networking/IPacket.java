package io.xerousso.courageous.util.networking;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

public interface IPacket {

    void toBytes(PacketBuffer packetBuffer);

    void handle(Supplier<Context> context);

}
