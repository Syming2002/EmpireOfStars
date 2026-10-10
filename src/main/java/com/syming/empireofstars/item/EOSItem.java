package com.syming.empireofstars.item;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.entity.EOSEntity;
import com.syming.empireofstars.item.lightsaber.LightsaberItem;
import com.syming.empireofstars.item.lightsaber.LightsaberType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
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

    public static final Map<KyberCrystalColor, RegistryObject<KyberCrystalShardItem>> KYBER_CRYSTAL_SHARD_TYPES = new HashMap<>();

    static {
        for (KyberCrystalColor kybercolor : KyberCrystalColor.values()) {
            KYBER_CRYSTAL_SHARD_TYPES.put(kybercolor, ITEMS.register(kybercolor.name().toLowerCase(Locale.ROOT) + '_' + "kyber_crystal_shard", KyberCrystalShardItem::new));
        }
    }


    // Spawn Egg

    public static final RegistryObject<SpawnEggItem> BANTHA_SPAWN_EGG = ITEMS.register("bantha_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    EOSEntity.BANTHA,
                    0x4B331B,
                    0x7A502A,
                    new Item.Properties()));

    public static final RegistryObject<SpawnEggItem> SARLACC_BEAK_SPAWN_EGG = ITEMS.register("sarlacc_beak_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    EOSEntity.SARLACC_BEAK,
                    0xB1A688,
                    0xD1908E,
                    new Item.Properties()));

    public static final RegistryObject<SpawnEggItem> SARLACC_TENTACLE_SPAWN_EGG = ITEMS.register("sarlacc_tentacle_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    EOSEntity.SARLACC_TENTACLE,
                    0xB1A688,
                    0x766F5B,
                    new Item.Properties()));

    public static final RegistryObject<SpawnEggItem> TUSKEN_RAIDER_SPAWN_EGG = ITEMS.register("tusken_raider_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    EOSEntity.RAIDER_TUSKEN,
                    0xB59D7D,
                    0x705E41,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
