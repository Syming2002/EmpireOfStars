package com.syming.empireofstars.event;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.block.blockentity.EOSBlockEntity;
import com.syming.empireofstars.client.EOSKeybinding;
import com.syming.empireofstars.client.kybercrystal.KyberCrystalBlockEntityRenderer;
import com.syming.empireofstars.client.lightsaber.LightsaberBladeModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EmpireOfStarsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EOSClientModEvent {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
//        event.registerLayerDefinition(KyberCrystalBlockEntityRenderer.KYBER_CRYSTAL_LAYER_LOCATION, KyberCrystalBlockEntityRenderer::createShardLayer);
    event.registerLayerDefinition(LightsaberBladeModel.BLADE_LAYER, LightsaberBladeModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerBlockEntitiesRenderer(EntityRenderersEvent.RegisterRenderers event) {
        for (KyberCrystalColor color : KyberCrystalColor.values()) {
            ItemBlockRenderTypes.setRenderLayer(EOSBlock.KYBER_CRYSTAL_TYPES.get(color).get(), RenderType.translucent());
            event.registerBlockEntityRenderer(EOSBlockEntity.KYBER_CRYSTAL_BLOCK_ENTITY_TYPES.get(color).get(), KyberCrystalBlockEntityRenderer::new);

            Minecraft.getInstance().getItemColors().register((stack, tintIndex) -> {
                if (tintIndex == 0 && stack.getItem() == EOSBlock.KYBER_CRYSTAL_TYPES.get(color).get().asItem()) {
                    return color.getColor();
                }
                return 0xFFFFFFFF;
            }, EOSBlock.KYBER_CRYSTAL_TYPES.get(color).get());

            Minecraft.getInstance().getBlockColors().register((pState, pLevel, pBlockPos, pTintIndex) -> {
                if (pState.getBlock() instanceof KyberCrystalBlock && pTintIndex == 0) {
                    return color.getColor();
                }
                return 0xFFFFFFFF;
            }, EOSBlock.KYBER_CRYSTAL_TYPES.get(color).get());
        }
    }

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(EOSKeybinding.INSTANCE.ACTIVE_LIGHTSABER);
    }

}
