package com.crypticmushroom.planetbound.world.biome;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PBBiomeColorHelper {
	private static final ColorResolver DIMENSIONAL_GRASS_COLOR = new ColorResolver() {
		@Override
		public int getColorAtPos(Biome biome, BlockPos blockPosition) {
			if (biome instanceof PBBiome) {
				PBBiome pbbiome = (PBBiome) biome;
				return pbbiome.getPBGrassColorAtPos(blockPosition);
			}
			return ColorizerGrass.getGrassColor(1.0, 0.0);
		}
	};
	private static final ColorResolver DIMENSIONAL_FOLIAGE_COLOR = new ColorResolver() {
		@Override
		public int getColorAtPos(Biome biome, BlockPos blockPosition) {
			if (biome instanceof PBBiome) {
				PBBiome pbbiome = (PBBiome) biome;
				return pbbiome.getPBFoliageColorAtPos(blockPosition);
			}
			return ColorizerFoliage.getFoliageColor(1.0, 0.0);
		}
	};

	private static int getPBColorAtPos(IBlockAccess blockAccess, BlockPos pos,
			ColorResolver colorResolver) {
		int i = 0;
		int j = 0;
		int k = 0;

		for (BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.getAllInBoxMutable(pos.add(-1, 0, -1),
				pos.add(1, 0, 1))) {
			int l = colorResolver.getColorAtPos(blockAccess.getBiome(blockpos$mutableblockpos),
					blockpos$mutableblockpos);
			i += (l & 16711680) >> 16;
			j += (l & 65280) >> 8;
			k += l & 255;
		}

		return (i / 9 & 255) << 16 | (j / 9 & 255) << 8 | k / 9 & 255;
	}

	public static int getPBGrassColorAtPos(IBlockAccess blockAccess, BlockPos pos) {
		return getPBColorAtPos(blockAccess, pos, DIMENSIONAL_GRASS_COLOR);
	}

	public static int getPBFoliageColorAtPos(IBlockAccess blockAccess, BlockPos pos) {
		return getPBColorAtPos(blockAccess, pos, DIMENSIONAL_FOLIAGE_COLOR);
	}

	@SideOnly(Side.CLIENT)
	interface ColorResolver {
		int getColorAtPos(Biome biome, BlockPos blockPosition);
	}
}
