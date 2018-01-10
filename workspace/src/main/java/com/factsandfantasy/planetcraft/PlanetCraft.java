package com.factsandfantasy.planetcraft;

import org.apache.logging.log4j.core.Logger;

import com.factsandfantasy.planetcraft.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = PlanetCraft.MODID, name = PlanetCraft.MODNAME, version = PlanetCraft.MODVERSION, dependencies = "required-after:forge@[14.23.1.2555,)", useMetadata = true)
public class PlanetCraft {
	
	public static final String MODID = "planetcraft";
	public static final String MODNAME = "PlanetCraft";
	public static final String MODVERSION = "0.0.1";
	
	@SidedProxy(clientSide = "com.factsandfantasy.planetcraft.proxy.ClientProxy", serverSide = "com.factsandfantasy.planetcraft.proxy.ServerProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance
	public static PlanetCraft instance;
	
	public static Logger logger;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = (Logger) event.getModLog();
		proxy.preInit(event);
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
	
	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			ModBlocks.register(event.getRegistry());
		}
		
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			ModItems.register(event.getRegistry());
			ModBlocks.registerItemBlocks(event.getRegistry());
		}
		
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			ModItems.registerItemModels();
			ModBlocks.registerItemModels();
		}
	}

}
