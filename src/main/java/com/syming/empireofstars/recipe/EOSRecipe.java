package com.syming.empireofstars.recipe;

import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EOSRecipe {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, EmpireOfStarsMod.MOD_ID);


    public static final RegistryObject<RecipeSerializer<?>> ITEM_COLOR_RECIPE = RECIPE_SERIALIZERS.register("item_color_recipe", ShapelessItemColorRecipe.ItemColorRecipeSerializer::new);


    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
