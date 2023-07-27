package com.cwdj.learningtomod.items;

import org.apache.commons.lang3.NotImplementedException;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public abstract class CwdjItem extends Item {
    public static Identifier ID;
    
    public CwdjItem(Settings settings) {
        super(settings);
    }
    
    public static CwdjItem registerItem() {
        throw new NotImplementedException("An implementation of this class must override the item registry behavior");
    }
}
