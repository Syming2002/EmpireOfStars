package com.syming.empireofstars.item;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class EOSItem {

    public static List<Item> ITEMS = new ArrayList<>();
    private static IForgeRegistry<Item> itemRegistry;

    @SubscribeEvent
    // Register items here (Remove if not needed)
    public void registerItems(RegistryEvent.Register<Item> event) {
        itemRegistry = event.getRegistry();
        itemRegistry.registerAll(EOSItem.ITEMS.toArray(new Item[0]));
    }
}
