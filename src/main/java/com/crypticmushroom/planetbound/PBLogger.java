package com.crypticmushroom.planetbound;

import com.crypticmushroom.planetbound.server.config.ConfigVariables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PBLogger
{
    public static final Logger log = LogManager.getLogger(PBCore.NAME);

    public static void printDevelop(Object messageDevelop)
    {
        if(ConfigVariables.developerMode)
            log.info("[DEVELOPER DEBUG] " + messageDevelop.toString());
        else
            printDebug(messageDevelop);
    }

    public static void printWorldGen(Object messageExtra)
    {
        log.debug("[WORLDGEN] " + messageExtra.toString());
    }

    // NOTE: Developer mode and debug logging are two very different things.
    public static void printDebug(Object messageDebug)
    {
        log.debug("[DEBUG] " + messageDebug.toString());
    }

    public static void printWarn(Object messageWarn)
    {
        log.warn("[WARNING] " + messageWarn.toString());
    }

    public static void printError(Object messageError)
    {
        log.error("[ERROR] " + messageError.toString());
    }

    public static void printNotice(Object messageNotice)
    {
        log.info("[NOTICE] " + messageNotice.toString());
    }

    public static void printInfo(Object messageInfo)
    {
        log.info(messageInfo.toString());
    }
}
