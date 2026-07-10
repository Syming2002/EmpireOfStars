package com.syming.empireofstars;

import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.blockentity.EOSBlockEntity;
import com.syming.empireofstars.creativetab.EOSCreativeTab;
import com.syming.empireofstars.entity.EOSEntity;
import com.syming.empireofstars.entity.animal.BanthaEntity;
import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.network.EOSPacketHandler;
import com.syming.empireofstars.world.biome.EOSBiome;
import com.syming.empireofstars.world.biome.EOSSurfaceRuleData;
//import com.syming.empireofstars.world.biome.TatooineRegion;
import com.syming.empireofstars.world.feature.EOSFeature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
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
        EOSEntity.register(modEventBus);
        EOSFeature.register(modEventBus);
        EOSItem.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            EOSPacketHandler.register();

            SpawnPlacements.register(EOSEntity.BANTHA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BanthaEntity::checkBanthaSpawnRules);
        });
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}
