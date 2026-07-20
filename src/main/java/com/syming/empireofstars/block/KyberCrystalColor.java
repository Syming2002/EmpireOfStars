package com.syming.empireofstars.block;

import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum KyberCrystalColor implements StringRepresentable {
    AQUAMARINE("aquamarine", 0x7FFFD4),
    BLACK("black", 0x000000),
    BLUE("blue",0x2E67F8),
    GREEN("green",0x2FF924),
    PINK("pink",0xF4D4E1),
    PURPLE("purple", 0x730FF0),
    RED("red",0xEB212E),
    WHITE("white", 0xFFFFFF),
    YELLOW("yellow", 0xFFF738);


    private final float red, green, blue;
    private final String name;
    private final int color;

    KyberCrystalColor(String name, int color) {
        this.name = name;
        this.color = color;

        this.red = ((color >> 16) & 0xFF) / 255.0F;
        this.green = ((color >> 8) & 0xFF) / 255.0F;
        this.blue = (color & 0xFF) / 255.0F;
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

    public int getColor() {
        return this.color;
    }

    public static KyberCrystalColor fromSerialized(String name) {
        return KyberCrystalColor.valueOf(name.toUpperCase(Locale.ROOT));
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
