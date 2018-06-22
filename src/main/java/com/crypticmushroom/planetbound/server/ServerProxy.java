package com.crypticmushroom.planetbound.server;

import com.crypticmushroom.planetbound.CommonProxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
// Server side only stuff in SERVER package.
/*
 * ServerProxy is the same thing as CommonProxy. Can we get rid of this class and use CommonProxy instead,
 * since ClientProxy already extends off of CommonProxy, and a server doesn't need specific features to itself for the mod?
 */
public class ServerProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {

    }

    @Override
    public void init(FMLInitializationEvent event)
    {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}