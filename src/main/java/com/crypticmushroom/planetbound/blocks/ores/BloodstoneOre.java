package com.crypticmushroom.planetbound.blocks.ores;

import net.minecraft.block.BlockOre;

public class HalkirOre extends BlockOre
{
    public HalkirOre()
    {
        super();

        setHarvestLevel("pickaxe", 2);
        setHardness(3);
        setResistance(5);
    }
}