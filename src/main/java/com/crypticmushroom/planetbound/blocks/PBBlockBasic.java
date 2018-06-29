package com.crypticmushroom.planetbound.blocks;

import java.util.Arrays;

import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class PBBlockBasic extends Block implements PBBlock
{
    private final Planet[] planets_found_on;

    public PBBlockBasic(Material blockMaterialIn, MapColor blockMapColorIn, Planet... planets)
    {
        super(blockMaterialIn, blockMapColorIn);
        planets_found_on = planets;
    }

    public PBBlockBasic(Material materialIn, Planet... planets)
    {
        super(materialIn);
        planets_found_on = planets;
    }

    @Override
    public PBBlockBasic setSoundType(SoundType soundTypeIn)
    {
        super.setSoundType(soundTypeIn);
        return this;
    }

    @Override
    public Planet[] getPlanets()
    {
        return planets_found_on.clone();
    }
}
