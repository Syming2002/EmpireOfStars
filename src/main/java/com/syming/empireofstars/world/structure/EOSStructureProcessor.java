//package com.syming.empireofstars.world.structure;
//
//import com.syming.empireofstars.EmpireOfStarsMod;
//import net.minecraft.core.registries.Registries;
//import net.minecraft.data.worldgen.BootstapContext;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
//import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.RegistryObject;
//
//import java.util.function.Supplier;
//
//public class EOSStructureProcessor {
//
//    public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSOR = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, EmpireOfStarsMod.MOD_ID);
//
//    public static final RegistryObject<StructureProcessorType<SarlaccPitStructureProcessor>> SARLACC_PIT_PROCESSOR = STRUCTURE_PROCESSOR.register("sarlacc_pit", () -> SarlaccPitStructureProcessor.PROCESSOR_CODEC::codec);
//
//
//    public static void register(IEventBus eventBus) {
//        STRUCTURE_PROCESSOR.register(eventBus);
//    }
//}
