package com.sjkz1.mixin;

import com.sjkz1.TornElementals;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.monster.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Zombie.class, Spider.class, Creeper.class, AbstractSkeleton.class, AbstractIllager.class})
public class MonsterMixin extends Monster {

    protected MonsterMixin() {
        super(null, null);
    }

    @Inject(method = "registerGoals", at = @At("HEAD"))
    public void initGoals(CallbackInfo ci) {
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Frog.class, 6.0F, 1.0, 1.2, livingEntity -> {
            if (livingEntity instanceof Frog frog) {
                return frog.getVariant().is(TornElementals.SCARED_HOSTILE_MOB_FROG);
            }
            return false;
        }));
    }
}
