package com.franco227.doob.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.franco227.doob.Doob.MOD_ID;

public class DoobEntities {

    public static final EntityType<JerboaEntity> JERBOA = FabricEntityTypeBuilder
        .create(SpawnGroup.CREATURE, JerboaEntity::new)
        .dimensions(new EntityDimensions(0.42f, 0.68f, true))
        .build();

    public static void initialize() {
        Registry.register(Registries.ENTITY_TYPE, Identifier.of(MOD_ID, "jerboa"), JERBOA);
        FabricDefaultAttributeRegistry.register(JERBOA, JerboaEntity.createJerboaAttributes());
    }
}
