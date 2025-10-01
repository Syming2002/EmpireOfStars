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

public class LightsaberStandardModel extends Model {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation STANDARD_LIGHTSABER_LAYER = new ModelLayerLocation(new ResourceLocation(EmpireOfStarsMod.MOD_ID, "standard_lightsaber"), "main");
    private final ModelPart standardLightsaber;
    private final ModelPart emitter;
    private final ModelPart upperBody;
    private final ModelPart lowerBody;
    private final ModelPart upperButton;
    private final ModelPart lowerButton;
    private final ModelPart grip;
    private final ModelPart attachment;

    public LightsaberStandardModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.standardLightsaber = root.getChild("standardLightsaber");
        this.emitter = this.standardLightsaber.getChild("emitter");
        this.upperBody = this.standardLightsaber.getChild("upperBody");
        this.lowerBody = this.standardLightsaber.getChild("lowerBody");
        this.upperButton = this.standardLightsaber.getChild("upperButton");
        this.lowerButton = this.standardLightsaber.getChild("lowerButton");
        this.grip = this.standardLightsaber.getChild("grip");
        this.attachment = this.standardLightsaber.getChild("attachment");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition standardLightsaber = partdefinition.addOrReplaceChild("standardLightsaber", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition emitter = standardLightsaber.addOrReplaceChild("emitter", CubeListBuilder.create().texOffs(8, 0).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offset(-0.45F, -11.0F, -0.2F));

        PartDefinition upperBody = standardLightsaber.addOrReplaceChild("upperBody", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(-0.45F, -8.9F, -0.2F));

        PartDefinition lowerBody = standardLightsaber.addOrReplaceChild("lowerBody", CubeListBuilder.create().texOffs(6, 6).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.45F, -4.0F, -0.2F));

        PartDefinition upperButton = standardLightsaber.addOrReplaceChild("upperButton", CubeListBuilder.create().texOffs(6, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.45F, -4.7F, 0.0F));

        PartDefinition lowerButton = standardLightsaber.addOrReplaceChild("lowerButton", CubeListBuilder.create().texOffs(0, 11).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.45F, -3.7F, 0.0F));

        PartDefinition grip = standardLightsaber.addOrReplaceChild("grip", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.45F, -1.0F, 0.3F));

        PartDefinition attachment = standardLightsaber.addOrReplaceChild("attachment", CubeListBuilder.create().texOffs(8, 3).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F)), PartPose.offset(-0.45F, -1.3F, -0.3F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.standardLightsaber.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
