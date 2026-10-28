package com.syming.empireofstars;

import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.blockentity.EOSBlockEntity;
import com.syming.empireofstars.creativetab.EOSCreativeTab;
import com.syming.empireofstars.entity.EOSEntity;
import com.syming.empireofstars.fluid.EOSFluid;
import com.syming.empireofstars.fluid.EOSFluidType;
import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.network.ActiveLightsaber;
import com.syming.empireofstars.recipe.EOSRecipe;
import com.syming.empireofstars.sound.EOSSound;
import com.syming.empireofstars.world.feature.EOSFeature;
//import com.syming.empireofstars.world.structure.EOSStructurePlacement;
//import com.syming.empireofstars.world.structure.EOSStructureProcessor;
import com.syming.empireofstars.world.structure.EOSStructureTypes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EmpireOfStarsMod.MOD_ID)
public class EmpireOfStarsMod {
    public static final String MOD_ID = "empireofstars";

    public EmpireOfStarsMod(ModContainer modContainer, IEventBus modEventBus) {
        EOSBlock.register(modEventBus);
        EOSBlockEntity.register(modEventBus);
        EOSCreativeTab.register(modEventBus);
//        EOSDataComponent.register(modEventBus);
        EOSEntity.register(modEventBus);
        EOSFeature.register(modEventBus);
        EOSFluid.register(modEventBus);
        EOSFluidType.register(modEventBus);
        EOSItem.register(modEventBus);
        EOSRecipe.register(modEventBus);
//        EOSStructurePlacement.register(modEventBus);
//        EOSStructureProcessor.register(modEventBus);
        EOSStructureTypes.register(modEventBus);
        EOSSound.register(modEventBus);


        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerPackets);

        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    public void registerPackets(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar payloadRegistrar = event.registrar("1").optional();

        // Both
        payloadRegistrar.playBidirectional(ActiveLightsaber.TYPE, ActiveLightsaber.STREAM_CODEC, ActiveLightsaber::execute);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}
