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
            if (block instanceof SarlaccStomachAcidOutputBlock) {
                getBuilder(path)
                        .renderType(mcLoc("cutout"))
                        .parent(new ModelFile.UncheckedModelFile(
                                mcLoc("block/block")
                        ))
                        .texture("particle", mcLoc("block/nether_wart_block"))
                        .texture("base", mcLoc("block/nether_wart_block"))
                        .texture("overlay", modLoc("block/sarlacc_acid_stomach_output"))
                        .element()
                        .from(0, 0, 0)
                        .to(16, 16, 16)
                        .allFaces((direction, face) -> face
                                .texture("#base")
                                .cullface(direction)
                        )
                        .end()
                        .element()
                        .from(0, 0, 0)
                        .to(16, 16, 16)
                        .face(Direction.UP)
                        .texture("#overlay")
                        .end()
                        .end();
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
