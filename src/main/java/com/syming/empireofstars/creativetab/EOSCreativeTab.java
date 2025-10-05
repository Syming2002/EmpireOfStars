package com.syming.empireofstars.creativetab;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalBlockKey;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.item.lightsaber.LightsaberItem;
import com.syming.empireofstars.item.lightsaber.LightsaberType;
import com.syming.empireofstars.item.lightsaber.LightsaberTypeBlade;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
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
                            LightsaberItem.setColor(standard, KyberCrystalColor.BLUE);
                            standard.setTag(new CompoundTag());
                            standard.getTag().putString("kyberColor", KyberCrystalColor.BLUE.getName().toString());
                            standard.getTag().putString("type", LightsaberTypeBlade.SINGLE.toString());
                            standard.getTag().putBoolean("active", true);
                            return standard;
                        }
                        return null;
                    }
                    )
                    .title(Component.translatable("creativetab.empireofstars_tab"))

                    .displayItems((itemDisplayParameters, output) -> {
                        for (KyberCrystalColor color : KyberCrystalColor.values()) {
                            for (int shardCount = 1; shardCount <= 5; shardCount++) {
                                ItemStack kyberCrystal = new ItemStack(KyberCrystalBlockKey.KYBER_CRYSTAL_TYPES.get(new KyberCrystalBlockKey(shardCount, color)).get());

                                CompoundTag tag = new CompoundTag();
                                tag.putString("kyberColor", color.getName());
                                tag.putInt("shardCount", shardCount);

                                kyberCrystal.setTag(tag);

                                output.accept(kyberCrystal);
                            }
                        }

                        for (KyberCrystalColor color : KyberCrystalColor.values()) {
                            for (LightsaberType type : LightsaberType.values()) {
                                ItemStack standard = new ItemStack(EOSItem.LIGHTSABER_TYPES.get(type).get());
                                standard.setTag(new CompoundTag());
                                standard.getTag().putString("kyberColor", color.getName().toString());
                                standard.getTag().putString("type", LightsaberTypeBlade.SINGLE.toString());
                                standard.getTag().putBoolean("active", false);
                                output.accept(standard);
                            }
                        }
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
