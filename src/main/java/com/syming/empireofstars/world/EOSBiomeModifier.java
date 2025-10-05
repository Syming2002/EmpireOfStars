package com.syming.empireofstars.world;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.world.feature.EOSFeature;
import io.netty.bootstrap.BootstrapConfig;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.Bootstrap;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class EOSBiomeModifier {

    public static final ResourceKey<BiomeModifier> ADD_KYBER_CRYSTAL = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(EmpireOfStarsMod.MOD_ID, "add_kyber_crystal"));

    public static void bootstrap(BootstapContext<BiomeModifier> pContext) {
        var placedFeatures = pContext.lookup(Registries.PLACED_FEATURE);
        var biomes = pContext.lookup(Registries.BIOME);

        pContext.register(ADD_KYBER_CRYSTAL, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(EOSFeature.KYBER_CRYSTAL_PLACED)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));


    }

}
