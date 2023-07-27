package com.cwdj.learningtomod.items;

import net.minecraft.item.FoodComponent;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

/**
 * placeable item similar to how a cake can be
 * 1. ✅ edible
 * 2. ✅ stack size: 4
 * 3. add to our custom item group
 * 4. recipe
 * 5. 🌓 model
 * 6. add relevant tags
 * 7. right-click with it
 * 8. react to the environment when placed
 */
public class PizzaSlice extends CwdjItem {
    public static PizzaSlice registerItem() {
        var settings = new FabricItemSettings()
            .maxCount(4)
            .food(
                new FoodComponent.Builder()
                    .hunger(5)
                    .saturationModifier(0.4f)
                    .build()
            );
        var pizzaSlice = new PizzaSlice(settings);
        Registry.register(Registries.ITEM, new Identifier("cwdj", "pizza_slice"), pizzaSlice);
        
        return pizzaSlice;
    }
    
    public PizzaSlice(Settings settings) {
        super(settings);
    }
}
