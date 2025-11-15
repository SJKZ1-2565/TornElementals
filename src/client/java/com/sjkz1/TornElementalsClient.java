package com.sjkz1;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.model.FrogModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.FrogRenderer;
import net.minecraft.client.renderer.entity.layers.LivingEntityEmissiveLayer;
import net.minecraft.client.renderer.entity.state.FrogRenderState;
import net.minecraft.resources.ResourceLocation;

public class TornElementalsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, livingEntityRenderer, registrationHelper, context) -> {
            if (livingEntityRenderer instanceof FrogRenderer frogRenderer) {
                registrationHelper.register(
                        new LivingEntityEmissiveLayer<>(
                                frogRenderer,
                                this::getElementsFrogEmissiveTexture,
                                (frogRenderState, f) -> frogRenderState.texture.getNamespace().contains(TornElementals.MOD_ID) ? 0.75F : 0F,
                                new FrogModel(context.bakeLayer(ModelLayers.FROG)),
                                RenderType::eyes,
                                true
                        )
                );
            }
        });
    }

    private ResourceLocation getElementsFrogEmissiveTexture(FrogRenderState frogRenderState) {
        String path = frogRenderState.texture.getPath();
        return TornElementals.id(path.replace(".png", "_emissive.png"));
    }
}