package com.cwdj.learningtomod.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SharpKnife extends CwdjItem {
    public static final Identifier ID = new Identifier("cwdj", "sharpknife");
    private static SharpKnife REGISTRY_ENTRY;
    
    public SharpKnife(Settings settings) {
        super(settings);
    }
    
    public static SharpKnife registerItem() {
        if (REGISTRY_ENTRY == null) {
            REGISTRY_ENTRY = new SharpKnife(
                new FabricItemSettings()
                    .maxCount(1)
            );
        }
        
        if (!Registries.ITEM.containsId(ID)) {
            Registry.register(Registries.ITEM, ID, REGISTRY_ENTRY);
        }
        
        return REGISTRY_ENTRY;
    }
}
