package com.syming.empireofstars.item.lightsaber;

import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.client.lightsaber.LightsaberRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class LightsaberItem extends Item {

    private float attackDamage;
    private float attackSpeed;

    public LightsaberItem() {
        super(new Properties()
                .stacksTo(1));
    }

    public static ItemAttributeModifiers createAttributes(float attackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, attackDamage, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build();
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        return createAttributes(
                isActiveFromStack(stack) ? 15 : 2,
                -2.5F
        );
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
    }


    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable("item.empireofstars.standard_lightsaber").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(KyberCrystalColor.getKyberColorCodeFromStack(stack))));
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public float getAttackSpeed() {
        return this.attackSpeed;
    }

    public static void setActiveFromStack(ItemStack stack, boolean isActive) {
        CustomData.update(DataComponents.CUSTOM_DATA, stack, compoundTag -> compoundTag.putBoolean("active", isActive));
    }

    public static boolean isActiveFromStack(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            if (tag.contains("active")) {
                return tag.getBoolean("active");
            }
        }
        return false;
    }

    public static void setBladeTypeFromStack(ItemStack stack, String bladeType) {
        CustomData.update(DataComponents.CUSTOM_DATA, stack, compoundTag -> compoundTag.putString("bladeType", bladeType));
    }

    public static String getBladeTypeFromStack(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            if (tag.contains("bladeType")) {
                return tag.getString("bladeType");
            }
        }
        return null;
    }
}
