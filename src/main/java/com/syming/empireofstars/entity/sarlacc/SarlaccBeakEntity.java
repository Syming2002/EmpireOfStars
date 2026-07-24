package com.syming.empireofstars.entity.sarlacc;

import com.syming.empireofstars.entity.EOSEntity;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class SarlaccBeakEntity extends PathfinderMob {

    private static final EntityDataAccessor<Byte> ANIMATION = SynchedEntityData.defineId(SarlaccBeakEntity.class, EntityDataSerializers.BYTE);

    public static final byte IDLE_ANIMATION = 0;
    public static final float IDLE_ANIMATION_LENGTH_IN_SECONDS = 1.75F;
    public static final int IDLE_ANIMATION_LENGTH_IN_TICKS = (int) (IDLE_ANIMATION_LENGTH_IN_SECONDS * 20);

    public final AnimationState idleAnimationState = new AnimationState();

    public SarlaccBeakEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public SarlaccBeakEntity(Level pLevel) {
        super(EOSEntity.SARLACC_BEAK.get(), pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(ANIMATION, IDLE_ANIMATION);
    }

    public void setAnimation(byte pAnimation) {
        this.entityData.set(ANIMATION, pAnimation);
    }

    public byte getAnimation() {
        return this.entityData.get(ANIMATION);
    }

    public static AttributeSupplier.Builder createSarlaccBeakAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 48.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }

    @Override
    public void tick() {

        if (this.level().isClientSide()) {
            this.idleAnimationState.animateWhen(getAnimation() == IDLE_ANIMATION, this.tickCount);
        }

        super.tick();
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.PIG_DEATH;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }
}
