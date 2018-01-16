package com.crypticmushroom.planetbound.proxy;

import com.crypticmushroom.planetbound.init.ModBlocks;
import com.crypticmushroom.planetbound.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonProxy
{
	public void preInit(FMLPreInitializationEvent event)
	{

	}

	//Thomas is here and ready to play
	public void init(FMLInitializationEvent event)
	{

	}
	
	public void postInit(FMLPostInitializationEvent event)
	{

	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(ModBlocks.kybrite_ore);
		event.getRegistry().register(ModBlocks.kybrite_block);
		event.getRegistry().register(ModBlocks.verdanite_ore);
		event.getRegistry().register(ModBlocks.verdanite_block);
		event.getRegistry().register(ModBlocks.rendium_ore);
		event.getRegistry().register(ModBlocks.rendium_block);
		event.getRegistry().register(ModBlocks.gauntlet_core);
		event.getRegistry().register(ModBlocks.gauntlet_core_frame);
		event.getRegistry().register(ModBlocks.gauntlet_shell);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().register(ModItems.kybrite_ingot);
		event.getRegistry().register(ModItems.verdanite_ingot);
		event.getRegistry().register(ModItems.rendium_chunk);
		event.getRegistry().register(ModItems.rendium_crystal);
		//event.getRegistry().register(ModItems.rift_gauntlet);
		event.getRegistry().register(new ItemBlock(ModBlocks.kybrite_ore).setRegistryName(ModBlocks.kybrite_ore.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.kybrite_block).setRegistryName(ModBlocks.kybrite_block.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.verdanite_ore).setRegistryName(ModBlocks.verdanite_ore.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.verdanite_block).setRegistryName(ModBlocks.verdanite_block.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.rendium_ore).setRegistryName(ModBlocks.rendium_ore.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.rendium_block).setRegistryName(ModBlocks.rendium_block.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.gauntlet_core).setRegistryName(ModBlocks.gauntlet_core.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.gauntlet_core_frame).setRegistryName(ModBlocks.gauntlet_core_frame.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.gauntlet_shell).setRegistryName(ModBlocks.gauntlet_shell.getRegistryName()));
	}
}