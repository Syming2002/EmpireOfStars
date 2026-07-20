//package com.syming.empireofstars.world.structure;
//
//import com.mojang.serialization.Codec;
//import com.mojang.serialization.MapCodec;
//import com.mojang.serialization.codecs.RecordCodecBuilder;
//import net.minecraft.core.BlockPos;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.level.LevelReader;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
//import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
//import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
//import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
//import org.jetbrains.annotations.Nullable;
//
//public class SarlaccPitStructureProcessor extends StructureProcessor {
//
//    public static final SarlaccPitStructureProcessor INSTANCE = new SarlaccPitStructureProcessor();
//    public static final MapCodec<SarlaccPitStructureProcessor> PROCESSOR_CODEC = MapCodec.unit(() -> INSTANCE);
//
//    private SarlaccPitStructureProcessor() {
//
//    }
//
//
//    @Override
//    public @Nullable StructureTemplate.StructureBlockInfo process(LevelReader levelReader, BlockPos pos, BlockPos piecePos, StructureTemplate.StructureBlockInfo blockInfo, StructureTemplate.StructureBlockInfo modifiedBlockInfo, StructurePlaceSettings placeSettings, @Nullable StructureTemplate template) {
//        RandomSource randomSource = placeSettings.getRandom(modifiedBlockInfo.pos());
//
//        BlockState blockState = modifiedBlockInfo.state();
//        Block block = blockState.getBlock();
//
//        int y = modifiedBlockInfo.pos().getY();
//        int x = modifiedBlockInfo.pos().getX();
//        int z = modifiedBlockInfo.pos().getZ();
//
//        if (block == Blocks.AIR) {
//
//            int sizeX = 13;
//            int sizeZ = 13;
//
//            int centerX = sizeX / 2;
//            int centerZ = sizeZ / 2;
//
//
//            double distance = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(z - centerZ, 2));
//
//
//            boolean isBorder = distance > 10;
//
//            if (isBorder) {
//                if (y > 18 && y < 23) {
//                    if (randomSource.nextFloat() >= 0.25F) {
//                        return new StructureTemplate.StructureBlockInfo(modifiedBlockInfo.pos(), Blocks.SAND.defaultBlockState(), null);
//                    }
//
//                    if (randomSource.nextFloat() <= 0.5F) {
//                        return new StructureTemplate.StructureBlockInfo(modifiedBlockInfo.pos(), Blocks.SANDSTONE.defaultBlockState(), null);
//                    }
//                }
//            }
//            if (y > 0 && y <= 18) {
//                if (randomSource.nextFloat() >= 0.25F) {
//                    return new StructureTemplate.StructureBlockInfo(modifiedBlockInfo.pos(), Blocks.STONE.defaultBlockState(), null);
//                }
//
//                if (randomSource.nextFloat() <= 0.5F) {
//                    return new StructureTemplate.StructureBlockInfo(modifiedBlockInfo.pos(), Blocks.SANDSTONE.defaultBlockState(), null);
//                }
//            }
//        }
//
//        return modifiedBlockInfo;
//    }
//
//    @Override
//    protected StructureProcessorType<?> getType() {
//        return EOSStructureProcessor.SARLACC_PIT_PROCESSOR.get();
//    }
//}
