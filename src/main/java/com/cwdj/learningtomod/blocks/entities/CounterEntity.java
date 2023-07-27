package com.cwdj.learningtomod.blocks.entities;

import org.jetbrains.annotations.Nullable;
import org.slf4j.LoggerFactory;

import com.cwdj.learningtomod.blocks.Counter;
import com.cwdj.learningtomod.misc.SingleItemInventory;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class CounterEntity extends BlockEntity implements SingleItemInventory {
    private static Counter COUNTER = null;
    private static BlockEntityType<CounterEntity> COUNTER_ENTITY_TYPE;
    public static final Identifier COUNTER_ENTITY_ID = new Identifier("cwdj", "counter");
    
    protected DefaultedList<ItemStack> storedItem = DefaultedList.ofSize(1, ItemStack.EMPTY);
    public Item getItem() {
        var itemStack = storedItem.get(0);
        
        if (itemStack.isEmpty()) {
            return null;
        }
        
        return itemStack.getItem();
    }
    
    public static BlockEntityType<CounterEntity> getCounterEntityType() {
        if (COUNTER_ENTITY_TYPE == null) {
            COUNTER_ENTITY_TYPE = FabricBlockEntityTypeBuilder
                .create(CounterEntity::new, getCounter())
                .build();
        }
        
        return COUNTER_ENTITY_TYPE;
    }
    
    public static Counter getCounter() {
        if (COUNTER == null) {
            COUNTER = Counter.registerBlock().getBlock();
        }
        
        return COUNTER;
    }
    
    public static BlockEntityType<CounterEntity> registerBlockEntityType() {
        if (!Registries.BLOCK_ENTITY_TYPE.containsId(COUNTER_ENTITY_ID)) {
            Registry.register(Registries.BLOCK_ENTITY_TYPE, COUNTER_ENTITY_ID, getCounterEntityType());
        }
        
        return COUNTER_ENTITY_TYPE;
    }
    
    public CounterEntity(BlockPos pos, BlockState state) {
        super(COUNTER_ENTITY_TYPE, pos, state);
    }
    
    public void storeItem(Item item) {
        setStack(item);
    }
    
    public Item pickUpItem() {
        var item = getItem();
        
        removeStack(0);
        
        return item;
    }
    
    @Override
    public void writeNbt(NbtCompound nbt) {
        var storedItem = getItem();
        
        if (storedItem != null) {
            nbt.putString("storedItem", Registries.ITEM.getId(storedItem).toString());
        }
        else if (!nbt.getString("storedItem").isEmpty()) {
            nbt.remove("storedItem");
        }
    }
    
    @Override
    public void readNbt(NbtCompound nbt) {
        try {
            var result = nbt.getString("storedItem");
            
            if (!result.isEmpty()) {
                setStack(Registries.ITEM.get(new Identifier(result)));
            }
            
            if (result.isEmpty() && getItem() != null) {
                removeStack(0);
            }
        }
        catch (Exception e) {
            LoggerFactory.getLogger("learning-to-mod").error("Hmm...", e);
        }
    }
    
    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
    
    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return storedItem;
    }
}
