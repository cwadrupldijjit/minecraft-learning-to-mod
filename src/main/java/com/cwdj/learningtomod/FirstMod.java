package com.cwdj.learningtomod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemGroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cwdj.learningtomod.groups.PlateupApplianceItemGroup;
import com.cwdj.learningtomod.groups.PlateupFoodItemGroup;

public class FirstMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("learning-to-mod");
	public static ItemGroup PLATEUP_FOOD_GROUP;
	public static ItemGroup PLATEUP_APPLIANCE_GROUP;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		PLATEUP_FOOD_GROUP = PlateupFoodItemGroup.registerItemGroup();
		PLATEUP_APPLIANCE_GROUP = PlateupApplianceItemGroup.registerItemGroup();
		LOGGER.info("Hello Fabric world!");
	}
}
