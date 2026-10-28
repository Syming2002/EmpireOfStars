package com.syming.empireofstars.block.blockentity;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import kroppeb.stareval.function.Type;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EOSBlockEntity {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, EmpireOfStarsMod.MOD_ID);

    public static final Map<KyberCrystalColor, Supplier<BlockEntityType<KyberCrystalBlockEntity>>> KYBER_CRYSTAL_BLOCK_ENTITY_TYPES = new HashMap<>();

    static {
        for (KyberCrystalColor kybercolor : KyberCrystalColor.values()) {
                KYBER_CRYSTAL_BLOCK_ENTITY_TYPES.put(kybercolor, BLOCK_ENTITIES.register(kybercolor.getSerializedName().toLowerCase() + '_' + "kyber_crystal",
                        () -> BlockEntityType.Builder.of((pos, state) -> new KyberCrystalBlockEntity(pos, state, kybercolor), EOSBlock.KYBER_CRYSTAL_TYPES.get(kybercolor).get()).build(null)));
            }
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
