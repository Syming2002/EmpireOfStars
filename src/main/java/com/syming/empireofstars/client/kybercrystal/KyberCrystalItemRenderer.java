package com.syming.empireofstars.client.kybercrystal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.client.EOSRenderType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
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

        pPoseStack.pushPose();

        try {
            CompoundTag tag = pStack.getTag();

            if (pStack.getItem() instanceof BlockItem blockItem) {
                if (blockItem.getBlock() instanceof KyberCrystalBlock kyberCrystalBlock) {
                    if (tag != null) {
                        if (tag.contains("kyberColor")) {
                            KyberCrystalColor kyberCrystalColor = kyberCrystalBlock.getColor();

                            VertexConsumer consumer = pBuffer.getBuffer(EOSRenderType.kyberCrystalGlow(KyberCrystalBlockEntityRenderer.KYBER_CRYSTAL_TEXTURE));

                            this.kyberCrystalModel.renderToBuffer(pPoseStack, consumer, pPackedLight, pPackedOverlay, kyberCrystalColor.getRed(), kyberCrystalColor.getGreen(), kyberCrystalColor.getBlue(), 0.5F);

                            switch (pDisplayContext) {
                                case FIRST_PERSON_LEFT_HAND -> {

                                }
                            }
                        }
                    }
                }
            }
        } finally {
            pPoseStack.popPose();
        }
    }
}
