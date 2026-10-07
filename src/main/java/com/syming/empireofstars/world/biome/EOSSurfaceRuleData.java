package com.syming.empireofstars.world.biome;

import com.google.common.collect.ImmutableList;
import com.syming.empireofstars.block.EOSBlock;
import net.lointain.cosmos.init.CosmosModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class EOSSurfaceRuleData {

    private static final SurfaceRules.RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);

    public static SurfaceRules.RuleSource tatooineSurface() {

        return SurfaceRules.sequence(

                SurfaceRules.ifTrue(
                        SurfaceRules.verticalGradient(
                                "bedrock_floor",
                                VerticalAnchor.bottom(),
                                VerticalAnchor.aboveBottom(2)
                        ),
                        BEDROCK
                ),

                SurfaceRules.ifTrue(
                        SurfaceRules.verticalGradient(
                                "deepslate",
                                VerticalAnchor.absolute(0),
                                VerticalAnchor.absolute(8)
                        ),
                        DEEPSLATE
                ),

                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(EOSBiome.TATOOINE_DESERT),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.state(EOSBlock.TATOOINE_SAND.get().defaultBlockState())
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.state(EOSBlock.TATOOINE_SAND.get().defaultBlockState())
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.DEEP_UNDER_FLOOR,
                                        SurfaceRules.state(EOSBlock.TATOOINE_SANDSTONE.get().defaultBlockState())
                                )
                        )
                ),

                SurfaceRules.state(Blocks.BASALT.defaultBlockState())
        );
    }

    public static SurfaceRules.RuleSource ghomrassenRockyDesertSurface() {

        return SurfaceRules.sequence(

                SurfaceRules.ifTrue(
                        SurfaceRules.verticalGradient(
                                "bedrock_floor",
                                VerticalAnchor.bottom(),
                                VerticalAnchor.aboveBottom(5)
                        ),
                        BEDROCK
                ),

                SurfaceRules.ifTrue(
                        SurfaceRules.verticalGradient(
                                "deepslate",
                                VerticalAnchor.absolute(0),
                                VerticalAnchor.absolute(8)
                        ),
                        DEEPSLATE
                ),

                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(EOSBiome.GHOMRASSEN_ROCKY_DESERT),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.state(CosmosModBlocks.MOON_CLAYDUST.get().defaultBlockState())
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.state(CosmosModBlocks.SILICONEDMOONCLAY.get().defaultBlockState())
                                )
                        )
                ),

                SurfaceRules.state(CosmosModBlocks.SILICONEDMOONCLAY.get().defaultBlockState())
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block pBlock) {
        return SurfaceRules.state(pBlock.defaultBlockState());
    }
}
