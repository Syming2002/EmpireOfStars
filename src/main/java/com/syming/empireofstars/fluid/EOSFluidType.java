package com.syming.empireofstars.fluid;

import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.joml.Vector3f;

import java.util.function.Supplier;

public class EOSFluidType {

    public static final ResourceLocation WATER_STILL_RL = ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_overlay");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, EmpireOfStarsMod.MOD_ID);

    public static final Supplier<FluidType> SARLACC_STOMACH_ACID_FLUID_TYPE = FLUID_TYPES.register("sarlacc_stomach_acid", () -> new StomachAcidFluidType(FluidType.Properties.create(), WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL, 0xFFB7D62B, new Vector3f(183.1F / 255F, 214F / 255F, 43F / 255F)));


    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
