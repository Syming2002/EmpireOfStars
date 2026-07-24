package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.entity.EOSEntity;
import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.sound.EOSSound;
import com.syming.empireofstars.world.biome.EOSBiome;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
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

        for (KyberCrystalColor color : KyberCrystalColor.values()) {
            add("translation.empireofstars." + color.getSerializedName(), color.getSerializedName().substring(0, 1).toUpperCase() + color.getSerializedName().substring(1));
        }

        for (RegistryObject<EntityType<?>> entity : EOSEntity.ENTITY_TYPES.getEntries()) {
            if (entity.getId() != null) {
                addEntityType(entity, getEntityDisplayName(entity.getId()));
            }
        }

        // Biomes

        for (ResourceKey<Biome> biome : EOSBiome.EOS_BIOMES_LIST) {
            add("biome" + '.' + EmpireOfStarsMod.MOD_ID + '.' + biome.location().getPath(), getBiomeDisplayName(biome.location()));
        }

        // Sounds

        for (RegistryObject<SoundEvent> sound : EOSSound.SOUNDS.getEntries()) {
            if (sound.getId() != null) {
                add("sounds.empireofstars" + '.' + sound.get().getLocation().getPath(), getSoundDisplayName(sound.getId()));
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

    private String getEntityDisplayName(ResourceLocation id) {
        String raw = id.getPath();
        String[] parts = raw.split("_");
        StringBuilder builder = new StringBuilder();

        for (String part : parts) {
            if (!builder.isEmpty()) builder.append(' ');
            builder.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1));
        }

        return builder.toString();
    }

    private String getBiomeDisplayName(ResourceLocation id) {
        String raw = id.getPath();
        String[] parts = raw.split("_");
        StringBuilder builder = new StringBuilder();

        for (String part : parts) {
            if (!builder.isEmpty()) builder.append(' ');
            builder.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1));
        }

        return builder.toString();
    }

    private String getSoundDisplayName(ResourceLocation id) {
        String raw = id.getPath();
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
