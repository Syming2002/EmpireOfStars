package com.syming.empireofstars.world.biome;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

public final class EOSBiomeBuilder {

    private final Climate.Parameter[] temperatures = new Climate.Parameter[]
            {

                    Climate.Parameter.span(-1.0F, -0.45F),
                    Climate.Parameter.span(-0.45F, -0.15F),
                    Climate.Parameter.span(-0.15F, 0.2F),
                    Climate.Parameter.span(0.2F, 0.55F),
                    Climate.Parameter.span(0.55F, 1.0F)
            };
    private final Climate.Parameter[] humidities = new Climate.Parameter[]
            {
                    Climate.Parameter.span(-1.0F, -0.35F),
                    Climate.Parameter.span(-0.35F, -0.1F),
                    Climate.Parameter.span(-0.1F, 0.1F),
                    Climate.Parameter.span(0.1F, 0.3F),
                    Climate.Parameter.span(0.3F, 1.0F)
            };
    private final Climate.Parameter[] erosions = new Climate.Parameter[]{
            Climate.Parameter.span(-1.0F, -0.78F),
            Climate.Parameter.span(-0.78F, -0.375F),
            Climate.Parameter.span(-0.375F, -0.2225F),
            Climate.Parameter.span(-0.2225F, 0.05F),
            Climate.Parameter.span(0.05F, 0.45F),
            Climate.Parameter.span(0.45F, 0.55F),
            Climate.Parameter.span(0.55F, 1.0F)
    };


    private final Climate.Parameter inlandContinentalness = Climate.Parameter.span(-0.11F, 0.55F);
    private final Climate.Parameter nearInlandContinentalness = Climate.Parameter.span(-0.11F, 0.03F);
    private final Climate.Parameter midInlandContinentalness = Climate.Parameter.span(0.03F, 0.3F);
    private final Climate.Parameter farInlandContinentalness = Climate.Parameter.span(0.3F, 1.0F);

    private final ResourceKey<Biome>[][] EOS_MIDDLE_BIOMES = new ResourceKey[][]{
            {
                    Biomes.SNOWY_PLAINS,
                    Biomes.SNOWY_PLAINS,
                    Biomes.SNOWY_PLAINS,
                    Biomes.SNOWY_TAIGA,
                    Biomes.TAIGA
            },
            {
                    Biomes.PLAINS,
                    Biomes.PLAINS,
                    Biomes.FOREST,
                    Biomes.TAIGA,
                    Biomes.OLD_GROWTH_SPRUCE_TAIGA
            },
            {
                    Biomes.FLOWER_FOREST,
                    Biomes.PLAINS,
                    Biomes.FOREST,
                    Biomes.BIRCH_FOREST,
                    Biomes.DARK_FOREST
            },
            {
                    Biomes.SAVANNA,
                    Biomes.SAVANNA,
                    Biomes.FOREST,
                    Biomes.JUNGLE,
                    Biomes.JUNGLE
            },
            {
                    EOSBiome.TATOOINE_DESERT,
                    EOSBiome.TATOOINE_DESERT,
                    EOSBiome.TATOOINE_DESERT,
                    EOSBiome.TATOOINE_DESERT,
                    EOSBiome.TATOOINE_DESERT,
            }
    };


}
