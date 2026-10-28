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
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class EOSItem {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EmpireOfStarsMod.MOD_ID);

    public static final Map<LightsaberType, Supplier<LightsaberEmptyItem>> EMPTY_LIGHTSABER_TYPES = new HashMap<>();
    public static final Map<LightsaberType, Supplier<LightsaberItem>> LIGHTSABER_TYPES = new HashMap<>();


    static {
        for (LightsaberType type : LightsaberType.values()) {
            EMPTY_LIGHTSABER_TYPES.put(type, ITEMS.register(type.getSerializedName() + '_' + "empty_lightsaber", LightsaberEmptyItem::new));
            LIGHTSABER_TYPES.put(type, ITEMS.register(type.getSerializedName() + '_' + "lightsaber", LightsaberItem::new));
        }
    }

    public static final Supplier<KyberCrystalShardItem> KYBER_CRYSTAL_SHARD = ITEMS.register("kyber_crystal_shard", KyberCrystalShardItem::new);

    // Bucket Items
    public static final Supplier<Item> SARLACC_STOMACH_ACID_BUCKET = ITEMS.register("sarlacc_stomach_acid_bucket",
            () -> new BucketItem(EOSFluid.SOURCE_SARLACC_STOMACH_ACID.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));


    // Spawn Egg

    public static final Supplier<SpawnEggItem> BANTHA_SPAWN_EGG = ITEMS.register("bantha_spawn_egg",
            () -> new SpawnEggItem(
                    EOSEntity.BANTHA.get(),
                    0x4B331B,
                    0x7A502A,
                    new Item.Properties()));

    public static final Supplier<SpawnEggItem> SARLACC_BEAK_SPAWN_EGG = ITEMS.register("sarlacc_beak_spawn_egg",
            () -> new SpawnEggItem(
                    EOSEntity.SARLACC_BEAK.get(),
                    0xB1A688,
                    0xD1908E,
                    new Item.Properties()));

    public static final Supplier<SpawnEggItem> SARLACC_TENTACLE_SPAWN_EGG = ITEMS.register("sarlacc_tentacle_spawn_egg",
            () -> new SpawnEggItem(
                    EOSEntity.SARLACC_TENTACLE.get(),
                    0xB1A688,
                    0x766F5B,
                    new Item.Properties()));

    public static final Supplier<SpawnEggItem> TUSKEN_RAIDER_SPAWN_EGG = ITEMS.register("tusken_raider_spawn_egg",
            () -> new SpawnEggItem(
                    EOSEntity.RAIDER_TUSKEN.get(),
                    0xB59D7D,
                    0x705E41,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
