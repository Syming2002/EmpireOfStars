package com.syming.empireofstars.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderStateShard;

public class EOSRenderState {

    public static final RenderStateShard.TransparencyStateShard VANILLA_LIGHTSABER_OUTER =
            new RenderStateShard.TransparencyStateShard(
                    "vanilla_lightsaber_outer",
                    () -> {
                        RenderSystem.enableBlend();
                        RenderSystem.blendFunc(
                                GlStateManager.SourceFactor.SRC_ALPHA,
                                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                    },
                    () -> {
                        RenderSystem.disableBlend();
                        RenderSystem.defaultBlendFunc();
                    }
            );

    public static final RenderStateShard.TransparencyStateShard SHADER_LIGHTSABER_OUTER =
            new RenderStateShard.TransparencyStateShard(
                    "shader_lightsaber_outer",
                    () -> {
                        RenderSystem.enableBlend();
                        RenderSystem.blendFuncSeparate(
                                GlStateManager.SourceFactor.SRC_ALPHA,
                                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                                GlStateManager.SourceFactor.ZERO,
                                GlStateManager.DestFactor.ONE
                        );
                    },

                    () -> {
                        RenderSystem.disableBlend();
                        RenderSystem.defaultBlendFunc();
                    });

//    public static final RenderStateShard.OutputStateShard TRANSLUCENT_TARGET =
//            new RenderStateShard.OutputStateShard(
//                    "translucent_target",
//                    () -> {
//                        if (Minecraft.useShaderTransparency()) {
//                            Minecraft.getInstance().levelRenderer
//                                    .getTranslucentTarget()
//                                    .bindWrite(false);
//                        }
//                    },
//                    () -> {
//                        if (Minecraft.useShaderTransparency()) {
//                            Minecraft.getInstance()
//                                    .getMainRenderTarget()
//                                    .bindWrite(false);
//                        }
//                    }
//            );
}
