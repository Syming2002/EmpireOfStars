package com.syming.empireofstars.event;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.client.EOSKeybinding;
import com.syming.empireofstars.item.lightsaber.LightsaberItem;
import com.syming.empireofstars.network.ActiveLightsaber;
import com.syming.empireofstars.network.EOSPacketHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
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

}
