package com.syming.empireofstars.fluid;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.item.EOSItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EOSFluid {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, EmpireOfStarsMod.MOD_ID);

    public static final Supplier<FlowingFluid> SOURCE_SARLACC_STOMACH_ACID = FLUIDS.register("source_sarlacc_stomach_acid", () -> new BaseFlowingFluid.Source(EOSFluid.STOMACH_ACID_FLUID_PROPERTIES));
    public static final Supplier<FlowingFluid> FLOWING_SARLACC_STOMACH_ACID = FLUIDS.register("flowing_sarlacc_stomach_acid", () -> new BaseFlowingFluid.Flowing(EOSFluid.STOMACH_ACID_FLUID_PROPERTIES));

    public static final BaseFlowingFluid.Properties STOMACH_ACID_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(EOSFluidType.SARLACC_STOMACH_ACID_FLUID_TYPE, SOURCE_SARLACC_STOMACH_ACID, FLOWING_SARLACC_STOMACH_ACID)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(EOSBlock.SARLACC_STOMACH_ACID_BLOCK).bucket(EOSItem.SARLACC_STOMACH_ACID_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
