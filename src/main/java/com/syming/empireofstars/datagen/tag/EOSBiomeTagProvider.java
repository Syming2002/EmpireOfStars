package com.syming.empireofstars.datagen.tag;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.tag.EOSBiomeTag;
import com.syming.empireofstars.world.biome.EOSBiome;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EOSBiomeTagProvider extends BiomeTagsProvider {

    public static final TagKey<Biome> IS_TATOOINE = TagKey.create(Registries.BIOME, new ResourceLocation(EmpireOfStarsMod.MOD_ID, "is_tatooine"));


    public EOSBiomeTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, EmpireOfStarsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(EOSBiomeTagProvider.IS_TATOOINE)
                .add(EOSBiome.TATOOINE_CANYON)
                .add(EOSBiome.TATOOINE_DESERT);
    }

    @Override
    public String getName() {
        return "Empire Of Stars Biome Tags";
    }
}
