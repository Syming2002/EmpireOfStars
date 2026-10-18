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
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

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
    public void renderByItem(ItemStack pStack, ItemDisplayContext pDisplayContext, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        super.renderByItem(pStack, pDisplayContext, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);

        CompoundTag tag = pStack.getTag();

        if (pStack.getItem() instanceof BlockItem blockItem) {
            if (blockItem.getBlock() instanceof KyberCrystalBlock kyberCrystalBlock) {
                if (tag != null) {
                    if (tag.contains("kybercolorName")) {
                        KyberCrystalColor kyberCrystalColor = KyberCrystalColor.getKyberColorNameFromStack(pStack);
                        if (tag.contains("shards")) {
                            int shardCount = tag.getInt("shards");
                            KyberCrystalBlockEntity tempEntity = new KyberCrystalBlockEntity(BlockPos.ZERO, kyberCrystalBlock.defaultBlockState(), kyberCrystalColor);
                            tempEntity.setShards(shardCount);

                            pPoseStack.pushPose();

                            VertexConsumer emissiveConsumer = pBuffer.getBuffer(EOSRenderType.kyberCrystalEmissive(KyberCrystalBlockEntityRenderer.KYBER_CRYSTAL_TEXTURE));


                            switch (pDisplayContext) {
                                case FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND, THIRD_PERSON_LEFT_HAND,
                                     THIRD_PERSON_RIGHT_HAND -> {
                                    pPoseStack.translate(0.5D, 0.65D, 0.5D);
                                    pPoseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
                                }
                                case GUI -> {
                                    pPoseStack.translate(0.5D, 0.5D, 0.5D);
                                    pPoseStack.mulPose(Axis.XP.rotationDegrees(210.0F));
                                    pPoseStack.mulPose(Axis.YP.rotationDegrees(45.0F));
                                }
                                case FIXED -> {
                                    pPoseStack.translate(0.5D, 0.5D, 0.5D);
                                    pPoseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
                                }
                                case GROUND -> {
                                    pPoseStack.translate(0.5D, 0.4D, 0.5D);
                                    pPoseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
                                    pPoseStack.scale(0.5F, 0.5F, 0.5F);
                                }
                            }



                            this.kyberCrystalModel.renderShards(pPoseStack, emissiveConsumer, LightTexture.FULL_BRIGHT, pPackedOverlay, kyberCrystalColor.getRed(), kyberCrystalColor.getGreen(), kyberCrystalColor.getBlue(), 0.75F, tempEntity);

                            pPoseStack.popPose();
                        }
                    }
                }
            }
        }
    }
}
