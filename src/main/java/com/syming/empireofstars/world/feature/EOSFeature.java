package com.syming.empireofstars.world.feature;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class EOSFeature {

    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, EmpireOfStarsMod.MOD_ID);

    public static final RegistryObject<KyberCrystalFeature> KYBER_CRYSTAL_FEATURE = FEATURES.register("kyber_crystal", () -> new KyberCrystalFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<KyberCrystalClusterFeature> KYBER_CRYSTAL_GEODE_FEATURE = FEATURES.register("kyber_crystal_geode", () -> new KyberCrystalClusterFeature(GeodeConfiguration.CODEC));



    public static final ResourceKey<ConfiguredFeature<?, ?>> KYBER_CRYSTAL_CONFIGURED =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(EmpireOfStarsMod.MOD_ID, "kyber_crystal"));



    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_KYBER_CRYSTAL_GEODE_CONFIGURED =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(EmpireOfStarsMod.MOD_ID, "green_kyber_crystal_geode"));

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_KYBER_CRYSTAL_GEODE_CONFIGURED =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(EmpireOfStarsMod.MOD_ID, "blue_kyber_crystal_geode"));






    public static final ResourceKey<PlacedFeature> KYBER_CRYSTAL_PLACED =
            ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(EmpireOfStarsMod.MOD_ID, "kyber_crystal_placed"));




    public static final ResourceKey<PlacedFeature> GREEN_KYBER_CRYSTAL_GEODE_PLACED =
            ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(EmpireOfStarsMod.MOD_ID, "green_kyber_crystal_geode_placed"));

    public static final ResourceKey<PlacedFeature> BLUE_KYBER_CRYSTAL_GEODE_PLACED =
            ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(EmpireOfStarsMod.MOD_ID, "blue_kyber_crystal_geode_placed"));



    public static void bootstrapConfiguredFeature(BootstapContext<ConfiguredFeature<?, ?>> context) {
        context.register(KYBER_CRYSTAL_CONFIGURED, new ConfiguredFeature<>(EOSFeature.KYBER_CRYSTAL_FEATURE.get(), NoneFeatureConfiguration.INSTANCE));
        context.register(GREEN_KYBER_CRYSTAL_GEODE_CONFIGURED, kyberGeode(KyberCrystalColor.GREEN));
        context.register(BLUE_KYBER_CRYSTAL_GEODE_CONFIGURED, kyberGeode(KyberCrystalColor.BLUE));
    }

    public static void bootstrapPlacedFeature(BootstapContext<PlacedFeature> context) {
        context.register(KYBER_CRYSTAL_PLACED,
                new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(KYBER_CRYSTAL_CONFIGURED),
                        List.of(CountPlacement.of(2), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(40)), BiomeFilter.biome())));

        context.register(GREEN_KYBER_CRYSTAL_GEODE_PLACED,
                new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(GREEN_KYBER_CRYSTAL_GEODE_CONFIGURED),
                        List.of(RarityFilter.onAverageOnceEvery(48), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(16), VerticalAnchor.absolute(32)), BiomeFilter.biome())));

        context.register(BLUE_KYBER_CRYSTAL_GEODE_PLACED,
                new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(BLUE_KYBER_CRYSTAL_GEODE_CONFIGURED),
                        List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(8), VerticalAnchor.absolute(24)), BiomeFilter.biome())));
    }

    private static ConfiguredFeature<GeodeConfiguration, ?> kyberGeode(KyberCrystalColor kybercolor) {
        return new ConfiguredFeature<>(EOSFeature.KYBER_CRYSTAL_GEODE_FEATURE.get(),
                        new GeodeConfiguration(
                                new GeodeBlockSettings(
                                        BlockStateProvider.simple(Blocks.AIR),
                                        BlockStateProvider.simple(Blocks.COBBLESTONE),
                                        BlockStateProvider.simple(Blocks.STONE),
                                        BlockStateProvider.simple(Blocks.CALCITE),
                                        BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                                        List.of(EOSBlock.KYBER_CRYSTAL_TYPES.get(kybercolor).get().defaultBlockState()),
                                        BlockTags.FEATURES_CANNOT_REPLACE,
                                        BlockTags.GEODE_INVALID_BLOCKS),
                                new GeodeLayerSettings(
                                        1.9D,
                                        2.2D,
                                        3.2D,
                                        4.2D),
                                new GeodeCrackSettings(
                                        0.95D,
                                        2.0D,
                                        2),
                                0.35D,
                                0.083D,
                                true,
                                UniformInt.of(8, 12),
                                UniformInt.of(6, 8),
                                UniformInt.of(2, 4),
                                -16,
                                16,
                                0.05D,
                                1
                        ));

    }

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
