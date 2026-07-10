package com.syming.empireofstars.entity.animal;

import com.syming.empireofstars.entity.EOSEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerListener;
import net.minecraft.world.SimpleContainer;
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

        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, WHEAT_FOOD, false));
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

    public static boolean checkBanthaSpawnRules(EntityType<? extends Animal> pAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return pLevel.getBlockState(pPos.below()).is(Blocks.SAND) && isBrightEnoughToSpawn(pLevel, pPos);
    }
}
