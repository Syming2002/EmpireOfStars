package com.syming.empireofstars.client.kybercrystal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.block.blockentity.KyberCrystalBlockEntity;
import com.syming.empireofstars.client.EOSRenderType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

public class KyberCrystalItemRenderer extends BlockEntityWithoutLevelRenderer {

    public static KyberCrystalItemRenderer INSTANCE;

    private final KyberCrystalModel kyberCrystalModel;

    public static KyberCrystalItemRenderer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new KyberCrystalItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        }
        return INSTANCE;
    }


    public KyberCrystalItemRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);

        this.kyberCrystalModel = new KyberCrystalModel(pEntityModelSet.bakeLayer(KyberCrystalModel.KYBER_CRYSTAL_LAYER));
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
        super.renderByItem(stack, displayContext, poseStack, multiBufferSource, packedLight, packedOverlay);


        if (stack.getItem() instanceof BlockItem blockItem) {
            if (blockItem.getBlock() instanceof KyberCrystalBlock kyberCrystalBlock) {
                KyberCrystalColor kyberCrystalColor = KyberCrystalColor.getKyberColorNameFromStack(stack);
                if (kyberCrystalColor != null) {
                    int shardCount = KyberCrystalColor.getShardCountFromStack(stack);
                    KyberCrystalBlockEntity tempEntity = new KyberCrystalBlockEntity(BlockPos.ZERO, kyberCrystalBlock.defaultBlockState(), kyberCrystalColor);
                    tempEntity.setShards(shardCount);
                    tempEntity.setKyberColor(kyberCrystalColor);

                    poseStack.pushPose();

                    VertexConsumer emissiveConsumer = multiBufferSource.getBuffer(EOSRenderType.kyberCrystalEmissive(KyberCrystalBlockEntityRenderer.KYBER_CRYSTAL_TEXTURE));


                    switch (displayContext) {
                        case FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND, THIRD_PERSON_LEFT_HAND,
                             THIRD_PERSON_RIGHT_HAND -> {
                            poseStack.translate(0.5D, 0.65D, 0.5D);
                            poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
                        }
                        case GUI -> {
                            poseStack.translate(0.5D, 0.5D, 0.5D);
                            poseStack.mulPose(Axis.XP.rotationDegrees(210.0F));
                            poseStack.mulPose(Axis.YP.rotationDegrees(45.0F));
                        }
                        case FIXED -> {
                            poseStack.translate(0.5D, 0.5D, 0.5D);
                            poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
                        }
                        case GROUND -> {
                            poseStack.translate(0.5D, 0.4D, 0.5D);
                            poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
                            poseStack.scale(0.5F, 0.5F, 0.5F);
                        }
                    }

                    int color = FastColor.ARGB32.color(
                            191,
                            (int) (kyberCrystalColor.getRed() * 255.0F),
                            (int) (kyberCrystalColor.getGreen() * 255.0F),
                            (int) (kyberCrystalColor.getBlue() * 255.0F));

                    this.kyberCrystalModel.renderShards(poseStack, emissiveConsumer, LightTexture.FULL_BRIGHT, packedOverlay, color, tempEntity);

                    poseStack.popPose();
                }
            }
        }
    }
}

