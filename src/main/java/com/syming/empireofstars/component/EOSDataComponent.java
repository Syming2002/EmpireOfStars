//package com.syming.empireofstars.component;
//
//import com.mojang.serialization.Codec;
//import com.syming.empireofstars.EmpireOfStarsMod;
//import net.minecraft.core.component.DataComponentType;
//import net.neoforged.bus.api.IEventBus;
//import net.neoforged.neoforge.registries.DeferredHolder;
//import net.neoforged.neoforge.registries.DeferredRegister;
//
//import java.util.function.UnaryOperator;
//
//public class EOSDataComponent {
//
//    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPE =
//            DeferredRegister.createDataComponents(EmpireOfStarsMod.MOD_ID);
//
//    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> BLADE_TYPE =
//            register("bladeType", stringBuilder -> stringBuilder.persistent(Codec.STRING));
//
//    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> COLOR_NAME =
//            register("kyberColorName", stringBuilder -> stringBuilder.persistent(Codec.STRING));
//
//    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> COLOR_CODE =
//            register("kyberColorCode", integerBuilder -> integerBuilder.persistent(Codec.INT));
//
//    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> ACTIVE =
//            register("active", booleanBuilder -> booleanBuilder.persistent(Codec.BOOL));
//
//    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> SHARD =
//            register("shard", integerBuilder -> integerBuilder.persistent(Codec.INT));
//
//    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderUnaryOperator) {
//        return DATA_COMPONENT_TYPE.register(name, () -> builderUnaryOperator.apply(DataComponentType.builder()).build());
//    }
//
//    public static void register(IEventBus eventBus) {
//        DATA_COMPONENT_TYPE.register(eventBus);
//    }
//}
