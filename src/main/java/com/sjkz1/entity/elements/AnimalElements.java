package com.sjkz1.entity.elements;

import com.sjkz1.TornElementals;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;
import net.minecraft.world.entity.variant.StructureCheck;
import net.minecraft.world.level.levelgen.structure.Structure;

public class AnimalElements {
    public static final ResourceKey<FrogVariant> ELEMENTS_MOUNTAIN_FROG = ResourceKey.create(Registries.FROG_VARIANT, TornElementals.id("elements_mountain"));
    public static final ResourceKey<FrogVariant> ELEMENTS_SNOW_FROG = ResourceKey.create(Registries.FROG_VARIANT, TornElementals.id("elements_snow"));
    public static final ResourceKey<FrogVariant> ELEMENTS_FIRE_FROG = ResourceKey.create(Registries.FROG_VARIANT, TornElementals.id("elements_fire"));
    public static final ResourceKey<FrogVariant> ELEMENTS_POISON_FROG = ResourceKey.create(Registries.FROG_VARIANT, TornElementals.id("elements_poison"));

    public static void frogVariantBootstrap(BootstrapContext<FrogVariant> context) {
        HolderSet<Structure> mineShaftStructure = context.lookup(Registries.STRUCTURE).getOrThrow(StructureTags.ON_DESERT_VILLAGE_MAPS);
        HolderSet<Structure> iglooStructure = context.lookup(Registries.STRUCTURE).getOrThrow(TornElementals.IS_IGLOO);
        HolderSet<Structure> ruinedPortalStructure = context.lookup(Registries.STRUCTURE).getOrThrow(StructureTags.RUINED_PORTAL);
        HolderSet<Structure> hutStructure = context.lookup(Registries.STRUCTURE).getOrThrow(StructureTags.CATS_SPAWN_AS_BLACK);
        context.register(ELEMENTS_MOUNTAIN_FROG, new FrogVariant(new ClientAsset.ResourceTexture(TornElementals.id("entity/frog/mountain")), SpawnPrioritySelectors.single(new StructureCheck(mineShaftStructure), 1)));
        context.register(ELEMENTS_SNOW_FROG, new FrogVariant(new ClientAsset.ResourceTexture(TornElementals.id("entity/frog/snow")), SpawnPrioritySelectors.single(new StructureCheck(iglooStructure), 1)));
        context.register(ELEMENTS_FIRE_FROG, new FrogVariant(new ClientAsset.ResourceTexture(TornElementals.id("entity/frog/fire")), SpawnPrioritySelectors.single(new StructureCheck(ruinedPortalStructure), 1)));
        context.register(ELEMENTS_POISON_FROG, new FrogVariant(new ClientAsset.ResourceTexture(TornElementals.id("entity/frog/poison")), SpawnPrioritySelectors.single(new StructureCheck(hutStructure), 1)));
    }
}
