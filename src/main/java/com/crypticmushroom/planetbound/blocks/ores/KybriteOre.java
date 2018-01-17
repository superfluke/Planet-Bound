package com.crypticmushroom.planetbound.blocks.ores;

import net.minecraft.block.BlockOre;

public class KybriteOre extends BlockOre
{
	public KybriteOre()
	{
		super();
		
		setHarvestLevel("pickaxe", 3);
		setHardness(3);
		setResistance(5);
	}
}