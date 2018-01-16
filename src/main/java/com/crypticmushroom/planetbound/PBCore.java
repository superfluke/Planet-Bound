package com.crypticmushroom.planetbound;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

//Heya, let's keep things tidy in here shall we? Use the proxy classes for your registry shit.
@Mod(modid = PBCore.MOD_ID, name = PBCore.NAME, version = PBCore.VERSION, acceptedMinecraftVersions = "[1.12.2]")
public class PBCore
{
	public static final String MOD_ID = "planetbound";
	public static final String NAME = "Planet Bound";
	public static final String VERSION = "1.0";
	
	@Instance(MOD_ID)
	private static PBCore instance;
	
	@SidedProxy(clientSide = "com.crypticmushroom.planetbound.client.ClientProxy", serverSide = "com.crypticmushroom.planetbound.server.ServerProxy")
	public static CommonProxy proxy; //TODO not sure what's best here; static abuse?
	
	//Logging is nice. Please use logger.
	private final Logger logger = LogManager.getLogger(MOD_ID);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger.info("Not a final pre-init message");
		
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		logger.info("Not a final init message");
		
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		logger.info("Not a final post-init message");
		
		proxy.postInit(event);
	}
	
	public static PBCore instance()
	{
		return instance;
	}
}