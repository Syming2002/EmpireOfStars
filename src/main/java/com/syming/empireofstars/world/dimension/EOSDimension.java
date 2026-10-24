package com.syming.empireofstars.world.dimension;

import com.mojang.datafixers.util.Pair;
import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.world.biome.EOSBiome;
import com.syming.empireofstars.world.biome.EOSNoiseGeneratorSettings;
import net.lointain.cosmos.CosmosMod;
import net.lointain.cosmos.network.CosmosModVariables;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.OptionalLong;

public class EOSDimension {

    // Planets
    public static final ResourceKey<LevelStem> TATOOINE_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(EmpireOfStarsMod.MOD_ID, "tatooine"));

    public static final ResourceKey<Level> TATOOINE_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(EmpireOfStarsMod.MOD_ID, "tatooine"));

    public static final ResourceKey<DimensionType> TATOOINE_DIMENSION_TYPE_KEY = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(EmpireOfStarsMod.MOD_ID, "tatooine_type"));

    // Moons
    public static final ResourceKey<LevelStem> GHOMRASSEN_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(EmpireOfStarsMod.MOD_ID, "ghomrassen"));

    public static final ResourceKey<Level> GHOMRASSEN_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(EmpireOfStarsMod.MOD_ID, "ghomrassen"));

    public static final ResourceKey<DimensionType> GHOMRASSEN_DIMENSION_TYPE_KEY = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(EmpireOfStarsMod.MOD_ID, "ghomrassen_type"));


    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(TATOOINE_DIMENSION_TYPE_KEY, new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                false,
                1.0D,
                true,
                false,
                -64,
                384, 384,
                BlockTags.INFINIBURN_OVERWORLD,
                new ResourceLocation(EmpireOfStarsMod.MOD_ID, "tatooine_effects"),
                0.0F,
                new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 7), 0)));

        context.register(GHOMRASSEN_DIMENSION_TYPE_KEY, new DimensionType(
                OptionalLong.empty(),
                false,
                false,
                false,
                false,
                1.0D,
                true,
                false,
                -64,
                384, 384,
                BlockTags.INFINIBURN_OVERWORLD,
                new ResourceLocation(CosmosMod.MODID, "solar_system"),
                1.0F,
                new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 7), 0)));

    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeHolderGetter = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimensionTypeHolderGetter = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGeneratorSettingsHolderGetter = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator tatooineNoiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(
                                List.of(
                                        Pair.of(
                                                Climate.parameters(
                                                        Climate.Parameter.span(0.55F, 1.0F),
                                                        Climate.Parameter.span(-1.0F, -0.35F),
                                                        Climate.Parameter.span(0.2F, 1.0F),
                                                        Climate.Parameter.span(-1.0F, -0.375F),
                                                        Climate.Parameter.point(0.0F),
                                                        Climate.Parameter.span(-1.0F, 1.0F),
                                                        0.0F
                                                ),
                                                biomeHolderGetter.getOrThrow(
                                                        EOSBiome.TATOOINE_CANYON
                                                )
                                        ),
                                        Pair.of(
                                                Climate.parameters(
                                                        Climate.Parameter.span(0.55F, 1.0F),
                                                        Climate.Parameter.span(-1.0F, -0.35F),
                                                        Climate.Parameter.span(-0.11F, 1.0F),
                                                        Climate.Parameter.span(-0.375F, 1.0F),
                                                        Climate.Parameter.point(0.0F),
                                                        Climate.Parameter.span(-1.0F, 1.0F),
                                                        0.0F
                                                ),
                                                biomeHolderGetter.getOrThrow(
                                                        EOSBiome.TATOOINE_DESERT
                                                )
                                        )
                                ))),
                noiseGeneratorSettingsHolderGetter.getOrThrow(EOSNoiseGeneratorSettings.TATOOINE));

        NoiseBasedChunkGenerator ghomrassenNoiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(Pair.of(
                                Climate.parameters(0.8F, 0.2F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomeHolderGetter.getOrThrow(EOSBiome.GHOMRASSEN_ROCKY_DESERT))

                        ))),
                noiseGeneratorSettingsHolderGetter.getOrThrow(EOSNoiseGeneratorSettings.GHOMRASSEN));

        LevelStem tatooineStem = new LevelStem(dimensionTypeHolderGetter.getOrThrow(TATOOINE_DIMENSION_TYPE_KEY), tatooineNoiseBasedChunkGenerator);

        LevelStem ghomrassenStem = new LevelStem(dimensionTypeHolderGetter.getOrThrow(GHOMRASSEN_DIMENSION_TYPE_KEY), ghomrassenNoiseBasedChunkGenerator);

        context.register(TATOOINE_KEY, tatooineStem);

        context.register(GHOMRASSEN_KEY, ghomrassenStem);
    }
}
