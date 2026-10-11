package com.syming.empireofstars.fluid;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.item.EOSItem;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EOSFluid {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, EmpireOfStarsMod.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_SARLACC_STOMACH_ACID = FLUIDS.register("source_sarlacc_stomach_acid", () -> new ForgeFlowingFluid.Source(EOSFluid.STOMACH_ACID_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SARLACC_STOMACH_ACID = FLUIDS.register("flowing_sarlacc_stomach_acid", () -> new ForgeFlowingFluid.Flowing(EOSFluid.STOMACH_ACID_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties STOMACH_ACID_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(EOSFluidType.SARLACC_STOMACH_ACID_FLUID_TYPE, SOURCE_SARLACC_STOMACH_ACID, FLOWING_SARLACC_STOMACH_ACID)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(EOSBlock.SARLACC_STOMACH_ACID_BLOCK).bucket(EOSItem.SARLACC_STOMACH_ACID_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
