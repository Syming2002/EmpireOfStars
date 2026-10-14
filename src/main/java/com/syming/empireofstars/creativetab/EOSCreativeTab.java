package com.syming.empireofstars.creativetab;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.item.lightsaber.LightsaberItem;
import com.syming.empireofstars.item.lightsaber.LightsaberType;
import com.syming.empireofstars.item.lightsaber.LightsaberTypeBlade;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class EOSCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EmpireOfStarsMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> EMPIREOFSTARS_TAB = CREATIVE_MODE_TAB.register("empireofstars_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> {
                        for (LightsaberType type : LightsaberType.values()) {
                            ItemStack standard = new ItemStack(EOSItem.LIGHTSABER_TYPES.get(type).get());
                            LightsaberItem.setKyberColor(standard, KyberCrystalColor.BLUE);
                            standard.setTag(new CompoundTag());
                            standard.getTag().putString("kybercolor", KyberCrystalColor.BLUE.getSerializedName());
                            standard.getTag().putString("type", LightsaberTypeBlade.SINGLE.toString());
                            standard.getTag().putBoolean("active", true);
                            return standard;
                        }
                        return null;
                    }
                    )
                    .title(Component.translatable("creativetab.empireofstars_tab"))

                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(EOSBlock.SARLACC_SPEAR_TOOTH.get());
                        output.accept(EOSBlock.TATOOINE_SAND.get());
                        output.accept(EOSBlock.TATOOINE_SANDSTONE.get());

                        for (KyberCrystalColor kybercolor : KyberCrystalColor.values()) {
                            for (int shardCount = 1; shardCount <= 5; shardCount++) {
                                ItemStack kyberCrystal = new ItemStack(EOSBlock.KYBER_CRYSTAL_TYPES.get(kybercolor).get());

                                CompoundTag tag = new CompoundTag();
                                tag.putString("kybercolor", kybercolor.getSerializedName());
                                tag.putInt("shards", shardCount);

                                kyberCrystal.setTag(tag);

                                output.accept(kyberCrystal);
                            }

                            ItemStack kyberShard = new ItemStack(EOSItem.KYBER_CRYSTAL_SHARD_TYPES.get(kybercolor).get());
                            CompoundTag tag = new CompoundTag();
                            tag.putString("kybercolor", kybercolor.getSerializedName());
                            kyberShard.setTag(tag);
                            output.accept(kyberShard);


                            for (LightsaberType type : LightsaberType.values()) {
                                ItemStack standard = new ItemStack(EOSItem.LIGHTSABER_TYPES.get(type).get());
                                standard.setTag(new CompoundTag());
                                standard.getTag().putString("kybercolor", kybercolor.getSerializedName());
                                standard.getTag().putString("type", LightsaberTypeBlade.SINGLE.toString());
                                standard.getTag().putBoolean("active", false);
                                output.accept(standard);
                            }
                        }

                        // Spawn Eggs
                        for (RegistryObject<Item> itemRegistryObject : EOSItem.ITEMS.getEntries()) {
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
