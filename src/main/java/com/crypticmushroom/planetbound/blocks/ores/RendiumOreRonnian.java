package com.crypticmushroom.planetbound.blocks.ores;

import net.minecraft.block.BlockOre;

public class RendiumOreRonnian extends BlockOre
{
    public RendiumOreRonnian()
    {
        super();

        setHarvestLevel("pickaxe", 2);
        setHardness(3);
        setResistance(5);
    }
}