package com.syming.empireofstars.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.RenderStateShard;
import org.embeddedt.modernfix.render.RenderState;

public class EOSRenderState {


    public static final RenderStateShard.TransparencyStateShard ADDITIVE_GLOW =
            new RenderStateShard.TransparencyStateShard(
                    "additive_glow",
                    () -> {
                        RenderSystem.enableBlend();
                        RenderSystem.blendFunc(
                                GlStateManager.SourceFactor.SRC_ALPHA,
                                GlStateManager.DestFactor.ONE
                        );
                    },
                    () -> {
                        RenderSystem.disableBlend();
                        RenderSystem.defaultBlendFunc();
                    }
            );

}
