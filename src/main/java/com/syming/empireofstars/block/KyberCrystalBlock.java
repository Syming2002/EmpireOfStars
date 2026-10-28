package com.syming.empireofstars.block;

import com.syming.empireofstars.block.blockentity.KyberCrystalBlockEntity;
import com.syming.empireofstars.item.EOSItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

public class KyberCrystalBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final EnumProperty<KyberCrystalColor> COLOR = EnumProperty.create("kybercolor", KyberCrystalColor.class);
    public static final IntegerProperty SHARDS = IntegerProperty.create("shard", 1, 5);

    protected final VoxelShape upShape;
    protected final VoxelShape downShape;
    protected final VoxelShape northShape;
    protected final VoxelShape southShape;
    protected final VoxelShape eastShape;
    protected final VoxelShape westShape;

    public KyberCrystalBlock(KyberCrystalColor kybercolor) {
        super(Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).requiresCorrectToolForDrops());
        this.registerDefaultState(this.defaultBlockState()
                .setValue(KyberCrystalBlock.FACING, Direction.UP)
                .setValue(KyberCrystalBlock.COLOR, kybercolor)
                .setValue(KyberCrystalBlock.SHARDS, 1));

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
        pBuilder.add(FACING, COLOR, SHARDS);
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
        return RenderShape.INVISIBLE;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new KyberCrystalBlockEntity(pPos, pState, pState.getValue(COLOR));
    }

    @Override
    public List<ItemStack> getDrops(BlockState pState, LootParams.Builder pParams) {
        BlockEntity blockEntity = pParams.getParameter(LootContextParams.BLOCK_ENTITY);
        if (!(blockEntity instanceof KyberCrystalBlockEntity kyberCrystalBlockEntity)) return Collections.emptyList();

        int totalShards = kyberCrystalBlockEntity.getShards();

        Item shardItem = EOSItem.KYBER_CRYSTAL_SHARD.get();
        ItemStack stack = new ItemStack(shardItem, totalShards);

        KyberCrystalColor.setKyberColorNameFromStack(stack, kyberCrystalBlockEntity.getKyberColor());
        KyberCrystalColor.setShardCountFromStack(stack, totalShards);

        KyberCrystalColor colorName = KyberCrystalColor.getKyberColorNameFromStack(stack);

        return colorName != null ? List.of(stack) : List.of();
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        KyberCrystalColor colorName = KyberCrystalColor.getKyberColorNameFromStack(stack);

        if (colorName != null) {
            if (stack.getItem() instanceof BlockItem blockItem) {
                if (blockItem.getBlock() instanceof KyberCrystalBlock) {
                    KyberCrystalColor kyberColor = KyberCrystalColor.getKyberColorNameFromStack(stack);
                    String hex = String.format(Locale.ROOT, "#%06X", kyberColor.getKyberColor());
                    tooltipComponents.add(Component.translatable("kybercolor.empireofstars").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(kyberColor.getKyberColor())))
                            .append(Component.literal(hex)
                                    .withStyle(style ->
                                            style.withColor(TextColor.fromRgb(kyberColor.getKyberColor()))
                                    )
                            ));
                }
            }
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);

        KyberCrystalColor color = KyberCrystalColor.getKyberColorNameFromStack(stack);

        int shardCount = KyberCrystalColor.getShardCountFromStack(stack);

        if (color == null) {
            color = state.getValue(COLOR);
        }

        shardCount = Mth.clamp(shardCount, 1, 5);

        BlockState newState = state
                .setValue(COLOR, color)
                .setValue(SHARDS, shardCount);

        level.setBlock(pos, newState, 3);

        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof KyberCrystalBlockEntity kyberBlockEntity) {
            kyberBlockEntity.setKyberColor(color);
            kyberBlockEntity.setShards(shardCount);
            kyberBlockEntity.setChanged();
        }
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        ItemStack stack = super.getCloneItemStack(state, target, level, pos, player);

        KyberCrystalColor color = state.getValue(COLOR);
        int shardCount = state.getValue(SHARDS);

        KyberCrystalColor.setKyberColorNameFromStack(stack, color);
        KyberCrystalColor.setShardCountFromStack(stack, shardCount);

        return stack;
    }
}
