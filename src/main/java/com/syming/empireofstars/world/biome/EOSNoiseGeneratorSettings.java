package com.syming.empireofstars.world.biome;

import com.syming.empireofstars.EmpireOfStarsMod;
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


    public static void bootstrap(BootstapContext<NoiseGeneratorSettings> context) {
        context.register(TATOOINE, tatooine(context));
    }


    private static NoiseGeneratorSettings tatooine(BootstapContext<?> context) {
        return new NoiseGeneratorSettings(TATOOINE_NOISE_SETTINGS, Blocks.SAND.defaultBlockState(), Blocks.AIR.defaultBlockState(), NoiseRouterData.overworld(context.lookup(Registries.DENSITY_FUNCTION), context.lookup(Registries.NOISE), false, false), EOSSurfaceRuleData.tatooineSurface(), (new OverworldBiomeBuilder()).spawnTarget(), -64, false, false, true, false);
    }
}
