package com.cwdj.learningtomod.misc;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.DisplayEntity.ItemDisplayEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

public final class EntityTypes {
    public static final EntityType<ItemDisplayEntity> DISPLAY_ENTITY_TYPE = FabricEntityTypeBuilder
        .<ItemDisplayEntity>create()
        .disableSummon()
        .build();
}
