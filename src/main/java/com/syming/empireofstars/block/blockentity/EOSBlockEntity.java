package com.syming.empireofstars.block.blockentity;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EOSBlockEntity {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EmpireOfStarsMod.MOD_ID);

    public static final Map<KyberCrystalColor, RegistryObject<BlockEntityType<KyberCrystalBlockEntity>>> KYBER_CRYSTAL_BLOCK_ENTITY_TYPES = new HashMap<>();

    static {
        for (KyberCrystalColor color : KyberCrystalColor.values()) {
                KYBER_CRYSTAL_BLOCK_ENTITY_TYPES.put(color, BLOCK_ENTITIES.register(color.name().toLowerCase(Locale.ROOT) + '_' + "kyber_crystal",
                        () -> BlockEntityType.Builder.of((pos, state) -> new KyberCrystalBlockEntity(pos, state, color), EOSBlock.KYBER_CRYSTAL_TYPES.get(color).get()).build(null)));
            }
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
