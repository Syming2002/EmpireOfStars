package com.syming.empireofstars.block;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.client.kybercrystal.KyberCrystalItemRenderer;
import com.syming.empireofstars.client.lightsaber.LightsaberRenderer;
import com.syming.empireofstars.item.EOSItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.syming.empireofstars.block.KyberCrystalBlockKey.KYBER_CRYSTAL_TYPES;

public class EOSBlock {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EmpireOfStarsMod.MOD_ID);


    static {
        for (KyberCrystalColor color : KyberCrystalColor.values()) {
            for (int shardCount = 1; shardCount <= 5; shardCount++) {
                int finalShardCount = shardCount;
                KYBER_CRYSTAL_TYPES.put(new KyberCrystalBlockKey(finalShardCount, color), registerKyberCrystalBlock(finalShardCount, color, () -> new KyberCrystalBlock(color, finalShardCount)));
            }
        }
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> blockToRegister = BLOCKS.register(name, block);
        registerBlockItem(name, blockToRegister);
        return blockToRegister;
    }

    private static <T extends Block> RegistryObject<T> registerKyberCrystalBlock(int shardCount, KyberCrystalColor color, Supplier<T> block) {
        RegistryObject<T> blockToRegister = BLOCKS.register(String.valueOf(shardCount) + '_' + color.getName() + '_' + "kyber_crystal", block);
        registerBlockItemKyberCrystal(shardCount, color, blockToRegister);
        return blockToRegister;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return EOSItem.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItemKyberCrystal(int shardCount, KyberCrystalColor color, RegistryObject<T> block) {
        return EOSItem.ITEMS.register(String.valueOf(shardCount) + '_' + color.getName() + '_' + "kyber_crystal", () -> new BlockItem(block.get(), new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)) {
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
