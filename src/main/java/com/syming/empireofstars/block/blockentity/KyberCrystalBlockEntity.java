package com.syming.empireofstars.block.blockentity;

import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class KyberCrystalBlockEntity extends BlockEntity {
    private final List<Boolean> shards = new ArrayList<>(5);
    private KyberCrystalColor color;

    public KyberCrystalBlockEntity(BlockPos pPos, BlockState pBlockState, KyberCrystalColor pColor) {
        super(EOSBlockEntity.KYBER_CRYSTAL_BLOCK_ENTITY_TYPES.get(pColor).get(), pPos, pBlockState);

        this.color = pColor;

        randomizeShards();
    }

    public void setColor(KyberCrystalColor pColor) {
        this.color = pColor;
    }

    public KyberCrystalColor getColor() {
        return this.color;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);

        ListTag listTag = new ListTag();
        for (Boolean shard : this.shards) {
            CompoundTag shardTag = new CompoundTag();
            shardTag.putBoolean("shard", shard);
            listTag.add(shardTag);
        }
        pTag.put("shards", listTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        this.shards.clear();

        if (pTag.contains("shards", Tag.TAG_LIST)) {
            ListTag listTag = pTag.getList("shards", Tag.TAG_COMPOUND);
            for (int i = 0; i < listTag.size(); i++) {
                CompoundTag shardTag = listTag.getCompound(i);
                this.shards.add(shardTag.getBoolean("shard"));
            }
        }

    }

    public void randomizeShards() {
        this.shards.clear();

        RandomSource randomSource = this.level != null ? this.level.random : RandomSource.create();

        for (int i = 0; i < 5; i++) {
            this.shards.add(randomSource.nextBoolean());
        }

        if (this.shards.stream().noneMatch(Boolean::booleanValue)) {
            int pick = randomSource.nextInt(5);
            this.shards.set(pick, true);
        }
    }

    public boolean hasShards(int shardIndex) {
        return shardIndex >= 0 && shardIndex < this.shards.size() && this.shards.get(shardIndex);
    }


}
