package com.syming.empireofstars.entity;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.entity.animal.BanthaEntity;
import com.syming.empireofstars.entity.sarlacc.SarlaccBeakEntity;
import com.syming.empireofstars.entity.sarlacc.SarlaccTentacleEntity;
import com.syming.empireofstars.entity.tusken.RaiderTuskenEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EOSEntity {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, EmpireOfStarsMod.MOD_ID);


    // Animal
    public static final Supplier<EntityType<BanthaEntity>> BANTHA = ENTITY_TYPES.register("bantha", () -> EntityType.Builder.<BanthaEntity>of(BanthaEntity::new, MobCategory.CREATURE)
            .sized(1.875F, 1.875F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("bantha"));

    // Humanoid
    public static final Supplier<EntityType<RaiderTuskenEntity>> RAIDER_TUSKEN = ENTITY_TYPES.register("raider_tusken", () -> EntityType.Builder.<RaiderTuskenEntity>of(RaiderTuskenEntity::new, MobCategory.CREATURE)
            .sized(0.5F, 1.99F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("raider_tusken"));

    // Sarlacc
    public static final Supplier<EntityType<SarlaccBeakEntity>> SARLACC_BEAK = ENTITY_TYPES.register("sarlacc_beak", () -> EntityType.Builder.<SarlaccBeakEntity>of(SarlaccBeakEntity::new, MobCategory.CREATURE)
            .sized(1.125F, 1.0625F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("sarlacc_beak"));

    public static final Supplier<EntityType<SarlaccTentacleEntity>> SARLACC_TENTACLE = ENTITY_TYPES.register("sarlacc_tentacle", () -> EntityType.Builder.<SarlaccTentacleEntity>of(SarlaccTentacleEntity::new, MobCategory.CREATURE)
            .sized(0.25F, 2.125F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("sarlacc_tentacle"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
