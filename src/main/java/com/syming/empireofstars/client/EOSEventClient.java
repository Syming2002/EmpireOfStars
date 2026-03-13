package com.syming.empireofstars.client;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.item.EOSItem;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class EOSEventClient {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onModelRegistry(ModelRegistryEvent event) {
        for (Item item : EOSItem.ITEMS) {
            EmpireOfStarsMod.proxy.registerItemRenderer(item, 0, "inventory");
        }


    }


}
