package com.franco227.doob.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.entity.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

import static com.franco227.doob.Doob.MOD_ID;

public class DoobEntities {

    public static final EntityType<JerboaEntity> JERBOA = FabricEntityType.Builder
        .createMob(JerboaEntity::new, SpawnGroup.CREATURE, UnaryOperator.identity())
        .dimensions(0.42f, 0.68f)
        .eyeHeight(0.55f)
        .build();

    public static void initialize() {
        Registry.register(Registries.ENTITY_TYPE, Identifier.of(MOD_ID, "jerboa"), JERBOA);
        FabricDefaultAttributeRegistry.register(JERBOA, JerboaEntity.createJerboaAttributes());
    }
}
