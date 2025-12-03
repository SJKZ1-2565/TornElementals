package com.sjkz1.mixin;

import com.sjkz1.TornElementals;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {

    @Inject(method = "canEntityWalkOnPowderSnow", at = @At("HEAD"), cancellable = true)
    private static void canEntityWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof Frog frog) {
            if (frog.getVariant().is(TornElementals.SPEED_FROG)) {
                cir.setReturnValue(true);
            }
        }
    }
}
