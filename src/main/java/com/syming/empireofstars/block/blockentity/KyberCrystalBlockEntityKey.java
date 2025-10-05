package com.syming.empireofstars.block.blockentity;

import com.syming.empireofstars.block.KyberCrystalColor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public record KyberCrystalBlockEntityKey(int shardCount, KyberCrystalColor color) {

    public static final Map<KyberCrystalBlockEntityKey, RegistryObject<BlockEntityType<KyberCrystalBlockEntity>>> KYBER_CRYSTAL_BLOCK_ENTITY_TYPES = new HashMap<>();


}
