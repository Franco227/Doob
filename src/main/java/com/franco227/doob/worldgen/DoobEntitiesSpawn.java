package com.franco227.doob.worldgen;

import com.franco227.doob.entity.DoobEntities;
import com.franco227.doob.entity.JerboaEntity;
import com.franco227.doob.tag.DoobTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;

public class DoobEntitiesSpawn {
    public static void addJerboaSpawn() {
        BiomeModifications.addSpawn(
            BiomeSelectors.tag(DoobTags.JERBOA_SPAWNABLE),
            SpawnGroup.CREATURE, DoobEntities.JERBOA,
            3, 2, 4
        );
        SpawnRestriction.register(
            DoobEntities.JERBOA,
            SpawnRestriction.Location.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            JerboaEntity::isValidSpawn
        );
    }

    public static void initialize() {
        addJerboaSpawn();
    }
}
