package com.crypticmushroom.planetbound;

import com.crypticmushroom.planetbound.server.config.ConfigHandler;
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
	public static final String VERSION = "1.0-dev";
	
	@Instance(MOD_ID)
	private static PBCore instance;
	
	@SidedProxy(clientSide = "com.crypticmushroom.planetbound.client.ClientProxy", serverSide = "com.crypticmushroom.planetbound.server.ServerProxy")
	public static CommonProxy proxy; //TODO not sure what's best here; static abuse?
                                     //yes actually

    //Logger is in PBLogger class now.
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigHandler.loadConfig(event);
		ConfigHandler.autoDeveloperMode("dev"); //If version contains "dev", enable developer mode.
		ConfigHandler.configWarnings();

		proxy.preInit(event);

        PBLogger.printDevelop("Target acquired...");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);

        PBLogger.printDevelop("Thomas the Dank Engine is here.");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);

        PBLogger.printDevelop("Ready for combat.");
	}
	
	public static PBCore instance()
	{
		return instance;
	}
}