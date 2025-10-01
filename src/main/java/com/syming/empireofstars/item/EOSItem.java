package com.syming.empireofstars.item;

import com.mojang.datafixers.util.Pair;
import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.item.lightsaber.LightsaberItem;
import com.syming.empireofstars.item.lightsaber.LightsaberKey;
import com.syming.empireofstars.item.lightsaber.LightsaberType;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EOSItem {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EmpireOfStarsMod.MOD_ID);

    public static final Map<LightsaberType, RegistryObject<LightsaberItem>> LIGHTSABER_TYPES = new HashMap<>();

    static {
        for (LightsaberType type : LightsaberType.values()) {
            LIGHTSABER_TYPES.put(type, ITEMS.register(type.name().toLowerCase(Locale.ROOT) + '_' + "lightsaber", LightsaberItem::new));
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
