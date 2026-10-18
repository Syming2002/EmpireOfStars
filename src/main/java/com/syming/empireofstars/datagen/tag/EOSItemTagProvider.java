package com.syming.empireofstars.datagen.tag;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.tag.EOSTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EOSItemTagProvider extends ItemTagsProvider {

    public EOSItemTagProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, EmpireOfStarsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(EOSTag.Items.KYBER_SHARDS_ITEMS)
                .add(EOSItem.KYBER_CRYSTAL_SHARD.get());
    }
}
