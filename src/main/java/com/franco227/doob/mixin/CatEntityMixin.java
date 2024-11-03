package com.franco227.doob.mixin;

import com.franco227.doob.entity.JerboaEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.UntamedActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(CatEntity.class)
public class CatEntityMixin extends MobEntity {

    protected CatEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("TAIL"), method = "initGoals")
    public void initGoals(CallbackInfo ci) {
        this.targetSelector.add(1, new UntamedActiveTargetGoal<>((TameableEntity)(Object)this, JerboaEntity.class, false, null));
    }
}
