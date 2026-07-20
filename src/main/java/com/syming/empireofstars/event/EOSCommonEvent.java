package com.syming.empireofstars.event;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.entity.EOSEntity;
import com.syming.empireofstars.entity.animal.BanthaEntity;
import com.syming.empireofstars.entity.sarlacc.SarlaccBeakEntity;
import com.syming.empireofstars.entity.sarlacc.SarlaccTentacleEntity;
import com.syming.empireofstars.entity.tusken.TuskenEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EmpireOfStarsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EOSCommonEvent {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EOSEntity.BANTHA.get(), BanthaEntity.createMobAttributes().build());
        event.put(EOSEntity.RAIDER_TUSKEN.get(), TuskenEntity.createTuskenAttributes().build());

        event.put(EOSEntity.SARLACC_BEAK.get(), SarlaccBeakEntity.createSarlaccBeakAttributes().build());
        event.put(EOSEntity.SARLACC_TENTACLE.get(), SarlaccTentacleEntity.createSarlaccTentacleAttributes().build());
    }
}
