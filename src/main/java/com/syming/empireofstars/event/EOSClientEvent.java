package com.syming.empireofstars.event;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.client.EOSKeybinding;
import com.syming.empireofstars.item.lightsaber.LightsaberItem;
import com.syming.empireofstars.network.ActiveLightsaber;
import com.syming.empireofstars.world.dimension.TatooineEffects;
import com.syming.empireofstars.world.dimension.TatooineOrbitEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = EmpireOfStarsMod.MOD_ID, value = Dist.CLIENT)
public class EOSClientEvent {

    @SubscribeEvent
    public static void onKeybindingPressed(InputEvent.Key event) {
        if (event.getAction() == 1) {
            if (event.getKey() == EOSKeybinding.INSTANCE.ACTIVE_LIGHTSABER.getKey().getValue()) {
                Minecraft minecraft = Minecraft.getInstance();

                if (minecraft.player != null) {

                    PacketDistributor.sendToServer(new ActiveLightsaber());
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerEffects(RegisterDimensionSpecialEffectsEvent event) {
        event.register(ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "tatooine_orbit_effects"), new TatooineOrbitEffects());
        event.register(ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "tatooine_effects"), new TatooineEffects());
    }

}
