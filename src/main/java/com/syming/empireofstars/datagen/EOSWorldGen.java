package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.world.EOSBiomeModifier;
import com.syming.empireofstars.world.biome.EOSBiome;
import com.syming.empireofstars.world.biome.EOSNoiseGeneratorSettings;
import com.syming.empireofstars.world.dimension.EOSDimension;
import com.syming.empireofstars.world.feature.EOSFeature;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class EOSWorldGen extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.NOISE_SETTINGS, EOSNoiseGeneratorSettings::bootstrap)
            .add(Registries.DIMENSION_TYPE, EOSDimension::bootstrapType)
            .add(Registries.CONFIGURED_FEATURE, EOSFeature::bootstrapConfiguredFeature)
            .add(Registries.PLACED_FEATURE, EOSFeature::bootstrapPlacedFeature)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, EOSBiomeModifier::bootstrap)
            .add(Registries.BIOME, EOSBiome::bootstrapBiome)
            .add(Registries.LEVEL_STEM, EOSDimension::bootstrapStem);

    public EOSWorldGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(EmpireOfStarsMod.MOD_ID));
    }
}
