package com.syming.empireofstars.item.lightsaber;

import net.minecraft.util.StringRepresentable;

public enum LightsaberTypeBlade implements StringRepresentable {
    SINGLE("single"),
    DOUBLE("double");

    private final String name;

    LightsaberTypeBlade(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
