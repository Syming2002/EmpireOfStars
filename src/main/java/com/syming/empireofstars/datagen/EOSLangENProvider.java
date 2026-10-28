package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.KyberCrystalColor;
import com.syming.empireofstars.entity.EOSEntity;
import com.syming.empireofstars.fluid.EOSFluidType;
import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.sound.EOSSound;
import com.syming.empireofstars.world.biome.EOSBiome;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class EOSLangENProvider extends LanguageProvider {
    public EOSLangENProvider(PackOutput output) {
        super(output, EmpireOfStarsMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (DeferredHolder<Item, ? extends Item> item : EOSItem.ITEMS.getEntries()) {
            if (item.getId() != null) {
                addItem(item, getDisplayNameFromResourceLocation(item.getId()));
            }
        }

        add("item.empireofstars.kyber_crystal_shard.tooltip", "You can mix other shard color to get custom color in the crafting table for custom lightsaber color blade");

        add("creativetab." + EmpireOfStarsMod.MOD_ID + "_tab", "Empire Of Stars");

        for (DeferredHolder<FluidType, ? extends FluidType> fluidType : EOSFluidType.FLUID_TYPES.getEntries()) {
            if (fluidType.getId() != null) {
                add("fluid" + '_' + "type" + '.' + EmpireOfStarsMod.MOD_ID + '.' + fluidType.getId().getPath(), getDisplayNameFromResourceLocation(fluidType.getId()));
            }
        }

        add("kybercolor.empireofstars", "Kyber Color: ");

        for (DeferredHolder<EntityType<?>, ? extends EntityType<?>> entity : EOSEntity.ENTITY_TYPES.getEntries()) {
            if (entity.getId() != null) {
                addEntityType(entity, getDisplayNameFromResourceLocation(entity.getId()));
            }
        }

        // Biomes
        for (ResourceKey<Biome> biome : EOSBiome.EOS_BIOMES_LIST) {
            add("biome" + '.' + EmpireOfStarsMod.MOD_ID + '.' + biome.location().getPath(), getDisplayNameFromResourceLocation(biome.location()));
        }

        // Sounds
        for (DeferredHolder<SoundEvent, ? extends SoundEvent> sound : EOSSound.SOUNDS.getEntries()) {
            if (sound.getId() != null) {
                add("sounds.empireofstars" + '.' + sound.get().getLocation().getPath(), getDisplayNameFromResourceLocation(sound.getId()));
            }
        }
    }

    private String getDisplayNameFromResourceLocation(ResourceLocation id) {
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
