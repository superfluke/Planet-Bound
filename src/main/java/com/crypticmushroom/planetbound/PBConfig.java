package com.crypticmushroom.planetbound;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*
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
@Config(modid = PBCore.MOD_ID, name = "PlanetBound")
@Config.LangKey(PBCore.MOD_ID + ".config.title")
public class PBConfig
{
    public static final Dimension dimension = new Dimension();

    public static class Dimension
    {
        @Config.RangeInt(min = 2, max = 256)
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
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(PBCore.MOD_ID))
            {
                ConfigManager.sync(PBCore.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}