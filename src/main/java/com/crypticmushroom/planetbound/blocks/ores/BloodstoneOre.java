package com.crypticmushroom.planetbound.blocks.ores;

import java.util.Random;

import com.crypticmushroom.planetbound.init.PBItems;

import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BloodstoneOre extends BlockOre
{
	public BloodstoneOre()
	{
		super();

		setHarvestLevel("pickaxe", 2);
		setHardness(3);
		setResistance(5);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return PBItems.bloodstone_shard;
	}

	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		Random rand = world instanceof World ? ((World)world).rand : new Random();
		int i = MathHelper.getInt(rand, 0, 3);
		return i;
	}
}