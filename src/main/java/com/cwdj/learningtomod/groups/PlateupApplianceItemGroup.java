package com.cwdj.learningtomod.groups;

import com.cwdj.learningtomod.blocks.Counter;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PlateupApplianceItemGroup extends CwdjItemGroup {
    public static final Identifier ID = new Identifier("cwdj", "plateup_appliance_group");
    public static Counter COUNTER;
    
    public static ItemGroup registerItemGroup() {
        registerItems();
        
        var itemGroup = FabricItemGroup.builder()
            .icon(() -> new ItemStack(COUNTER))
            .displayName(Text.translatable("itemGroup.cwdj.plateup_appliance_group"))
            .build();
        
        Registry.register(Registries.ITEM_GROUP, ID, itemGroup);
        
        registerItemGroupEvents(ID);
        
        return itemGroup;
    }
    
    protected static void registerItems() {
        entries.put("COUNTER", Counter.registerBlock());
    }
}
