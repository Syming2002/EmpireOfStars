package com.syming.empireofstars.entity;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.entity.animal.BanthaEntity;
import com.syming.empireofstars.entity.tusken.TuskenRaiderEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.internal.ForgeBindings;
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
    public static final RegistryObject<EntityType<TuskenRaiderEntity>> TUSKEN_RAIDER = ENTITY_TYPES.register("tusken_raider", () -> EntityType.Builder.<TuskenRaiderEntity>of(TuskenRaiderEntity::new, MobCategory.CREATURE)
            .sized(0.5F, 1.99F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("tusken_raider"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
