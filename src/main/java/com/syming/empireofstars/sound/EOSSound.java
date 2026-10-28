package com.syming.empireofstars.sound;

import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EOSSound {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, EmpireOfStarsMod.MOD_ID);


    public static final Supplier<SoundEvent> BANTHA_AMBIENT = SOUNDS.register("bantha_ambient", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(EmpireOfStarsMod.MOD_ID, "bantha_ambient")));

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
