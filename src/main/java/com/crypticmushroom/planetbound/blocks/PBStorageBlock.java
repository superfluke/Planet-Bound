package com.crypticmushroom.planetbound.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class PBStorageBlock extends Block {
	public PBStorageBlock(MapColor mapColorIn) {
		super(Material.ROCK, MapColor.GRAY);

		setHarvestLevel("pickaxe", 2);
		setHardness(5.0f);
		setResistance(10.0f);
		setSoundType(SoundType.METAL);
	}
}
