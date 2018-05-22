package com.crypticmushroom.planetbound.blocks.ores;

import net.minecraft.block.BlockOre;

public class KybriteOreRonnian extends BlockOre
{
    public KybriteOreRonnian()
    {
        super();

        setHarvestLevel("pickaxe", 2);
        setHardness(3);
        setResistance(5);
    }
}