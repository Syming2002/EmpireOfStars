package com.syming.empireofstars.creativetab;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.item.lightsaber.LightsaberType;
import com.syming.empireofstars.item.lightsaber.LightsaberTypeBlade;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
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
                    .icon(() -> new ItemStack(EOSBlock.KYBER_CRYSTAL_TYPES.get(KyberCrystalColor.BLUE).get()))
                    .title(Component.translatable("creativetab.empireofstars_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        for (KyberCrystalColor color : KyberCrystalColor.values()) {
                            output.accept(EOSBlock.KYBER_CRYSTAL_TYPES.get(color).get());
                        }

                        for (LightsaberType type : LightsaberType.values()) {
                            ItemStack blueStandard = new ItemStack(EOSItem.LIGHTSABER_TYPES.get(type).get());
                            blueStandard.setTag(new CompoundTag());
                            blueStandard.getTag().putString("kyberColor", EOSBlock.KYBER_CRYSTAL_TYPES.get(KyberCrystalColor.BLUE).toString());
                            blueStandard.getTag().putString("type", LightsaberTypeBlade.SINGLE.toString());
                            blueStandard.getTag().putBoolean("active", false);
                            output.accept(blueStandard);

                            ItemStack greenStandard = new ItemStack(EOSItem.LIGHTSABER_TYPES.get(type).get());
                            greenStandard.setTag(new CompoundTag());
                            greenStandard.getTag().putString("kyberColor", EOSBlock.KYBER_CRYSTAL_TYPES.get(KyberCrystalColor.GREEN).toString());
                            greenStandard.getTag().putString("type", LightsaberTypeBlade.SINGLE.toString());
                            greenStandard.getTag().putBoolean("active", false);
                            output.accept(greenStandard);
                        }


                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
