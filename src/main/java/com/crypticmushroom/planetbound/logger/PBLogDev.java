package com.crypticmushroom.planetbound.logger;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.config.ConfigVariables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// NOTE: Developer mode logging and debug logging are two very different things.
public class PBLogDev {
    public static final Logger logDev = LogManager.getLogger(PBCore.NAME + " Developer Mode");

    public static void printInfo(Object messageDevelop) {
        if (ConfigVariables.developerMode) {
            logDev.info(messageDevelop.toString());
        } else {
            printDebug(messageDevelop);
        }
    }

    public static void printWarn(Object messageDevelop) {
        if (ConfigVariables.developerMode) {
            logDev.warn(messageDevelop.toString());
        } else {
            printDebug(messageDevelop);
        }
    }

    public static void printError(Object messageDevelop) {
        if (ConfigVariables.developerMode) {
            logDev.error(messageDevelop.toString());
        } else {
            printDebug(messageDevelop);
        }
    }

    public static void printDebug(Object messageDevelop) {
        logDev.debug(messageDevelop);
    }
}
