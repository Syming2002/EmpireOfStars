package com.syming.empireofstars.block;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.client.kybercrystal.KyberCrystalItemRenderer;
import com.syming.empireofstars.item.EOSItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class EOSBlock {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EmpireOfStarsMod.MOD_ID);

    public static final Map<KyberCrystalColor, RegistryObject<Block>> KYBER_CRYSTAL_TYPES = new HashMap<>();

    static {
        for (KyberCrystalColor color : KyberCrystalColor.values()) {
            KYBER_CRYSTAL_TYPES.put(color, registerKyberCrystalBlock(color, () -> new KyberCrystalBlock(color)));
        }
    }

    public static final RegistryObject<Block> SARLACC_SPEAR_TOOTH = registerBlock("sarlacc_spear_tooth", () -> new SarlaccSpearToothBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).noOcclusion().sound(SoundType.POINTED_DRIPSTONE).randomTicks().strength(1.5F, 3.0F).dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY).isRedstoneConductor(((pState, pLevel, pPos) -> false))));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> blockToRegister = BLOCKS.register(name, block);
        registerBlockItem(name, blockToRegister);
        return blockToRegister;
    }

    private static <T extends Block> RegistryObject<T> registerKyberCrystalBlock(KyberCrystalColor color, Supplier<T> block) {
        RegistryObject<T> blockToRegister = BLOCKS.register(color.getSerializedName() + '_' + "kyber_crystal", block);
        registerBlockItemKyberCrystal(color, blockToRegister);
        return blockToRegister;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return EOSItem.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItemKyberCrystal(KyberCrystalColor color, RegistryObject<T> block) {
        return EOSItem.ITEMS.register(color.getSerializedName() + '_' + "kyber_crystal", () -> new BlockItem(block.get(), new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)) {
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
