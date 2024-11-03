package com.franco227.doob.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.franco227.doob.Doob.MOD_ID;
import static com.franco227.doob.entity.DoobEntities.JERBOA;

public class DoobItems {

    public static final Item JERBOA_SPAWN_EGG = registerItem(
        "jerboa_spawn_egg",
        new SpawnEggItem(JERBOA, 0x9b834f, 0xb5a98d, new Item.Settings())
    );
    public static final Item ROOTS = registerItem("roots", new Item(new Item.Settings()));
    public static final Item SMALL_LEAVES = registerItem("small_leaves", new Item(new Item.Settings()));

    private static void addSpawnEggItems (@NotNull FabricItemGroupEntries entries) {
        entries.add(JERBOA_SPAWN_EGG);
    }

    private static void addIngredientItems (@NotNull FabricItemGroupEntries entries) {
        entries.add(ROOTS);
        entries.add(SMALL_LEAVES);
    }

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(DoobItems::addSpawnEggItems);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(DoobItems::addIngredientItems);
    }
}
