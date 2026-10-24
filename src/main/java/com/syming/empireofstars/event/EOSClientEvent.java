package com.syming.empireofstars.event;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.client.EOSKeybinding;
import com.syming.empireofstars.network.ActiveLightsaber;
import com.syming.empireofstars.network.EOSPacketHandler;
import com.syming.empireofstars.world.dimension.TatooineEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EmpireOfStarsMod.MOD_ID, value = Dist.CLIENT)
public class EOSClientEvent {

    @SubscribeEvent
    public static void onKeybindingPressed(InputEvent.Key event) {
        if (event.getAction() == 1) {
            if (event.getKey() == EOSKeybinding.INSTANCE.ACTIVE_LIGHTSABER.getKey().getValue()) {
                EOSPacketHandler.sendToServer(new ActiveLightsaber());
            }
        }
    }

    @SubscribeEvent
    public static void registerEffects(RegisterDimensionSpecialEffectsEvent event) {
        event.register(new ResourceLocation(EmpireOfStarsMod.MOD_ID, "tatooine_effects"), new TatooineEffects());
    }

}
