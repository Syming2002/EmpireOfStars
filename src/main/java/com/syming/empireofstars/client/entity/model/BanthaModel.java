package com.syming.empireofstars.client.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.entity.animal.BanthaEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BanthaModel<T extends BanthaEntity> extends AgeableListModel<T> {

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation BANTHA_LAYER = new ModelLayerLocation(new ResourceLocation(EmpireOfStarsMod.MOD_ID, "bantha"), "main");
    private final ModelPart bantha;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart mouth;
    private final ModelPart topLip;
    private final ModelPart bottomLip;
    private final ModelPart rightHorn;
    private final ModelPart leftHorn;
    private final ModelPart rightUpFoot;
    private final ModelPart leftUpFoot;
    private final ModelPart rightDownFoot;
    private final ModelPart leftDownFoot;
    private final ModelPart tail;
    private final ModelPart tailTip;
    private final ModelPart chestRight;
    private final ModelPart chestLeft;

    public BanthaModel(ModelPart pRoot) {
        this.bantha = pRoot.getChild("bantha");
        this.body = this.bantha.getChild("body");
        this.head = this.bantha.getChild("head");
        this.mouth = this.head.getChild("mouth");
        this.topLip = this.mouth.getChild("topLip");
        this.bottomLip = this.mouth.getChild("bottomLip");
        this.rightHorn = this.head.getChild("rightHorn");
        this.leftHorn = this.head.getChild("leftHorn");
        this.rightUpFoot = this.bantha.getChild("rightUpFoot");
        this.leftUpFoot = this.bantha.getChild("leftUpFoot");
        this.rightDownFoot = this.bantha.getChild("rightDownFoot");
        this.leftDownFoot = this.bantha.getChild("leftDownFoot");
        this.tail = this.bantha.getChild("tail");
        this.tailTip = this.tail.getChild("tailTip");
        this.chestRight = this.bantha.getChild("chestRight");
        this.chestLeft = this.bantha.getChild("chestLeft");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bantha = partdefinition.addOrReplaceChild("bantha", CubeListBuilder.create(), PartPose.offset(0.0F, 14.7934F, 0.715F));

        PartDefinition body = bantha.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -8.0F, -8.0F, 14.0F, 16.0F, 32.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -1.7934F, -8.7503F));

        PartDefinition head = bantha.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 71).addBox(-4.0F, -6.3631F, -7.5923F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.4303F, -17.158F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create(), PartPose.offset(0.0F, 15.6369F, -2.8423F));

        PartDefinition topLip = mouth.addOrReplaceChild("topLip", CubeListBuilder.create().texOffs(50, 79).addBox(-4.0F, -0.5F, 7.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -17.0F, -11.75F));

        PartDefinition bottomLip = mouth.addOrReplaceChild("bottomLip", CubeListBuilder.create().texOffs(32, 79).addBox(-4.0F, -0.5F, 7.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -15.5F, -11.75F));

        PartDefinition rightHorn = head.addOrReplaceChild("rightHorn", CubeListBuilder.create(), PartPose.offset(-5.3853F, -7.1369F, -8.7827F));

        PartDefinition cube_r1 = rightHorn.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 81).addBox(-1.0F, -2.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2351F, 0.619F, 8.2949F, 1.1893F, -0.6225F, 1.9453F));

        PartDefinition cube_r2 = rightHorn.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(80, 79).addBox(-3.0F, -0.5F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-0.2411F, 0.6785F, 6.0789F, 0.7093F, -0.6225F, 1.9453F));

        PartDefinition cube_r3 = rightHorn.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(80, 83).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6273F, -1.0716F, 9.3192F, 0.3117F, -0.9502F, -2.8791F));

        PartDefinition cube_r4 = rightHorn.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(64, 83).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4132F, -0.6067F, 9.1625F, -1.8922F, -1.0614F, 0.7652F));

        PartDefinition cube_r5 = rightHorn.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(48, 81).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0853F, 1.0738F, 7.6904F, -0.7418F, -0.7854F, 0.0F));

        PartDefinition leftHorn = head.addOrReplaceChild("leftHorn", CubeListBuilder.create(), PartPose.offset(5.3853F, -7.1369F, -8.7827F));

        PartDefinition cube_r6 = leftHorn.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(40, 81).addBox(-1.0F, -2.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2351F, 0.619F, 8.2949F, 1.1893F, 0.6225F, -1.9453F));

        PartDefinition cube_r7 = leftHorn.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(48, 86).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6273F, -1.0716F, 9.3192F, 0.3117F, 0.9502F, 2.8791F));

        PartDefinition cube_r8 = leftHorn.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(72, 83).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4132F, -0.6067F, 9.1625F, -1.8922F, 1.0614F, -0.7652F));

        PartDefinition cube_r9 = leftHorn.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(56, 81).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0853F, 1.0738F, 7.6904F, -0.7418F, 0.7854F, 0.0F));

        PartDefinition cube_r10 = leftHorn.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(68, 79).addBox(-1.0F, -0.5F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.2411F, 0.6785F, 6.0789F, 0.7093F, 0.6225F, -1.9453F));

        PartDefinition rightUpFoot = bantha.addOrReplaceChild("rightUpFoot", CubeListBuilder.create().texOffs(32, 71).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 6.7066F, -13.0003F));

        PartDefinition leftUpFoot = bantha.addOrReplaceChild("leftUpFoot", CubeListBuilder.create().texOffs(46, 71).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 6.7066F, -13.0003F));

        PartDefinition rightDownFoot = bantha.addOrReplaceChild("rightDownFoot", CubeListBuilder.create().texOffs(60, 71).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 6.7066F, 11.9997F));

        PartDefinition leftDownFoot = bantha.addOrReplaceChild("leftDownFoot", CubeListBuilder.create().texOffs(74, 71).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 6.7066F, 11.9997F));

        PartDefinition tail = bantha.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0161F, 15.91F));

        PartDefinition cube_r11 = tail.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, -0.5F, 0.0F, 4.0F, 1.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.2773F, 0.0897F, -1.0036F, 0.0F, 0.0F));

        PartDefinition tailTip = tail.addOrReplaceChild("tailTip", CubeListBuilder.create().texOffs(52, 48).addBox(-2.0F, -0.5F, 0.0F, 4.0F, 1.0F, 22.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 18.2773F, 11.9103F));

        PartDefinition chestRight = bantha.addOrReplaceChild("chestRight", CubeListBuilder.create().texOffs(22, 89).addBox(-1.5F, -4.0F, -4.0F, 3.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, -4.7934F, 5.9997F, 0.0F, 3.1416F, 0.0F));

        PartDefinition chestLeft = bantha.addOrReplaceChild("chestLeft", CubeListBuilder.create().texOffs(0, 89).mirror().addBox(-1.5F, -4.0F, -4.0F, 3.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.5F, -4.7934F, 5.9997F, 0.0F, -3.1416F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(BanthaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.rightDownFoot.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftDownFoot.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rightUpFoot.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leftUpFoot.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        if (entity.hasChest()) {
            this.chestLeft.visible = true;
            this.chestRight.visible = true;
        } else {
            this.chestLeft.visible = false;
            this.chestRight.visible = false;
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.bantha.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head, this.rightHorn, this.leftHorn, this.mouth, this.bottomLip, this.topLip);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body);
    }
}
