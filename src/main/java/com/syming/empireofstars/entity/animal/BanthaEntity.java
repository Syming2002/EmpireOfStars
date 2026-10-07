package com.syming.empireofstars.entity.animal;

import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.entity.EOSEntity;
import com.syming.empireofstars.sound.EOSSound;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

import java.rmi.server.UID;
import java.util.UUID;

public class BanthaEntity extends AbstractChestedHorse {

    private static final Ingredient WHEAT_FOOD = Ingredient.of(Items.WHEAT, Blocks.HAY_BLOCK.asItem());

    public BanthaEntity(EntityType<? extends AbstractChestedHorse> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BanthaEntity(Level pLevel) {
        super(EOSEntity.BANTHA.get(),  pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.2D, WHEAT_FOOD, false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 32.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_SPEED, 1.2D)
                .add(Attributes.MOVEMENT_SPEED, 0.125D)
                .add(Attributes.JUMP_STRENGTH, 0.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D);
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return EOSSound.BANTHA_AMBIENT.get();
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return EOSEntity.BANTHA.get().create(pLevel);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return WHEAT_FOOD.test(pStack);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void containerChanged(Container pContainer) {
        super.containerChanged(pContainer);
    }

    @Override
    public void openCustomInventoryScreen(Player pPlayer) {
        super.openCustomInventoryScreen(pPlayer);
    }

    @Override
    public boolean isSaddleable() {
        return super.isSaddleable();
    }

    @Override
    public void equipSaddle(@Nullable SoundSource pSource) {
        super.equipSaddle(pSource);
    }

    @Override
    public boolean canBreed() {
        return true;
    }

    @Override
    public void spawnChildFromBreeding(ServerLevel pLevel, Animal pMate) {
        super.spawnChildFromBreeding(pLevel, pMate);
    }

    @Override
    public boolean isInLove() {
        return super.isInLove();
    }

    @Override
    public boolean canFallInLove() {
        return super.canFallInLove();
    }

    @Override
    public boolean canMate(Animal pOtherAnimal) {
        if (pOtherAnimal == this) {
            return false;
        }

        if (!(pOtherAnimal instanceof BanthaEntity bantha)) {
            return false;
        }

        return this.canParent() && bantha.canParent();
    }

    @Override
    protected boolean canParent() {
        return super.canParent();
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        boolean flag = !this.isBaby() && this.isTamed() && pPlayer.isSecondaryUseActive();
        if (!this.isVehicle() && !flag) {
            ItemStack itemstack = pPlayer.getItemInHand(pHand);
            if (!itemstack.isEmpty()) {
                if (this.isFood(itemstack)) {
                    return this.fedFood(pPlayer, itemstack);
                }

                if (!this.isTamed()) {
                    this.makeMad();
                    return InteractionResult.sidedSuccess(this.level().isClientSide());
                }
            }

        }
        return super.mobInteract(pPlayer, pHand);
    }

    @Override
    protected boolean handleEating(Player pPlayer, ItemStack pStack) {
        boolean handled = false;

        if (WHEAT_FOOD.test(pStack)) {
            if (this.getHealth() < this.getMaxHealth()) {
                this.heal(2.0F);
                handled = true;
            }

            if (this.isBaby()) {
                this.ageUp(20, true);
                handled = true;
            }

            if (!this.level().isClientSide()
                    && this.getAge() == 0
                    && this.canFallInLove()) {
                this.setInLove(pPlayer);
                handled = true;
            }
        }

        return handled;
    }

    @Override
    public void finalizeSpawnChildFromBreeding(ServerLevel pLevel, Animal pAnimal, @Nullable AgeableMob pBaby) {
        super.finalizeSpawnChildFromBreeding(pLevel, pAnimal, pBaby);
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public static boolean checkBanthaSpawnRules(EntityType<? extends Animal> pAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return pLevel.getBlockState(pPos.below()).is(EOSBlock.TATOOINE_SAND.get()) && isBrightEnoughToSpawn(pLevel, pPos);
    }
}
