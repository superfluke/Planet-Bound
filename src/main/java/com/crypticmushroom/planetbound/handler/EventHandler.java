package com.crypticmushroom.planetbound.handler;

import net.minecraftforge.common.MinecraftForge;

public class EventHandler
{
    public void register()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
