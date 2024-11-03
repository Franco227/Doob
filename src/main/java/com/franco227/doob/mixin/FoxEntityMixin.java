package com.franco227.doob.mixin;

import com.franco227.doob.entity.JerboaEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoxEntity.class)
public class FoxEntityMixin extends MobEntity {

    protected FoxEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("TAIL"), method = "addTypeSpecificGoals")
    public void addTypeSpecificGoals(CallbackInfo ci) {
        this.targetSelector.add(6, new ActiveTargetGoal<>(this, JerboaEntity.class, false));
    }
}
