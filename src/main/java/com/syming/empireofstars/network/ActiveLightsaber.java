package com.syming.empireofstars.network;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.item.lightsaber.LightsaberItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.function.Supplier;

public record ActiveLightsaber() implements CustomPacketPayload {

    public static final Type<ActiveLightsaber> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "active_lightsaber"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ActiveLightsaber> STREAM_CODEC = StreamCodec.unit(new ActiveLightsaber());

    public void execute(IPayloadContext context) {
        Player player = context.player();
        context.enqueueWork(
                () -> {
                    ItemStack mainStack = player.getMainHandItem();

                    if (!mainStack.isEmpty()) {
                        if (mainStack.getItem() instanceof LightsaberItem) {
                           LightsaberItem.setActiveFromStack(mainStack, !LightsaberItem.isActiveFromStack(mainStack));
                        }
                    }
                }
        );
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
