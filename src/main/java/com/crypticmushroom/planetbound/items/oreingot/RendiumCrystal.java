package com.crypticmushroom.planetbound.items.oreingot;

import com.crypticmushroom.planetbound.init.ModItems.CrystalType;
import net.minecraft.item.Item;

public class RendiumCrystal extends Item
{
    CrystalType type;

    public RendiumCrystal(CrystalType t)
    {
        String name = null;

        switch(t)
        {
            case CHUNK:
                name = "rendium_chunk";
                break;
            case CRYSTAL:
                name = "rendium_crystal";
                break;
        }
        setUnlocalizedName(name);
        setRegistryName(name);
        type = t;
    }
}
