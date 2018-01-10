package com.factsandfantasy.planetcraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

	public static ItemBase ingotVerdanite = new ItemBase("ingot_verdanite").setCreativeTab(CreativeTabs.MATERIALS);
	public static ItemBase ingotKybrite = new ItemBase("ingot_kybrite").setCreativeTab(CreativeTabs.MATERIALS); 
	public static ItemBase rawchunkRendium = new ItemBase("raw_chunk_rendium").setCreativeTab(CreativeTabs.MATERIALS);
	public static ItemBase gauntletRift = new ItemBase("gauntlet_rift").setCreativeTab(CreativeTabs.TOOLS);
	
	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(
				ingotVerdanite,
				ingotKybrite,
				rawchunkRendium,
				gauntletRift
			);
	}
	
	public static void registerModels() {
		ingotVerdanite.registerItemModel();
		ingotKybrite.registerItemModel();
		rawchunkRendium.registerItemModel();
		gauntletRift.registerItemModel();
	}

	public static void registerItemModels() {
		ingotVerdanite.registerItemModel();
		ingotKybrite.registerItemModel();
		rawchunkRendium.registerItemModel();
	}

}
