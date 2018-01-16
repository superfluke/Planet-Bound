package com.crypticmushroom.planetbound.items.oreingot;

import com.crypticmushroom.planetbound.init.ModItems.CrystalType;
import net.minecraft.item.Item;

public class RendiumCrystal extends Item
{
    CrystalType type;

    public RendiumCrystal(CrystalType type)
    {
        String name = null;

        switch(type)
        {
            case CHUNK:
                name = "rendium_chunk";
                break;
            case CRYSTAL:
                name = "rendium_crystal";
                break;
        }

    }
}
