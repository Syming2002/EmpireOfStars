package com.syming.empireofstars.block;

import com.syming.empireofstars.block.blockentity.KyberCrystalBlockEntity;
import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.item.KyberCrystalShardItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
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

public class KyberCrystalBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final EnumProperty<KyberCrystalColor> COLOR = EnumProperty.create("color", KyberCrystalColor.class);

    protected final VoxelShape upShape;
    protected final VoxelShape downShape;
    protected final VoxelShape northShape;
    protected final VoxelShape southShape;
    protected final VoxelShape eastShape;
    protected final VoxelShape westShape;

    public KyberCrystalBlock(KyberCrystalColor pColor) {
        super(Properties.copy(Blocks.AMETHYST_BLOCK).requiresCorrectToolForDrops());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.UP).setValue(COLOR, pColor));

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
        pBuilder.add(FACING, COLOR);
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
        if (totalShards <= 0) return Collections.emptyList();

        Item shardItem = EOSItem.KYBER_CRYSTAL_SHARD_TYPES.get(kyberCrystalBlockEntity.getColor()).get();
        ItemStack stack = new ItemStack(shardItem, totalShards);

        CompoundTag tag = new CompoundTag();
        tag.putString("Color", kyberCrystalBlockEntity.getColor().getName());
        stack.setTag(tag);

        return List.of(stack);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);

        if (pStack.hasTag()) {
            if (pStack.getTag() != null && pStack.getTag().contains("Color")) {
                if (pStack.getItem() instanceof BlockItem blockItem) {
                    if (blockItem.getBlock() instanceof KyberCrystalBlock) {
                        KyberCrystalColor color = getColor(pStack);
                        if (color == KyberCrystalColor.AQUAMARINE) {
                            pTooltip.add(Component.translatable("translation.empireofstars.aquamarine_kyber_crystal").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x7FFFD4))));
                        }
                        if (color == KyberCrystalColor.BLACK) {
                            pTooltip.add(Component.translatable("translation.empireofstars.black_kyber_crystal").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x000000))));
                        }
                        if (color == KyberCrystalColor.BLUE) {
                            pTooltip.add(Component.translatable("translation.empireofstars.blue_kyber_crystal").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x2E67F8))));
                        }
                        if (color == KyberCrystalColor.GREEN) {
                            pTooltip.add(Component.translatable("translation.empireofstars.green_kyber_crystal").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x2FF924))));
                        }
                        if (color == KyberCrystalColor.PURPLE) {
                            pTooltip.add(Component.translatable("translation.empireofstars.purple_kyber_crystal").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x730FF0))));
                        }
                        if (color == KyberCrystalColor.RED) {
                            pTooltip.add(Component.translatable("translation.empireofstars.red_kyber_crystal").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xEB212E))));
                        }
                        if (color == KyberCrystalColor.WHITE) {
                            pTooltip.add(Component.translatable("translation.empireofstars.white_kyber_crystal").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFFFFF))));
                        }
                        if (color == KyberCrystalColor.YELLOW) {
                            pTooltip.add(Component.translatable("translation.empireofstars.yellow_kyber_crystal").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFF738))));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        if (!(pLevel.getBlockEntity(pPos) instanceof KyberCrystalBlockEntity kyberCrystalBlockEntity)) return;

        CompoundTag tag = pStack.getTag();

        if (tag == null) return;

        if (tag.contains("Shards")) {
            kyberCrystalBlockEntity.setShards(tag.getInt("Shards"));
        }

        if (tag.contains("Color")) {
            kyberCrystalBlockEntity.setColor(getColor(pStack));
        }
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {

        ItemStack stack = new ItemStack(EOSBlock.KYBER_CRYSTAL_TYPES.get(state.getValue(COLOR)).get());

        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof KyberCrystalBlockEntity kyberCrystalBlockEntity) {
            CompoundTag tag = new CompoundTag();

            tag.putString("Color", kyberCrystalBlockEntity.getColor().getName());
            tag.putInt("Shards", kyberCrystalBlockEntity.getShards());

            stack.setTag(tag);
        }

        return stack;
    }

    public KyberCrystalColor getColor(ItemStack pStack) {
        if (pStack.getTag() != null && pStack.getTag().contains("Color")) {
            return KyberCrystalColor.fromSerialized(pStack.getTag().getString("Color"));
        }
        return null;
    }
}
