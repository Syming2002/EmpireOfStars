package com.syming.empireofstars.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SarlaccSpearToothBlock extends Block implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE_UP = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 11.0D, 11.0D);

    private static final VoxelShape SHAPE_DOWN = Block.box(5.0D, 5.0D, 5.0D, 11.0D, 16.0D, 11.0D);

    private static final VoxelShape SHAPE_SOUTH = Block.box(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 11.0D);

    private static final VoxelShape SHAPE_NORTH = Block.box(5.0D, 5.0D, 5.0D, 11.0D, 11.0D, 16.0D);

    private static final VoxelShape SHAPE_WEST = Block.box(5.0D, 5.0D, 5.0D, 16.0D, 11.0D, 11.0D);

    private static final VoxelShape SHAPE_EAST = Block.box(0.0D, 5.0D, 5.0D, 11.0D, 11.0D, 11.0D);


    public SarlaccSpearToothBlock(Properties pProperties) {
        super(pProperties);

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, WATERLOGGED);
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        VoxelShape voxelShape;
        Direction facing = pState.getValue(FACING);
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        if (facing == Direction.DOWN) {
            voxelShape = SHAPE_DOWN;
        } else if (facing == Direction.NORTH) {
            voxelShape = SHAPE_NORTH;
        } else if (facing == Direction.SOUTH) {
            voxelShape = SHAPE_SOUTH;
        } else if (facing == Direction.EAST) {
            voxelShape = SHAPE_EAST;
        } else if (facing == Direction.WEST) {
            voxelShape = SHAPE_WEST;
        } else {
            voxelShape = SHAPE_UP;
        }
        return voxelShape.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return false;
    }

    @Override
    public float getMaxHorizontalOffset() {
        return 0.125F;
    }

    @Override
    public float getMaxVerticalOffset() {
        return 0.125F;
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getClickedFace());
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return isValidToothPlacement(pLevel, pPos, pState.getValue(FACING));
    }

    private static boolean isValidToothPlacement(LevelReader pLevel, BlockPos pPos, Direction pDirection) {
        BlockPos blockPos = pPos.relative(pDirection.getOpposite());
        BlockState blockState = pLevel.getBlockState(blockPos);
        return blockState.isFaceSturdy(pLevel, blockPos, pDirection);
    }
}
