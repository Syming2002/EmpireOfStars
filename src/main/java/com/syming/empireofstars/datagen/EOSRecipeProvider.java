package com.syming.empireofstars.datagen;

import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.tag.EOSTag;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class EOSRecipeProvider extends RecipeProvider {
    public EOSRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapelessKyberShardRecipeBuilder.shardColor(EOSItem.KYBER_CRYSTAL_SHARD.get())
                .requires(EOSItem.KYBER_CRYSTAL_SHARD.get(), 2)
                .unlockedBy("has_kyber_shard", has(EOSTag.Items.KYBER_SHARDS_ITEMS))
                .save(pWriter);
    }
}
