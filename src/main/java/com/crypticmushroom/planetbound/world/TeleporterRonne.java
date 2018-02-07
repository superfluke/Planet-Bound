package com.crypticmushroom.planetbound.world;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterRonne extends Teleporter
{
    public TeleporterRonne(WorldServer world)
    {
        super(world);
    }
    
    @Override
    public boolean makePortal(Entity entity)
    {
        return true;
    }
}