package com.syming.empireofstars.compat.jei;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.item.lightsaber.LightsaberType;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class EOSJeiPlugin implements IModPlugin {


    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        for (KyberCrystalColor kyberCrystalColor : KyberCrystalColor.values()) {
            for (LightsaberType lightsaberType : LightsaberType.values()) {
                registration.registerSubtypeInterpreter(EOSItem.KYBER_CRYSTAL_SHARD.get(), (stack, context) -> {
                    return kyberCrystalColor.getSerializedName();
                });
                registration.registerSubtypeInterpreter(EOSBlock.KYBER_CRYSTAL_TYPES.get(kyberCrystalColor).get().asItem(), (stack, context) -> {
                    return kyberCrystalColor.getSerializedName();
                });
                registration.registerSubtypeInterpreter(EOSItem.LIGHTSABER_TYPES.get(lightsaberType).get(), (stack, context) -> {
                    return kyberCrystalColor.getSerializedName();
                });
            }
        }
    }
}
