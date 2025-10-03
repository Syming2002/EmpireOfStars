package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EOSBlockStateProvider extends BlockStateProvider {
    public EOSBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EmpireOfStarsMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (KyberCrystalColor color : KyberCrystalColor.values()) {
            simpleBlock(EOSBlock.KYBER_CRYSTAL_TYPES.get(color).get(), models().cubeAll("kyber_crystal", modLoc("block/kyber_crystal")));
        }
    }
}
