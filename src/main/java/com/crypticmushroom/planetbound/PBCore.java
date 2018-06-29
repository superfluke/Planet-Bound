package com.crypticmushroom.planetbound;

import com.crypticmushroom.planetbound.crafting.InventorsForgeManager;
import com.crypticmushroom.planetbound.init.PBBiomes;
import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.init.PBItems;
import com.crypticmushroom.planetbound.init.PBPlanets;
import com.crypticmushroom.planetbound.init.PBSmelting;
import com.crypticmushroom.planetbound.init.PBTileEntities;
import com.crypticmushroom.planetbound.init.PBWorld;
import com.crypticmushroom.planetbound.logger.PBLogDev;
import com.crypticmushroom.planetbound.logger.PBLogger;
import com.crypticmushroom.planetbound.networking.PBNetworkHandler;
import com.crypticmushroom.planetbound.player.PBPlayer;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = PBCore.MOD_ID,
        name = PBCore.NAME,
        version = PBCore.VERSION,
        acceptedMinecraftVersions = PBCore.MC_VERSIONS,
        updateJSON = PBCore.UPDATE_JSON,
        dependencies = "required:forge@[14.23.4.2705,);"
)
public class PBCore
{
    public static final String MOD_ID = "planetbound";
    public static final String NAME = "Planet Bound";
    public static final String VERSION = "1.0-dev";
    public static final String MC_VERSIONS = "[1.12.2]";
    public static final String UPDATE_JSON = "https://raw.githubusercontent.com/cipherzerox/Planet-Bound/master/update.json";
    public static final String CLIENT_PROXY = "com.crypticmushroom.planetbound.client.ClientProxy";
    public static final String COMMON_PROXY = "com.crypticmushroom.planetbound.CommonProxy";

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;
    @Instance(MOD_ID)
    private static PBCore instance;

    public static PBCore instance()
    {
        return instance;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        PBLogger.printInfo("Preparing to take over the Universe...");
        PBLogDev.printInfo("Running Pre-Initialization...");

        PBLogDev.printInfo("Initializing planets...");
        PBPlanets.init();

        PBLogDev.printInfo("Preparing items for registry...");
        PBItems.init();

        PBLogDev.printInfo("Preparing blocks for registry...");
        PBBlocks.init();

        PBLogDev.printInfo("Initializing biomes...");
        PBBiomes.init();

        PBLogDev.printInfo("Initializing player...");
        PBPlayer.init();

        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        PBLogDev.printInfo("Running Initialization...");

        PBLogDev.printInfo("Registering tile entities...");
        PBTileEntities.init();

        PBLogDev.printInfo("Registering recipes..."); // Order is important here
        PBSmelting.init();
        InventorsForgeManager.init();

        PBNetworkHandler.init();

        PBWorld.init();

        PBBlocks.setupColors();

        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        PBLogDev.printInfo("Running Post-Initialization...");

        proxy.postInit(event);
    }
}