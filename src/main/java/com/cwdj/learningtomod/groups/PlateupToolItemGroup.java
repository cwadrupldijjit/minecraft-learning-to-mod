package com.cwdj.learningtomod.groups;

import com.cwdj.learningtomod.items.SharpKnife;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PlateupToolItemGroup extends CwdjItemGroup {
    public static final Identifier ID = new Identifier("cwdj", "plateup_tool_group");
    public static SharpKnife SHARP_KNIFE;
    private static ItemGroup PLATEUP_TOOL_ITEM_GROUP;
    
    public static ItemGroup registerItemGroup() {
        registerItems();
        
        if (!Registries.ITEM_GROUP.containsId(ID)) {
            PLATEUP_TOOL_ITEM_GROUP = FabricItemGroup.builder()
                .icon(() -> new ItemStack(SHARP_KNIFE))
                .displayName(Text.translatable("itemGroup.cwdj.plateup_tool_group"))
                .build();
        }
        
        if (PLATEUP_TOOL_ITEM_GROUP == null) {
            PLATEUP_TOOL_ITEM_GROUP = Registries.ITEM_GROUP.get(ID);
        }
        
        registerItemGroupEvents(ID);
        
        return PLATEUP_TOOL_ITEM_GROUP;
    }
    
    protected static void registerItems() {
        entries.put("SHARP_KNIFE", SharpKnife.registerItem());
    }
}
