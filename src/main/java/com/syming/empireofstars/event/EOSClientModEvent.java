package com.syming.empireofstars.event;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.block.blockentity.EOSBlockEntity;
import com.syming.empireofstars.client.EOSKeybinding;
import com.syming.empireofstars.client.entity.model.BanthaModel;
import com.syming.empireofstars.client.entity.model.SarlaccBeakModel;
import com.syming.empireofstars.client.entity.model.SarlaccTentacleModel;
import com.syming.empireofstars.client.entity.model.TuskenModel;
import com.syming.empireofstars.client.entity.renderer.BanthaRenderer;
import com.syming.empireofstars.client.entity.renderer.SarlaccBeakRenderer;
import com.syming.empireofstars.client.entity.renderer.SarlaccTentacleRenderer;
import com.syming.empireofstars.client.entity.renderer.TuskenRenderer;
import com.syming.empireofstars.client.kybercrystal.KyberCrystalBlockEntityRenderer;
import com.syming.empireofstars.client.kybercrystal.KyberCrystalModel;
import com.syming.empireofstars.client.lightsaber.StandardLightsaberModel;
import com.syming.empireofstars.entity.EOSEntity;
import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.item.KyberCrystalShardItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@EventBusSubscriber(modid = EmpireOfStarsMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EOSClientModEvent {

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(EOSBlock.SARLACC_SPEAR_TOOTH.get(), RenderType.cutout());

            Minecraft.getInstance().getItemColors().register((pStack, pTintIndex) -> {
                if ((pStack.getItem() == EOSItem.SARLACC_STOMACH_ACID_BUCKET.get() || pStack.getItem() == EOSBlock.SARLACC_STOMACH_ACID_BLOCK.get().asItem()) && pTintIndex == 1) {
                    return 0xFFB7D62B;
                }
                return 0xFFFFFFFF;
            }, EOSItem.SARLACC_STOMACH_ACID_BUCKET.get(), EOSBlock.SARLACC_STOMACH_ACID_BLOCK.get().asItem());
        });
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(StandardLightsaberModel.STANDARD_LIGHTSABER_LAYER, StandardLightsaberModel::createBodyLayer);
        event.registerLayerDefinition(KyberCrystalModel.KYBER_CRYSTAL_LAYER, KyberCrystalModel::createShardLayer);

        event.registerLayerDefinition(BanthaModel.BANTHA_LAYER, () -> LayerDefinition.create(BanthaModel.createBanthaMesh(15.0F), 128, 128));
        event.registerLayerDefinition(BanthaModel.BANTHA_SADDLE_LAYER, () -> LayerDefinition.create(BanthaModel.createBanthaMesh(15.0F), 128, 128));

        event.registerLayerDefinition(SarlaccBeakModel.SARLACC_BEAK_LAYER, SarlaccBeakModel::createBodyLayer);
        event.registerLayerDefinition(SarlaccTentacleModel.SARLACC_TENTACLE_LAYER, SarlaccTentacleModel::createSarlaccTentacleLayer);

        event.registerLayerDefinition(TuskenModel.TUSKEN_LAYER, () -> LayerDefinition.create(TuskenModel.createMesh(CubeDeformation.NONE, 0.0F), 64, 32));
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        KyberCrystalShardItem kyberCrystalShardItem = EOSItem.KYBER_CRYSTAL_SHARD.get();

        event.register((stack, tintIndex) -> {
            if (tintIndex != 0) {
                return 0xFFFFFF;
            }

            CustomData customData = stack.get(DataComponents.CUSTOM_DATA);

            if (customData != null) {
                return KyberCrystalColor.getKyberColorCodeFromStack(stack);
            }
            return 0xFFFFFF;
        }, kyberCrystalShardItem);
    }

    @SubscribeEvent
    public static void registerBlockEntitiesRenderer(EntityRenderersEvent.RegisterRenderers event) {
        for (KyberCrystalColor kybercolor : KyberCrystalColor.values()) {
            event.registerBlockEntityRenderer(EOSBlockEntity.KYBER_CRYSTAL_BLOCK_ENTITY_TYPES.get(kybercolor).get(), KyberCrystalBlockEntityRenderer::new);
        }
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        for (KyberCrystalColor kybercolor : KyberCrystalColor.values()) {
            event.register((pState, pLevel, pBlockPos, pTintIndex) -> {
                if (pState.getBlock() instanceof KyberCrystalBlock && pTintIndex == 0) {
                    return kybercolor.getKyberColor();
                }
                return 0xFFFFFFFF;
            }, EOSBlock.KYBER_CRYSTAL_TYPES.get(kybercolor).get());
        }
    }

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(EOSKeybinding.INSTANCE.ACTIVE_LIGHTSABER);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EOSEntity.BANTHA.get(), BanthaRenderer::new);
        event.registerEntityRenderer(EOSEntity.RAIDER_TUSKEN.get(), TuskenRenderer::new);

        event.registerEntityRenderer(EOSEntity.SARLACC_BEAK.get(), SarlaccBeakRenderer::new);
        event.registerEntityRenderer(EOSEntity.SARLACC_TENTACLE.get(), SarlaccTentacleRenderer::new);
    }

}
