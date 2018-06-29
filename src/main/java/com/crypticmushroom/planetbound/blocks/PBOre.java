package com.crypticmushroom.planetbound.blocks;

import java.util.Arrays;

import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.BlockOre;

public class PBOre extends BlockOre implements PBBlock
{
    private final Planet[] planets_found_on;

    public PBOre(Planet... planets)
    {
        setHardness(3.0f);
        setResistance(5.0f);
        setHarvestLevel("pickaxe", 2);
        planets_found_on = planets;
    }

    @Override
    public Planet[] getPlanets()
    {
        return planets_found_on.clone();
    }

    public PBOre setPickaxeHarvestLevel(int harvestLevel)
    {
        setHarvestLevel("pickaxe", harvestLevel);
        return this;
    }
}
