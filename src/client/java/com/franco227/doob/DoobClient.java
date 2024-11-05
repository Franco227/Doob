package com.franco227.doob;

import com.franco227.doob.entity.JerboaEntityModel;
import com.franco227.doob.entity.JerboaEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static com.franco227.doob.Doob.MOD_ID;
import static com.franco227.doob.entity.DoobEntities.JERBOA;

public class DoobClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_JERBOA_LAYER = new EntityModelLayer(Identifier.of(MOD_ID, "jerboa"), "main");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(JERBOA, JerboaEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_JERBOA_LAYER, JerboaEntityModel::getTexturedModelData);
    }
}
