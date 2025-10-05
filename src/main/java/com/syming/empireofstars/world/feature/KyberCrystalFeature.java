package com.syming.empireofstars.world.feature;

import com.mojang.serialization.Codec;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.block.KyberCrystalBlockKey;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.world.EOSBiomeModifier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

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
                    randomSource.nextInt(16) - 4,
                    randomSource.nextInt(12) - 3,
                    randomSource.nextInt(16) - 4);

            if (!worldGenLevel.getBlockState(pos).isAir()) continue;

            for (Direction dir : Direction.values()) {
                BlockPos neighbor = pos.relative(dir);
                BlockState neighborState = worldGenLevel.getBlockState(neighbor);

                if (neighborState.isSolid()) {
                    Direction facing = dir.getOpposite();

                    for (KyberCrystalColor color : KyberCrystalColor.values()) {
                        for (int shardCount = 1; shardCount <= 5; shardCount++) {
                            BlockState crystalState = KyberCrystalBlockKey.KYBER_CRYSTAL_TYPES.get(new KyberCrystalBlockKey(shardCount, color)).get()
                                    .defaultBlockState()
                                    .setValue(KyberCrystalBlock.FACING, facing);

                            worldGenLevel.setBlock(pos, crystalState, 2);
                        }
                    }
                    break;
                }
            }
        }

        return true;
    }
}
