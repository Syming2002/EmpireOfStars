package com.syming.empireofstars.block.blockentity;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.EOSBlock;
import com.syming.empireofstars.block.KyberCrystalColor;
import kroppeb.stareval.function.Type;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class EOSBlockEntity {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EmpireOfStarsMod.MOD_ID);

    public static final Map<KyberCrystalColor, RegistryObject<BlockEntityType<KyberCrystalBlockEntity>>> KYBER_CRYSTAL_BLOCK_ENTITY_TYPES = new HashMap<>();

    static {
        for (KyberCrystalColor kybercolor : KyberCrystalColor.values()) {
                KYBER_CRYSTAL_BLOCK_ENTITY_TYPES.put(kybercolor, BLOCK_ENTITIES.register(kybercolor.name().toLowerCase() + '_' + "kyber_crystal",
                        () -> BlockEntityType.Builder.of((pos, state) -> new KyberCrystalBlockEntity(pos, state, kybercolor), EOSBlock.KYBER_CRYSTAL_TYPES.get(kybercolor).get()).build(null)));
            }
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
