package com.syming.empireofstars.world.structure;

import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EOSStructureTypes {

    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPE = DeferredRegister.create(Registries.STRUCTURE_TYPE, EmpireOfStarsMod.MOD_ID);

    public static final DeferredHolder<StructureType<?>, StructureType<SarlaccPitStructure>> SARLACC_PIT = STRUCTURE_TYPE.register("sarlacc_pit", () -> () -> SarlaccPitStructure.CODEC);


    public static void register(IEventBus eventBus) {
        STRUCTURE_TYPE.register(eventBus);
    }
}
