package com.syming.empireofstars.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.irisshaders.iris.api.v0.IrisApi;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class EOSRenderType extends RenderStateShard {

    private EOSRenderType() {
        super("eos_render_type", () -> {

        }, () -> {

        });
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
                .setShaderState(RenderStateShard.POSITION_COLOR_SHADER)
                .setTextureState(RenderStateShard.NO_TEXTURE)
                .setTransparencyState(IrisApi.getInstance().isShaderPackInUse() ? RenderStateShard.GLINT_TRANSPARENCY : EOSRenderState.VANILLA_LIGHTSABER_OUTER)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.NO_OVERLAY)
                .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                .setDepthTestState(new DepthTestStateShard("lequal_depth", 515))
//                .setOutputState(EOSRenderState.TRANSLUCENT_TARGET)
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
                .setTransparencyState(RenderStateShard.LIGHTNING_TRANSPARENCY)
                .setCullState(CullStateShard.NO_CULL)
                .setLightmapState(LightmapStateShard.LIGHTMAP)
                .setOverlayState(RenderType.NO_OVERLAY)
                .setWriteMaskState(RenderStateShard.COLOR_DEPTH_WRITE)
                .setDepthTestState(new DepthTestStateShard("lequal_depth", 515))
                .createCompositeState(false);

        return RenderType.create("lightsaber_inner",
                DefaultVertexFormat.POSITION_COLOR,
                VertexFormat.Mode.QUADS,
                256,
                false,
                false,
                state);
    }
}
