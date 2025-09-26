package com.syming.empireofstars.client.kybercrystal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.block.blockentity.KyberCrystalBlockEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class KyberCrystalBlockEntityRenderer implements BlockEntityRenderer<KyberCrystalBlockEntity> {

//    private static final ResourceLocation KYBER_CRYSTAL_TEXTURE = new ResourceLocation(EmpireOfStarsMod.MOD_ID, "textures/entity/kyber_crystal.png");
//
//    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
//    public static final ModelLayerLocation KYBER_CRYSTAL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EmpireOfStarsMod.MOD_ID, "kyber_crystal"), "main");
//    private final ModelPart kyberCrystal;

    public KyberCrystalBlockEntityRenderer(BlockEntityRendererProvider.Context pContext) {
//        this.kyberCrystal = pContext.bakeLayer(KYBER_CRYSTAL_LAYER_LOCATION);
    }

//    public static LayerDefinition createShardLayer() {
//        MeshDefinition meshdefinition = new MeshDefinition();
//        PartDefinition partdefinition = meshdefinition.getRoot();
//
//        PartDefinition kyberCrystal = partdefinition.addOrReplaceChild("kyberCrystal", CubeListBuilder.create(), PartPose.offset(0.5F, 16.0F, 0.5F));
//
//        PartDefinition firstShard = kyberCrystal.addOrReplaceChild("firstShard", CubeListBuilder.create().texOffs(2, 14).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
//
//        PartDefinition secondShard = kyberCrystal.addOrReplaceChild("secondShard", CubeListBuilder.create().texOffs(6, 14).mirror().addBox(-1.5F, -4.0F, -0.5F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, -0.3927F, 0.0F, 0.0F));
//
//        PartDefinition thirdShard = kyberCrystal.addOrReplaceChild("thirdShard", CubeListBuilder.create().texOffs(8, 14).addBox(-1.5F, -2.0F, -0.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 6.0F, -2.0F, 0.3927F, 0.0F, 0.0F));
//
//        PartDefinition fourthShard = kyberCrystal.addOrReplaceChild("fourthShard", CubeListBuilder.create().texOffs(10, 6).addBox(-1.5F, -2.0F, -0.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 6.0F, -1.0F, 0.0F, 0.0F, -0.3927F));
//
//        PartDefinition fifthShard = kyberCrystal.addOrReplaceChild("fifthShard", CubeListBuilder.create().texOffs(12, 8).addBox(-1.5F, -2.0F, -0.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 6.0F, 0.0F, 0.0F, 0.0F, 0.3927F));
//
//        return LayerDefinition.create(meshdefinition, 16, 16);
//    }

    @Override
    public void render(KyberCrystalBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
//        pPoseStack.pushPose();
//
//        KyberCrystalColor kyberCrystalColor = pBlockEntity.getColor();
//        BlockPos pos = pBlockEntity.getBlockPos();
//
//        float red = kyberCrystalColor.getRed();
//        float blue = kyberCrystalColor.getBlue();
//        float green = kyberCrystalColor.getGreen();
//        float alpha = 1.0F;
//
//        VertexConsumer builder = pBuffer.getBuffer(RenderType.entityTranslucent(KYBER_CRYSTAL_TEXTURE));
//
//        this.kyberCrystal.render(pPoseStack, builder, pPackedLight, pPackedOverlay, red, green, blue, alpha);
//
//        pPoseStack.popPose();

    }
}
