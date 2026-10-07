package com.syming.empireofstars.client.lightsaber;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class StandardLightsaberModel extends Model {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation STANDARD_LIGHTSABER_LAYER = new ModelLayerLocation(new ResourceLocation(EmpireOfStarsMod.MOD_ID, "standard_lightsaber"), "main");
    private final ModelPart standardLightsaber;
    private final ModelPart bottomGrip;
    private final ModelPart ring;
    private final ModelPart topGrip;
    private final ModelPart ring3;
    private final ModelPart ring2;

    public StandardLightsaberModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.standardLightsaber = root.getChild("standardLightsaber");
        this.bottomGrip = this.standardLightsaber.getChild("bottomGrip");
        this.ring = this.bottomGrip.getChild("ring");
        this.topGrip = this.standardLightsaber.getChild("topGrip");
        this.ring3 = this.standardLightsaber.getChild("ring3");
        this.ring2 = this.standardLightsaber.getChild("ring2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition standardLightsaber = partdefinition.addOrReplaceChild("standardLightsaber", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition bottomGrip = standardLightsaber.addOrReplaceChild("bottomGrip", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, -3.3F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false)
                .texOffs(4, 13).addBox(-1.5F, -2.0F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(0.5F, -2.0F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 12).addBox(-1.5F, -2.0F, 0.5F, 3.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 0).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition ring = bottomGrip.addOrReplaceChild("ring", CubeListBuilder.create().texOffs(24, 3).addBox(-1.0F, 5.6F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.8F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition topGrip = standardLightsaber.addOrReplaceChild("topGrip", CubeListBuilder.create().texOffs(8, 0).addBox(-1.0F, -13.3F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0625F))
                .texOffs(18, 14).addBox(-1.0F, -7.3F, 0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 10).mirror().addBox(-1.0F, -7.3F, -1.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(24, 9).mirror().addBox(-1.0F, -8.1F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.3F)).mirror(false)
                .texOffs(24, 0).addBox(-1.0F, -4.9F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition ring3 = standardLightsaber.addOrReplaceChild("ring3", CubeListBuilder.create().texOffs(24, 6).addBox(-1.0F, -12.7F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition ring2 = standardLightsaber.addOrReplaceChild("ring2", CubeListBuilder.create().texOffs(24, 12).mirror().addBox(-1.0F, -9.8F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.standardLightsaber.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }
}
