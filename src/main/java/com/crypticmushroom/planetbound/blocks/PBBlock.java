package com.crypticmushroom.planetbound.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class PBBlock extends Block {
	public PBBlock(Material materialIn, MapColor mapColorIn, SoundType soundTypeIn) {
		super(materialIn, mapColorIn);

		this.setSoundType(soundTypeIn);
	}

	public PBBlock(Material materialIn, MapColor mapColorIn) {
		super(materialIn, mapColorIn);
	}

	public PBBlock(Material materialIn) {
		super(materialIn);
	}

	@Override
	public PBBlock setSoundType(SoundType soundTypeIn) {
		return (PBBlock)super.setSoundType(soundTypeIn);
	}
}
