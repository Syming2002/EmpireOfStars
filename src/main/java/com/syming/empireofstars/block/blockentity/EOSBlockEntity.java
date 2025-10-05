package com.syming.empireofstars.block.blockentity;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalBlockKey;
import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

import static com.syming.empireofstars.block.blockentity.KyberCrystalBlockEntityKey.KYBER_CRYSTAL_BLOCK_ENTITY_TYPES;

public class EOSBlockEntity {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EmpireOfStarsMod.MOD_ID);


    static {
        for (KyberCrystalColor color : KyberCrystalColor.values()) {
            for (int shardCount = 1; shardCount <= 5; shardCount++) {
                int finalShardCount = shardCount;
                KYBER_CRYSTAL_BLOCK_ENTITY_TYPES.put(new KyberCrystalBlockEntityKey(finalShardCount, color), BLOCK_ENTITIES.register(String.valueOf(finalShardCount) + '_' + color.name().toLowerCase(Locale.ROOT) + '_' + "kyber_crystal",
                        () -> BlockEntityType.Builder.of((pos, state) -> new KyberCrystalBlockEntity(pos, state, color, finalShardCount), KyberCrystalBlockKey.KYBER_CRYSTAL_TYPES.get(new KyberCrystalBlockKey(finalShardCount, color)).get()).build(null)));
            }
        }
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
