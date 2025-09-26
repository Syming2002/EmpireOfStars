package com.syming.empireofstars.item.lightsaber;

public enum LightsaberTypeBlade {
    SINGLE("single"),
    DOUBLE("double");

    private final String name;

    LightsaberTypeBlade(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
