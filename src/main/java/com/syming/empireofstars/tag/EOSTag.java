package com.syming.empireofstars.tag;

import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class EOSTag {


    public static class Items {
        public static final TagKey<Item> KYBER_SHARDS_ITEMS = tag("kyber_shards");
        public static final TagKey<Item> LIGHTSABER_ITEMS = tag("lightsabers");


        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(EmpireOfStarsMod.MOD_ID, name));
        }
    }

}
