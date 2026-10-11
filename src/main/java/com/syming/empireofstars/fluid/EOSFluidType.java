package com.syming.empireofstars.fluid;

import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class EOSFluidType {

    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, EmpireOfStarsMod.MOD_ID);

    public static final RegistryObject<FluidType> SARLACC_STOMACH_ACID_FLUID_TYPE = FLUID_TYPES.register("sarlacc_stomach_acid", () -> new StomachAcidFluidType(FluidType.Properties.create(), WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL, 0xFFB7D62B, new Vector3f(183.1F / 255F, 214F / 255F, 43F / 255F)));


    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
