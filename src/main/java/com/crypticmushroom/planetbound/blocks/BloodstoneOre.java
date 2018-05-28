package com.crypticmushroom.planetbound.blocks;

import java.util.Random;

import com.crypticmushroom.planetbound.init.PBItems;
import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BloodstoneOre extends PBOre
{

	public BloodstoneOre(Planet... planets) {
		super(planets);
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