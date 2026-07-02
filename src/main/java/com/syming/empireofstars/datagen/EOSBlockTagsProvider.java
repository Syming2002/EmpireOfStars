package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
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
        for (KyberCrystalColor color : KyberCrystalColor.values()) {
            for (int shardCount = 1; shardCount <= 5; shardCount++) {
                tag(BlockTags.MINEABLE_WITH_PICKAXE)
                        .add(EOSBlock.KYBER_CRYSTAL_TYPES.get(color).get());

                tag(BlockTags.NEEDS_IRON_TOOL)
                        .add(EOSBlock.KYBER_CRYSTAL_TYPES.get(color).get());
            }
        }
    }
}
