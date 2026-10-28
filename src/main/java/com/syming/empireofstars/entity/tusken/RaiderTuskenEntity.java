package com.syming.empireofstars.entity.tusken;

import com.syming.empireofstars.entity.EOSEntity;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class RaiderTuskenEntity extends TuskenEntity {
    public RaiderTuskenEntity(EntityType<? extends TuskenEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public RaiderTuskenEntity(Level pLevel) {
        super(EOSEntity.RAIDER_TUSKEN.get(), pLevel);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }


}
