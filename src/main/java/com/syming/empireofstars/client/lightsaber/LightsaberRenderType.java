package com.syming.empireofstars.client.lightsaber;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class LightsaberRenderType extends RenderType {
    public LightsaberRenderType(String pName, VertexFormat pFormat, VertexFormat.Mode pMode, int pBufferSize, boolean pAffectsCrumbling, boolean pSortOnUpload, Runnable pSetupState, Runnable pClearState) {
        super(pName, pFormat, pMode, pBufferSize, pAffectsCrumbling, pSortOnUpload, pSetupState, pClearState);
    }

    public static RenderType lightsaberGlow(ResourceLocation pTextureLocation) {
        RenderType.CompositeState state = RenderType.CompositeState.builder()
                .setShaderState(RENDERTYPE_ENTITY_TRANSLUCENT_SHADER)
                .setTextureState(new TextureStateShard(pTextureLocation, false, false))
                .setTransparencyState(ADDITIVE_TRANSPARENCY)
                .setCullState(NO_CULL)
                .setLightmapState(LIGHTMAP)
                .setOverlayState(OVERLAY)
                .createCompositeState(true);

        return RenderType.create("lightsaber_glow",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                256,
                true,
                true,
                state);
    }

    public static RenderType lightsaberAdditive(ResourceLocation pTextureLocation) {
        RenderType.CompositeState state = RenderType.CompositeState.builder()
                .setShaderState(RenderType.RENDERTYPE_ENTITY_TRANSLUCENT_SHADER)
                .setTextureState(new RenderStateShard.TextureStateShard(pTextureLocation, false, false))
                .setTransparencyState(RenderType.ADDITIVE_TRANSPARENCY)
                .setCullState(RenderType.NO_CULL)
                .setLightmapState(RenderType.LIGHTMAP)
                .setOverlayState(RenderType.OVERLAY)
                .createCompositeState(true);

        return RenderType.create("lightsaber_additive",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                256,
                true,
                true,
                state);
    }
}
