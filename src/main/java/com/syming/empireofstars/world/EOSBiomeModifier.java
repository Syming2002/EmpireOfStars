package com.syming.empireofstars.world;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.world.feature.EOSFeature;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

public class EOSBiomeModifier {

    public static final ResourceKey<BiomeModifier> ADD_KYBER_CRYSTAL = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "add_kyber_crystal"));

    public static final ResourceKey<BiomeModifier> ADD_GREEN_KYBER_CRYSTAL_GEODE = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "add_green_kyber_crystal_geode"));
    public static final ResourceKey<BiomeModifier> ADD_BLUE_KYBER_CRYSTAL_GEODE = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "add_blue_kyber_crystal_geode"));



    public static void bootstrap(BootstrapContext<BiomeModifier> pContext) {
        var placedFeatures = pContext.lookup(Registries.PLACED_FEATURE);
        var biomes = pContext.lookup(Registries.BIOME);

        HolderSet<Biome> greenBiomeHolderSet = HolderSet.direct(
                List.of(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.BIRCH_FOREST),
                        biomes.getOrThrow(Biomes.DARK_FOREST),
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE)
                )
        );

        HolderSet<Biome> blueBiomeHolderSet = HolderSet.direct(
                List.of(
                        biomes.getOrThrow(Biomes.SNOWY_PLAINS),
                        biomes.getOrThrow(Biomes.SNOWY_BEACH),
                        biomes.getOrThrow(Biomes.SNOWY_SLOPES),
                        biomes.getOrThrow(Biomes.SNOWY_TAIGA),
                        biomes.getOrThrow(Biomes.ICE_SPIKES),
                        biomes.getOrThrow(Biomes.TAIGA)
                )
        );

        pContext.register(ADD_KYBER_CRYSTAL, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(EOSFeature.KYBER_CRYSTAL_PLACED)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));

        pContext.register(ADD_GREEN_KYBER_CRYSTAL_GEODE, new BiomeModifiers.AddFeaturesBiomeModifier(
                greenBiomeHolderSet,
                HolderSet.direct(placedFeatures.getOrThrow(EOSFeature.GREEN_KYBER_CRYSTAL_GEODE_PLACED)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));

        pContext.register(ADD_BLUE_KYBER_CRYSTAL_GEODE, new BiomeModifiers.AddFeaturesBiomeModifier(
                blueBiomeHolderSet,
                HolderSet.direct(placedFeatures.getOrThrow(EOSFeature.BLUE_KYBER_CRYSTAL_GEODE_PLACED)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));
    }

}
