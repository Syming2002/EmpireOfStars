package com.syming.empireofstars.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.syming.empireofstars.EmpireOfStarsMod;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public final class EOSKeybinding {

    public static final EOSKeybinding INSTANCE = new EOSKeybinding();

    private EOSKeybinding() {

    }

    private static final String EMPIRE_OF_STARS_CATEGORY = "key.categories." + EmpireOfStarsMod.MOD_ID;

    public final KeyMapping ACTIVE_LIGHTSABER = new KeyMapping(
            "key." + EmpireOfStarsMod.MOD_ID + ".active_lightsaber_key",
            InputConstants.KEY_NUMPAD8,
            EMPIRE_OF_STARS_CATEGORY

    );


}
