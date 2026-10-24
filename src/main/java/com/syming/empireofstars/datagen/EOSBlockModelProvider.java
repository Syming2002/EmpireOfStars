package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.*;
import com.syming.empireofstars.item.EOSItem;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

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

            if (block instanceof SarlaccSpearToothBlock) {
                for (Direction direction : Direction.values()) {
                    if (direction == Direction.DOWN) {
                        registerSarlaccSpearToothBlock(path);
                    }
                }
            }
        }
    }

    private void registerKyberCrystalBlock(String name) {
        getBuilder(name).parent(new ModelFile.UncheckedModelFile("builtin/entity"));
        getBuilder(name).texture("particle", modLoc("block/kyber_crystal_particle"));
    }

    private void registerSarlaccSpearToothBlock(String name) {
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(mcLoc("block/pointed_dripstone")));
        getBuilder(name).texture("cross", modLoc("block/sarlacc_spear_tooth"));
    }
}
