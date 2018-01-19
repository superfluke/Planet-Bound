package com.crypticmushroom.planetbound;

import com.crypticmushroom.planetbound.config.ConfigHandler;
import com.crypticmushroom.planetbound.handler.BlockEvents;
import com.crypticmushroom.planetbound.init.InventorsForgeRecipes;
import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.init.PBItems;
import com.crypticmushroom.planetbound.init.PBSmelting;
import com.crypticmushroom.planetbound.init.PBTileEntities;
import com.crypticmushroom.planetbound.networking.PBNetworkHandler;
import com.crypticmushroom.planetbound.world.gen.PBOreGenerator;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = PBCore.MOD_ID,
        name = PBCore.NAME,
        version = PBCore.VERSION,
        acceptedMinecraftVersions = PBCore.MC_VERSIONS,
        updateJSON = PBCore.UPDATE_JSON)
public class PBCore
{
    public static final String MOD_ID = "planetbound";
    public static final String NAME = "Planet Bound";
    public static final String VERSION = "1.0-dev";
    public static final String MC_VERSIONS = "[1.12.2]";
    public static final String UPDATE_JSON = "https://raw.githubusercontent.com/cipherzerox/Planet-Bound/master/update.json";
    
    @Instance(MOD_ID)
    private static PBCore instance;
    
    @SidedProxy(clientSide = "com.crypticmushroom.planetbound.client.ClientProxy", serverSide = "com.crypticmushroom.planetbound.server.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigHandler.loadConfig(event);
        ConfigHandler.autoDeveloperMode("dev"); //If version contains "dev", enable developer mode.
        ConfigHandler.configWarnings();
        
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new BlockEvents());
        
        PBItems.init();
        PBBlocks.init();
        
        proxy.preInit(event);

        PBLogger.printNotice("Target acquired...");
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        PBSmelting.init();
        PBTileEntities.init();
        InventorsForgeRecipes.init();
        
        PBNetworkHandler.init();
        
        GameRegistry.registerWorldGenerator(new PBOreGenerator(), 0);
        
        proxy.init(event);

        PBLogger.printNotice("Thomas the Dank Engine is here.");
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);

        PBLogger.printNotice("Ready for combat.");
    }
    
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(PBBlocks.getBlocks());
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(PBItems.getItems());
    }
    
    public static PBCore instance()
    {
        return instance;
    }
}