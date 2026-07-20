package com.syming.empireofstars.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.client.entity.model.SarlaccBeakModel;
import com.syming.empireofstars.entity.sarlacc.SarlaccBeakEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SarlaccBeakRenderer extends MobRenderer<SarlaccBeakEntity, SarlaccBeakModel<SarlaccBeakEntity>> {

    public static final ResourceLocation SARLACC_BEAK_TEXTURE = new ResourceLocation(EmpireOfStarsMod.MOD_ID, "textures/entity/sarlacc/sarlacc_beak.png");

    public SarlaccBeakRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SarlaccBeakModel<>(pContext.bakeLayer(SarlaccBeakModel.SARLACC_BEAK_LAYER)), 0.25F);
    }

    @Override
    protected void scale(SarlaccBeakEntity pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        pPoseStack.scale(1.5F, 1.5F, 1.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(SarlaccBeakEntity pEntity) {
        return SARLACC_BEAK_TEXTURE;
    }
}
