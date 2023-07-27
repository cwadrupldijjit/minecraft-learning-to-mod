package com.cwdj.learningtomod.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ShreddedCheese extends CwdjItem {
    public static ShreddedCheese registerItem() {
        var shreddedCheese = new ShreddedCheese(
            new FabricItemSettings()
                .maxCount(1)
        );
        
        Registry.register(Registries.ITEM, new Identifier("cwdj", "shredded_cheese"), shreddedCheese);
        
        return shreddedCheese;
    }
    
    public ShreddedCheese(Settings settings) {
        super(settings);
    }
}
