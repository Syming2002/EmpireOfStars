package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.datagen.tag.EOSBiomeTagProvider;
import com.syming.empireofstars.world.feature.EOSFeature;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = EmpireOfStarsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EOSDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new EOSItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new EOSBlockModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new EOSBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new EOSLangENProvider(packOutput));

        DatapackBuiltinEntriesProvider worldGen = new EOSWorldGen(packOutput, lookupProvider);

        CompletableFuture<HolderLookup.Provider> providerCompletableFuture = worldGen.getRegistryProvider();

        generator.addProvider(event.includeServer(), new EOSBiomeTagProvider(packOutput, providerCompletableFuture, existingFileHelper));
        generator.addProvider(event.includeServer(), worldGen);
        generator.addProvider(event.includeServer(), new EOSBlockTagsProvider(packOutput, lookupProvider, existingFileHelper));
    }

}
