package com.franco227.doob.entity;

import com.franco227.doob.DoobClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static com.franco227.doob.Doob.MOD_ID;

public class JerboaEntityRenderer extends MobEntityRenderer<JerboaEntity, JerboaEntityModel> {

    public JerboaEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new JerboaEntityModel(context.getPart(DoobClient.MODEL_JERBOA_LAYER)), 0.3f);
    }

    @Override
    public Identifier getTexture(JerboaEntity entity) {
        return Identifier.of(MOD_ID, "textures/entity/jerboa/jerboa.png");
    }

    @Override
    public void render(JerboaEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (entity.isBaby()) {
            matrixStack.scale(0.7f, 0.7f, 0.7f);
        }
        super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
