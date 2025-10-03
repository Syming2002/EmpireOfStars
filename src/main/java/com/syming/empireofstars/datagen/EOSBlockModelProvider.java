package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.item.EOSItem;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EOSBlockModelProvider extends BlockModelProvider {
    public EOSBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EmpireOfStarsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Block> registryObject : EOSBlock.BLOCKS.getEntries()) {

            final Block block = registryObject.get();
            final String path = ForgeRegistries.BLOCKS.getKey(block).getPath();
            if (block instanceof KyberCrystalBlock) {
                registerKyberCrystalBlock(path);
            }
        }
    }

    private void registerKyberCrystalBlock(String name) {
        getBuilder(name).parent(new ModelFile.UncheckedModelFile("builtin/entity"));
        getBuilder(name).texture("particle", modLoc("block/kyber_crystal_particle"));
    }
}
