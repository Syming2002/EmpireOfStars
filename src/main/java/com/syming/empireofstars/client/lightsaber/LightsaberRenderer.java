package com.syming.empireofstars.client.lightsaber;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.client.EOSRenderType;
import com.syming.empireofstars.item.lightsaber.LightsaberItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

@OnlyIn(Dist.CLIENT)
public class LightsaberRenderer extends BlockEntityWithoutLevelRenderer {

    public static LightsaberRenderer INSTANCE;
    private final LightsaberBladeModel bladeModel;
    private final StandardLightsaberModel standardLightsaberModel;

//    private static final ResourceLocation INNER_BLADE = new ResourceLocation(EmpireOfStarsMod.MOD_ID, "textures/item/lightsaber/inner_blade.png");
//    private static final ResourceLocation OUTER_BLADE = new ResourceLocation(EmpireOfStarsMod.MOD_ID, "textures/item/lightsaber/outer_blade.png");

    private static final ResourceLocation STANDARD_LIGHTSABER_LIGHTSABER = new ResourceLocation(EmpireOfStarsMod.MOD_ID, "textures/item/lightsaber/standard_lightsaber.png");


    public LightsaberRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);

        this.bladeModel = new LightsaberBladeModel();

        this.standardLightsaberModel = new StandardLightsaberModel(pEntityModelSet.bakeLayer(StandardLightsaberModel.STANDARD_LIGHTSABER_LAYER));
    }

    public static LightsaberRenderer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LightsaberRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        }
        return INSTANCE;
    }

    @Override
    public void renderByItem(ItemStack pStack, ItemDisplayContext pDisplayContext, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        super.renderByItem(pStack, pDisplayContext, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);

        if (pStack.getItem() instanceof LightsaberItem lightsaberItem) {
            CompoundTag tag = pStack.getTag();

            if (LightsaberItem.isActive(pStack)) {
                if (tag != null && tag.contains("kybercolorCode")) {
                    int kyberCrystalColor = KyberCrystalColor.getKyberColorCodeFromStack(pStack);

                    int red   = (kyberCrystalColor >> 16) & 0xFF;
                    int green = (kyberCrystalColor >> 8) & 0xFF;
                    int blue  = kyberCrystalColor & 0xFF;

                    pPoseStack.pushPose();

                    switch (pDisplayContext) {
                        case GUI -> {
                            pPoseStack.translate(1.125F, 1.125F, 0.0F);
                            pPoseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                            pPoseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
                            pPoseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                            pPoseStack.mulPose(Axis.XP.rotationDegrees(45.0F));
                        }
                        case FIRST_PERSON_RIGHT_HAND, FIRST_PERSON_LEFT_HAND -> {
                            pPoseStack.translate(0.5F, 0.8F, 0.475F);
                            pPoseStack.mulPose(Axis.XP.rotationDegrees(15.0F));
                        }
                        case THIRD_PERSON_RIGHT_HAND, THIRD_PERSON_LEFT_HAND -> {
                            pPoseStack.translate(0.5F, 0.625F, 0.6375F);
                        }
                        case GROUND -> {
                            pPoseStack.translate(0.510, 0.55, 0.5);
                        }
                        case FIXED -> {
                            pPoseStack.translate(-0.125F, 1.125F, 0.525F);
                            pPoseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                            pPoseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
                            pPoseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                            pPoseStack.mulPose(Axis.XP.rotationDegrees(-45.0F));
                        }
                    }

                    RenderType glowType = EOSRenderType.lightsaberOuter();
                    VertexConsumer outer = pBuffer.getBuffer(glowType);
                    this.bladeModel.renderOuter(pPoseStack, outer, pPackedLight, NO_OVERLAY, red / 255.0F, green / 255.0F, blue / 255.0F);

                    RenderType solidType = EOSRenderType.lightsaberInner();
                    VertexConsumer inner = pBuffer.getBuffer(solidType);
                    this.bladeModel.renderInner(pPoseStack, inner, LightTexture.FULL_BRIGHT, NO_OVERLAY);


                    pPoseStack.popPose();
                }
            }

            pPoseStack.pushPose();

            switch (pDisplayContext) {
                case GUI -> {
                    pPoseStack.translate(0.525F, 0.5F, 0.0F);
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(-180.0F));
                    pPoseStack.mulPose(Axis.YP.rotationDegrees(-180.0F));
                    pPoseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(-45.0F));
                    pPoseStack.scale(0.5F, 0.5F, 0.5F);
                }
                case FIRST_PERSON_RIGHT_HAND, FIRST_PERSON_LEFT_HAND -> {
                    pPoseStack.translate(0.5F, 0.95F, 0.5F);
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(-180.0F));
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(15.0F));
                    pPoseStack.mulPose(Axis.YP.rotationDegrees(-180.0F));
                    pPoseStack.scale(0.5F, 0.5F, 0.5F);
                }
                case THIRD_PERSON_RIGHT_HAND, THIRD_PERSON_LEFT_HAND -> {
                    pPoseStack.translate(0.5F, 0.75F, 0.625F);
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(-180.0F));
                    pPoseStack.mulPose(Axis.YP.rotationDegrees(-180.0F));
                    pPoseStack.scale(0.5F, 0.5F, 0.5F);
                }
                case GROUND -> {
                    pPoseStack.translate(0.51F, 0.65F, 0.49F);
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(-180.0F));
                    pPoseStack.scale(0.5F, 0.5F, 0.5F);
                }
                case FIXED -> {
                    pPoseStack.translate(0.525F, 0.5F, 0.525F);
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                    pPoseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
                    pPoseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(-45.0F));
                    pPoseStack.scale(0.5F, 0.5F, 0.5F);
                }
            }

            RenderType solidType = RenderType.entitySolid(STANDARD_LIGHTSABER_LIGHTSABER);
            VertexConsumer standard = pBuffer.getBuffer(solidType);
            this.standardLightsaberModel.renderToBuffer(pPoseStack, standard, pPackedLight, pPackedOverlay,
                    1.0F, 1.0F, 1.0F, 1.0F);

            pPoseStack.popPose();
        }
    }
}
