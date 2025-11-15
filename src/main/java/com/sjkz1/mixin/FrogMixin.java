package com.sjkz1.mixin;

import com.sjkz1.TornElementals;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Frog.class)
public abstract class FrogMixin extends Animal {

    FrogMixin() {
        super(null, null);
    }

    @Shadow
    public abstract Holder<FrogVariant> getVariant();

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return (LivingEntity) this.getFirstPassenger() instanceof Player player ? player : super.getControllingPassenger();
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        if (!this.isVehicle() && !this.isBaby()) {
            if (!this.getVariant().is(TornElementals.UNRIDEABLE_VARIANT)) {
                this.doPlayerRide(player);
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(player, interactionHand);
    }

    @Override
    protected void tickRidden(Player player, Vec3 vec3) {
        super.tickRidden(player, vec3);
        Vec2 vec2 = this.getRiddenRotation(player);
        float f = this.getYRot();
        float g = Mth.wrapDegrees(vec2.y - f);
        f += g * 0.08F;
        this.setRot(f, vec2.x);
        this.yRotO = this.yBodyRot = this.yHeadRot = f;
    }

    @Override
    protected @NotNull Vec3 getRiddenInput(Player player, Vec3 vec3) {
        float f = player.xxa;
        float g = 0.0F;
        float h = 0.0F;
        if (player.zza != 0.0F) {
            float i = Mth.cos(player.getXRot() * (float) (Math.PI / 180.0));
            float j = -Mth.sin(player.getXRot() * (float) (Math.PI / 180.0));
            if (player.zza < 0.0F) {
                i *= -0.5F;
                j *= -0.5F;
            }

            h = j;
            g = i;
        }
        return new Vec3(f, h, g).scale(0.1F);
    }

    @Unique
    protected Vec2 getRiddenRotation(LivingEntity livingEntity) {
        return new Vec2(livingEntity.getXRot() * 0.5F, livingEntity.getYRot());
    }

    @Override
    protected void addPassenger(Entity entity) {
        super.addPassenger(entity);
        entity.absSnapRotationTo(this.getViewYRot(0.0F), this.getViewXRot(0.0F));
    }

    @Override
    protected float getRiddenSpeed(Player player) {
        return (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED);
    }

    @Override
    protected void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
        super.positionRider(entity, moveFunction);
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).yBodyRot = this.yBodyRot;
        }
    }

    @Unique
    protected void doPlayerRide(Player player) {
        if (!this.level().isClientSide()) {
            player.setYRot(this.getYRot());
            player.setXRot(this.getXRot());
            player.startRiding(this);
        }
    }
}
