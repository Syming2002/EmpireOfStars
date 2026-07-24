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
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class SarlaccTentacleEntity extends PathfinderMob {
    private static final EntityDataAccessor<Byte> ANIMATION = SynchedEntityData.defineId(SarlaccTentacleEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Float> TENTACLE_HEIGHT_SCALE = SynchedEntityData.defineId(SarlaccTentacleEntity.class, EntityDataSerializers.FLOAT);

    private static final EntityDataAccessor<Integer> TARGET_ID = SynchedEntityData.defineId(SarlaccTentacleEntity.class, EntityDataSerializers.INT);

    private boolean initializedScale = false;

    public int attackTick;

    public static final byte IDLE_ANIMATION = 0;
    public static final float IDLE_ANIMATION_LENGTH_IN_SECONDS = 3.0F;
    public static final int IDLE_ANIMATION_LENGTH_IN_TICKS = (int) (IDLE_ANIMATION_LENGTH_IN_SECONDS * 20);


    public static final byte GRAB_ANIMATION = 1;
    public static final float GRAB_ANIMATION_LENGTH_IN_SECONDS = 5.0F;
    public static final int GRAB_ANIMATION_LENGTH_IN_TICKS = (int) (GRAB_ANIMATION_LENGTH_IN_SECONDS * 20);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState grabAnimationState = new AnimationState();

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

        this.entityData.define(TARGET_ID, -1);
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
                .add(Attributes.FOLLOW_RANGE, 4.0D)
                .add(Attributes.ATTACK_SPEED, 1.2D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D);
    }

    @Override
    public void setTarget(@Nullable LivingEntity pTarget) {
        super.setTarget(pTarget);
        this.entityData.set(TARGET_ID, pTarget == null ? -1 : pTarget.getId());
    }

    @Override
    public @Nullable LivingEntity getTarget() {
        int id = this.entityData.get(TARGET_ID);
        Entity entity = this.level().getEntity(id);
        return (entity instanceof LivingEntity living) ? living : null;
    }

    @Override
    public void tick() {

        if (this.level().isClientSide()) {
            this.idleAnimationState.animateWhen(getAnimation() == IDLE_ANIMATION, this.tickCount);
            this.grabAnimationState.animateWhen(getAnimation() == GRAB_ANIMATION, this.tickCount);
        }

        super.tick();

        LivingEntity target = this.getTarget();



        if (target != null) {
            getLookControl().setLookAt(target, 30.0F, 30.0F);

            checkAttractionRadius();
            if (getAnimation() == GRAB_ANIMATION) {
                this.attackTick++;
                Vec3 tentaclePos = this.position();
                if (tentaclePos.distanceTo(target.position()) <= 2.0D) {
                    if (this.attackTick >= 20 && this.attackTick < 40) {
                        pullTarget(target);
                    }

                    if (this.attackTick == 40) {
                        throwTargetOpposite(target);
                    }

                    if (this.attackTick >= 100) {
                        setAnimation(IDLE_ANIMATION);
                        this.attackTick = 0;
                    }
                }
            }
        }

        if (target == null) {
            setAnimation(IDLE_ANIMATION);
            this.attackTick = 0;
        }

        if (!this.initializedScale) {
            refreshDimensions();
            this.initializedScale = true;
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
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

        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true, (livingEntity) -> {
            return !(livingEntity instanceof SarlaccBeakEntity || livingEntity instanceof SarlaccTentacleEntity);
        }));
    }


    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        EntityDimensions dimensions = super.getDimensions(pPose);
        return EntityDimensions.scalable(dimensions.width, dimensions.height * getTentacleHeightScale());
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        pSpawnData = super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        float tentacleScaleHeight = Mth.nextFloat(this.random, 1.0F, 5.0F);
        setTentacleHeightScale(tentacleScaleHeight);
        return pSpawnData;
    }

    public float getTentacleHeightScale() {
        return this.entityData.get(TENTACLE_HEIGHT_SCALE);
    }

    public void setTentacleHeightScale(float tentacleHeightScale) {
        this.entityData.set(TENTACLE_HEIGHT_SCALE, tentacleHeightScale);
        refreshDimensions();
    }

    private void pullTarget(LivingEntity target) {
        Vec3 tentaclePos = this.position();
        Vec3 direction = tentaclePos.subtract(target.position());

        Vec3 motion = direction.normalize().scale(0.1D);
        target.setDeltaMovement(motion);
    }

    private void throwTargetOpposite(LivingEntity target) {
        Vec3 forward = this.getLookAngle().normalize();
        Vec3 throwDirection = forward.scale(-1.0D);

        Vec3 velocity = throwDirection.scale(0.5D);

        target.setDeltaMovement(velocity);
    }

    private void checkAttractionRadius() {
        AABB radiusBox = this.getBoundingBox().inflate(getTentacleHeightScale());

        LivingEntity nearest =
                this.level().getNearestEntity(
                        this.level().getEntitiesOfClass(LivingEntity.class,
                                radiusBox),
                        TargetingConditions.DEFAULT,
                        this,
                        this.getX(),
                        this.getY(),
                        this.getZ());

        if (nearest != null && nearest.distanceTo(this) <= getTentacleHeightScale()) {
            setAnimation(GRAB_ANIMATION);
        }
    }
}
