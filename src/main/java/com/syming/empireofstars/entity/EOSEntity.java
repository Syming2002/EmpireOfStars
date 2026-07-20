package com.syming.empireofstars.entity;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.entity.animal.BanthaEntity;
import com.syming.empireofstars.entity.sarlacc.SarlaccBeakEntity;
import com.syming.empireofstars.entity.sarlacc.SarlaccTentacleEntity;
import com.syming.empireofstars.entity.tusken.RaiderTuskenEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EOSEntity {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EmpireOfStarsMod.MOD_ID);


    // Animal
    public static final RegistryObject<EntityType<BanthaEntity>> BANTHA = ENTITY_TYPES.register("bantha", () -> EntityType.Builder.<BanthaEntity>of(BanthaEntity::new, MobCategory.CREATURE)
            .sized(1.875F, 1.875F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("bantha"));

    // Humanoid
    public static final RegistryObject<EntityType<RaiderTuskenEntity>> RAIDER_TUSKEN = ENTITY_TYPES.register("raider_tusken", () -> EntityType.Builder.<RaiderTuskenEntity>of(RaiderTuskenEntity::new, MobCategory.CREATURE)
            .sized(0.5F, 1.99F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("raider_tusken"));

    // Sarlacc
    public static final RegistryObject<EntityType<SarlaccBeakEntity>> SARLACC_BEAK = ENTITY_TYPES.register("sarlacc_beak", () -> EntityType.Builder.<SarlaccBeakEntity>of(SarlaccBeakEntity::new, MobCategory.CREATURE)
            .sized(1.125F, 1.0625F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("sarlacc_beak"));

    public static final RegistryObject<EntityType<SarlaccTentacleEntity>> SARLACC_TENTACLE = ENTITY_TYPES.register("sarlacc_tentacle", () -> EntityType.Builder.<SarlaccTentacleEntity>of(SarlaccTentacleEntity::new, MobCategory.CREATURE)
            .sized(0.25F, 2.125F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("sarlacc_tentacle"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
