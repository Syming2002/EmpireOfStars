package com.syming.empireofstars.item.lightsaber;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.client.lightsaber.LightsaberRenderer;
import com.syming.empireofstars.network.ActiveLightsaber;
import com.syming.empireofstars.network.EOSPacketHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class LightsaberItem extends Item {

    private float attackDamage;
    private float attackSpeed;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public LightsaberItem(Properties pProperties, int pAttackDamageModifier, float pAttackSpeedModifier) {
        super(pProperties);

        this.attackDamage = pAttackDamageModifier;
        this.attackSpeed = pAttackSpeedModifier;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)this.attackSpeed, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pSlot) {
        return pSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pSlot);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public LightsaberRenderer getCustomRenderer() {
                return LightsaberRenderer.getInstance();
            }
        });
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        if (pLevel.isClientSide()) {
            setActive(stack, !isActive(stack));
            EOSPacketHandler.sendToServer(new ActiveLightsaber());
        }

        return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        if (pStack.hasTag()) {
            if (pStack.getTag().contains("kyberColor")) {
                KyberCrystalColor color = getColor(pStack);
                if (color == KyberCrystalColor.BLUE) {
                    pTooltipComponents.add(Component.translatable("translation.empireofstars.blue_lightsaber").withStyle(ChatFormatting.BLUE));
                }
                if (color == KyberCrystalColor.GREEN) {
                    pTooltipComponents.add(Component.translatable("translation.empireofstars.green_lightsaber").withStyle(ChatFormatting.GREEN));
                }
            }
        }
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public float getAttackSpeed() {
        return this.attackSpeed;
    }

    public static void setColor(ItemStack pStack, KyberCrystalColor pColor) {
        pStack.getOrCreateTag().putString("kyberColor", pColor.getName());
    }

    public static KyberCrystalColor getColor(ItemStack pStack) {
        if (pStack.hasTag() && pStack.getTag().contains("kyberColor")) {
            String name = pStack.getTag().getString("kyberColor");
            for (KyberCrystalColor color : KyberCrystalColor.values()) {
                if (color.getName().equalsIgnoreCase(name)) {
                    return color;
                }
            }
        }
        return KyberCrystalColor.BLUE;
    }

    public static boolean isActive(ItemStack stack) {
        return stack != null && stack.hasTag() && stack.getTag().getBoolean("active");
    }

    public static ItemStack setActive(ItemStack stack, boolean isActive) {
        if (!stack.hasTag()) {
            stack.setTag(new CompoundTag());
        }

        stack.getTag().putBoolean("active", isActive);

        return stack;
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if (!stack.getTag().contains("length")) {
            stack.getTag().putFloat("length", 0);
            stack.getTag().putBoolean("active", false);
        }

        float length = stack.getTag().getFloat("length");
        if (!stack.getTag().getBoolean("active"))  {
            if (length > 0) {
                stack.getTag().putFloat("length", Math.max(length - 0.2F, 0.0F));
            }
        } else {
            if (length < 1) {
                stack.getTag().putFloat("length", Math.min(length + 0.3F, 1.0F));
            }
        }

        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
    }
}
