package com.crypticmushroom.planetbound.config;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.logger.PBLogDev;
import com.crypticmushroom.planetbound.logger.PBLogger;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/*
 * Config Handler by Jonathan
 */

@Config(modid = PBCore.MOD_ID)
@Mod.EventBusSubscriber(modid = PBCore.MOD_ID)
public class ConfigHandler
{
    private static final String configKey = PBCore.MOD_ID + ".config.";
    private static boolean checkDevMode = getAutoDevMode(PBCore.VERSION);

    public static void loadConfig(FMLPreInitializationEvent event)
    {
        // Call config
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());

        // Load config
        config.load();

        // Developer
        ConfigVariables.developerMode = config.getBoolean("Developer Mode", "development", checkDevMode, ConfigDescriptions.DeveloperMode);

        // Save config
        config.save();

        PBLogger.printInfo("Configuration file loaded.");
    }

    public static boolean getAutoDevMode(String versionCode)
    {
        if(versionCode.contains("dev"))
        {
            return true;
        }
        else
        {
            return false;
        }

        /*
         * if(PBCore.VERSION.contains(versionCode)) { ConfigVariables.developerMode =
         * true; PBLogger.
         * printInfo("This is a development version of Planet Bound. Therefore, developer mode has been automatically enabled."
         * ); }
         */
    }

    public static void configWarnings()
    {
        // Developer Mode logging
        if(ConfigVariables.developerMode)
        {
            if(checkDevMode)
            {
                PBLogDev.printWarn("Developer Mode has automatically been enabled since you are running a development version of Planet Bound. You may turn this off in the configuration file.");
            }
            else
            {
                PBLogDev.printInfo("Developer Mode is enabled. Development logging will occur at the [INFO] level. Generation of rocks and sticks will always occur at [DEBUG] level.");
            }
        }
        else
        {
            if(checkDevMode)
            {
                PBLogger.printWarn("Developer Mode has been manually disabled. You may turn it back on in the configuration settings.");
            }
            else
            {
                PBLogDev.printInfo("Developer Mode logging will continue at the DEBUG level.");
            }
        }
    }

    public static Dimension dimension = new Dimension();

    public static class Dimension
    {
        @Config.LangKey(configKey + "dimension_id_ronne")
        @Config.RequiresMcRestart
        @Config.Comment("Set the Dimension ID for Ronne. Will require a restart for effects to take place.")
        public int dimensionIDRonne = 4;
    }
}