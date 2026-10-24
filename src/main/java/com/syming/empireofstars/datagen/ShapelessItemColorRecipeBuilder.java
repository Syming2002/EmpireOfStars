package com.syming.empireofstars.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.recipe.EOSRecipe;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
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
    private final Map<String, CriterionTriggerInstance> criteria = new LinkedHashMap<>();

    private String group = "";

    public ShapelessItemColorRecipeBuilder(Item result, int count) {
        this.result = result.asItem();
        this.count = count;
    }

    public static ShapelessItemColorRecipeBuilder shardColor(Item result) {
        return new ShapelessItemColorRecipeBuilder(result, 1);
    }

    public static ShapelessItemColorRecipeBuilder shardColor(Item result, int count) {

        ItemStack stack = new ItemStack(result);

        int red = 0;
        int green = 0;
        int blue = 0;

        int color = KyberCrystalColor.getKyberColorCodeFromStack(stack);

        red += (color >> 16) & 0xFF;
        green += (color >> 8) & 0xFF;
        blue += color & 0xFF;

        count++;

        int r = red / count;
        int g = green / count;
        int b = blue / count;

        int mixedColor = (r << 16) | (g << 8) | b;

        KyberCrystalColor.setKyberColorCodeFromStack(stack, mixedColor);

        return new ShapelessItemColorRecipeBuilder(result, count);
    }

    public static ShapelessItemColorRecipeBuilder ligthsaberColor(Item result) {
        return new ShapelessItemColorRecipeBuilder(result, 1);
    }

    public ShapelessItemColorRecipeBuilder requires(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    public ShapelessItemColorRecipeBuilder requires(
            Item item,
            int count
    ) {
        for (int i = 0; i < count; i++) {
            this.ingredients.add(Ingredient.of(item));
        }
        return this;
    }

    public ShapelessItemColorRecipeBuilder requires(
            TagKey<Item> tag,
            int count
    ) {
        for (int i = 0; i < count; i++) {
            this.ingredients.add(Ingredient.of(tag));
        }
        return this;
    }

    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        this.criteria.put(pCriterionName, pCriterionTrigger);
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
    public void save(Consumer<FinishedRecipe> consumer) {
        this.save(
                consumer,
                BuiltInRegistries.ITEM.getKey(this.result)
        );
    }

    @Override
    public void save(
            Consumer<FinishedRecipe> consumer,
            ResourceLocation id
    ) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException(
                    "No way of obtaining recipe " + id
            );
        }

        if (this.ingredients.size() < 2) {
            throw new IllegalStateException(
                    "Kyber shard color recipe needs at least 2 ingredients"
            );
        }

        Advancement.Builder advancement = Advancement.Builder.recipeAdvancement()
                .addCriterion(
                        "has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(id)
                );

        this.criteria.forEach(advancement::addCriterion);

        advancement.rewards(AdvancementRewards.Builder.recipe(id));
        advancement.requirements(RequirementsStrategy.OR);

        consumer.accept(new Result(
                id,
                this.group,
                this.ingredients,
                this.result,
                this.count,
                advancement,
                id.withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/")
        ));
    }

    private record Result(
            ResourceLocation id,
            String group,
            List<Ingredient> ingredients,
            Item result,
            int count,
            Advancement.Builder advancement,
            ResourceLocation advancementId
    ) implements FinishedRecipe {

        @Override
        public void serializeRecipeData(JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }

            json.addProperty("category", CraftingBookCategory.MISC.getSerializedName());

            var ingredientArray = new JsonArray();

            for (Ingredient ingredient : this.ingredients) {
                ingredientArray.add(ingredient.toJson());
            }

            json.add("ingredients", ingredientArray);

            JsonObject resultJson = new JsonObject();
            resultJson.addProperty(
                    "item",
                    BuiltInRegistries.ITEM.getKey(this.result).toString()
            );
            resultJson.addProperty("count", this.count);

            json.add("result", resultJson);
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return EOSRecipe.ITEM_COLOR_RECIPE.get();
        }

        @Override
        public JsonObject serializeAdvancement() {
            return this.advancement != null ? this.advancement.serializeToJson() : null;
        }

        @Override
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
