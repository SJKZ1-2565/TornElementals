package com.sjkz1.utils;

import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.DataComponentMatchers;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class TELootableManager {

    public static LootItemCondition.Builder killedByFrogVariant(
            HolderGetter<EntityType<?>> holderGetter, HolderGetter<FrogVariant> holderGetter2, ResourceKey<FrogVariant> resourceKey
    ) {
        return DamageSourceCondition.hasDamageSource(
                DamageSourcePredicate.Builder.damageType()
                        .source(
                                EntityPredicate.Builder.entity()
                                        .of(holderGetter, EntityType.FROG)
                                        .components(
                                                DataComponentMatchers.Builder.components()
                                                        .exact(DataComponentExactPredicate.expect(DataComponents.FROG_VARIANT, holderGetter2.getOrThrow(resourceKey)))
                                                        .build()
                                        )
                        )
        );
    }
}
