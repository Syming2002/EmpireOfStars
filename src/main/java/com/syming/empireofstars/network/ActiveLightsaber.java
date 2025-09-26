package com.syming.empireofstars.network;

import com.syming.empireofstars.item.lightsaber.LightsaberItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ActiveLightsaber {

    public  ActiveLightsaber() {}

    public void encode(FriendlyByteBuf friendlyByteBuf) {

    }

    public static ActiveLightsaber decode(FriendlyByteBuf friendlyByteBuf) {
        return new ActiveLightsaber();
    }

    public static void handle(ActiveLightsaber message, final Supplier<NetworkEvent.Context> context) {
        Player player = context.get().getSender();
        ItemStack mainStack = player.getMainHandItem();
        ItemStack offStack = player.getOffhandItem();

        if (!mainStack.isEmpty()) {
            if (mainStack.getItem() instanceof LightsaberItem) {
                mainStack.getOrCreateTag().putBoolean("active", !mainStack.getOrCreateTag().getBoolean("active"));
            }
        }

        if (!offStack.isEmpty()) {
            if (offStack.getItem() instanceof LightsaberItem) {
                offStack.getOrCreateTag().putBoolean("active", !offStack.getOrCreateTag().getBoolean("active"));
            }
        }
    }
}
