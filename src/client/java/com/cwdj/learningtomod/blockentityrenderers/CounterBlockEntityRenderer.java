package com.cwdj.learningtomod.blockentityrenderers;

import com.cwdj.learningtomod.blocks.entities.CounterEntity;
import net.minecraft.client.MinecraftClient;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class CounterBlockEntityRenderer implements BlockEntityRenderer<CounterEntity> {
    private static final MinecraftClient MC = MinecraftClient.getInstance();
    
    public CounterBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}
    
    @Override
    public void render(CounterEntity blockEntity, float tickData, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (blockEntity.getItem() == null) return;
        
        matrices.push();
        
        matrices.translate(0.5, 1, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
        
        MC.getItemRenderer().renderItem(blockEntity.getStack(), ModelTransformationMode.GROUND, light, overlay, matrices, vertexConsumers, MC.world, 0);
        
        matrices.pop();
    }
}
