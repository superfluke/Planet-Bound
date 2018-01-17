package com.crypticmushroom.planetbound.blocks.ores;

import java.util.Random;

import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class RendiumOre extends BlockOre
{
	public RendiumOre()
	{
		super();
		
		setHarvestLevel("pickaxe", 3);
		setHardness(3);
		setResistance(5);
	}
	
	@Override
    public Item getItemDropped(IBlockState state, Random random, int fortune)
    {
        return null;
    }
}