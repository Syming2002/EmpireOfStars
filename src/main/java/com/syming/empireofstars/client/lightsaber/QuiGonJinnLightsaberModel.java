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

public class QuiGonJinnLightsaberModel extends Model {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation QUI_GON_JINN_LIGHTSABER_LAYER = new ModelLayerLocation(new ResourceLocation(EmpireOfStarsMod.MOD_ID, "qui_gon_jinn_lightsaber"), "main");
    private final ModelPart quiGonJinnHilt;
    private final ModelPart hilt;
    private final ModelPart redButton;
    private final ModelPart grip;

    public QuiGonJinnLightsaberModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.quiGonJinnHilt = root.getChild("quiGonJinnHilt");
        this.hilt = this.quiGonJinnHilt.getChild("hilt");
        this.redButton = this.quiGonJinnHilt.getChild("redButton");
        this.grip = this.quiGonJinnHilt.getChild("grip");
    }

    public static LayerDefinition createHiltLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition quiGonJinnHilt = partdefinition.addOrReplaceChild("quiGonJinnHilt", CubeListBuilder.create(), PartPose.offset(-0.1929F, 25.0571F, -0.0857F));

        PartDefinition hilt = quiGonJinnHilt.addOrReplaceChild("hilt", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.1929F, -9.0571F, 0.0857F));

        PartDefinition redButton = quiGonJinnHilt.addOrReplaceChild("redButton", CubeListBuilder.create().texOffs(8, 0).addBox(-0.3071F, -9.0571F, 0.7857F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition grip = quiGonJinnHilt.addOrReplaceChild("grip", CubeListBuilder.create().texOffs(8, 2).addBox(-0.5F, 4.0F, -1.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.1929F, -9.0571F, 0.0857F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.quiGonJinnHilt.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
