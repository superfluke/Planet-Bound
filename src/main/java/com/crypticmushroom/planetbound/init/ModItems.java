package com.crypticmushroom.planetbound.init;

import com.crypticmushroom.planetbound.items.oreingot.KybriteOreIngot;
import com.crypticmushroom.planetbound.items.oreingot.RendiumCrystal;
import com.crypticmushroom.planetbound.items.oreingot.VerdaniteOreIngot;

public class ModItems
{
    public static KybriteOreIngot kybrite_ingot;
    public static VerdaniteOreIngot verdanite_ingot;
    public static RendiumCrystal rendium_chunk;
    public static RendiumCrystal rendium_crystal;

    public static enum CrystalType
    {
        CHUNK,
        CRYSTAL
    }

    public static void init()
    {
        kybrite_ingot = new KybriteOreIngot();
        verdanite_ingot = new VerdaniteOreIngot();
        rendium_chunk = new RendiumCrystal(CrystalType.CHUNK);
        rendium_crystal = new RendiumCrystal(CrystalType.CRYSTAL);
    }
}
