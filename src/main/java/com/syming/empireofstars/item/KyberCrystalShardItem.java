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
            if (pStack.getTag() != null && pStack.getTag().contains("kybercolorCode")) {
                int kyberColor = KyberCrystalColor.getKyberColorCodeFromStack(pStack);
                String hex = String.format(Locale.ROOT, "#%06X", kyberColor);
                pTooltipComponents.add(Component.translatable("kybercolor.empireofstars").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(kyberColor)))
                        .append(Component.literal(hex)
                                .withStyle(style ->
                                        style.withColor(TextColor.fromRgb(kyberColor))
                                )
                        ));

                pTooltipComponents.add(Component.translatable("item.empireofstars.kyber_crystal_shard.tooltip"));
            }
        }
    }
}
