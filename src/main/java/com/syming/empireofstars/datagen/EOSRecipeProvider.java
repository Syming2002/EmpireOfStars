package com.syming.empireofstars.datagen;

import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.item.lightsaber.LightsaberType;
import com.syming.empireofstars.tag.EOSTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class EOSRecipeProvider extends RecipeProvider {
    public EOSRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        ShapelessItemColorRecipeBuilder.shardColor(EOSItem.KYBER_CRYSTAL_SHARD.get())
                .requires(EOSItem.KYBER_CRYSTAL_SHARD.get(), 2)
                .unlockedBy("has_kyber_shard", has(EOSTag.Items.KYBER_SHARDS_ITEMS))
                .save(output);

        for (LightsaberType type : LightsaberType.values()) {
            ShapelessItemColorRecipeBuilder.lightsaberColor(EOSItem.LIGHTSABER_TYPES.get(type).get())
                    .requires(EOSItem.EMPTY_LIGHTSABER_TYPES.get(type).get(), 1)
                    .requires(EOSItem.KYBER_CRYSTAL_SHARD.get(), 1)
                    .unlockedBy("has_empty_lightsaber", has(EOSTag.Items.LIGHTSABER_ITEMS))
                    .save(output);
        }
    }
}
