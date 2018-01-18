package com.crypticmushroom.planetbound.blocks.ores;

import net.minecraft.block.BlockOre;

public class VerdaniteOre extends BlockOre
{
    public VerdaniteOre()
    {
        super();
        
        setHarvestLevel("pickaxe", 2);
        setHardness(3);
        setResistance(5);
    }
}