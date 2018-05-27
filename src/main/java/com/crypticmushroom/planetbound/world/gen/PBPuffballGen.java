package com.crypticmushroom.planetbound.world.gen;

import java.util.List;
import java.util.Random;

import com.crypticmushroom.planetbound.blocks.Puffball;
import com.google.common.collect.Lists;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class PBPuffballGen extends WorldGenerator {
	private final Puffball block;
	private final int startRadius;
	private final IBlockState ALL_SIDES, NO_SIDES;

	public PBPuffballGen(Puffball blockIn, int startRadiusIn) {
		super(false);
		this.block = blockIn;
		this.startRadius = startRadiusIn;
		ALL_SIDES = block.getDefaultState().withProperty(Puffball.VARIANT, Puffball.EnumType.ALL_OUTSIDE);
		NO_SIDES = block.getDefaultState().withProperty(Puffball.VARIANT, Puffball.EnumType.ALL_INSIDE);
	}

	private boolean inEllipsoid(int radiusA, int radiusB, int radiusC, int x, int y, int z) {
		double x_A = (double)x / radiusA;
		double y_B = (double)y / radiusB;
		double z_C = (double)z / radiusC;

		x_A *= x_A;
		y_B *= y_B;
		z_C *= z_C;

		return x_A + y_B + z_C < 1;
	}

	private void generateEllipsoid(World worldIn, int radiusA, int radiusB, int radiusC, BlockPos position, List<BlockPos> blocks) {
		BlockPos lowerBound = position.add(-radiusA, -radiusB, -radiusC);
		BlockPos upperBound = position.add(radiusA, radiusB, radiusC);

		for(BlockPos pos : BlockPos.getAllInBox(lowerBound, upperBound)) {
			//BlockPos pos2 = pos.add(-position.getX(), -position.getY(), -position.getZ());

			if(inEllipsoid(radiusA, radiusB, radiusC, pos.getX() - position.getX(), pos.getY() - position.getY(), pos.getZ() - position.getZ())) {
				if(worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos)) {
					worldIn.setBlockState(pos, NO_SIDES, 4);
					blocks.add(pos);
				}
			}
		}
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {

		if(rand.nextInt(6) > 0 ) return false;

		//if(!canGenerateOn(worldIn.getBlockState(position).getBlock()))
		//	return false;

		List<BlockPos> blocks = Lists.newArrayList();

		final int radiusStart = this.startRadius + rand.nextInt(5) - 2;

		if(radiusStart <= 1) return false;

		int radiusA = radiusStart + rand.nextInt(2);
		int radiusB = radiusStart;
		int radiusC = radiusStart + rand.nextInt(2);

		int raise = radiusB/2;

		position = position.up(raise);

		generateEllipsoid(worldIn, radiusA, radiusB, radiusC, position, blocks);

		if(rand.nextInt(3) == 0 /* 33% chance */) {
			int count = 1 + rand.nextInt(2);
			for(int i = 0; i < count; i++) {
				int addX, addZ;
				switch(rand.nextInt(4)) {
				case 0:
					addX = radiusA;
					addZ = rand.nextInt(2 * radiusC) - radiusC;
					break;
				case 1:
					addX = -radiusA;
					addZ = rand.nextInt(2 * radiusC) - radiusC;
					break;
				case 2:
					addZ = radiusC;
					addX = rand.nextInt(2 * radiusA) - radiusA;
					break;
				case 3:
					addZ = -radiusC;
					addX = rand.nextInt(2 * radiusA) - radiusA;
				}

				int radius_a = radiusStart / 4;
				int radius_b = radiusStart / 4 - rand.nextInt(2);
				int radius_c = radiusStart / 4;

				generateEllipsoid(worldIn, radius_a, radius_b, radius_c, position.up(radius_b/2 - raise), blocks);
			}
		}

		for(BlockPos pos : blocks) {
			updateBlock(worldIn, pos);
		}

		return true;
		/*while (true) {
			label50: {
			if (position.getY() > 3) {
				if (worldIn.isAirBlock(position.down())) {
					break label50;
				}

				Block block = worldIn.getBlockState(position.down()).getBlock();

				if (!canGenerateOn(block)) {
					break label50;
				}
			}

			if (position.getY() <= 3) {
				for(BlockPos pos : blocks) {
					updateBlock(worldIn, pos);
				}
				return false;
			}

			int i1 = this.startRadius;

			for (int i = 0; i1 >= 0 && i < 3; ++i) {
				int j = i1 + rand.nextInt(2);
				int k = i1 + rand.nextInt(2);
				int l = i1 + rand.nextInt(2);
				float f = (j + k + l) * 0.333F + 0.5F;
				f *= 2;
				for (BlockPos blockpos : BlockPos.getAllInBox(position.add(-j, -(k/2), -l), position.add(j, k*3/2, l))) {
					if (blockpos.distanceSq(position) <= f) {
						if(worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos)) {
							worldIn.setBlockState(blockpos, ALL_SIDES, 4);
							blocks.add(blockpos);
						}
					}
				}

				position = position.add(-(i1 + 1) + rand.nextInt(2 + i1 * 2), 0 - rand.nextInt(2),
						-(i1 + 1) + rand.nextInt(2 + i1 * 2));
			}

			for(BlockPos pos : blocks) {
				updateBlock(worldIn, pos);
			}

			return true;
		}
		position = position.down();
		}*/
	}

	private void updateBlock(World worldIn, BlockPos pos) {
		boolean north, south, east, west, up, down;

		north = worldIn.getBlockState(pos.north()).getBlock() != block;
		south = worldIn.getBlockState(pos.south()).getBlock() != block;
		east = worldIn.getBlockState(pos.east()).getBlock() != block;
		west = worldIn.getBlockState(pos.west()).getBlock() != block;
		up = worldIn.getBlockState(pos.up()).getBlock() != block;
		down = worldIn.getBlockState(pos.down()).getBlock() != block;

		IBlockState result = ALL_SIDES;

		if(!north && !south && !east && !west && !down) {
			result = up? result.withProperty(Puffball.VARIANT, Puffball.EnumType.CENTER) : NO_SIDES;
		}else if(north && (west ^ east) && !south) {
			result = result.withProperty(Puffball.VARIANT, west? Puffball.EnumType.NORTH_WEST : Puffball.EnumType.NORTH_EAST);
		}else if(south && (west ^ east) && !north) {
			result = result.withProperty(Puffball.VARIANT, west? Puffball.EnumType.SOUTH_WEST : Puffball.EnumType.SOUTH_EAST);
		}else if(north && !west && !east && !south) {
			result = result.withProperty(Puffball.VARIANT, Puffball.EnumType.NORTH);
		}else if(south && !north && !east && !west) {
			result = result.withProperty(Puffball.VARIANT, Puffball.EnumType.SOUTH);
		}else if(east && !north && !south && !west) {
			result = result.withProperty(Puffball.VARIANT, Puffball.EnumType.EAST);
		}else if(west && !north && !south && !east) {
			result = result.withProperty(Puffball.VARIANT, Puffball.EnumType.WEST);
		}

		worldIn.setBlockState(pos, result.getActualState(worldIn, pos), 4);
	}
}
