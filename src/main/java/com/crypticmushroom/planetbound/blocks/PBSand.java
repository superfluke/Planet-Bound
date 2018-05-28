package com.crypticmushroom.planetbound.blocks;

import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class PBSand extends BlockFalling implements PBBlock {
	private Planet[] planets_found_on;
	private MapColor mapColor;
	public PBSand(MapColor color, Planet... planets) {
		planets_found_on = planets;
		mapColor = color;
		this.setSoundType(SoundType.SAND);
		this.setHardness(0.5f);
	}

	@Override
	public Planet[] getPlanets() {
		return planets_found_on.clone();
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return mapColor;
	}
}
