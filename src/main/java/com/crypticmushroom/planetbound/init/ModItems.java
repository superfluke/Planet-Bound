package com.crypticmushroom.planetbound.init;

import com.crypticmushroom.planetbound.items.RiftGauntlet;
import com.crypticmushroom.planetbound.items.oreingot.KybriteIngot;
import com.crypticmushroom.planetbound.items.oreingot.RendiumCrystal;
import com.crypticmushroom.planetbound.items.oreingot.VerdaniteIngot;

public class ModItems
{
    public static KybriteIngot kybrite_ingot;
    public static VerdaniteIngot verdanite_ingot;
    public static RendiumCrystal rendium_chunk;
    public static RendiumCrystal rendium_crystal;
    public static RiftGauntlet rift_gauntlet;

    public static enum CrystalType
    {
        CHUNK,
        CRYSTAL
    }

    public static void init()
    {
        kybrite_ingot = new KybriteIngot();
        verdanite_ingot = new VerdaniteIngot();
        rendium_chunk = new RendiumCrystal(CrystalType.CHUNK);
        rendium_crystal = new RendiumCrystal(CrystalType.CRYSTAL);
        rift_gauntlet = new RiftGauntlet();
    }
}
