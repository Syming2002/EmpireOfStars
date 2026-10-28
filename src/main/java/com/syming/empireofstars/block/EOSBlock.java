package com.syming.empireofstars.block;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.liquidblock.SarlaccStomachAcidLiquidBlock;
import com.syming.empireofstars.client.kybercrystal.KyberCrystalItemRenderer;
import com.syming.empireofstars.fluid.EOSFluid;
import com.syming.empireofstars.item.EOSItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class EOSBlock {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EmpireOfStarsMod.MOD_ID);

    public static final Map<KyberCrystalColor, Supplier<Block>> KYBER_CRYSTAL_TYPES = new HashMap<>();

    static {
        for (KyberCrystalColor kybercolor : KyberCrystalColor.values()) {
            KYBER_CRYSTAL_TYPES.put(kybercolor, registerKyberCrystalBlock(kybercolor, () -> new KyberCrystalBlock(kybercolor)));
        }
    }

    public static final Supplier<Block> SARLACC_SPEAR_TOOTH = registerBlock("sarlacc_spear_tooth", () -> new SarlaccSpearToothBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).noOcclusion().sound(SoundType.POINTED_DRIPSTONE).randomTicks().strength(1.5F, 3.0F).dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY).isRedstoneConductor(((pState, pLevel, pPos) -> false))));

    public static final Supplier<Block> SARLACC_STOMACH_ACID_OUTPUT = registerBlock("sarlacc_stomach_acid_output", SarlaccStomachAcidOutputBlock::new);

    public static final Supplier<LiquidBlock> SARLACC_STOMACH_ACID_BLOCK = registerLiquidBlock("sarlacc_stomach_acid", () -> new SarlaccStomachAcidLiquidBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));

    public static final Supplier<Block> TATOOINE_SAND = registerBlock("tatooine_sand", () -> new TatooineSandBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)));

    public static final Supplier<Block> TATOOINE_SANDSTONE = registerBlock("tatooine_sandstone", () -> new TatooineSandstoneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE)));

    private static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        Supplier<T> blockToRegister = BLOCKS.register(name, block);
        registerBlockItem(name, blockToRegister);
        return blockToRegister;
    }

    private static <T extends Block> Supplier<T> registerKyberCrystalBlock(KyberCrystalColor kybercolor, Supplier<T> block) {
        Supplier<T> blockToRegister = BLOCKS.register(kybercolor.getSerializedName() + '_' + "kyber_crystal", block);
        registerBlockItemKyberCrystal(kybercolor, blockToRegister);
        return blockToRegister;
    }

    private static <T extends Block> Supplier<T> registerLiquidBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }


    private static <T extends Block> Supplier<Item> registerBlockItem(String name, Supplier<T> block) {
        return EOSItem.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> Supplier<Item> registerBlockItemKyberCrystal(KyberCrystalColor kybercolor, Supplier<T> block) {
        return EOSItem.ITEMS.register(kybercolor.getSerializedName() + '_' + "kyber_crystal", () -> new BlockItem(block.get(), new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)) {
            @Override
            public void initializeClient(Consumer<IClientItemExtensions> consumer) {
                consumer.accept(new IClientItemExtensions() {
                    private KyberCrystalItemRenderer renderer;

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        if (this.renderer == null) {
                            return this.renderer = KyberCrystalItemRenderer.getInstance();
                        }
                        return this.renderer;
                    }

                });
            }
        });
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
