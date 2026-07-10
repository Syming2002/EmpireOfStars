package com.syming.empireofstars.world.dimension;

import com.mojang.datafixers.util.Pair;
import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.world.biome.EOSBiome;
import com.syming.empireofstars.world.biome.EOSNoiseGeneratorSettings;
import net.lointain.cosmos.CosmosMod;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;
import java.util.OptionalLong;

public class EOSDimension {

    public static final ResourceKey<LevelStem> TATOOINE_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(EmpireOfStarsMod.MOD_ID, "tatooine"));

    public static final ResourceKey<Level> TATOOINE_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(EmpireOfStarsMod.MOD_ID, "tatooine"));

    public static final ResourceKey<DimensionType> TATOOINE_DIMENSION_TYPE_KEY = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(EmpireOfStarsMod.MOD_ID, "tatooine_type"));


    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(TATOOINE_DIMENSION_TYPE_KEY, new DimensionType(
                OptionalLong.of(6000),
                true,
                false,
                false,
                false,
                1.0F,
                true,
                false,
                0,
                512, 512,
                BlockTags.INFINIBURN_OVERWORLD,
                new ResourceLocation(CosmosMod.MODID, "solar_system"),
                1.0F,
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeHolderGetter = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType>  dimensionTypeHolderGetter = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGeneratorSettingsHolderGetter = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(Pair.of(
                                        Climate.parameters(0.8F, 0.2F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomeHolderGetter.getOrThrow(EOSBiome.TATOOINE_DESERT))

                        ))),
                noiseGeneratorSettingsHolderGetter.getOrThrow(EOSNoiseGeneratorSettings.TATOOINE));

        LevelStem stem = new LevelStem(dimensionTypeHolderGetter.getOrThrow(TATOOINE_DIMENSION_TYPE_KEY), noiseBasedChunkGenerator);

        context.register(TATOOINE_KEY, stem);
    }
}
