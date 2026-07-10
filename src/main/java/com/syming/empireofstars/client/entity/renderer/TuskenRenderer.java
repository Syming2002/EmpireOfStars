package com.syming.empireofstars.client.entity.renderer;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.client.entity.model.TuskenModel;
import com.syming.empireofstars.entity.tusken.TuskenEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TuskenRenderer extends MobRenderer<TuskenEntity, TuskenModel<TuskenEntity>> {

    public static final ResourceLocation RAIDER_TUSKEN = new ResourceLocation(EmpireOfStarsMod.MOD_ID, "textures/entity/tusken/raider.png");

    public TuskenRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new TuskenModel<>(pContext.bakeLayer(TuskenModel.TUSKEN_LAYER)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(TuskenEntity pEntity) {
        return RAIDER_TUSKEN;
    }
}
