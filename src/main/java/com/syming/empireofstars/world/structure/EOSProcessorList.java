//package com.syming.empireofstars.world.structure;
//
//import com.google.common.collect.ImmutableList;
//import com.syming.empireofstars.EmpireOfStarsMod;
//import net.minecraft.core.registries.Registries;
//import net.minecraft.data.worldgen.BootstapContext;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
//
//public class EOSProcessorList {
//
//    public static final ResourceKey<StructureProcessorList> SARLACC_PIT_PROCESSOR = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "sarlacc_pit"));
//
//    public static void bootstrap(BootstapContext<StructureProcessorList> context) {
//        context.register(SARLACC_PIT_PROCESSOR, new StructureProcessorList(ImmutableList.of(SarlaccPitStructureProcessor.INSTANCE)));
//    }
//
//}
