package com.syming.empireofstars.world.structure;

import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;

public class EOSStructure {

    public static final ResourceKey<Structure> SARLACC_PIT = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "sarlacc_pit"));


    public static void bootstrap(BootstrapContext<Structure> context) {
        context.register(SARLACC_PIT, SarlaccPitStructure.buildStructureConfig(context));
    }
}
