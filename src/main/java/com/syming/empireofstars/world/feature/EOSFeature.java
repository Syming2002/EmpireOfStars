package com.syming.empireofstars.world.feature;

import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
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


    public static final ResourceKey<ConfiguredFeature<?, ?>> KYBER_CRYSTAL_CONFIGURED =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(EmpireOfStarsMod.MOD_ID, "kyber_crystal"));

    public static final ResourceKey<PlacedFeature> KYBER_CRYSTAL_PLACED =
            ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(EmpireOfStarsMod.MOD_ID, "kyber_crystal_placed"));

    public static void bootstrapConfiguredFeature(BootstapContext<ConfiguredFeature<?, ?>> context) {
        context.register(KYBER_CRYSTAL_CONFIGURED, new ConfiguredFeature<>(EOSFeature.KYBER_CRYSTAL_FEATURE.get(), NoneFeatureConfiguration.INSTANCE));
    }

    public static void bootstrapPlacedFeature(BootstapContext<PlacedFeature> context) {
        context.register(KYBER_CRYSTAL_PLACED,
                new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(KYBER_CRYSTAL_CONFIGURED),
                        List.of(CountPlacement.of(2), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(40)), BiomeFilter.biome())));
    }

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
