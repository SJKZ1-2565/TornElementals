package com.sjkz1;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TornElementals implements ModInitializer {
    public static final String MOD_ID = "torn-elementals";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final TagKey<Structure> IS_IGLOO = TagKey.create(Registries.STRUCTURE, TornElementals.id("is_igloo"));
    public static final TagKey<FrogVariant> UNRIDEABLE_VARIANT = TagKey.create(Registries.FROG_VARIANT, TornElementals.id("unrideable_variant"));
    public static final TagKey<FrogVariant> SCARED_HOSTILE_MOB_FROG = TagKey.create(Registries.FROG_VARIANT, TornElementals.id("scared_hostile_mob_frog"));
    public static final TagKey<FrogVariant> SPEED_FROG = TagKey.create(Registries.FROG_VARIANT, TornElementals.id("speed_frog"));

    public static ResourceLocation id(String string) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, string);
    }

    @Override
    public void onInitialize() {

    }
}
