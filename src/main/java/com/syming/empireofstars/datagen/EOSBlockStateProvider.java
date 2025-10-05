package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.KyberCrystalBlockKey;
import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EOSBlockStateProvider extends BlockStateProvider {
    public EOSBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EmpireOfStarsMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (KyberCrystalColor color : KyberCrystalColor.values()) {
            for (int shardCount = 1; shardCount <= 5; shardCount++) {
                simpleBlock(KyberCrystalBlockKey.KYBER_CRYSTAL_TYPES.get(new KyberCrystalBlockKey(shardCount, color)).get(), models().getExistingFile(modLoc("block/kyber_crystal")));
            }
        }
    }

}
