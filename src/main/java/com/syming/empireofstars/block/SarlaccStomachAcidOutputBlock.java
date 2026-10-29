package com.syming.empireofstars.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

public class SarlaccStomachAcidOutputBlock extends Block {
    public SarlaccStomachAcidOutputBlock() {
        super(Properties.of().isValidSpawn(((pState, pLevel, pPos, pValue) -> false)));
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide()) {
            BlockPos blockPos = pos.above();

            if (level.isEmptyBlock(blockPos)) {
                level.setBlockAndUpdate(blockPos, EOSBlock.SARLACC_STOMACH_ACID_BLOCK.get().defaultBlockState());
            }
        }
        super.stepOn(level, pos, state, entity);
    }
}
