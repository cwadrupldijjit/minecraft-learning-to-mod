package com.cwdj.learningtomod.blocks;

import org.apache.commons.lang3.NotImplementedException;

import net.minecraft.block.Block;

public abstract class CwdjBlock extends Block {
    public CwdjBlock(Settings settings) {
        super(settings);
    }
    
    protected static CwdjBlock registerBlock() {
        throw new NotImplementedException("A block must implement its own register block method");
    }
}
