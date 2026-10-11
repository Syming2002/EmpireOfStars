package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EOSBlockTagsProvider extends BlockTagsProvider {
    public EOSBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EmpireOfStarsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        for (KyberCrystalColor kybercolor : KyberCrystalColor.values()) {
            for (int shardCount = 1; shardCount <= 5; shardCount++) {
                tag(BlockTags.MINEABLE_WITH_PICKAXE)
                        .add(EOSBlock.KYBER_CRYSTAL_TYPES.get(kybercolor).get());

                tag(BlockTags.NEEDS_IRON_TOOL)
                        .add(EOSBlock.KYBER_CRYSTAL_TYPES.get(kybercolor).get());
            }
        }

        tag(BlockTags.SAND).add(EOSBlock.TATOOINE_SAND.get());

        tag(BlockTags.DEAD_BUSH_MAY_PLACE_ON).add(EOSBlock.TATOOINE_SAND.get());
    }
}
