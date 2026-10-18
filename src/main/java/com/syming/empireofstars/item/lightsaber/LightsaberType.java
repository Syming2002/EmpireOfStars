package com.syming.empireofstars.item.lightsaber;

import net.minecraft.util.StringRepresentable;

public enum LightsaberType implements StringRepresentable {
    STANDARD("standard");

    private final String name;

    LightsaberType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
