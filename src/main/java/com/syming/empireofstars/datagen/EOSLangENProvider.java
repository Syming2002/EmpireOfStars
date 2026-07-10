package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.item.EOSItem;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

public class EOSLangENProvider extends LanguageProvider {
    public EOSLangENProvider(PackOutput output) {
        super(output, EmpireOfStarsMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (RegistryObject<Item> item : EOSItem.ITEMS.getEntries()) {
            if (item.getId() != null) {
                addItem(item, getItemDisplayName(item));
            }
        }
    }

    private String getItemDisplayName(RegistryObject<Item> item) {
        String raw = item.getId().getPath();
        String[] parts = raw.split("_");
        StringBuilder builder = new StringBuilder();

        for (String part : parts) {
            if (!builder.isEmpty()) builder.append(' ');
            builder.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1));
        }

        return builder.toString();
    }
}
