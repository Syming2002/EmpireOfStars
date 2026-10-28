package com.syming.empireofstars.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.item.KyberCrystalShardItem;
import com.syming.empireofstars.item.lightsaber.LightsaberItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public class ShapelessItemColorRecipe extends ShapelessRecipe {
    public ShapelessItemColorRecipe(String pGroup, ItemStack pResult, NonNullList<Ingredient> pIngredients) {
        super(pGroup, CraftingBookCategory.MISC, pResult, pIngredients);
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack result = super.assemble(input, registries);

        int red = 0;
        int green = 0;
        int blue = 0;
        int count = 0;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);

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

//    public static class ItemColorRecipeSerializer implements RecipeSerializer<ShapelessItemColorRecipe> {
//
//        private static final MapCodec<ShapelessItemColorRecipe> CODEC = RecordCodecBuilder.mapCodec((p_340779_) -> p_340779_.group(Codec.STRING.optionalFieldOf("group", "").forGetter((p_301127_) -> p_301127_.group), CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter((p_301133_) -> p_301133_.category), ItemStack.STRICT_CODEC.fieldOf("result").forGetter((p_301142_) -> p_301142_.result), Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").flatXmap((p_301021_) -> {
//            Ingredient[] aingredient = (Ingredient[])p_301021_.toArray((x$0) -> new Ingredient[x$0]);
//            if (aingredient.length == 0) {
//                return DataResult.error(() -> "No ingredients for shapeless recipe");
//            } else {
//                return aingredient.length > ShapedRecipePattern.maxHeight * ShapedRecipePattern.maxWidth ? DataResult.error(() -> "Too many ingredients for shapeless recipe. The maximum is: %s".formatted(ShapedRecipePattern.maxHeight * ShapedRecipePattern.maxWidth)) : DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
//            }
//        }, DataResult::success).forGetter((p_300975_) -> p_300975_.ingredients)).apply(p_340779_, ShapelessItemColorRecipe::new));
//        public static final StreamCodec<RegistryFriendlyByteBuf, ShapelessItemColorRecipe> STREAM_CODEC = StreamCodec.of(ShapelessItemColorRecipe.ItemColorRecipeSerializer::toNetwork, ShapelessItemColorRecipe.ItemColorRecipeSerializer::fromNetwork);
//
//        public ItemColorRecipeSerializer() {
//
//        }
//
//
//        private static ShapelessItemColorRecipe toNetwork(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
//            String group = GsonHelper.getAsString(pSerializedRecipe, "group", "");
//
//            CraftingBookCategory craftingBookCategory = CraftingBookCategory.MISC;
//
//            NonNullList<Ingredient> ingredients = NonNullList.create();
//            JsonArray ingredientArray =
//                    GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
//
//            if (ingredientArray.isEmpty()) {
//                throw new JsonSyntaxException(
//                        "Kyber shard recipe must have ingredients"
//                );
//            }
//
//            for (JsonElement element : ingredientArray) {
//                ingredients.add(Ingredient.fromValues(element));
//            }
//
//            if (ingredients.size() > 9) {
//                throw new JsonSyntaxException(
//                        "Kyber shard recipe cannot have more than 9 ingredients"
//                );
//            }
//
//            JsonObject resultJson = GsonHelper.getAsJsonObject(pSerializedRecipe, "result");
//
//            ItemStack result = ShapedRecipe.itemStackFromJson(resultJson);
//
//            if (resultJson.has("nbt")) {
//                JsonObject nbt = GsonHelper.getAsJsonObject(resultJson, "nbt");
//
//                if (nbt.has("kybercolorCode")) {
//                    int color = GsonHelper.getAsInt(nbt, "kybercolorCode");
//
//                    KyberCrystalColor.setKyberColorCodeFromStack(
//                            result, color
//                    );
//                }
//            }
//
//            return new ShapelessItemColorRecipe(
//                    pRecipeId,
//                    group,
//                    result,
//                    ingredients
//            );
//        }
//
//        private static ShapelessItemColorRecipe fromNetwork(
//                ResourceLocation id,
//                FriendlyByteBuf buffer
//        ) {
//            String group = buffer.readUtf();
//            int size = buffer.readVarInt();
//
//            NonNullList<Ingredient> ingredients =
//                    NonNullList.withSize(size, Ingredient.EMPTY);
//
//            for (int i = 0; i < size; i++) {
//                ingredients.set(i, Ingredient.fromNetwork(buffer));
//            }
//
//            ItemStack result = buffer.readItem();
//
//            return new ShapelessItemColorRecipe(
//                    id,
//                    group,
//                    result,
//                    ingredients
//            );
//        }
//
//        @Override
//        public void toNetwork(
//                FriendlyByteBuf buffer,
//                ShapelessItemColorRecipe recipe
//        ) {
//            buffer.writeUtf(recipe.getGroup());
//            buffer.writeVarInt(recipe.getIngredients().size());
//
//            for (Ingredient ingredient : recipe.getIngredients()) {
//                ingredient.toNetwork(buffer);
//            }
//
//            buffer.writeItem(recipe.getResultItem(RegistryAccess.EMPTY));
//        }
//
//        @Override
//        public MapCodec<ShapelessItemColorRecipe> codec() {
//            return null;
//        }
//
//        @Override
//        public StreamCodec<RegistryFriendlyByteBuf, ShapelessItemColorRecipe> streamCodec() {
//            return null;
//        }
//    }
}
