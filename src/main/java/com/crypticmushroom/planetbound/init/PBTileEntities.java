package com.crypticmushroom.planetbound.init;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.tileentity.TileEntityInventorsForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PBTileEntities
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityInventorsForge.class, PBCore.MOD_ID + ":inventors_forge");
    }
}