package com.syming.empireofstars.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.client.entity.model.SarlaccBeakModel;
import com.syming.empireofstars.client.entity.model.SarlaccTentacleModel;
import com.syming.empireofstars.entity.sarlacc.SarlaccBeakEntity;
import com.syming.empireofstars.entity.sarlacc.SarlaccTentacleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SarlaccTentacleRenderer extends MobRenderer<SarlaccTentacleEntity, SarlaccTentacleModel<SarlaccTentacleEntity>> {

    public static final ResourceLocation SARLACC_TENTACLE_TEXTURE = new ResourceLocation(EmpireOfStarsMod.MOD_ID, "textures/entity/sarlacc/sarlacc_tentacle.png");

    public SarlaccTentacleRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SarlaccTentacleModel<>(pContext.bakeLayer(SarlaccTentacleModel.SARLACC_TENTACLE_LAYER)), 0.15F);
    }

    @Override
    protected void scale(SarlaccTentacleEntity pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        float scaleFactor = pLivingEntity.getTentacleHeightScale();

        pPoseStack.scale(1.0F, scaleFactor, 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(SarlaccTentacleEntity pEntity) {
        return SARLACC_TENTACLE_TEXTURE;
    }
}
