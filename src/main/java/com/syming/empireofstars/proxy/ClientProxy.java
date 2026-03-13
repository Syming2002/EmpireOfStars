package com.syming.empireofstars.proxy;

import codechicken.lib.model.ModelRegistryHelper;
import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.item.EOSItem;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    @Override
    public void registerItemVariantsRenderer(Item item, int meta, String fileName) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(EmpireOfStarsMod.MOD_ID, fileName), "inventory"));
    }


    @SuppressWarnings("deprecation")
    @Override
    public void registerRenderers() {
        ModelRegistryHelper.registerItemRenderer(EOSItem.);
    }


    @Override
    public void registerModelBakeryVariants() {

    }

    @Override
    public void registerItemsVariantsRenderer() {
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

}
