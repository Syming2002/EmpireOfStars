package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.*;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class EOSBlockModelProvider extends BlockModelProvider {
    public EOSBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EmpireOfStarsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (DeferredHolder<Block, ? extends Block> blockDeferredHolder : EOSBlock.BLOCKS.getEntries()) {

            final Block block = blockDeferredHolder.get();
            final String path = blockDeferredHolder.getId().getPath();
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
