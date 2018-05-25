package com.crypticmushroom.planetbound.blocks;

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
public class PBDirt extends Block {
	public PBDirt() {
		this(Material.GROUND);
	}

	public PBDirt(SoundType soundTypeIn) {
		this(Material.GROUND, Material.GROUND.getMaterialMapColor(), soundTypeIn);
	}

	public PBDirt(MapColor mapColorIn) {
		this(Material.GROUND, mapColorIn);
	}

	public PBDirt(Material materialIn) {
		this(materialIn, materialIn.getMaterialMapColor());
	}

	public PBDirt(Material materialIn, MapColor mapColorIn) {
		this(materialIn, mapColorIn, SoundType.GROUND);
	}

	public PBDirt(Material materialIn, MapColor mapColorIn, SoundType soundTypeIn) {
		super(materialIn, mapColorIn);

		this.setSoundType(soundTypeIn);
		this.setHardness(0.9F);
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
