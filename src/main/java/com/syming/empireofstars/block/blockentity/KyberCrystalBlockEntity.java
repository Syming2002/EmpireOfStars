package com.syming.empireofstars.block.blockentity;

import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class KyberCrystalBlockEntity extends BlockEntity {
    private final int MAX_SHARDS = 5;
    private int shardCount;
    private KyberCrystalColor color;

    public KyberCrystalBlockEntity(BlockPos pPos, BlockState pBlockState, KyberCrystalColor pColor, int pShardCount) {
        super(KyberCrystalBlockEntityKey.KYBER_CRYSTAL_BLOCK_ENTITY_TYPES.get(new KyberCrystalBlockEntityKey(pShardCount, pColor)).get(), pPos, pBlockState);

        this.color = pColor;

        this.shardCount = pShardCount;
    }

    public void setShardCount(int count) {
        this.shardCount = Math.min(count, MAX_SHARDS);
    }

    public int getShardCount() {
        return shardCount;
    }

    public boolean hasShards(int index) {
        return index < shardCount;
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("shardCount", this.shardCount);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("shardCount", Tag.TAG_INT)) {
            this.shardCount = Math.min(tag.getInt("shardCount"), MAX_SHARDS);
        }
    }

    public void setColor(KyberCrystalColor pColor) {
        this.color = pColor;
    }

    public KyberCrystalColor getColor() {
        return this.color;
    }
}
