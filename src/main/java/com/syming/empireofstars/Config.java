package com.syming.empireofstars;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@EventBusSubscriber(modid = EmpireOfStarsMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Config {

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
    }
}
