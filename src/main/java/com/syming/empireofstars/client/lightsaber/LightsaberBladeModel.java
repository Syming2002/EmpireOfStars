package com.syming.empireofstars.client.lightsaber;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class LightsaberBladeModel extends Model {

    public static ModelLayerLocation BLADE_LAYER = new ModelLayerLocation(new ResourceLocation(EmpireOfStarsMod.MOD_ID, "blade"), "main");
    private final ModelPart root;
    private final ModelPart innerBlade;
    private final ModelPart outerBlade;

    public LightsaberBladeModel(ModelPart root) {
        super(RenderType::entityTranslucentEmissive);
        this.root = root;
        this.innerBlade = root.getChild("innerBlade");
        this.outerBlade = root.getChild("outerBlade");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition innerBlade = root.addOrReplaceChild("innerBlade",
                CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-0.5F, 0.0F, -0.5F, 1, 24, 1),
                PartPose.ZERO);

        PartDefinition outerBlade = root.addOrReplaceChild("outerBlade",
                CubeListBuilder.create()
                        .texOffs(2, 0).addBox(-0.625F, -0.125F, -0.625F, 1.25F, 24.25F, 1.25F),
                PartPose.ZERO);

        return LayerDefinition.create(mesh, 32, 32);
    }

    public void renderInnerBlade(PoseStack poseStack, VertexConsumer buffer, int light, int overlay,
                                 float red, float green, float blue, float alpha) {
        this.innerBlade.render(poseStack, buffer, light, overlay, red, green, blue, alpha);
    }

    public void renderOuterBlade(PoseStack poseStack, VertexConsumer buffer, int light, int overlay,
                                 float red, float green, float blue, float alpha) {
        this.outerBlade.render(poseStack, buffer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        renderInnerBlade(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        renderOuterBlade(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }
}
