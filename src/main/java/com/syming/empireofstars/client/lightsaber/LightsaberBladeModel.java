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
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class LightsaberBladeModel {

    public LightsaberBladeModel() {
    }

    public void renderInner(PoseStack poseStack, VertexConsumer buffer,
                            int packedLight, int packedOverlay) {
        drawCuboid(poseStack, buffer, packedLight, packedOverlay,
                1f, 1f, 1f, 1f,
                -0.05f, 0f, -0.05f, 0.05f, 1.0f, 0.05f);
    }

    public void renderOuter(PoseStack poseStack, VertexConsumer buffer,
                            int packedLight, int packedOverlay,
                            float r, float g, float b, float a) {
        poseStack.pushPose();
        poseStack.scale(1.25f, 1.25f, 1.25f);
        drawCuboid(poseStack, buffer, packedLight, packedOverlay,
                r, g, b, a,
                -0.05f, 0f, -0.05f, 0.05f, 1.0f, 0.05f);
        poseStack.popPose();
    }

    private void drawCuboid(PoseStack poseStack, VertexConsumer consumer,
                            int packedLight, int packedOverlay,
                            float r, float g, float b, float a,
                            float minX, float minY, float minZ,
                            float maxX, float maxY, float maxZ) {
        Matrix4f mat = poseStack.last().pose();
        Matrix3f normal = poseStack.last().normal();

        // Front
        quad(consumer, mat, normal, packedLight, packedOverlay, r, g, b, a,
                minX, minY, maxZ, maxX, minY, maxZ, maxX, maxY, maxZ, minX, maxY, maxZ,
                0, 0, 1);
        // Back
        quad(consumer, mat, normal, packedLight, packedOverlay, r, g, b, a,
                maxX, minY, minZ, minX, minY, minZ, minX, maxY, minZ, maxX, maxY, minZ,
                0, 0, -1);
        // Left
        quad(consumer, mat, normal, packedLight, packedOverlay, r, g, b, a,
                minX, minY, minZ, minX, minY, maxZ, minX, maxY, maxZ, minX, maxY, minZ,
                -1, 0, 0);
        // Right
        quad(consumer, mat, normal, packedLight, packedOverlay, r, g, b, a,
                maxX, minY, maxZ, maxX, minY, minZ, maxX, maxY, minZ, maxX, maxY, maxZ,
                1, 0, 0);
        // Top
        quad(consumer, mat, normal, packedLight, packedOverlay, r, g, b, a,
                minX, maxY, maxZ, maxX, maxY, maxZ, maxX, maxY, minZ, minX, maxY, minZ,
                0, 1, 0);
        // Bottom
        quad(consumer, mat, normal, packedLight, packedOverlay, r, g, b, a,
                minX, minY, minZ, maxX, minY, minZ, maxX, minY, maxZ, minX, minY, maxZ,
                0, -1, 0);
    }

    private void quad(VertexConsumer c, Matrix4f mat, Matrix3f normal,
                      int packedLight, int packedOverlay,
                      float r, float g, float b, float a,
                      float x1, float y1, float z1,
                      float x2, float y2, float z2,
                      float x3, float y3, float z3,
                      float x4, float y4, float z4,
                      float nx, float ny, float nz) {
        c.vertex(mat, x1, y1, z1).color(r, g, b, a).uv(0, 0).overlayCoords(packedOverlay).uv2(packedLight).normal(normal, nx, ny, nz).endVertex();
        c.vertex(mat, x2, y2, z2).color(r, g, b, a).uv(1, 0).overlayCoords(packedOverlay).uv2(packedLight).normal(normal, nx, ny, nz).endVertex();
        c.vertex(mat, x3, y3, z3).color(r, g, b, a).uv(1, 1).overlayCoords(packedOverlay).uv2(packedLight).normal(normal, nx, ny, nz).endVertex();
        c.vertex(mat, x4, y4, z4).color(r, g, b, a).uv(0, 1).overlayCoords(packedOverlay).uv2(packedLight).normal(normal, nx, ny, nz).endVertex();
    }
}
