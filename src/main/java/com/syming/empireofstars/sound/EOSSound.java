package com.syming.empireofstars.sound;

import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class EOSSound {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, EmpireOfStarsMod.MOD_ID);


    public static final RegistryObject<SoundEvent> BANTHA_AMBIENT = SOUNDS.register("bantha_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(EmpireOfStarsMod.MOD_ID, "bantha_ambient")));

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
