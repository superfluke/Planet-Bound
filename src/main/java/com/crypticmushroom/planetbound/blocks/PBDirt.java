package com.crypticmushroom.planetbound.blocks;

import java.util.Arrays;

import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

/**
 * Base class for all planet's dirt blocks
 */
public class PBDirt extends Block implements PBBlock {
	private final Planet[] planets_found_on;

	public PBDirt(Planet... planets) {
		this(Material.GROUND, planets);
	}

	public PBDirt(SoundType soundTypeIn, Planet... planets) {
		this(Material.GROUND, Material.GROUND.getMaterialMapColor(), soundTypeIn, planets);
	}

	public PBDirt(MapColor mapColorIn, Planet... planets) {
		this(Material.GROUND, mapColorIn, planets);
	}

	public PBDirt(Material materialIn, Planet... planets) {
		this(materialIn, materialIn.getMaterialMapColor(), planets);
	}

	public PBDirt(Material materialIn, MapColor mapColorIn, Planet... planets) {
		this(materialIn, mapColorIn, SoundType.GROUND, planets);
	}

	public PBDirt(Material materialIn, MapColor mapColorIn, SoundType soundTypeIn, Planet... planets) {
		super(materialIn, mapColorIn);

		this.setSoundType(soundTypeIn);
		this.setHardness(0.9F);
		this.planets_found_on = planets;
	}

	@Override
	public Planet[] getPlanets() {
		return Arrays.copyOf(planets_found_on, planets_found_on.length);
	}

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
			IPlantable plantable) {
		boolean hasWater = world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
				world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
				world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
				world.getBlockState(pos.south()).getMaterial() == Material.WATER;
		return plantable.getPlantType(world, pos.offset(direction)) == EnumPlantType.Plains ||
				plantable.getPlantType(world, pos.offset(direction)) == EnumPlantType.Beach && hasWater;
	}
}
