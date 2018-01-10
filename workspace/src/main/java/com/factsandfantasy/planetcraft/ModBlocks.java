package com.factsandfantasy.planetcraft;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {
	
	public static BlockOre oreVerdanite = new BlockOre("ore_verdanite").setCreativeTab(CreativeTabs.MATERIALS);
	public static BlockOre oreKybrite = new BlockOre("ore_kybrite").setCreativeTab(CreativeTabs.MATERIALS);
	public static BlockOre oreRendium = new BlockOre("ore_rendium").setCreativeTab(CreativeTabs.MATERIALS);
	
	public static void register(IForgeRegistry<Block> registry) {
		registry.registerAll(
				oreVerdanite,
				oreKybrite,
				oreRendium
		);
		
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		registry.registerAll(
				oreVerdanite.createItemBlock(),
				oreKybrite.createItemBlock(),
				oreRendium.createItemBlock()
			);
		
	}
	
	public static void registerItemModels() {
		oreVerdanite.registerItemModel(Item.getItemFromBlock(oreVerdanite));
		oreKybrite.registerItemModel(Item.getItemFromBlock(oreKybrite));
		oreRendium.registerItemModel(Item.getItemFromBlock(oreRendium));
		
	}
	
}
