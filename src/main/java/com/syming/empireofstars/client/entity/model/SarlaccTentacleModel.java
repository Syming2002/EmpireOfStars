package com.syming.empireofstars.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.client.animaton.EOSAnimation;
import com.syming.empireofstars.entity.sarlacc.SarlaccTentacleEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class SarlaccTentacleModel<T extends SarlaccTentacleEntity> extends HierarchicalModel<T> {

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation SARLACC_TENTACLE_LAYER = new ModelLayerLocation(new ResourceLocation(EmpireOfStarsMod.MOD_ID, "sarlacc_tantacle"), "main");
    private final ModelPart sarlaccTentacle;
    private final ModelPart firstPartTentacle;
    private final ModelPart secondPartTentacle;
    private final ModelPart thirdPartTentacle;
    private final ModelPart fourthPartTentacle;

    public SarlaccTentacleModel(ModelPart pRoot) {
        this.sarlaccTentacle = pRoot.getChild("sarlaccTentacle");
        this.firstPartTentacle = this.sarlaccTentacle.getChild("firstPartTentacle");
        this.secondPartTentacle = this.firstPartTentacle.getChild("secondPartTentacle");
        this.thirdPartTentacle = this.secondPartTentacle.getChild("thirdPartTentacle");
        this.fourthPartTentacle = this.thirdPartTentacle.getChild("fourthPartTentacle");
    }

    public static LayerDefinition createSarlaccTentacleLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition sarlaccTentacle = partdefinition.addOrReplaceChild("sarlaccTentacle", CubeListBuilder.create(), PartPose.offset(-0.0125F, 24.0F, 0.5F));

        PartDefinition firstPartTentacle = sarlaccTentacle.addOrReplaceChild("firstPartTentacle", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -11.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

        PartDefinition secondPartTentacle = firstPartTentacle.addOrReplaceChild("secondPartTentacle", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, -9.75F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, -11.0F, 0.0F));

        PartDefinition thirdPartTentacle = secondPartTentacle.addOrReplaceChild("thirdPartTentacle", CubeListBuilder.create().texOffs(16, 0).addBox(-1.475F, -10.375F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(-0.125F)), PartPose.offset(0.0F, -9.0F, 0.0F));

        PartDefinition fourthPartTentacle = thirdPartTentacle.addOrReplaceChild("fourthPartTentacle", CubeListBuilder.create().texOffs(16, 14).addBox(-1.475F, -3.0F, -1.475F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, -10.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        root().getAllParts().forEach(ModelPart::resetPose);

        animate(pEntity.idleAnimationState, EOSAnimation.SARLACC_TENTACLE_IDLE_ANIMATION, pAgeInTicks);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.sarlaccTentacle.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }

    @Override
    public ModelPart root() {
        return this.sarlaccTentacle;
    }
}
