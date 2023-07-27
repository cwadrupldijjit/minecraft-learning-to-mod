package com.cwdj.learningtomod.misc;

import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

@FunctionalInterface
public interface SingleItemInventory extends Inventory {
    // ItemStack getStoredItem();
    // ItemStack setStoredItem(Item item);
    
    DefaultedList<ItemStack> getItems();
    
    
    static SingleItemInventory of(DefaultedList<ItemStack> stacks) {
        Item selectedItem = null;
        
        for (var stack : stacks) {
            if (!stack.isEmpty()) {
                selectedItem = stack.getItem();
                break;
            }
        }
        
        final var finalItem = selectedItem;
        
        return () -> DefaultedList.ofSize(1, finalItem == null ?
            ItemStack.EMPTY :
            new ItemStack(finalItem));
    }
    
    static SingleItemInventory ofSize() {
        return of(DefaultedList.ofSize(1, ItemStack.EMPTY));
    }
    
    @Override
    default int size() {
        return isEmpty() ? 0 : 1;
    }
    
    @Override
    default boolean isEmpty() {
        return getItems().get(0).isEmpty();
    }
    
    @Override
    default ItemStack getStack(int slot) {
        return isEmpty() ?
            ItemStack.EMPTY :
            getItems().get(0);
    }
    
    default ItemStack getStack() {
        return getStack(0);
    }
    
    @Override
    default ItemStack removeStack(int slot, int count) {
        if (isEmpty()) {
            return ItemStack.EMPTY;
        }
        
        markDirty();
        
        var stack = getItems().get(0);
        
        getItems().set(0, ItemStack.EMPTY);
        
        return stack;
    }
    
    @Override
    default ItemStack removeStack(int slot) {
        return removeStack(0, 1);
    }
    
    default ItemStack removeStack() {
        return removeStack(0);
    }
    
    @Override
    default void setStack(int slot, ItemStack stack) {
        if (stack.isEmpty() && getItems().get(0).isEmpty()) {
            return;
        }
        
        getItems().set(0, Inventories.splitStack(List.of(stack), 0, 1));
        
        markDirty();
    }
    
    default void setStack(Item item) {
        if (item == null) {
            setStack(0, ItemStack.EMPTY);
            return;
        }
        
        setStack(0, new ItemStack(item));
    }
    
    @Override
    default void clear() {
        getItems().set(0, ItemStack.EMPTY);
    }
    
    @Override
    default void markDirty() {
        // TODO: figure out what I need to do with this...
    }
    
    @Override
    default boolean canPlayerUse(PlayerEntity player) {
        return true;
    }
}
