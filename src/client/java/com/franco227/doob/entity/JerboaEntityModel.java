package com.franco227.doob.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class JerboaEntityModel extends SinglePartEntityModel<JerboaEntity> {
    private final ModelPart Jerboa;

    public JerboaEntityModel(ModelPart root) {
        this.Jerboa = root.getChild("Jerboa");
        ModelPart body = this.Jerboa.getChild("Body");
        ModelPart legs = body.getChild("Legs");
        ModelPart legR = legs.getChild("LegR");
        ModelPart legL = legs.getChild("LegL");
        ModelPart tail = body.getChild("Tail");
        ModelPart doobHat = body.getChild("DoobHat");
    }

    @Override
    public ModelPart getPart() {
        return this.Jerboa;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData Jerboa = modelPartData.addChild("Jerboa", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        ModelPartData Body = Jerboa.addChild("Body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -2.0F, 0.0F));
        ModelPartData RightEar = Body.addChild("EarR_r1", ModelPartBuilder.create().uv(0, 19).cuboid(-2.5F, -2.0F, -0.5F, 5.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -8.4414F, -1.257F, -0.2032F, 0.3465F, 0.142F));
        ModelPartData LeftEar = Body.addChild("EarL_r1", ModelPartBuilder.create().uv(18, 16).cuboid(-2.5F, -2.0F, -0.5F, 5.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -8.4414F, -1.257F, -0.2106F, -0.4319F, -0.1226F));
        ModelPartData Legs = Body.addChild("Legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 2.0F, 0.0F));
        ModelPartData LegR = Legs.addChild("LegR", ModelPartBuilder.create().uv(26, 0).cuboid(-1.0F, 4.9701F, -2.356F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -5.0F, 1.0F));
        ModelPartData LegL = Legs.addChild("LegL", ModelPartBuilder.create().uv(24, 10).cuboid(-1.0F, 4.9701F, -2.356F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -5.0F, 1.0F));
        ModelPartData Tail = Body.addChild("Tail", ModelPartBuilder.create(), ModelTransform.pivot(0.5F, -4.0F, 2.0F));
        ModelPartData DoobHat = Body.addChild("DoobHat", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 2.0F, 0.0F));

        ModelPartData cube_r1 = Body.addChild("cube_r1", ModelPartBuilder.create().uv(24, 6).cuboid(-2.0F, -3.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.2F))
            .uv(24, 8).cuboid(0.0F, -2.0F, -0.2F, 1.0F, 1.0F, 1.0F, new Dilation(-0.1F))
            .uv(12, 19).cuboid(2.0F, -3.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.2F))
            .uv(0, 0).cuboid(-2.0F, -4.0F, 0.0F, 5.0F, 4.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -4.0F, -5.0F, -0.2618F, 0.0F, 0.0F));
        ModelPartData cube_r2 = LegR.addChild("cube_r2", ModelPartBuilder.create().uv(24, 24).cuboid(0.0F, -1.0F, 0.0F, 1.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 3.5549F, 0.0583F, -0.7854F, 0.0F, 0.0F));
        ModelPartData cube_r3 = LegR.addChild("cube_r3", ModelPartBuilder.create().uv(10, 24).cuboid(0.0F, -1.0F, 0.0F, 1.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 1.0F, 0.0F, 0.3927F, 0.0F, 0.0F));
        ModelPartData cube_r4 = LegL.addChild("cube_r4", ModelPartBuilder.create().uv(0, 26).cuboid(0.0F, -1.0F, 0.0F, 1.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 3.5549F, 0.0583F, -0.7854F, 0.0F, 0.0F));
        ModelPartData cube_r5 = LegL.addChild("cube_r5", ModelPartBuilder.create().uv(24, 21).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, 0.3927F, 0.0F, 0.0F));
        ModelPartData cube_r6 = Tail.addChild("cube_r6", ModelPartBuilder.create().uv(18, 11).cuboid(-1.0F, -6.0F, 2.0F, 3.0F, 0.0F, 5.0F, new Dilation(0.0F))
            .uv(24, 0).cuboid(0.0F, -6.0F, 7.0F, 1.0F, 6.0F, 0.0F, new Dilation(0.0F))
            .uv(0, 11).cuboid(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));
        ModelPartData cube_r7 = DoobHat.addChild("cube_r7", ModelPartBuilder.create().uv(0, 24).cuboid(-1.0F, -4.0F, -1.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F))
            .uv(12, 21).cuboid(-1.0F, -6.0F, 0.0F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -6.0F, -5.0F, -0.2618F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void setAngles(JerboaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.updateAnimation(entity.jumpAnimationState, JerboaEntityAnimations.JUMPING_ANIMATION, ageInTicks, 2f);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        Jerboa.render(matrices, vertexConsumer, light, overlay, color);
    }
}
