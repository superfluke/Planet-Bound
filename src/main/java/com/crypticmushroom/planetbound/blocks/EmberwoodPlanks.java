package com.crypticmushroom.planetbound.blocks;

import com.crypticmushroom.planetbound.init.PBPlanets;
import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class EmberwoodPlanks extends Block implements PBBlock
{
	public EmberwoodPlanks()
	{
		super(Material.WOOD);
		this.setSoundType(SoundType.WOOD);
	}

	@Override
	public Planet[] getPlanets() {
		return new Planet[] { PBPlanets.RONNE };
	}
}
