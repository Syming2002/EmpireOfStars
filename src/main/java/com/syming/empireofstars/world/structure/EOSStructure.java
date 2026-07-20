package com.syming.empireofstars.world.structure;

import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.registries.ForgeRegistries;

public class EOSStructure {

    public static final ResourceKey<Structure> SARLACC_PIT = ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(EmpireOfStarsMod.MOD_ID, "sarlacc_pit"));


    public static void bootstrap(BootstapContext<Structure> context) {
        context.register(SARLACC_PIT, SarlaccPitStructure.buildStructureConfig(context));
    }
}
