package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.datagen.tag.EOSBiomeTagProvider;
import com.syming.empireofstars.datagen.tag.EOSItemTagProvider;
import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.world.feature.EOSFeature;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = EmpireOfStarsMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
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
        EOSBlockTagsProvider blockTagGenerator = generator.addProvider(event.includeServer(), new EOSBlockTagsProvider(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new EOSItemTagProvider(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new EOSRecipeProvider(packOutput, lookupProvider));
    }

}
