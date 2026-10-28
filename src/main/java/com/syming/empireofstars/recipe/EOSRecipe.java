package com.syming.empireofstars.recipe;

import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EOSRecipe {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, EmpireOfStarsMod.MOD_ID);


    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> ITEM_COLOR_RECIPE = RECIPE_SERIALIZERS.register("item_color_recipe", ShapelessRecipe.Serializer::new);


    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
