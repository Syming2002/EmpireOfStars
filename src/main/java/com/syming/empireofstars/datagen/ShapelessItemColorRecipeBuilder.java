package com.syming.empireofstars.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.recipe.EOSRecipe;
import com.syming.empireofstars.recipe.ShapelessItemColorRecipe;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.*;
import java.util.function.Consumer;

public class ShapelessItemColorRecipeBuilder implements RecipeBuilder {

    private final Item result;
    private final int count;
    private final List<Ingredient> ingredients = new ArrayList<>();
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    private String group = "";

    public ShapelessItemColorRecipeBuilder(Item result, int count) {
        this.result = result;
        this.count = count;
    }

    public static ShapelessItemColorRecipeBuilder shardColor(Item result) {
        return new ShapelessItemColorRecipeBuilder(result, 1);
    }

    public static ShapelessItemColorRecipeBuilder shardColor(
            Item result, int count) {

        ItemStack stack = new ItemStack(result);

        int color = KyberCrystalColor.getKyberColorCodeFromStack(stack);

        int red = (color >> 16) & 0xFF;
        int green = (color >> 8) & 0xFF;
        int blue = color & 0xFF;

        // Keep your original calculation here if you need to mix
        // the result color from multiple input crystals.
        int mixedColor = (red << 16) | (green << 8) | blue;

        KyberCrystalColor.setKyberColorCodeFromStack(stack, mixedColor);

        return new ShapelessItemColorRecipeBuilder(result, count);
    }

    public static ShapelessItemColorRecipeBuilder lightsaberColor(Item result) {
        return new ShapelessItemColorRecipeBuilder(result, 1);
    }

    public ShapelessItemColorRecipeBuilder requires(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    public ShapelessItemColorRecipeBuilder requires(Item item, int count) {
        for (int i = 0; i < count; i++) {
            this.ingredients.add(Ingredient.of(item));
        }
        return this;
    }

    public ShapelessItemColorRecipeBuilder requires(
            TagKey<Item> tag, int count) {
        for (int i = 0; i < count; i++) {
            this.ingredients.add(Ingredient.of(tag));
        }
        return this;
    }

    @Override
    public ShapelessItemColorRecipeBuilder unlockedBy(
            String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public ShapelessItemColorRecipeBuilder group(String group) {
        this.group = group;
        return this;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(RecipeOutput output, ResourceLocation id) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException(
                    "No way of obtaining recipe " + id);
        }

        if (this.ingredients.size() < 2) {
            throw new IllegalStateException(
                    "Kyber shard color recipe needs at least 2 ingredients");
        }

        Advancement.Builder advancement = output.advancement()
                .addCriterion(
                        "has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);

        this.criteria.forEach(advancement::addCriterion);

        ShapelessItemColorRecipe recipe = new ShapelessItemColorRecipe(this.group, new ItemStack(this.result, this.count), NonNullList.copyOf(this.ingredients));

        AdvancementHolder holder = advancement.build(
                id.withPrefix(
                        "recipes/" + RecipeCategory.MISC.getFolderName() + "/"
                )
        );

        output.accept(id, recipe, holder);
    }
}
