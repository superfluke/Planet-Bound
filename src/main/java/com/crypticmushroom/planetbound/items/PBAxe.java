package com.crypticmushroom.planetbound.items;

import net.minecraft.item.ItemAxe;

public class PBAxe extends ItemAxe
{
    public PBAxe(ToolMaterial material, float damage, float speed)
    {
        super(material, damage, speed);
        setMaxStackSize(1);
    }
}
