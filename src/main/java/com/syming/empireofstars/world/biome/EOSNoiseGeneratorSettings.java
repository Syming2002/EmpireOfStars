package com.syming.empireofstars.world.biome;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import net.lointain.cosmos.init.CosmosModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.*;
import net.minecraftforge.common.Tags;

import java.util.List;

public class EOSNoiseGeneratorSettings {

    public static final ResourceKey<NoiseGeneratorSettings> TATOOINE = ResourceKey.create(Registries.NOISE_SETTINGS, new ResourceLocation(EmpireOfStarsMod.MOD_ID, "tatooine"));
    public static final NoiseSettings TATOOINE_NOISE_SETTINGS = new NoiseSettings(-64, 384, 1, 2);

    public static final ResourceKey<NoiseGeneratorSettings> GHOMRASSEN = ResourceKey.create(Registries.NOISE_SETTINGS, new ResourceLocation(EmpireOfStarsMod.MOD_ID, "ghomrassen"));
    public static final NoiseSettings GHOMRASSEN_NOISE_SETTINGS = new NoiseSettings(-64, 256, 1, 2);


    public static void bootstrap(BootstapContext<NoiseGeneratorSettings> context) {
        context.register(TATOOINE, tatooine(context));

        context.register(GHOMRASSEN, ghomrassen(context));
    }


    private static NoiseGeneratorSettings tatooine(BootstapContext<?> context) {
        return new NoiseGeneratorSettings(TATOOINE_NOISE_SETTINGS, EOSBlock.TATOOINE_SAND.get().defaultBlockState(), Blocks.AIR.defaultBlockState(), NoiseRouterData.overworld(context.lookup(Registries.DENSITY_FUNCTION), context.lookup(Registries.NOISE), false, false), EOSSurfaceRuleData.tatooineSurface(), (new OverworldBiomeBuilder()).spawnTarget(), 63, false, false, true, false);
    }

    private static NoiseGeneratorSettings ghomrassen(BootstapContext<?> context) {
        return new NoiseGeneratorSettings(GHOMRASSEN_NOISE_SETTINGS, CosmosModBlocks.MOON_CLAYDUST.get().defaultBlockState(), Blocks.AIR.defaultBlockState(), NoiseRouterData.overworld(context.lookup(Registries.DENSITY_FUNCTION), context.lookup(Registries.NOISE), false, false), EOSSurfaceRuleData.ghomrassenRockyDesertSurface(), (new OverworldBiomeBuilder()).spawnTarget(), 63, false, false, true, false);
    }
}
