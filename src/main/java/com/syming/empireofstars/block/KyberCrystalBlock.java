package com.syming.empireofstars.block;

import com.syming.empireofstars.block.blockentity.KyberCrystalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class KyberCrystalBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    private KyberCrystalColor color;

    protected final VoxelShape upShape;
    protected final VoxelShape downShape;
    protected final VoxelShape northShape;
    protected final VoxelShape southShape;
    protected final VoxelShape eastShape;
    protected final VoxelShape westShape;

    public KyberCrystalBlock(Properties pProperties, KyberCrystalColor color) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.UP));

        this.color = color;

        this.upShape = Block.box(5.5D, 0.0D, 5.5D, 10.5D, 8.0D, 10.5D);
        this.downShape = Block.box(5.5D, 8.0D, 5.5D, 10.5D, 16.0D, 10.5D);
        this.northShape = Block.box(5.5D, 5.5D, 8.0D, 10.5D, 10.5D, 16.0D);
        this.southShape = Block.box(5.5D, 5.5D, 0.0D, 10.5D, 10.5D, 8.0D);
        this.eastShape = Block.box(0.0D, 5.5D, 5.5D, 8.0D, 10.5D, 10.5D);
        this.westShape = Block.box(8.0D, 5.5D, 5.5D, 16.0D, 10.5D, 10.5D);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch (direction) {
            case DOWN:
                return this.downShape;
            case NORTH:
                return this.northShape;
            case SOUTH:
                return this.southShape;
            case EAST:
                return this.eastShape;
            case WEST:
                return this.westShape;
            case UP:
            default:
                return this.upShape;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
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
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new KyberCrystalBlockEntity(pPos, pState, this.color);
    }
}
