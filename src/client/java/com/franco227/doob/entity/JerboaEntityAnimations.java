package com.franco227.doob.entity;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class JerboaEntityAnimations {
	public static final Animation JUMPING_ANIMATION = Animation.Builder.create(1.0F).looping()
		.addBoneAnimation("Body", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.125F, AnimationHelper.createRotationalVector(12.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Body", new Transformation(Transformation.Targets.TRANSLATE,
			new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.125F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 1.5F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("Body", new Transformation(Transformation.Targets.SCALE,
			new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.125F, AnimationHelper.createScalingVector(1.0F, 0.9F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.25F, AnimationHelper.createScalingVector(1.0F, 1.1F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createScalingVector(1.0F, 1.1F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5833F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("LegR", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.125F, AnimationHelper.createRotationalVector(-7.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("LegR", new Transformation(Transformation.Targets.SCALE,
			new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.125F, AnimationHelper.createScalingVector(1.0F, 0.9F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.625F, AnimationHelper.createScalingVector(1.0F, 0.875F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 0.9F, 1.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("LegL", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.125F, AnimationHelper.createRotationalVector(-7.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("LegL", new Transformation(Transformation.Targets.SCALE,
			new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.125F, AnimationHelper.createScalingVector(1.0F, 0.9F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.625F, AnimationHelper.createScalingVector(1.0F, 0.875F, 1.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 0.9F, 1.0F), Transformation.Interpolations.LINEAR)
		))
		.build();
}
