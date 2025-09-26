package com.syming.empireofstars.block;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.item.EOSItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class EOSBlock {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EmpireOfStarsMod.MOD_ID);

    public static final Map<KyberCrystalColor, RegistryObject<KyberCrystalBlock>> KYBER_CRYSTAL_TYPES = new HashMap<>();

    static {
        for (KyberCrystalColor color : KyberCrystalColor.values()) {
            KYBER_CRYSTAL_TYPES.put(color, registerBlock(color.name().toLowerCase(Locale.ROOT) + '_' + "kyber_crystal",
                    () -> new KyberCrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK), color)));
        }
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> blockToRegister = BLOCKS.register(name, block);
        registerBlockItem(name, blockToRegister);
        return blockToRegister;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return EOSItem.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
