package com.syming.empireofstars.client.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.entity.animal.BanthaEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BanthaModel<T extends BanthaEntity> extends QuadrupedModel<T> {

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation BANTHA_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "bantha"), "main");
    public static final ModelLayerLocation BANTHA_SADDLE_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "bantha"), "saddle");


    private final ModelPart beard;
    private final ModelPart rightHorn;
    private final ModelPart leftHorn;
    private final ModelPart tail;
    private final ModelPart tailTip;
    private final ModelPart chestRight;
    private final ModelPart chestLeft;

    public BanthaModel(ModelPart pRoot) {
        super(pRoot, false, 8.0F, 10.0F, 1.5F, 1.75F, 18);
        this.beard = this.head.getChild("beard");
        this.rightHorn = this.head.getChild("rightHorn");
        this.leftHorn = this.head.getChild("leftHorn");
        this.tail = pRoot.getChild("tail");
        this.tailTip = this.tail.getChild("tailTip");
        this.chestRight = pRoot.getChild("chestRight");
        this.chestLeft = pRoot.getChild("chestLeft");
    }

    public static MeshDefinition createBanthaMesh(float pYOffset) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -8.0F, -8.0F, 14.0F, 16.0F, 32.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -1.7934F + pYOffset, -8.7503F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 71).addBox(-4.0F, -6.3631F, -7.5923F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.4303F + pYOffset, -17.158F));

        PartDefinition rightHorn = head.addOrReplaceChild("rightHorn", CubeListBuilder.create(), PartPose.offset(-5.3853F, -7.1369F, -8.7827F));

        PartDefinition cube_r1 = rightHorn.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 89).mirror().addBox(-1.0F, -2.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.2351F, 0.619F, 8.2949F, 1.1893F, -0.6225F, 1.9453F));

        PartDefinition cube_r2 = rightHorn.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(12, 89).mirror().addBox(-3.0F, -0.5F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offsetAndRotation(-0.2411F, 0.6785F, 6.0789F, 0.7093F, -0.6225F, 1.9453F));

        PartDefinition cube_r3 = rightHorn.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(90, 91).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.6273F, -1.0716F, 9.3192F, 0.3117F, -0.9502F, -2.8791F));

        PartDefinition cube_r4 = rightHorn.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(92, 15).mirror().addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.4132F, -0.6067F, 9.1625F, -1.8922F, -1.0614F, 0.7652F));

        PartDefinition cube_r5 = rightHorn.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(92, 5).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0853F, 1.0738F, 7.6904F, -0.7418F, -0.7854F, 0.0F));

        PartDefinition leftHorn = head.addOrReplaceChild("leftHorn", CubeListBuilder.create(), PartPose.offset(5.3853F, -7.1369F, -8.7827F));

        PartDefinition cube_r6 = leftHorn.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(24, 89).mirror().addBox(-1.0F, -2.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.2351F, 0.619F, 8.2949F, 1.1893F, 0.6225F, -1.9453F));

        PartDefinition cube_r7 = leftHorn.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(90, 91).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.6273F, -1.0716F, 9.3192F, 0.3117F, 0.9502F, 2.8791F));

        PartDefinition cube_r8 = leftHorn.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(92, 15).mirror().addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.4132F, -0.6067F, 9.1625F, -1.8922F, 1.0614F, -0.7652F));

        PartDefinition cube_r9 = leftHorn.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(92, 5).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0853F, 1.0738F, 7.6904F, -0.7418F, 0.7854F, 0.0F));

        PartDefinition cube_r10 = leftHorn.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(12, 89).mirror().addBox(-1.0F, -0.5F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offsetAndRotation(0.2411F, 0.6785F, 6.0789F, 0.7093F, 0.6225F, -1.9453F));

        PartDefinition beard = head.addOrReplaceChild("beard", CubeListBuilder.create().texOffs(76, 71).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 5.0F, -7.0F));

        PartDefinition rightUpFoot = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(76, 85).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 6.7066F + pYOffset, -13.0003F));

        PartDefinition leftUpFoot = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(76, 85).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 6.7066F + pYOffset, -13.0003F));

        PartDefinition rightDownFoot = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(76, 85).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 6.7066F + pYOffset, 11.9997F));

        PartDefinition leftDownFoot = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(76, 85).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 6.7066F + pYOffset, 11.9997F));

        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0161F + pYOffset, 15.91F));

        PartDefinition cube_r11 = tail.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, -0.5F, 0.0F, 4.0F, 1.0F, 22.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -0.2773F, 0.0897F, -1.0036F, 0.0F, 0.0F));

        PartDefinition tailTip = tail.addOrReplaceChild("tailTip", CubeListBuilder.create().texOffs(52, 48).addBox(-2.0F, -0.5F, -0.5F, 4.0F, 1.0F, 22.0F, new CubeDeformation(0.5001F)), PartPose.offset(0.0F, 17.9773F, 11.9103F));

        PartDefinition chestRight = partdefinition.addOrReplaceChild("chestRight", CubeListBuilder.create().texOffs(54, 71).addBox(-1.5F, -4.0F, -4.0F, 3.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, -4.7934F + pYOffset, 5.9997F, 0.0F, 3.1416F, 0.0F));

        PartDefinition chestLeft = partdefinition.addOrReplaceChild("chestLeft", CubeListBuilder.create().texOffs(32, 71).mirror().addBox(-1.5F, -4.0F, -4.0F, 3.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.5F, -4.7934F + pYOffset, 5.9997F, 0.0F, -3.1416F, 0.0F));

        return meshdefinition;
    }

    @Override
    public void setupAnim(BanthaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.rightHindLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftHindLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rightFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leftFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        if (entity.hasChest()) {
            this.chestLeft.visible = true;
            this.chestRight.visible = true;
        } else {
            this.chestLeft.visible = false;
            this.chestRight.visible = false;
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, color);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return super.headParts();
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.rightFrontLeg, this.leftFrontLeg, this.rightHindLeg, this.leftHindLeg, this.tail);
    }
}
