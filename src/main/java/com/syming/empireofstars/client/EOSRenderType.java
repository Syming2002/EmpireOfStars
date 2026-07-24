package com.syming.empireofstars.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class EOSRenderType extends RenderType {
    public EOSRenderType(String pName, VertexFormat pFormat, VertexFormat.Mode pMode, int pBufferSize, boolean pAffectsCrumbling, boolean pSortOnUpload, Runnable pSetupState, Runnable pClearState) {
        super(pName, pFormat, pMode, pBufferSize, pAffectsCrumbling, pSortOnUpload, pSetupState, pClearState);
    }

    public static RenderType kyberCrystalEmissive(ResourceLocation pTextureLocation) {
        RenderType.CompositeState state = RenderType.CompositeState.builder()
                .setShaderState(RenderType.RENDERTYPE_ENTITY_TRANSLUCENT_EMISSIVE_SHADER)
                .setTextureState(new RenderStateShard.TextureStateShard(pTextureLocation, false, true))
                .setTransparencyState(RenderType.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderType.NO_CULL)
                .setLightmapState(RenderType.LIGHTMAP)
                .setOverlayState(RenderType.OVERLAY)
                .createCompositeState(true);

        return RenderType.create("kyber_crystal_emissive",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                256,
                false,
                true,
                state);
    }

    public static RenderType lightsaberOuter() {
        RenderType.CompositeState state = RenderType.CompositeState.builder()
                .setShaderState(RenderStateShard.RENDERTYPE_LIGHTNING_SHADER)
                .setTransparencyState(EOSRenderState.ADDITIVE_GLOW)
                .setCullState(NO_CULL)
                .setLightmapState(NO_LIGHTMAP)
                .setOverlayState(NO_OVERLAY)
                .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                .setDepthTestState(new DepthTestStateShard("lequal_depth", 515))
                .setOutputState(EOSRenderState.TRANSLUCENT_TARGET)
                .createCompositeState(false);

        return RenderType.create("lightsaber_outer",
                DefaultVertexFormat.POSITION_COLOR,
                VertexFormat.Mode.QUADS,
                256,
                false,
                true,
                state);
    }

    public static RenderType lightsaberInner() {
        RenderType.CompositeState state = RenderType.CompositeState.builder()
                .setShaderState(RenderType.POSITION_COLOR_SHADER)
                .setTransparencyState(RenderType.NO_TRANSPARENCY)
                .setCullState(RenderType.NO_CULL)
                .setLightmapState(RenderType.NO_LIGHTMAP)
                .setOverlayState(RenderType.NO_OVERLAY)
                .setWriteMaskState(RenderStateShard.COLOR_DEPTH_WRITE)
                .setDepthTestState(new DepthTestStateShard("lequal_depth", 515))
                .createCompositeState(false);

        return RenderType.create("lightsaber_inner",
                DefaultVertexFormat.POSITION_COLOR,
                VertexFormat.Mode.QUADS,
                256,
                false,
                true,
                state);
    }
}
