package com.crypticmushroom.planetbound.world.biome;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public abstract class PBBiome extends Biome {
	/*
	 * ALL our custom biomes should extend this to some degree
	 */
	public PBBiome(BiomeProperties properties) {
		super(properties);
	}

	public abstract int getPBGrassColorAtPos(BlockPos pos);

	public abstract int getPBFoliageColorAtPos(BlockPos pos);

}
