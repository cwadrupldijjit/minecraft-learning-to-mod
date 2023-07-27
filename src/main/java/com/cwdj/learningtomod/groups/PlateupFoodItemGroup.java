package com.cwdj.learningtomod.groups;

import net.minecraft.item.ItemGroup;

import com.cwdj.learningtomod.items.PizzaCrust;
import com.cwdj.learningtomod.items.PizzaSlice;
import com.cwdj.learningtomod.items.ShreddedCheese;
import com.cwdj.learningtomod.items.TomatoSauce;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.item.ItemStack;

public class PlateupFoodItemGroup extends CwdjItemGroup {
    public static final Identifier ID = new Identifier("cwdj", "plateup_food_group");
	public static PizzaSlice PIZZA_SLICE;
    public static PizzaCrust PIZZA_CRUST;
    public static TomatoSauce TOMATO_SAUCE;
    public static ShreddedCheese SHREDDED_CHEESE;
	
    public static ItemGroup registerItemGroup() {
        registerItems();
        
        var itemGroup = FabricItemGroup.builder()
            .icon(() -> new ItemStack(PIZZA_SLICE))
            .displayName(Text.translatable("itemGroup.cwdj.plateup_food_group"))
            .build();
        
        Registry.register(Registries.ITEM_GROUP, ID, itemGroup);
        
        
        return itemGroup;
    }
    
    protected static void registerItems() {
        entries.put("PIZZA_SLICE", PizzaSlice.registerItem());
        entries.put("PIZZA_CRUST", PizzaCrust.registerItem());
        entries.put("TOMATO_SAUCE", TomatoSauce.registerItem());
        entries.put("SHREDDED_CHEESE", ShreddedCheese.registerItem());
    }
}
