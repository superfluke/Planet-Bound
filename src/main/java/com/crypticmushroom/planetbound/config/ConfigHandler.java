package com.crypticmushroom.planetbound.config;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.logger.PBLogDev;
import com.crypticmushroom.planetbound.logger.PBLogger;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Config Handler by Choonster
 * Made from the open source mod TestMod3
 * https://github.com/Choonster-Minecraft-Mods/TestMod3/blob/1.12.2/src/main/java/choonster/testmod3/config/ModConfig.java
 *
 * TestMod3 Copyright Choonster 2015-2017 MIT License
 */

/**
 * PLEASE NOTE: IF YOU ARE GOING TO CHANGE ANY EXISTING
 * CONFIGURATION VARIABLES, YOU MUST DELETE YOUR EXISTING
 * planetbound.cfg FILE IN THE config FOLDER OF YOUR
 * TESTING DIRECTORY (normally named "run")
 */
@Config(modid = PBCore.MOD_ID)
@Config.LangKey(PBCore.MOD_ID + ".config.title")
public class ConfigHandler
{
    public static final Dimension dimension = new Dimension();

    public static class Dimension
    {
        @Config.RequiresMcRestart
        @Config.Comment("Set the Dimension ID for Ronne.")
        public int dimensionIDRonne = 4;
    }

    public static final Other other = new Other();

    public static class Other
    {
        public final Developer developer = new Developer();

        public static class Developer
        {
            @Config.Comment("Developer mode outputs extra content to the console for development purposes.")
            public boolean developerMode = false;
        }
    }

    @Mod.EventBusSubscriber(modid = PBCore.MOD_ID)
    private static class EventHandler
    {
        /**
         * Inject the new values and save to the config file when the config has been changed from the GUI.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(PBCore.MOD_ID)) {
                ConfigManager.sync(PBCore.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }

    /*
     * Depricated Configuration class below
     */
    /*
    protected static final String configKey = PBCore.MOD_ID + ".config.";

    public static Configuration config;

    public static int dimensionIDRonne;
    public static boolean developerMode;

    public static void loadConfig(FMLPreInitializationEvent event)
    {
        //Call config
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());

        //Reload config
        reloadConfig();

        PBLogger.printInfo("Loading configurations...");
    }

    private static void reloadConfig()
    {
        //Dimension
        dimensionIDRonne = config.getInt("Ronne Dimension ID", "dimension", 4, 2, 256,ConfigHandler.ronneDesc);

        //Developer
        developerMode = config.getBoolean("Developer Mode", "developer", false, ConfigHandler.devmodeDesc);

        if (config.hasChanged())
        {
            config.save();
            PBLogger.printInfo("Configurations saved.");
        }
        else
        {
            PBLogger.printInfo("Configurations reloaded.");
        }
    }

    public static void configWarnings()
    {
        // Developer Mode logging
        if(ConfigHandler.developer.developerMode)
        {
            PBLogDev.printWarn("Developer Mode is enabled. Extra logging will be pushed to the console.");
        }
        else
        {
            PBLogDev.printInfo("Developer Mode is disabled. Extra logging will continue at the DEBUG level.");
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(PBCore.MOD_ID))
        {
            reloadConfig();
        }
    }
    */
}