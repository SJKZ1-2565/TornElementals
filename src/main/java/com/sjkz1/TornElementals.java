package com.sjkz1;

import com.sjkz1.entity.elements.AnimalElements;
import com.sjkz1.utils.TELootableManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.DataComponentMatchers;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
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
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (source.isBuiltin() && EntityType.MAGMA_CUBE.getDefaultLootTable().get().equals(key)) {
                HolderGetter<EntityType<?>> holderGetter = registries.lookupOrThrow(Registries.ENTITY_TYPE);
                HolderGetter<FrogVariant> holderGetter2 = registries.lookupOrThrow(Registries.FROG_VARIANT);
                var poolBuilder = LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F));
                poolBuilder.add(
                                LootItem.lootTableItem(Items.DIAMOND_BLOCK)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                                        .when(TELootableManager.killedByFrogVariant(holderGetter, holderGetter2, AnimalElements.ELEMENTS_SNOW_FROG))
                        )
                        .add(
                                LootItem.lootTableItem(Items.EMERALD_BLOCK)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                                        .when(TELootableManager.killedByFrogVariant(holderGetter, holderGetter2, AnimalElements.ELEMENTS_POISON_FROG))
                        )
                        .add(
                                LootItem.lootTableItem(Items.GOLD_BLOCK)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                                        .when(TELootableManager.killedByFrogVariant(holderGetter, holderGetter2, AnimalElements.ELEMENTS_MOUNTAIN_FROG))
                        )
                        .add(
                                LootItem.lootTableItem(Items.REDSTONE_BLOCK)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                                        .when(TELootableManager.killedByFrogVariant(holderGetter, holderGetter2, AnimalElements.ELEMENTS_FIRE_FROG))
                        );
                tableBuilder.withPool(poolBuilder);
            }
        });
    }
}
