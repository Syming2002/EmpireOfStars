package com.syming.empireofstars.item.lightsaber;

import com.syming.empireofstars.client.lightsaber.LightsaberRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class LightsaberEmptyItem extends Item {
    public LightsaberEmptyItem() {
        super(new Properties().stacksTo(64));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            private LightsaberRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    return this.renderer = LightsaberRenderer.getInstance();
                }
                return this.renderer;
            }
        });
    }
}
