package com.crypticmushroom.planetbound.blocks;

import java.util.Arrays;

import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class PBStorageBlock extends Block implements PBBlock
{
    private final Planet[] planets_found_on;

    public PBStorageBlock(MapColor mapColorIn, Planet... associatedPlanets)
    {
        super(Material.ROCK, MapColor.GRAY);

        setHarvestLevel("pickaxe", 2);
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);

        planets_found_on = associatedPlanets;
    }

    @Override
    public Planet[] getPlanets()
    {
        return planets_found_on.clone();
    }
}
