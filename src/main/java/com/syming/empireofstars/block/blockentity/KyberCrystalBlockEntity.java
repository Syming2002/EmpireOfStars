package com.syming.empireofstars.block.blockentity;

import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Locale;

public class KyberCrystalBlockEntity extends BlockEntity {
    private KyberCrystalColor kybercolor;
    private int shard;

    public KyberCrystalBlockEntity(BlockPos pPos, BlockState pBlockState, KyberCrystalColor kyberCrystalColor) {
        super(EOSBlockEntity.KYBER_CRYSTAL_BLOCK_ENTITY_TYPES.get(kyberCrystalColor).get(), pPos, pBlockState);

        this.kybercolor = kyberCrystalColor;
    }

    @Override
    public void onLoad() {
        super.onLoad();

        if (this.getLevel() != null) {
            this.shard = getBlockState().getValue(KyberCrystalBlock.SHARDS);
            this.kybercolor = getBlockState().getValue(KyberCrystalBlock.COLOR);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        tag.putInt("shard", this.shard);
        tag.putString("kyberColorName", this.kybercolor.getSerializedName());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        this.shard = tag.getInt("shard");
        this.kybercolor = KyberCrystalColor.fromSerialized(tag.getString("kyberColorName"));
    }

    public void setKyberColor(KyberCrystalColor kyberColor) {
        this.kybercolor = kyberColor;
    }

    public KyberCrystalColor getKyberColor() {
        return this.kybercolor;
    }

    public int getShards() {
        return this.shard;
    }

    public void setShards(int shards) {
        this.shard = Mth.clamp(shards, 1, 5);
        setChanged();
    }
}
