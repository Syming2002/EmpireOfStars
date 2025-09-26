package com.syming.empireofstars.network;

import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class EOSPacketHandler {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(EmpireOfStarsMod.MOD_ID, "main"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals
    );

    private static int id;

    public static synchronized void register() {
        INSTANCE.messageBuilder(ActiveLightsaber.class, id++)
                .encoder(ActiveLightsaber::encode)
                .decoder(ActiveLightsaber::decode)
                .consumerMainThread(ActiveLightsaber::handle)
                .add();
    }

    public static void sendToPlayer(Object message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static void sendToServer(Object message) {
        INSTANCE.sendToServer(message);
    }

}
