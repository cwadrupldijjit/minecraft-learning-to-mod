package com.cwdj.learningtomod.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TomatoSauce extends CwdjItem {
    public static TomatoSauce registerItem() {
        var tomatoSauce = new TomatoSauce(
            new FabricItemSettings()
                .maxCount(1)
        );
        
        Registry.register(Registries.ITEM, new Identifier("cwdj", "tomato_sauce"), tomatoSauce);
        
        return tomatoSauce;
    }
    
    public TomatoSauce(Settings settings) {
        super(settings);
    }
}
