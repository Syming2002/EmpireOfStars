package com.syming.empireofstars.block.blockentity;

import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class KyberCrystalBlockEntity extends BlockEntity {
    private KyberCrystalColor color;

    public KyberCrystalBlockEntity(BlockPos pPos, BlockState pBlockState, KyberCrystalColor color) {
        super(EOSBlockEntity.KYBER_CRYSTAL_BLOCK_ENTITY_TYPES.get(color).get(), pPos, pBlockState);


        this.color = color;
    }

    public KyberCrystalColor getColor() {
        return this.color;
    }

    public void setColor(KyberCrystalColor color) {
        this.color = color;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);

        pTag.putString("kyberColor", this.color.name());
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        if (pTag.contains("kyberColor")) {
            this.color = KyberCrystalColor.valueOf(pTag.getString("kyberColor"));
        }
    }
}
