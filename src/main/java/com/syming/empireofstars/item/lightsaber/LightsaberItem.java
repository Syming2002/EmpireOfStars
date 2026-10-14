package com.syming.empireofstars.item.lightsaber;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.client.lightsaber.LightsaberRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class LightsaberItem extends Item {

    private float attackDamage;
    private float attackSpeed;

    public LightsaberItem() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {

        if (slot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

            this.attackDamage = isActive(stack) ? 15 : 2;
            this.attackSpeed = -2.5F;


            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)this.attackSpeed, AttributeModifier.Operation.ADDITION));
            return builder.build();
        }
        return super.getAttributeModifiers(slot, stack);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            private LightsaberRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    return this.renderer = LightsaberRenderer.getInstance();
                }
                return this.renderer;
            }
        });
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

    @Override
    public Component getName(ItemStack pStack) {
        return pStack.getTag() != null && pStack.getTag().contains("kybercolor") ? Component.translatable("item.empireofstars.standard_lightsaber").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(getKyberColor(pStack).getColor()))) : super.getName(pStack);
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public float getAttackSpeed() {
        return this.attackSpeed;
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

    public static boolean isActive(ItemStack stack) {
        return stack.hasTag() && stack.getOrCreateTag().getBoolean("active");
    }

    public static void setActive(ItemStack stack, boolean isActive) {
        stack.getOrCreateTag().putBoolean("active", isActive);
    }
}
