package com.syming.empireofstars.block;

import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public record KyberCrystalBlockKey(int shardCount, KyberCrystalColor color) {

    public static final Map<KyberCrystalBlockKey, RegistryObject<KyberCrystalBlock>> KYBER_CRYSTAL_TYPES = new HashMap<>();


}
