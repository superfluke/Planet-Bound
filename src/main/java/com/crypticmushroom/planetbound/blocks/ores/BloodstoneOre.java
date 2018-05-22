package com.crypticmushroom.planetbound.blocks.ores;

import net.minecraft.block.BlockOre;

public class BloodstoneOre extends BlockOre
{
    public BloodstoneOre()
    {
        super();

        setHarvestLevel("pickaxe", 2);
        setHardness(3);
        setResistance(5);
    }
}