package com.crypticmushroom.planetbound.logger;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.config.ConfigHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// NOTE: Developer mode logging and debug logging are two very different things.
public class PBLogDev
{
    public static final Logger logDev = LogManager.getLogger(PBCore.NAME + " Developer Mode");

    public static void printInfo(Object messageDevelop)
    {
        if(ConfigHandler.other.developer.developerMode)
        {
            logDev.info(messageDevelop.toString());
        }
        else
        {
            PBLogger.printDebug(messageDevelop);
        }
    }

    public static void printWarn(Object messageDevelop)
    {
        if(ConfigHandler.other.developer.developerMode)
        {
            logDev.warn(messageDevelop.toString());
        }
        else
        {
            PBLogger.printDebug(messageDevelop);
        }
    }

    public static void printError(Object messageDevelop)
    {
        if(ConfigHandler.other.developer.developerMode)
        {
            logDev.error(messageDevelop.toString());
        }
        else
        {
            PBLogger.printDebug(messageDevelop);
        }
    }
}
