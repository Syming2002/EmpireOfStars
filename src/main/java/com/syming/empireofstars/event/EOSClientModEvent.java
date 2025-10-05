package com.syming.empireofstars.event;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.block.KyberCrystalBlockKey;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.block.blockentity.EOSBlockEntity;
import com.syming.empireofstars.block.blockentity.KyberCrystalBlockEntityKey;
import com.syming.empireofstars.client.EOSKeybinding;
import com.syming.empireofstars.client.kybercrystal.KyberCrystalBlockEntityRenderer;
import com.syming.empireofstars.client.kybercrystal.KyberCrystalModel;
import com.syming.empireofstars.client.lightsaber.LightsaberStandardModel;
import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.item.KyberCrystalShardItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.checkerframework.checker.units.qual.C;
import org.checkerframework.checker.units.qual.K;

@Mod.EventBusSubscriber(modid = EmpireOfStarsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EOSClientModEvent {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LightsaberStandardModel.STANDARD_LIGHTSABER_LAYER, LightsaberStandardModel::createBodyLayer);
        event.registerLayerDefinition(KyberCrystalModel.KYBER_CRYSTAL_LAYER, KyberCrystalModel::createShardLayer);
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        for (KyberCrystalColor color : KyberCrystalColor.values()) {
            KyberCrystalShardItem kyberCrystalShardItem = EOSItem.KYBER_CRYSTAL_SHARD_TYPES.get(color).get();
            event.register((pStack, pTintIndex) -> pTintIndex == 0 ? color.getColor() : 0xFFFFFFFF, kyberCrystalShardItem);
        }
    }

    @SubscribeEvent
    public static void registerBlockEntitiesRenderer(EntityRenderersEvent.RegisterRenderers event) {
        for (KyberCrystalColor color : KyberCrystalColor.values()) {
            for (int shardCount = 1; shardCount <= 5; shardCount++) {
                event.registerBlockEntityRenderer(KyberCrystalBlockEntityKey.KYBER_CRYSTAL_BLOCK_ENTITY_TYPES.get(new KyberCrystalBlockEntityKey(shardCount, color)).get(), KyberCrystalBlockEntityRenderer::new);
            }
        }
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        for (KyberCrystalColor color : KyberCrystalColor.values()) {
            for (int shardCount = 1; shardCount <= 5; shardCount++) {
                event.register((pState, pLevel, pBlockPos, pTintIndex) -> {
                    if (pState.getBlock() instanceof KyberCrystalBlock && pTintIndex == 0) {
                        return color.getColor();
                    }
                    return 0xFFFFFFFF;
                }, KyberCrystalBlockKey.KYBER_CRYSTAL_TYPES.get(new KyberCrystalBlockKey(shardCount, color)).get());
            }
        }
    }

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(EOSKeybinding.INSTANCE.ACTIVE_LIGHTSABER);
    }

}
