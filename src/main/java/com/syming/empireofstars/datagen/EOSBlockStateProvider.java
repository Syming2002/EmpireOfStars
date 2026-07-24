package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.block.SarlaccSpearToothBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EOSBlockStateProvider extends BlockStateProvider {
    public EOSBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EmpireOfStarsMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(EOSBlock.TATOOINE_SAND.get(), cubeAll(EOSBlock.TATOOINE_SAND.get()));

        for (KyberCrystalColor color : KyberCrystalColor.values()) {
            simpleBlock(EOSBlock.KYBER_CRYSTAL_TYPES.get(color).get(), models().getExistingFile(modLoc("block/kyber_crystal")));
        }

        getVariantBuilder(EOSBlock.SARLACC_SPEAR_TOOTH.get())
                .forAllStates(blockState -> {
                    Direction direction = blockState.getValue(SarlaccSpearToothBlock.FACING);

                    ModelFile modelFile = new ModelFile.UncheckedModelFile(modLoc("block/sarlacc_spear_tooth"));

                    int xRot = 0;
                    int yRot = 0;

                    switch (direction) {
                        case UP -> {
                            xRot = 0;
                            yRot = 0;
                        }
                        case DOWN -> {
                            xRot = 180;
                            yRot = 0;
                        }
                        case NORTH -> {
                            xRot = 90;
                            yRot = 0;
                        }
                        case EAST -> {
                            xRot = 90;
                            yRot = 90;
                        }
                        case SOUTH -> {
                            xRot = 90;
                            yRot = 180;
                        }
                        case WEST -> {
                            xRot = 90;
                            yRot = 270;
                        }
                    }

                    return ConfiguredModel.builder()
                            .modelFile(modelFile)
                            .rotationX(xRot)
                            .rotationY(yRot)
                            .build();
                });
    }
}
