package com.syming.empireofstars.event;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.entity.EOSEntity;
import com.syming.empireofstars.entity.animal.BanthaEntity;
import com.syming.empireofstars.entity.sarlacc.SarlaccBeakEntity;
import com.syming.empireofstars.entity.sarlacc.SarlaccTentacleEntity;
import com.syming.empireofstars.entity.tusken.TuskenEntity;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = EmpireOfStarsMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class EOSCommonEvent {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EOSEntity.BANTHA.get(), BanthaEntity.createMobAttributes().build());
        event.put(EOSEntity.RAIDER_TUSKEN.get(), TuskenEntity.createTuskenAttributes().build());

        event.put(EOSEntity.SARLACC_BEAK.get(), SarlaccBeakEntity.createSarlaccBeakAttributes().build());
        event.put(EOSEntity.SARLACC_TENTACLE.get(), SarlaccTentacleEntity.createSarlaccTentacleAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(EOSEntity.BANTHA.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BanthaEntity::checkBanthaSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
