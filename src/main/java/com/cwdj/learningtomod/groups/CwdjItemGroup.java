package com.cwdj.learningtomod.groups;

import java.util.Hashtable;

import org.apache.commons.lang3.NotImplementedException;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public abstract class CwdjItemGroup {
    public static Hashtable<String, Item> entries = new Hashtable<String, Item>();
    
    public static boolean contains(Item item) {
        return entries.containsValue(item);
    }
    
    protected static void registerItemGroupEvents(Identifier id) {
        ItemGroupEvents.modifyEntriesEvent(RegistryKey.of(RegistryKeys.ITEM_GROUP, id))
            .register(content -> {
                for (var item : entries.values()) {
                    content.add(item);
                }
            });
    }
    
    // methods to override
    protected static void registerItems() {
        throw new NotImplementedException("An item group must implement this method to register the items in that group");
    }
    
    public static ItemGroup registerItemGroup() {
        throw new NotImplementedException("An item group must implement this method to register itself");
    }
}
