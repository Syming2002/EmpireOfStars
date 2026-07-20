package com.syming.empireofstars.entity.sarlacc;

import com.syming.empireofstars.entity.EOSEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Path;
import org.jetbrains.annotations.Nullable;

public class SarlaccTentacleEntity extends PathfinderMob {
    private static final EntityDataAccessor<Byte> ANIMATION = SynchedEntityData.defineId(SarlaccTentacleEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Float> TENTACLE_HEIGHT_SCALE = SynchedEntityData.defineId(SarlaccTentacleEntity.class, EntityDataSerializers.FLOAT);

    private boolean initializedScale = false;

    public static final byte IDLE_ANIMATION = 0;
    public static final float IDLE_ANIMATION_LENGTH_IN_SECONDS = 3.0F;
    public static final int IDLE_ANIMATION_LENGTH_IN_TICKS = (int) (IDLE_ANIMATION_LENGTH_IN_SECONDS * 20);

    public final AnimationState idleAnimationState = new AnimationState();

    public SarlaccTentacleEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public SarlaccTentacleEntity(Level pLevel) {
        super(EOSEntity.SARLACC_TENTACLE.get(), pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(ANIMATION, IDLE_ANIMATION);
        this.entityData.define(TENTACLE_HEIGHT_SCALE, 1.0F);
    }

    public void setAnimation(byte pAnimation) {
        this.entityData.set(ANIMATION, pAnimation);
    }

    public byte getAnimation() {
        return this.entityData.get(ANIMATION);
    }

    public static AttributeSupplier.Builder createSarlaccTentacleAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_SPEED, 1.2D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D);
    }

    @Override
    public void tick() {

        if (this.level().isClientSide()) {
            this.idleAnimationState.animateWhen(getAnimation() == IDLE_ANIMATION, this.tickCount);
        }

        super.tick();

        if (!this.initializedScale) {
            refreshDimensions();
            this.initializedScale = true;
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putFloat("TentacleHeightScale", getTentacleHeightScale());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);

        if (pCompound.contains("TentacleHeightScale")) {
            setTentacleHeightScale(pCompound.getFloat("TentacleHeightScale"));
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }


    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        EntityDimensions dimensions = super.getDimensions(pPose);
        return EntityDimensions.scalable(dimensions.width, dimensions.height * getTentacleHeightScale());
    }

//    @Override
//    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
//        pSpawnData = super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
//        float tentacleScale = Mth.nextFloat(this.random, 0.8F, 5.0F);
//        setTentacleHeightScale(tentacleScale);
//        return pSpawnData;
//    }

    public float getTentacleHeightScale() {
        return this.entityData.get(TENTACLE_HEIGHT_SCALE);
    }

    public void setTentacleHeightScale(float tentacleHeightScale) {
        this.entityData.set(TENTACLE_HEIGHT_SCALE, tentacleHeightScale);
        refreshDimensions();
    }
}
