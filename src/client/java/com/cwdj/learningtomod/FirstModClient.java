package com.cwdj.learningtomod;

import com.cwdj.learningtomod.blockentityrenderers.CounterBlockEntityRenderer;
import com.cwdj.learningtomod.blocks.entities.CounterEntity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

@Environment(EnvType.CLIENT)
public class FirstModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockEntityRendererFactories.register(CounterEntity.registerBlockEntityType(), CounterBlockEntityRenderer::new);
	}
}
