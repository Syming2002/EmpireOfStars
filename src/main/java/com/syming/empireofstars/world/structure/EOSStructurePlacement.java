//package com.syming.empireofstars.world.structure;
//
//import com.syming.empireofstars.EmpireOfStarsMod;
//import net.minecraft.core.registries.Registries;
//import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
//import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
//import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.RegistryObject;
//
//public class EOSStructurePlacement {
//
//    public static final DeferredRegister<StructurePlacementType<?>> STRUCTURE_PLACEMENT_TYPE = DeferredRegister.create(Registries.STRUCTURE_PLACEMENT, EmpireOfStarsMod.MOD_ID);
//
//
//    public static final RegistryObject<StructurePlacementType<?>> SARLACC_PIT_PLACEMENT = STRUCTURE_PLACEMENT_TYPE.register("sarlacc_pit", () -> () -> StructurePlacement.CODEC);
//
//
//    public static void register(IEventBus eventBus) {
//        STRUCTURE_PLACEMENT_TYPE.register(eventBus);
//    }
//}
