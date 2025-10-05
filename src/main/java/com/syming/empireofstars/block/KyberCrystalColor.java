package com.syming.empireofstars.block;

public enum KyberCrystalColor {
    AQUAMARINE("aquamarine", 0x7FFFD4),
    BLACK("black", 0x000000),
    BLUE("blue",0x2E67F8),
    GREEN("green",0x2FF924),
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
}
