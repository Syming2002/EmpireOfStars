package com.syming.empireofstars.item.lightsaber;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.client.lightsaber.LightsaberRenderer;
import com.syming.empireofstars.network.ActiveLightsaber;
import com.syming.empireofstars.network.EOSPacketHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
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
import java.util.Locale;
import java.util.function.Consumer;

public class LightsaberItem extends Item {

    private float attackDamage;
    private float attackSpeed;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public LightsaberItem() {
        super(new Properties().stacksTo(1));

        this.attackDamage = 15;
        this.attackSpeed = -2.5F;

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

//    @Override
//    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
//        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
//
//        EOSPacketHandler.sendToServer(new ActiveLightsaber());
//        return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide());
//    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        if (pStack.hasTag()) {
            if (pStack.getTag() != null && pStack.getTag().contains("kyberColor")) {
                KyberCrystalColor color = getColor(pStack);
                if (color == KyberCrystalColor.BLUE) {
                    pTooltipComponents.add(Component.translatable("translation.empireofstars.blue_lightsaber").withStyle(ChatFormatting.BLUE));
                }
                if (color == KyberCrystalColor.GREEN) {
                    pTooltipComponents.add(Component.translatable("translation.empireofstars.green_lightsaber").withStyle(ChatFormatting.GREEN));
                }
                if (color == KyberCrystalColor.RED) {
                    pTooltipComponents.add(Component.translatable("translation.empireofstars.red_lightsaber").withStyle(ChatFormatting.RED));
                }
                if (color == KyberCrystalColor.PURPLE) {
                    pTooltipComponents.add(Component.translatable("translation.empireofstars.purple_lightsaber").withStyle(ChatFormatting.DARK_PURPLE));
                }
                if (color == KyberCrystalColor.YELLOW) {
                    pTooltipComponents.add(Component.translatable("translation.empireofstars.yellow_lightsaber").withStyle(ChatFormatting.YELLOW));
                }
                if (color == KyberCrystalColor.WHITE) {
                    pTooltipComponents.add(Component.translatable("translation.empireofstars.white_lightsaber").withStyle(ChatFormatting.WHITE));
                }
                if (color == KyberCrystalColor.BLACK) {
                    pTooltipComponents.add(Component.translatable("translation.empireofstars.black_lightsaber").withStyle(ChatFormatting.BLACK));
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
        pStack.getOrCreateTag().putString("kyberColor", pColor.getName().toUpperCase(Locale.ROOT));
    }

    public KyberCrystalColor getColor(ItemStack pStack) {
        if (pStack.getTag() != null && pStack.getTag().contains("kyberColor")) {
            return KyberCrystalColor.valueOf(pStack.getTag().getString("kyberColor").toUpperCase(Locale.ROOT));
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
