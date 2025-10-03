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
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LightsaberRenderer extends BlockEntityWithoutLevelRenderer {

    public static LightsaberRenderer INSTANCE;
    private final LightsaberBladeModel bladeModel;
    private final LightsaberStandardModel standardModel;

    private static final ResourceLocation INNER_BLADE = new ResourceLocation(EmpireOfStarsMod.MOD_ID, "textures/item/lightsaber/inner_blade.png");
    private static final ResourceLocation OUTER_BLADE = new ResourceLocation(EmpireOfStarsMod.MOD_ID, "textures/item/lightsaber/outer_blade.png");

    private static final ResourceLocation STANDARD_LIGHTSABER = new ResourceLocation(EmpireOfStarsMod.MOD_ID, "textures/item/lightsaber/standard_lightsaber.png");


    public LightsaberRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);

        this.bladeModel = new LightsaberBladeModel();

        this.standardModel = new LightsaberStandardModel(pEntityModelSet.bakeLayer(LightsaberStandardModel.STANDARD_LIGHTSABER_LAYER));
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
                if (tag != null && tag.contains("kyberColor")) {
                    KyberCrystalColor kyberCrystalColor = lightsaberItem.getColor(pStack);

                    pPoseStack.pushPose();

                    switch (pDisplayContext) {
                        case GUI -> {
                            pPoseStack.translate(0.95F, 0.975F, 0.48F);
                            pPoseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                            pPoseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
                            pPoseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                            pPoseStack.mulPose(Axis.XP.rotationDegrees(45.0F));
                            pPoseStack.scale(0.75F, 0.75F, 0.75F);
                        }
                        case FIRST_PERSON_RIGHT_HAND -> {
                            pPoseStack.translate(0.5F, 0.75F, 0.533F);
                        }
                        case FIRST_PERSON_LEFT_HAND -> {
                            pPoseStack.translate(0.55F, 0.75F, 0.533F);
                        }
                        case THIRD_PERSON_RIGHT_HAND, THIRD_PERSON_LEFT_HAND -> {
                            pPoseStack.translate(0.5F, 0.725F, 0.6F);
                        }
                        case GROUND -> {
                            pPoseStack.translate(0.5, 0.25, 0.5);
                            pPoseStack.scale(0.5F, 0.5F, 0.5F);
                        }
                        case FIXED -> {
                            pPoseStack.translate(0.25F, 0.75F, 0.52F);
                            pPoseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                            pPoseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
                            pPoseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                            pPoseStack.mulPose(Axis.XP.rotationDegrees(-45.0F));
                            pPoseStack.scale(0.5F, 0.5F, 0.5F);
                        }
                    }

                    RenderType solidType = EOSRenderType.lightsaberAdditive(INNER_BLADE);
                    VertexConsumer inner = pBuffer.getBuffer(solidType);
                    this.bladeModel.renderInner(pPoseStack, inner, 0xF000F0, pPackedOverlay);

                    RenderType glowType = EOSRenderType.lightsaberGlow(OUTER_BLADE);
                    VertexConsumer outer = pBuffer.getBuffer(glowType);
                    this.bladeModel.renderOuter(pPoseStack, outer, pPackedLight, pPackedOverlay, kyberCrystalColor.getRed(), kyberCrystalColor.getGreen(), kyberCrystalColor.getBlue(), 1.0F);

                    pPoseStack.popPose();
                }
            }

            pPoseStack.pushPose();

            switch (pDisplayContext) {
                case GUI -> {
                    pPoseStack.translate(0.85F, 0.85F, 0.5F);
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(-180.0F));
                    pPoseStack.mulPose(Axis.YP.rotationDegrees(-180.0F));
                    pPoseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(-45.0F));
                    pPoseStack.scale(0.75F, 0.75F, 0.75F);
                }
                case FIRST_PERSON_RIGHT_HAND, FIRST_PERSON_LEFT_HAND -> {
                    pPoseStack.translate(0.5F, 1.5F, 0.5F);
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(-180.0F));
                    pPoseStack.mulPose(Axis.YP.rotationDegrees(-180.0F));
                }
                case THIRD_PERSON_RIGHT_HAND, THIRD_PERSON_LEFT_HAND -> {
                    pPoseStack.translate(0.475F, 1.5F, 0.6F);
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(-180.0F));
                    pPoseStack.mulPose(Axis.YP.rotationDegrees(-180.0F));
                }
                case GROUND -> {
                    pPoseStack.translate(0.51F, 0.65F, 0.49F);
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(-180.0F));
                    pPoseStack.scale(0.5F, 0.5F, 0.5F);
                }
                case FIXED -> {
                    pPoseStack.translate(0.35F, 0.65F, 0.5F);
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                    pPoseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
                    pPoseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(-45.0F));
                    pPoseStack.scale(0.5F, 0.5F, 0.5F);
                }
            }

            RenderType solidType = RenderType.entitySolid(STANDARD_LIGHTSABER);
            VertexConsumer standard = pBuffer.getBuffer(solidType);
            this.standardModel.renderToBuffer(pPoseStack, standard, pPackedLight, pPackedOverlay,
                    1.0F, 1.0F, 1.0F, 1.0F);

            pPoseStack.popPose();
        }
    }
}
