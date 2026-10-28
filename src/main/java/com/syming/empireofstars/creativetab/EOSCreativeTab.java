package com.syming.empireofstars.creativetab;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.item.lightsaber.LightsaberItem;
import com.syming.empireofstars.item.lightsaber.LightsaberType;
import com.syming.empireofstars.item.lightsaber.LightsaberTypeBlade;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EOSCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EmpireOfStarsMod.MOD_ID);

    public static final Supplier<CreativeModeTab> EMPIREOFSTARS_TAB = CREATIVE_MODE_TAB.register("empireofstars_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> {
                                ItemStack standard = new ItemStack(EOSItem.LIGHTSABER_TYPES.get(LightsaberType.STANDARD).get());

                                KyberCrystalColor.setKyberColorNameFromStack(standard, KyberCrystalColor.BLUE);
                                LightsaberItem.setBladeTypeFromStack(standard, LightsaberTypeBlade.SINGLE.getSerializedName());
                                LightsaberItem.setActiveFromStack(standard, true);
                                return standard;
                            }
                    )
                    .title(Component.translatable("creativetab.empireofstars_tab"))

                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(EOSBlock.SARLACC_SPEAR_TOOTH.get());
                        output.accept(EOSBlock.TATOOINE_SAND.get());
                        output.accept(EOSBlock.TATOOINE_SANDSTONE.get());
                        output.accept(EOSBlock.SARLACC_STOMACH_ACID_OUTPUT.get());

                        for (KyberCrystalColor kybercolor : KyberCrystalColor.values()) {
                            for (int shardCount = 1; shardCount <= 5; shardCount++) {
                                ItemStack kyberCrystal = new ItemStack(EOSBlock.KYBER_CRYSTAL_TYPES.get(kybercolor).get());

                                int finalShardCount = shardCount;
                                KyberCrystalColor.setKyberColorNameFromStack(kyberCrystal, kybercolor);
                                KyberCrystalColor.setShardCountFromStack(kyberCrystal, finalShardCount);

                                output.accept(kyberCrystal);
                            }

                            ItemStack kyberShard = new ItemStack(EOSItem.KYBER_CRYSTAL_SHARD.get());

                            KyberCrystalColor.setKyberColorCodeFromStack(kyberShard, kybercolor.getKyberColor());
                            output.accept(kyberShard);


                            for (LightsaberType type : LightsaberType.values()) {
                                output.accept(EOSItem.EMPTY_LIGHTSABER_TYPES.get(type).get());

                                ItemStack lightsaber = new ItemStack(EOSItem.LIGHTSABER_TYPES.get(type).get());

                                KyberCrystalColor.setKyberColorCodeFromStack(lightsaber, kybercolor.getKyberColor());
                                LightsaberItem.setBladeTypeFromStack(lightsaber, type.getSerializedName());
                                LightsaberItem.setActiveFromStack(lightsaber, false);

                                output.accept(lightsaber);
                            }
                        }

                        // Spawn Eggs
                        for (DeferredHolder<Item, ? extends Item> itemRegistryObject : EOSItem.ITEMS.getEntries()) {
                            if (itemRegistryObject.get() instanceof SpawnEggItem spawnEggItem) {
                                output.accept(spawnEggItem);
                            }
                        }

                        // Bucket Items
                        output.accept(EOSItem.SARLACC_STOMACH_ACID_BUCKET.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
