package com.franco227.doob.tag;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import static com.franco227.doob.Doob.MOD_ID;

public class DoobTags {
    public static final TagKey<Biome> JERBOA_SPAWNABLE = biomeTag("jerboa_spawnable");
    public static final TagKey<Block> JERBOA_SPAWNABLE_ON = blockTag("jerboa_spawnable_on");
    public static final TagKey<Item> JERBOA_FOOD = itemTag("jerboa_food");


    private static TagKey<Biome> biomeTag(String path) {
        return TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, path));
    }

    private static TagKey<Block> blockTag(String path) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, path));
    }

    private static TagKey<Item> itemTag(String path) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, path));
    }
}
