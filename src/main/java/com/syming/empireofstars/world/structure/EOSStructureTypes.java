package com.syming.empireofstars.world.structure;

import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class EOSStructureTypes {

    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPE = DeferredRegister.create(Registries.STRUCTURE_TYPE, EmpireOfStarsMod.MOD_ID);

    public static final RegistryObject<StructureType<SarlaccPitStructure>> SARLACC_PIT = STRUCTURE_TYPE.register("sarlacc_pit", () -> SarlaccPitStructure.CODEC::codec);


    public static void register(IEventBus eventBus) {
        STRUCTURE_TYPE.register(eventBus);
    }
}
