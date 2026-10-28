package com.syming.empireofstars.datagen;

import com.syming.empireofstars.EmpireOfStarsMod;
import com.syming.empireofstars.block.KyberCrystalBlock;
import com.syming.empireofstars.fluid.EOSFluid;
import com.syming.empireofstars.item.EOSItem;
import com.syming.empireofstars.item.KyberCrystalShardItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.loaders.DynamicFluidContainerModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class EOSItemModelProvider extends ItemModelProvider {
    public EOSItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EmpireOfStarsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (DeferredHolder<Item, ? extends Item> itemDeferredHolder : EOSItem.ITEMS.getEntries()) {
            if (itemDeferredHolder.get() instanceof SpawnEggItem) {
                withExistingParent(itemDeferredHolder.getId().getPath(), mcLoc("item/template_spawn_egg"));
            }

            if (itemDeferredHolder.get() instanceof BucketItem) {
                withExistingParent("sarlacc_stomach_acid_bucket", ResourceLocation.fromNamespaceAndPath("neoforge", "item/bucket")).customLoader(DynamicFluidContainerModelBuilder::begin)
                        .fluid(EOSFluid.SOURCE_SARLACC_STOMACH_ACID.get());
            }

            withExistingParent("sarlacc_spear_tooth", mcLoc("item/generated"))
                    .texture("layer0", modLoc("item/sarlacc_spear_tooth"));

            Item item = itemDeferredHolder.get();
            String path = itemDeferredHolder.getId().getPath();
            if (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof KyberCrystalBlock) {
                registerKyberCrystalBlockItem(path);
            }

            if (item instanceof KyberCrystalShardItem) {
                registerKyberCrystalShardItem(path);
            }
        }
    }

    private void registerKyberCrystalBlockItem(String name) {
        getBuilder(name).parent(new ModelFile.UncheckedModelFile("builtin/entity"));
        getBuilder(name).texture("particle", modLoc("block/kyber_crystal"));
    }

    private void registerKyberCrystalShardItem(String name) {
        getBuilder(name).parent(new ModelFile.UncheckedModelFile("item/generated"));
        getBuilder(name).texture("layer0", modLoc("item/kyber_crystal_shard"));
    }
}
