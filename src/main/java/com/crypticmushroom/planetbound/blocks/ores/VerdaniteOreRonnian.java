package com.crypticmushroom.planetbound.blocks.ores;

import net.minecraft.block.BlockOre;

public class VerdaniteOreRonnian extends BlockOre
{
    public VerdaniteOreRonnian()
    {
        super();

        setHarvestLevel("pickaxe", 2);
        setHardness(3);
        setResistance(5);
    }
}