package com.syming.empireofstars.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.client.animaton.EOSAnimation;
import com.syming.empireofstars.entity.sarlacc.SarlaccBeakEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class SarlaccBeakModel<T extends SarlaccBeakEntity> extends HierarchicalModel<T> {

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation SARLACC_BEAK_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "sarlacc_beak"), "main");
    private final ModelPart sarlaccBeak;
    private final ModelPart topBeak;
    private final ModelPart bottomBeak;
    private final ModelPart tongue;

    public SarlaccBeakModel(ModelPart root) {
        this.sarlaccBeak = root.getChild("sarlaccBeak");
        this.topBeak = this.sarlaccBeak.getChild("topBeak");
        this.bottomBeak = this.sarlaccBeak.getChild("bottomBeak");
        this.tongue = this.sarlaccBeak.getChild("tongue");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition sarlaccBeak = partdefinition.addOrReplaceChild("sarlaccBeak", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition topBeak = sarlaccBeak.addOrReplaceChild("topBeak", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -17.0F, -3.0F, 11.0F, 17.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 3.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition bottomBeak = sarlaccBeak.addOrReplaceChild("bottomBeak", CubeListBuilder.create().texOffs(0, 22).addBox(-5.25F, -17.0F, -1.0F, 11.0F, 17.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.0F, -2.0F, 0.3054F, 0.0F, 0.0F));

        PartDefinition tongue = sarlaccBeak.addOrReplaceChild("tongue", CubeListBuilder.create().texOffs(32, 0).addBox(-4.5F, -10.0F, -1.0F, 9.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -2.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public ModelPart root() {
        return this.sarlaccBeak;
    }

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        root().getAllParts().forEach(ModelPart::resetPose);

        animate(pEntity.idleAnimationState, EOSAnimation.SARLACC_BEAK_IDLE_ANIMATION, pAgeInTicks);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        this.sarlaccBeak.render(poseStack, buffer, packedLight, packedOverlay, color);
    }
}