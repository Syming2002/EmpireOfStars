package com.syming.empireofstars.world.structure;

import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

import java.util.Optional;
import java.util.Random;

public class EOSStructureSet {

    public static final ResourceKey<StructureSet> SARLACC_PIT = ResourceKey.create(Registries.STRUCTURE_SET, new ResourceLocation(EmpireOfStarsMod.MOD_ID, "sarlacc_pit"));


    public static void bootstrap(BootstapContext<StructureSet> context) {
        HolderGetter<Structure> structureHolderGetter = context.lookup(Registries.STRUCTURE);
        context.register(SARLACC_PIT, new StructureSet(structureHolderGetter.getOrThrow(EOSStructure.SARLACC_PIT), new RandomSpreadStructurePlacement(78, 16, RandomSpreadType.LINEAR, RandomSource.create().nextInt(0, Integer.MAX_VALUE))));
    }

}
