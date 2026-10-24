package com.syming.empireofstars.item;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.entity.EOSEntity;
import com.syming.empireofstars.fluid.EOSFluid;
import com.syming.empireofstars.item.lightsaber.LightsaberEmptyItem;
import com.syming.empireofstars.item.lightsaber.LightsaberItem;
import com.syming.empireofstars.item.lightsaber.LightsaberType;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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

    public static final Map<LightsaberType, RegistryObject<LightsaberEmptyItem>> EMPTY_LIGHTSABER_TYPES = new HashMap<>();
    public static final Map<LightsaberType, RegistryObject<LightsaberItem>> LIGHTSABER_TYPES = new HashMap<>();


    static {
        for (LightsaberType type : LightsaberType.values()) {
            EMPTY_LIGHTSABER_TYPES.put(type, ITEMS.register(type.getSerializedName() + '_' + "empty_lightsaber", LightsaberEmptyItem::new));
            LIGHTSABER_TYPES.put(type, ITEMS.register(type.getSerializedName() + '_' + "lightsaber", LightsaberItem::new));
        }
    }

    public static final RegistryObject<KyberCrystalShardItem> KYBER_CRYSTAL_SHARD = ITEMS.register("kyber_crystal_shard", KyberCrystalShardItem::new);

    // Bucket Items
    public static final RegistryObject<Item> SARLACC_STOMACH_ACID_BUCKET = ITEMS.register("sarlacc_stomach_acid_bucket",
            () -> new BucketItem(EOSFluid.SOURCE_SARLACC_STOMACH_ACID.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));


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
