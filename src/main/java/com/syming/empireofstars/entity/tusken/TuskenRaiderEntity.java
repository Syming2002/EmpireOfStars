package com.syming.empireofstars.entity.tusken;

import com.syming.empireofstars.entity.EOSEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class TuskenRaiderEntity extends TuskenEntity {
    public TuskenRaiderEntity(EntityType<? extends TuskenEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public TuskenRaiderEntity(Level pLevel) {
        super(EOSEntity.TUSKEN_RAIDER.get(), pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }


}
