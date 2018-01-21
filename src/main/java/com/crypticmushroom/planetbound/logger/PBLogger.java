package com.crypticmushroom.planetbound.logger;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.config.ConfigVariables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//TODO make a method to print to the player in-game
public class PBLogger
{
    public static final Logger log = LogManager.getLogger(PBCore.NAME);

    public static void printInfo(Object messageInfo)
    {
        log.info(messageInfo.toString());
    }

    public static void printWarn(Object messageWarn)
    {
        log.warn(messageWarn.toString());
    }

    public static void printError(Object messageError)
    {
        log.error(messageError.toString());
    }

    //Developer mode logging and debug logging are two very different things.
    //I moved Developer logging methods into PBLogDev
    public static void printDebug(Object messageDebug)
    {
        log.debug(messageDebug.toString());
    }

    /*
    public static void printlol(String name, Object messageLol)
    {
        Logger lol = LogManager.getLogger(name);

        lol.info(messageLol.toString());
    }
    */
}