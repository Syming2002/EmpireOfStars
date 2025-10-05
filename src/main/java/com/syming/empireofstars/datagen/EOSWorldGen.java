package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.world.EOSBiomeModifier;
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
            .add(Registries.CONFIGURED_FEATURE, EOSFeature::bootstrapConfiguredFeature)
            .add(Registries.PLACED_FEATURE, EOSFeature::bootstrapPlacedFeature)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, EOSBiomeModifier::bootstrap);

    public EOSWorldGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(EmpireOfStarsMod.MOD_ID));
    }
}
