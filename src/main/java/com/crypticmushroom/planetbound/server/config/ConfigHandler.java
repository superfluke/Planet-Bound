package com.crypticmushroom.planetbound.server.config;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.PBLogger;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/*
 * Config Handler by Jonathan
 */

public class ConfigHandler
{
    public static void loadConfig(FMLPreInitializationEvent event)
    {
        //Call config
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());

        //Load config
        config.load();

        //Developer
        ConfigVariables.developerMode = config.getBoolean("Developer Mode", "development", false, ConfigDescriptions.DeveloperMode);

        //Save config
        config.save();

        PBLogger.printInfo("Configuration file loaded.");
    }

    public static void autoDeveloperMode(String versionCode)
    {
        if(PBCore.VERSION.contains(versionCode))
        {
            ConfigVariables.developerMode = true;
            PBLogger.printInfo("This is a development version of Planet Bound. Therefore, developer mode has been automatically enabled.");
        }
    }

    public static void configWarnings()
    {
        // Developer Mode logging
        if(ConfigVariables.developerMode)
            PBLogger.printWarn("Developer Mode is enabled. Development logging will occur at the [INFO] level. Generation of rocks and sticks will always occur at [DEBUG] level.");
    }
}
