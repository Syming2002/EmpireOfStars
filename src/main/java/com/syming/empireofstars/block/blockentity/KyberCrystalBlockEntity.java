package com.syming.empireofstars.block.blockentity;

import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class KyberCrystalBlockEntity extends BlockEntity {
    private KyberCrystalColor color;
    private int shards = 1;

    public KyberCrystalBlockEntity(BlockPos pPos, BlockState pBlockState, KyberCrystalColor pColor) {
        super(EOSBlockEntity.KYBER_CRYSTAL_BLOCK_ENTITY_TYPES.get(pColor).get(), pPos, pBlockState);

        this.color = pColor;
    }

    @Override
    public void onLoad() {
        super.onLoad();

        if (this.getLevel() != null) {
            this.shards = getBlockState().getValue(KyberCrystalBlock.SHARDS);
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("Shards", this.shards);
        tag.putString("color", this.color.getName());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.shards = tag.getInt("Shards");
        this.color = KyberCrystalColor.fromSerialized(tag.getString("color"));
    }

    public void setColor(KyberCrystalColor pColor) {
        this.color = pColor;
    }

    public KyberCrystalColor getColor() {
        return this.color;
    }

    public int getShards() {
        return this.shards;
    }

    public void setShards(int shards) {
        this.shards = Mth.clamp(shards, 1, 5);
        setChanged();
    }


}
