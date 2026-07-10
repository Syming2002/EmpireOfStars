package com.syming.empireofstars.world.biome;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.entity.EOSEntity;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class EOSBiome {

    public static final ResourceKey<Biome> TATOOINE_DESERT = ResourceKey.create(Registries.BIOME, new ResourceLocation(EmpireOfStarsMod.MOD_ID, "tatooine_desert"));

    public static void bootstrapBiome(BootstapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatureHolderGetter = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carver = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(TATOOINE_DESERT, tatooineDesert(placedFeatureHolderGetter, carver));
    }

    private static Biome tatooineDesert(HolderGetter<PlacedFeature> placedFeatureHolderGetter, HolderGetter<ConfiguredWorldCarver<?>> carver) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder(placedFeatureHolderGetter, carver);

        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EOSEntity.BANTHA.get(), 25, 1, 2));

        BiomeDefaultFeatures.addDesertVegetation(biomeGenerationSettings);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(35.0F)
                .downfall(0.0F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3F76E4)
                        .waterFogColor(0x3F76E4)
                        .fogColor(0xD6BC84)
                        .skyColor(0xD8C07A)
                        .foliageColorOverride(0x8F8752)
                        .grassColorOverride(0xAFA05B).build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(biomeGenerationSettings.build())
                .build();
    }

}
