package com.cwdj.learningtomod.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class CwdjBlockItem<BlockType extends Block> extends BlockItem {

    public CwdjBlockItem(BlockType block) {
        super(block, new FabricItemSettings());
    }
    
    public CwdjBlockItem(BlockType block, Settings settings) {
        super(block, settings);
    }
    
    @Override
    public BlockType getBlock() {
        // due to the below suppression, it is not possible to just return the super.getBlock result with the cast
        @SuppressWarnings("unchecked")
        var block = (BlockType) super.getBlock();
        
        return block;
    }
}
