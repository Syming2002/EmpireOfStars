package com.syming.empireofstars.datagen;

import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.item.lightsaber.LightsaberType;
import com.syming.empireofstars.tag.EOSTag;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class EOSRecipeProvider extends RecipeProvider {
    public EOSRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapelessItemColorRecipeBuilder.shardColor(EOSItem.KYBER_CRYSTAL_SHARD.get())
                .requires(EOSItem.KYBER_CRYSTAL_SHARD.get(), 2)
                .unlockedBy("has_kyber_shard", has(EOSTag.Items.KYBER_SHARDS_ITEMS))
                .save(pWriter);

        for (LightsaberType type : LightsaberType.values()) {
            ShapelessItemColorRecipeBuilder.ligthsaberColor(EOSItem.LIGHTSABER_TYPES.get(type).get())
                    .requires(EOSItem.EMPTY_LIGHTSABER_TYPES.get(type).get(), 1)
                    .requires(EOSItem.KYBER_CRYSTAL_SHARD.get(), 1)
                    .unlockedBy("has_empty_lightsaber", has(EOSTag.Items.LIGHTSABER_ITEMS))
                    .save(pWriter);
        }
    }
}
