package com.syming.empireofstars.world.feature;

import com.mojang.serialization.Codec;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.block.blockentity.KyberCrystalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.function.Predicate;

public class KyberCrystalFeature extends Feature<NoneFeatureConfiguration> {
    public KyberCrystalFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        BlockPos origin = pContext.origin();
        WorldGenLevel worldGenLevel = pContext.level();
        RandomSource randomSource = pContext.random();

        for (int i = 0; i < 10; i++) {
            BlockPos pos = origin.offset(
                    randomSource.nextInt(16) - 8,
                    randomSource.nextInt(8) - 3,
                    randomSource.nextInt(16) - 8);

            if (!worldGenLevel.getBlockState(pos).isAir()) continue;

            for (Direction dir : Direction.values()) {
                BlockPos neighbor = pos.relative(dir);
                BlockState neighborState = worldGenLevel.getBlockState(neighbor);

                if (neighborState.isSolid()) {
                    Direction facing = dir.getOpposite();

                    KyberCrystalColor kyberColor = KyberCrystalColor.values()[randomSource.nextInt(KyberCrystalColor.values().length)];

                    int shards = 1 + randomSource.nextInt(5);

                    BlockState crystalState = EOSBlock.KYBER_CRYSTAL_TYPES.get(kyberColor).get()
                            .defaultBlockState()
                            .setValue(KyberCrystalBlock.FACING, facing)
                            .setValue(KyberCrystalBlock.COLOR, kyberColor)
                            .setValue(KyberCrystalBlock.SHARDS, shards);

                    this.safeSetBlock(worldGenLevel, pos, crystalState, BlockBehaviour.BlockStateBase::isAir);

                    if (worldGenLevel.getBlockEntity(pos) instanceof KyberCrystalBlockEntity kyberCrystalBlockEntity) {
                        kyberCrystalBlockEntity.setKyberColor(kyberColor);
                        kyberCrystalBlockEntity.setShards(shards);
                    }
                    break;
                }
            }
        }
        return true;
    }
}
