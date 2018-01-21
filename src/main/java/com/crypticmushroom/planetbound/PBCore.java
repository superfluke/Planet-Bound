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
        PBLogger.printNotice("Running pre-initialization");
        
        ConfigHandler.loadConfig(event);
        ConfigHandler.autoDeveloperMode("dev"); //If version contains "dev", enable developer mode.
        ConfigHandler.configWarnings();
        
        PBLogger.printNotice("Registering event handlers");
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new BlockEvents());
        
        PBLogger.printNotice("Preparing items for registry");
        PBItems.init();
        
        PBLogger.printNotice("Preparing blocks for registry");
        PBBlocks.init();
        
        proxy.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        PBLogger.printNotice("Running initialization");
        PBLogger.printNotice("Registering tile entities");
        PBTileEntities.init();
        
        PBLogger.printNotice("Registering recipes"); //Order is important here
        PBSmelting.init();
        InventorsForgeRecipes.init();
        
        PBNetworkHandler.init();
        
        GameRegistry.registerWorldGenerator(new PBOreGenerator(), 0);
        
        proxy.init(event);

        PBLogger.printNotice("Motu Patlu?");
        PBLogger.printlol("Motu Patlu", "Present!");
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        PBLogger.printNotice("Running post-initialization");
        
        proxy.postInit(event);

        PBLogger.printNotice("RUSH B!!!");
    }
    
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        PBLogger.printNotice("Registering blocks");
        
        event.getRegistry().registerAll(PBBlocks.getBlocks());
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        PBLogger.printNotice("Registering items");
        
        event.getRegistry().registerAll(PBItems.getItems());
    }
    
    public static PBCore instance()
    {
        return instance;
    }
}