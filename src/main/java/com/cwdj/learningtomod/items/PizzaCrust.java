package com.cwdj.learningtomod.items;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Identifier;

public class PizzaCrust extends CwdjItem {
    public static PizzaCrust registerItem() {
        var pizzaCrust = new PizzaCrust(
            new FabricItemSettings()
                .maxCount(1)
        );
        
        Registry.register(Registries.ITEM, new Identifier("cwdj", "pizza_crust"), pizzaCrust);
        
        return pizzaCrust;
    }
    
    public PizzaCrust(Settings settings) {
        super(settings);
    }
    
    public int portions = 4;
    public final List<Item> toppings = List.of();
}
