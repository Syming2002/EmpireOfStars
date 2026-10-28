package com.syming.empireofstars.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.client.entity.model.BanthaModel;
import com.syming.empireofstars.entity.animal.BanthaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;

public class BanthaRenderer<T extends BanthaEntity> extends MobRenderer<T, BanthaModel<T>> {

    public static final ResourceLocation BANTHA_TEXTURE = ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "textures/entity/bantha/bantha.png");

    public BanthaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BanthaModel<>(pContext.bakeLayer(BanthaModel.BANTHA_LAYER)), 0.75F);

        addLayer(new SaddleLayer<>(this, new BanthaModel<>(pContext.bakeLayer(BanthaModel.BANTHA_SADDLE_LAYER)), ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "textures/entity/bantha/bantha_saddle.png")));
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected void scale(T pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        pPoseStack.scale(1.5F, 1.5F, 1.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(BanthaEntity pEntity) {
        return BANTHA_TEXTURE;
    }
}
