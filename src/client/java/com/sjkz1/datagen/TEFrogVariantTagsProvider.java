package com.sjkz1.datagen;

import com.sjkz1.TornElementals;
import com.sjkz1.entity.elements.AnimalElements;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.entity.animal.frog.FrogVariants;

import java.util.concurrent.CompletableFuture;

public class TEFrogVariantTagsProvider extends FabricTagProvider<FrogVariant> {


    public TEFrogVariantTagsProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> provider) {
        super(dataOutput, Registries.FROG_VARIANT, provider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.builder(TornElementals.UNRIDEABLE_VARIANT).add(FrogVariants.TEMPERATE).add(FrogVariants.COLD).add(FrogVariants.WARM);
        this.builder(TornElementals.SCARED_HOSTILE_MOB_FROG).add(AnimalElements.ELEMENTS_POISON_FROG);
    }
}
