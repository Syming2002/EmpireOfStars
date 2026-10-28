package com.syming.empireofstars.item;

import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.Locale;

public class KyberCrystalShardItem extends Item {

    public KyberCrystalShardItem() {
        super(new Properties().stacksTo(64).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        int kyberColor = KyberCrystalColor.getKyberColorCodeFromStack(stack);
        String hex = String.format(Locale.ROOT, "#%06X", kyberColor);
        tooltipComponents.add(Component.translatable("kybercolor.empireofstars").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(kyberColor)))
                .append(Component.literal(hex)
                        .withStyle(style ->
                                style.withColor(TextColor.fromRgb(kyberColor))
                        )
                ));

        tooltipComponents.add(Component.translatable("item.empireofstars.kyber_crystal_shard.tooltip"));

    }
}
