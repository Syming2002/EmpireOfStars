package com.syming.empireofstars.client.kybercrystal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.blockentity.KyberCrystalBlockEntity;
import com.syming.empireofstars.client.EOSRenderType;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class KyberCrystalModel extends Model {

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation KYBER_CRYSTAL_LAYER = new ModelLayerLocation(new ResourceLocation(EmpireOfStarsMod.MOD_ID, "kyber_crystal"), "main");

    protected final ModelPart kyberCrystal;

    protected final ModelPart[] shards;

    public KyberCrystalModel(ModelPart pRoot) {
        super(RenderType::entityCutout);
        this.kyberCrystal = pRoot.getChild("kyberCrystal");
        this.shards = new ModelPart[]{
                this.kyberCrystal.getChild("firstShard"),
                this.kyberCrystal.getChild("secondShard"),
                this.kyberCrystal.getChild("thirdShard"),
                this.kyberCrystal.getChild("fourthShard"),
                this.kyberCrystal.getChild("fifthShard")
        };
    }

    public static LayerDefinition createShardLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition kyberCrystal = partdefinition.addOrReplaceChild("kyberCrystal", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition firstShard = kyberCrystal.addOrReplaceChild("firstShard", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.1F));

        PartDefinition secondShard = kyberCrystal.addOrReplaceChild("secondShard", CubeListBuilder.create().texOffs(8, 9).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, -1.1F, 0.4363F, 0.0F, 0.0F));

        PartDefinition thirdShard = kyberCrystal.addOrReplaceChild("thirdShard", CubeListBuilder.create().texOffs(16, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 2.0F, 1.1F, -0.1719F, -0.0302F, -0.1719F));

        PartDefinition fourthShard = kyberCrystal.addOrReplaceChild("fourthShard", CubeListBuilder.create().texOffs(0, 10).addBox(-1.0F, -2.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8706F, 1.483F, 1.1F, 0.0F, 0.0F, 0.2618F));

        PartDefinition fifthShard = kyberCrystal.addOrReplaceChild("fifthShard", CubeListBuilder.create().texOffs(8, 0).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.5F, -0.9F, 0.2533F, -0.067F, 0.2533F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.kyberCrystal.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void renderShards(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay,
                             float red, float green, float blue, float alpha, KyberCrystalBlockEntity kyberCrystalBlockEntity) {
        for (int i = 0; i < kyberCrystalBlockEntity.getShards(); i++) {
            this.shards[i].render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        }
    }
}
