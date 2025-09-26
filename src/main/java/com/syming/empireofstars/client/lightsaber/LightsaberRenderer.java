package com.syming.empireofstars.client.lightsaber;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.item.lightsaber.LightsaberItem;
import com.syming.empireofstars.item.lightsaber.LightsaberType;
import com.syming.empireofstars.item.lightsaber.LightsaberTypeBlade;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.checkerframework.checker.units.qual.A;

public class LightsaberRenderer extends BlockEntityWithoutLevelRenderer {

    public static LightsaberRenderer INSTANCE;
    private final LightsaberBladeModel bladeModel;

    public LightsaberRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);

        this.bladeModel = new LightsaberBladeModel(pEntityModelSet.bakeLayer(LightsaberBladeModel.BLADE_LAYER));
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

        CompoundTag tag = pStack.getTag();

        String type = tag.getString("type");

        if (LightsaberItem.isActive(pStack)) {

            if (pStack.hasTag()) {
                LightsaberTypeBlade typeBlade = LightsaberTypeBlade.valueOf(type);


                switch (typeBlade) {
                    case SINGLE -> renderSingleBlade(pDisplayContext, pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pStack);
                }

                System.out.println("Rendering blade. Active tag = " + pStack.getOrCreateTag().getBoolean("active"));
            }

            System.out.println("Lightsaber is Active !");
        }

        System.out.println("Rendering is called");
    }

    public void renderSingleBlade(ItemDisplayContext pContext, PoseStack pPoseStack, MultiBufferSource pBuffer, int pLight, int pOverlay, ItemStack pStack) {
        CompoundTag tag = pStack.getTag();
        pPoseStack.pushPose();
        switch (pContext) {
            case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND -> {
                pPoseStack.translate(0.5F, 1.0F, 0.5F);
            }
            case FIRST_PERSON_RIGHT_HAND -> {
                pPoseStack.translate(1, 0.1F, 0.0F);
            }
            case FIRST_PERSON_LEFT_HAND -> {

            }
            case GUI -> {
                pPoseStack.mulPose(Axis.ZN.rotationDegrees(45));
                pPoseStack.scale(1.9F, 1.9F, 1.9F);
            }
            case FIXED -> {
                pPoseStack.mulPose(Axis.ZN.rotationDegrees(-45));
                pPoseStack.mulPose(Axis.YP.rotationDegrees(180));
                pPoseStack.translate(-0.7F, -0.5F, -0.5F);
                pPoseStack.scale(1.9F, 1.9F, 1.9F);
            }
            case GROUND -> {
                pPoseStack.translate(0.25F, 0.5F, 0.5F);
                pPoseStack.mulPose(Axis.ZN.rotationDegrees(45));
            }
        }

        if (tag != null) {
            renderLightsaberBlade(tag, pBuffer, pPoseStack, pLight, pOverlay,1.5F);
        }

        pPoseStack.popPose();
    }

    private void renderLightsaberBlade(CompoundTag pTag, MultiBufferSource pBuffer, PoseStack pPoseStack, int pLight, int pOverlay, float pHeight) {
        float length = 1.0F;

        KyberCrystalBlock kyberCrystalBlock = (KyberCrystalBlock) ForgeRegistries.BLOCKS.getValue(new ResourceLocation(pTag.getString("kyberColor")));
        float red = kyberCrystalBlock.getKyberCrystalColor().getRed();
        float green = kyberCrystalBlock.getKyberCrystalColor().getGreen();
        float blue = kyberCrystalBlock.getKyberCrystalColor().getBlue();

        pPoseStack.scale(1.2F, 1.0F, 1.2F);
        pPoseStack.translate(0.0F, pHeight * 1, 0.0F);

        if (length > 0.1F) {
            this.bladeModel.renderOuterBlade(pPoseStack, pBuffer.getBuffer(RenderType.solid()), pLight, pOverlay, red, green, blue, 0.5F);
        }

        pPoseStack.popPose();

        pPoseStack.pushPose();
        pPoseStack.scale(0.5F, 0.95F * length, 0.5F);
        pPoseStack.translate(0.0F, pHeight * 1.06F, 0.0F);
        if (length > 0.1F) {
            this.bladeModel.renderInnerBlade(pPoseStack, pBuffer.getBuffer(RenderType.solid()), pLight, pOverlay, red, green, blue, 1.0F);
        }

        pPoseStack.popPose();
    }
}
