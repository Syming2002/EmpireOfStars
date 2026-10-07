package com.syming.empireofstars.item;

import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

import java.util.Locale;

public class KyberCrystalShardItem extends Item {

    public KyberCrystalShardItem() {
        super(new Properties().stacksTo(64).rarity(Rarity.UNCOMMON));
    }


    public static void setColor(ItemStack pStack, KyberCrystalColor pColor) {
        pStack.getOrCreateTag().putString("color", pColor.getSerializedName().toUpperCase(Locale.ROOT));
    }

    public KyberCrystalColor getColor(ItemStack pStack) {
        if (pStack.getTag() != null && pStack.getTag().contains("color")) {
            return KyberCrystalColor.valueOf(pStack.getTag().getString("color").toUpperCase(Locale.ROOT));
        }
        return null;
    }
}
