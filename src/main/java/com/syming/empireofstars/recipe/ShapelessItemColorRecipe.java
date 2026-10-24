package com.syming.empireofstars.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.item.KyberCrystalShardItem;
import com.syming.empireofstars.item.lightsaber.LightsaberItem;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public class ShapelessItemColorRecipe extends ShapelessRecipe {
    public ShapelessItemColorRecipe(ResourceLocation pId, String pGroup, ItemStack pResult, NonNullList<Ingredient> pIngredients) {
        super(pId, pGroup, CraftingBookCategory.MISC, pResult, pIngredients);
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        ItemStack result = super.assemble(pContainer, pRegistryAccess);

        int red = 0;
        int green = 0;
        int blue = 0;
        int count = 0;

        for (int i = 0; i < pContainer.getContainerSize(); i++) {
            ItemStack stack = pContainer.getItem(i);

            if (stack.isEmpty()) continue;


            if (stack.getItem() instanceof KyberCrystalShardItem || stack.getItem() instanceof LightsaberItem) {
                int color = KyberCrystalColor.getKyberColorCodeFromStack(stack);

                red += (color >> 16) & 0xFF;
                green += (color >> 8) & 0xFF;
                blue += color & 0xFF;

                count++;
            }
        }

        if (count == 0) {
            return result;
        }

        int r = red / count;
        int g = green / count;
        int b = blue / count;

        int mixedColor = (r << 16) | (g << 8) | b;

        KyberCrystalColor.setKyberColorCodeFromStack(result, mixedColor);

        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return EOSRecipe.ITEM_COLOR_RECIPE.get();
    }

    public static class ItemColorRecipeSerializer implements RecipeSerializer<ShapelessItemColorRecipe> {

        public ItemColorRecipeSerializer() {

        }

        @Override
        public ShapelessItemColorRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            String group = GsonHelper.getAsString(pSerializedRecipe, "group", "");

            CraftingBookCategory craftingBookCategory = CraftingBookCategory.MISC;

            NonNullList<Ingredient> ingredients = NonNullList.create();
            JsonArray ingredientArray =
                    GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");

            if (ingredientArray.isEmpty()) {
                throw new JsonSyntaxException(
                        "Kyber shard recipe must have ingredients"
                );
            }

            for (JsonElement element : ingredientArray) {
                ingredients.add(Ingredient.fromJson(element));
            }

            if (ingredients.size() > 9) {
                throw new JsonSyntaxException(
                        "Kyber shard recipe cannot have more than 9 ingredients"
                );
            }

            JsonObject resultJson = GsonHelper.getAsJsonObject(pSerializedRecipe, "result");

            ItemStack result = ShapedRecipe.itemStackFromJson(resultJson);

            if (resultJson.has("nbt")) {
                JsonObject nbt = GsonHelper.getAsJsonObject(resultJson, "nbt");

                if (nbt.has("kybercolorCode")) {
                    int color = GsonHelper.getAsInt(nbt, "kybercolorCode");

                    KyberCrystalColor.setKyberColorCodeFromStack(
                            result, color
                    );
                }
            }

            return new ShapelessItemColorRecipe(
                    pRecipeId,
                    group,
                    result,
                    ingredients
            );
        }

        @Override
        public ShapelessItemColorRecipe fromNetwork(
                ResourceLocation id,
                FriendlyByteBuf buffer
        ) {
            String group = buffer.readUtf();
            int size = buffer.readVarInt();

            NonNullList<Ingredient> ingredients =
                    NonNullList.withSize(size, Ingredient.EMPTY);

            for (int i = 0; i < size; i++) {
                ingredients.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack result = buffer.readItem();

            return new ShapelessItemColorRecipe(
                    id,
                    group,
                    result,
                    ingredients
            );
        }

        @Override
        public void toNetwork(
                FriendlyByteBuf buffer,
                ShapelessItemColorRecipe recipe
        ) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeVarInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.getResultItem(RegistryAccess.EMPTY));
        }
    }
}
