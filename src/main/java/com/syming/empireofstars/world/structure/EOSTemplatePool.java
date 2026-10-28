package com.syming.empireofstars.world.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.List;

public class EOSTemplatePool {

    public static final ResourceKey<StructureTemplatePool> SARLACC_PIT = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "sarlacc_pit"));
    public static final ResourceKey<StructureTemplatePool> SARLACC_PIT_BEAK = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "sarlacc_beak"));
    public static final ResourceKey<StructureTemplatePool> SARLACC_PIT_TENTACLES = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "sarlacc_tentacles"));


    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);
        HolderGetter<StructureProcessorList> processorListHolderGetter = context.lookup(Registries.PROCESSOR_LIST);

        Holder<StructureProcessorList> emptyProcessor = processorListHolderGetter.getOrThrow(ProcessorLists.EMPTY);
//        Holder<StructureProcessorList> sarlaccPitProcessor = processorListHolderGetter.getOrThrow(EOSProcessorList.SARLACC_PIT_PROCESSOR);

        Holder<StructureTemplatePool> empty = pools.getOrThrow(Pools.EMPTY);

        StructureTemplatePool sarlaccPool = new StructureTemplatePool(empty, ImmutableList.of(
                Pair.of(StructurePoolElement.single(EmpireOfStarsMod.MOD_ID + ':' + "sarlacc/sarlacc_pit", emptyProcessor), 1)), StructureTemplatePool.Projection.RIGID);

        StructureTemplatePool sarlaccBeakPool = new StructureTemplatePool(empty, ImmutableList.of(
                Pair.of(StructurePoolElement.single(EmpireOfStarsMod.MOD_ID + ':' + "sarlacc/sarlacc_beak", emptyProcessor), 1)), StructureTemplatePool.Projection.RIGID);

        StructureTemplatePool sarlaccTentaclesPool = new StructureTemplatePool(empty, ImmutableList.of(
                Pair.of(StructurePoolElement.single(EmpireOfStarsMod.MOD_ID + ':' + "sarlacc/tiny_sarlacc_tentacle", emptyProcessor), 7),
                Pair.of(StructurePoolElement.single(EmpireOfStarsMod.MOD_ID + ':' + "sarlacc/small_sarlacc_tentacle", emptyProcessor), 5),
                Pair.of(StructurePoolElement.single(EmpireOfStarsMod.MOD_ID + ':' + "sarlacc/medium_sarlacc_tentacle", emptyProcessor), 4),
                Pair.of(StructurePoolElement.single(EmpireOfStarsMod.MOD_ID + ':' + "sarlacc/tall_sarlacc_tentacle", emptyProcessor), 3),
                Pair.of(StructurePoolElement.single(EmpireOfStarsMod.MOD_ID + ':' + "sarlacc/giant_sarlacc_tentacle", emptyProcessor), 2)),
                StructureTemplatePool.Projection.RIGID);

        context.register(SARLACC_PIT, sarlaccPool);
        context.register(SARLACC_PIT_BEAK, sarlaccBeakPool);
        context.register(SARLACC_PIT_TENTACLES, sarlaccTentaclesPool);
    }

}
