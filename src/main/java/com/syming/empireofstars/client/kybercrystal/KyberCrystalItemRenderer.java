package com.syming.empireofstars.client.kybercrystal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.block.blockentity.KyberCrystalBlockEntity;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class KyberCrystalItemRenderer extends BlockEntityWithoutLevelRenderer {

    private final KyberCrystalBlockEntityRenderer kyberCrystalBlockEntityRenderer;

    public KyberCrystalItemRenderer(BlockEntityRendererProvider.Context pContext) {
        super(pContext.getBlockEntityRenderDispatcher(), pContext.getModelSet());

        this.kyberCrystalBlockEntityRenderer = new KyberCrystalBlockEntityRenderer(pContext);
    }

    @Override
    public void renderByItem(ItemStack pStack, ItemDisplayContext pDisplayContext, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        for (KyberCrystalColor color : KyberCrystalColor.values()) {
            KyberCrystalBlockEntity kyberCrystalBlockEntity = new KyberCrystalBlockEntity(BlockPos.ZERO, EOSBlock.KYBER_CRYSTAL_TYPES.get(color).get().defaultBlockState(), color);

            this.kyberCrystalBlockEntityRenderer.render(kyberCrystalBlockEntity, 0.0F, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        }
    }
}
