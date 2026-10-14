package com.syming.empireofstars.item;

import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;

public class KyberCrystalShardItem extends Item {

    public KyberCrystalShardItem() {
        super(new Properties().stacksTo(64).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        if (pStack.hasTag()) {
            if (pStack.getTag() != null && pStack.getTag().contains("kybercolor")) {
                KyberCrystalColor kybercolor = getKyberColor(pStack);
                if (kybercolor == KyberCrystalColor.AQUAMARINE) {
                    pTooltipComponents.add(Component.translatable("kybercolor.empireofstars.aquamarine").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(kybercolor.getColor()))));
                }
                if (kybercolor == KyberCrystalColor.BLACK) {
                    pTooltipComponents.add(Component.translatable("kybercolor.empireofstars.black").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(kybercolor.getColor()))));
                }
                if (kybercolor == KyberCrystalColor.BLUE) {
                    pTooltipComponents.add(Component.translatable("kybercolor.empireofstars.blue").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(kybercolor.getColor()))));
                }
                if (kybercolor == KyberCrystalColor.GREEN) {
                    pTooltipComponents.add(Component.translatable("kybercolor.empireofstars.green").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(kybercolor.getColor()))));
                }
                if (kybercolor == KyberCrystalColor.ORANGE) {
                    pTooltipComponents.add(Component.translatable("kybercolor.empireofstars.orange").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(kybercolor.getColor()))));
                }
                if (kybercolor == KyberCrystalColor.PINK) {
                    pTooltipComponents.add(Component.translatable("kybercolor.empireofstars.pink").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(kybercolor.getColor()))));
                }
                if (kybercolor == KyberCrystalColor.PURPLE) {
                    pTooltipComponents.add(Component.translatable("kybercolor.empireofstars.purple").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(kybercolor.getColor()))));
                }
                if (kybercolor == KyberCrystalColor.RED) {
                    pTooltipComponents.add(Component.translatable("kybercolor.empireofstars.red").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(kybercolor.getColor()))));
                }
                if (kybercolor == KyberCrystalColor.WHITE) {
                    pTooltipComponents.add(Component.translatable("kybercolor.empireofstars.white").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(kybercolor.getColor()))));
                }
                if (kybercolor == KyberCrystalColor.YELLOW) {
                    pTooltipComponents.add(Component.translatable("kybercolor.empireofstars.yellow").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(kybercolor.getColor()))));
                }
            }
        }
    }

    public static void setKyberColor(ItemStack pStack, KyberCrystalColor pColor) {
        pStack.getOrCreateTag().putString("kybercolor", pColor.getSerializedName().toUpperCase(Locale.ROOT));
    }

    public KyberCrystalColor getKyberColor(ItemStack pStack) {
        if (pStack.getTag() != null && pStack.getTag().contains("kybercolor")) {
            return KyberCrystalColor.valueOf(pStack.getTag().getString("kybercolor").toUpperCase(Locale.ROOT));
        }
        return null;
    }
}
