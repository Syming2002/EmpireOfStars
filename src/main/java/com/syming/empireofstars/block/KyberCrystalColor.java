package com.syming.empireofstars.block;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

import java.util.Locale;

public enum KyberCrystalColor implements StringRepresentable {
    AQUAMARINE("aquamarine", 0xFF7FFFD4),
    BLACK("black", 0xFF000000),
    BLUE("blue", 0xFF2E67F8),
    GREEN("green", 0xFF2FF924),
    ORANGE("orange", 0xFFFF7F00),
    PINK("pink", 0xFFF4D4E1),
    PURPLE("purple", 0xFF730FF0),
    RED("red", 0xFFEB212E),
    WHITE("white", 0xFFFFFFFF),
    YELLOW("yellow", 0xFFFFF738);


    private final float red, green, blue;
    private final String name;
    private final int kybercolor;

    KyberCrystalColor(String name, int kybercolor) {
        this.name = name;
        this.kybercolor = kybercolor;

        this.red = ((kybercolor >> 16) & 0xFF) / 255.0F;
        this.green = ((kybercolor >> 8) & 0xFF) / 255.0F;
        this.blue = (kybercolor & 0xFF) / 255.0F;
    }

    public float getRed() {
        return this.red;
    }

    public float getGreen() {
        return this.green;
    }

    public float getBlue() {
        return this.blue;
    }

    public String getName() {
        return this.name;
    }

    public int getKyberColor() {
        return this.kybercolor;
    }

    public static KyberCrystalColor fromSerialized(String name) {
        return KyberCrystalColor.valueOf(name.toUpperCase(Locale.ROOT));
    }

    public static void setKyberColorNameFromStack(ItemStack stack, KyberCrystalColor kyberColor) {
        CustomData.update(DataComponents.CUSTOM_DATA, stack, compoundTag ->  compoundTag.putString("kyberColorName", kyberColor.getSerializedName()));
    }

    public static void setKyberColorCodeFromStack(ItemStack stack, int kyberColor) {
        CustomData.update(DataComponents.CUSTOM_DATA, stack, compoundTag ->  compoundTag.putInt("kyberColorCode", kyberColor));
    }

    public static void setShardCountFromStack(ItemStack stack, int shard) {
        CustomData.update(DataComponents.CUSTOM_DATA, stack, compoundTag ->  compoundTag.putInt("shard", shard));
    }

    public static KyberCrystalColor getKyberColorNameFromStack(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            if (tag.contains("kyberColorName")) {
                return KyberCrystalColor.fromSerialized(tag.getString("kyberColorName"));
            }
        }
        return null;
    }

    public static int getKyberColorCodeFromStack(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            if (tag.contains("kyberColorCode")) {
                return tag.getInt("kyberColorCode");
            }
        }
        return 0xFFFFFFFF;
    }

    public static int getShardCountFromStack(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            if (tag.contains("shard")) {
                return tag.getInt("shard");
            }
        }
        return 1;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
