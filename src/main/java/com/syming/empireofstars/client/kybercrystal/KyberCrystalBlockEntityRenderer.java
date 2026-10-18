package com.syming.empireofstars.client.kybercrystal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.block.blockentity.KyberCrystalBlockEntity;
import com.syming.empireofstars.client.EOSRenderType;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class KyberCrystalBlockEntityRenderer implements BlockEntityRenderer<KyberCrystalBlockEntity> {

    public static final ResourceLocation KYBER_CRYSTAL_TEXTURE = new ResourceLocation(EmpireOfStarsMod.MOD_ID, "textures/block/kyber_crystal.png");
    private final KyberCrystalModel kyberCrystalModel;


    public KyberCrystalBlockEntityRenderer(BlockEntityRendererProvider.Context pContext) {

        this.kyberCrystalModel = new KyberCrystalModel(pContext.bakeLayer(KyberCrystalModel.KYBER_CRYSTAL_LAYER));
    }



    @Override
    public void render(KyberCrystalBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {

        Direction direction = pBlockEntity.getBlockState().getValue(KyberCrystalBlock.FACING);

        if (pBlockEntity.getBlockState().getBlock() instanceof KyberCrystalBlock kyberCrystalBlock) {
            pPoseStack.pushPose();

            switch (direction) {
                case DOWN:
                    pPoseStack.translate(0.5D, 0.75D, 0.5D);
                    break;
                case NORTH:
                    pPoseStack.translate(0.5D, 0.5D, 0.75D);
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                    break;
                case SOUTH:
                    pPoseStack.translate(0.5D, 0.5D, 0.25D);
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                    break;
                case EAST:
                    pPoseStack.translate(0.25D, 0.5D, 0.5D);
                    pPoseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                    break;
                case WEST:
                    pPoseStack.translate(0.75D, 0.5D, 0.5D);
                    pPoseStack.mulPose(Axis.ZP.rotationDegrees(270.0F));
                    break;
                case UP:
                    pPoseStack.translate(0.5D, 0.25D, 0.5D);
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
                    break;
            }

            KyberCrystalColor kyberCrystalColor = pBlockEntity.getKyberColor();

            float red = kyberCrystalColor.getRed();
            float blue = kyberCrystalColor.getBlue();
            float green = kyberCrystalColor.getGreen();
            float alpha = 0.75F;

            VertexConsumer emissive = pBuffer.getBuffer(EOSRenderType.kyberCrystalEmissive(KYBER_CRYSTAL_TEXTURE));

            this.kyberCrystalModel.renderShards(pPoseStack, emissive, LightTexture.FULL_BRIGHT, pPackedOverlay, red, green, blue, alpha, pBlockEntity);

            pPoseStack.popPose();
        }
    }
}
