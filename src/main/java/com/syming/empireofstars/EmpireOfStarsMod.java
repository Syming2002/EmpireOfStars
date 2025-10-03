package com.syming.empireofstars;

import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.blockentity.EOSBlockEntity;
import com.syming.empireofstars.creativetab.EOSCreativeTab;
import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.network.EOSPacketHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EmpireOfStarsMod.MOD_ID)
public class EmpireOfStarsMod {
    public static final String MOD_ID = "empireofstars";

    public EmpireOfStarsMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EOSBlock.register(modEventBus);
        EOSBlockEntity.register(modEventBus);
        EOSCreativeTab.register(modEventBus);
        EOSItem.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            EOSPacketHandler.register();
        });
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}
