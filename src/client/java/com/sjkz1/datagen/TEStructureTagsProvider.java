package com.sjkz1.datagen;

import com.sjkz1.TornElementals;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.concurrent.CompletableFuture;

public class TEStructureTagsProvider extends FabricTagProvider<Structure> {


    public TEStructureTagsProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> provider) {
        super(dataOutput, Registries.STRUCTURE, provider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.builder(TornElementals.IS_IGLOO).add(BuiltinStructures.IGLOO);
    }
}
