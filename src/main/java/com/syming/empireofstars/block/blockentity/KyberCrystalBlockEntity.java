package com.syming.empireofstars.block.blockentity;

import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class KyberCrystalBlockEntity extends BlockEntity {
    private KyberCrystalColor kybercolor;
    private int shards = 1;

    public KyberCrystalBlockEntity(BlockPos pPos, BlockState pBlockState, KyberCrystalColor kyberCrystalColor) {
        super(EOSBlockEntity.KYBER_CRYSTAL_BLOCK_ENTITY_TYPES.get(kyberCrystalColor).get(), pPos, pBlockState);

        this.kybercolor = kyberCrystalColor;
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
        tag.putInt("shards", this.shards);
        tag.putString("kybercolorName", this.kybercolor.getSerializedName());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.shards = tag.getInt("shards");
        this.kybercolor = KyberCrystalColor.fromSerialized(tag.getString("kybercolorName"));
    }

    public void setColor(KyberCrystalColor kyberColor) {
        this.kybercolor = kyberColor;
    }

    public KyberCrystalColor getKyberColor() {
        return this.kybercolor;
    }

    public int getShards() {
        return this.shards;
    }

    public void setShards(int shards) {
        this.shards = Mth.clamp(shards, 1, 5);
        setChanged();
    }


}
