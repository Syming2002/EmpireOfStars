package com.syming.empireofstars.client.lightsaber;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;

public class ModelLightsaberBlade {

    private final float offset = -0.0675F;

    public ModelLightsaberBlade() {
    }

    public void renderInner(BufferBuilder buffer, ItemCameraTransforms.TransformType transformType) {
        GlStateManager.pushMatrix();

        if (transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND || transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND) {
            GlStateManager.translate(0.0F, 0.0F, -0.005F);
        }

        GlStateManager.scale(0.75F, 0.75F, 0.75F);
        drawCuboid(buffer,-0.033F, 0F, -0.033F, 0.033F, 1.45F, 0.033F, 1F, 1F, 1F, 1F);

        GlStateManager.popMatrix();
    }

    public void renderOuter(BufferBuilder buffer, ItemCameraTransforms.TransformType transformType, float r, float g, float b, float a) {
        GlStateManager.pushMatrix();

        if (transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND || transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND) {
            GlStateManager.translate(0.0F, 0.0F, -0.005F);
        }

        GlStateManager.scale(1.1F, 1.1F, 1.1F);
        drawCuboid(buffer,-0.03F, 0F, -0.03F, 0.03F, 1.0F, 0.03F, r, g, b, a);

        GlStateManager.popMatrix();
    }

    public void renderInnerCrossguard(BufferBuilder buffer, ItemCameraTransforms.TransformType transformType) {
        GlStateManager.pushMatrix();

        if (transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND || transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND) {
            GlStateManager.translate(-0.5F + this.offset, -0.125F, 0.0F);
        }

        GlStateManager.scale(0.75F, 0.75F, 0.75F);
        drawCuboid(buffer,0.0F, -0.033F, -0.033F, 1.45F, 0.033F, 0.033F, 1F, 1F, 1F, 1F);

        GlStateManager.popMatrix();
    }

    public void renderOuterCrossguard(BufferBuilder buffer, ItemCameraTransforms.TransformType transformType, float r, float g, float b, float a) {
        GlStateManager.pushMatrix();

        if (transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND || transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND) {
            GlStateManager.translate(-0.50675F + this.offset, -0.125F, 0.0F);
        }

        GlStateManager.scale(1.1F, 1.1F, 1.1F);
        drawCuboid(buffer, 0.0F, -0.03F, -0.03F, 1.0F, 0.03F, 0.03F, r, g, b, a);

        GlStateManager.popMatrix();
    }

    private void drawCuboid(BufferBuilder buffer,
                            double minX, double minY, double minZ,
                            double maxX, double maxY, double maxZ,
                            float r, float g, float b, float a) {

        // Front
        quad(buffer, minX, minY, maxZ, maxX, minY, maxZ, maxX, maxY, maxZ, minX, maxY, maxZ, r, g, b, a);
        // Back
        quad(buffer, maxX, minY, minZ, minX, minY, minZ, minX, maxY, minZ, maxX, maxY, minZ, r, g, b, a);
        // Left
        quad(buffer, minX, minY, minZ, minX, minY, maxZ, minX, maxY, maxZ, minX, maxY, minZ, r, g, b, a);
        // Right
        quad(buffer, maxX, minY, maxZ, maxX, minY, minZ, maxX, maxY, minZ, maxX, maxY, maxZ, r, g, b, a);
        // Top
        quad(buffer, minX, maxY, maxZ, maxX, maxY, maxZ, maxX, maxY, minZ, minX, maxY, minZ, r, g, b, a);
        // Bottom
        quad(buffer, minX, minY, minZ, maxX, minY, minZ, maxX, minY, maxZ, minX, minY, maxZ, r, g, b, a);
    }

    private void quad(BufferBuilder buffer,
                      double x1, double y1, double z1,
                      double x2, double y2, double z2,
                      double x3, double y3, double z3,
                      double x4, double y4, double z4,
                      float r, float g, float b, float a) {
        buffer.pos(x1, y1, z1).color(r, g, b, a).tex(0, 0).endVertex();
        buffer.pos(x2, y2, z2).color(r, g, b, a).tex(1, 0).endVertex();
        buffer.pos(x3, y3, z3).color(r, g, b, a).tex(1, 1).endVertex();
        buffer.pos(x4, y4, z4).color(r, g, b, a).tex(0, 1).endVertex();
    }

}
