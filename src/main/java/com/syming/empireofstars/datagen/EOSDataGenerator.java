package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EmpireOfStarsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EOSDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new EOSItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new EOSBlockModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new EOSBlockStateProvider(packOutput, existingFileHelper));
    }

}
