package com.crypticmushroom.planetbound.blocks;

import com.crypticmushroom.planetbound.world.planet.Planet;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class PBStone extends Block implements PBBlock
{
    private Planet[] planets_found_on;
    private MapColor mapColor;

    public PBStone(MapColor color, Planet... planets)
    {
        super(Material.ROCK);
        planets_found_on = planets;
        mapColor = color;
        this.setHardness(2.0f);
        this.setResistance(5.0F);
    }

    @Override
    public Planet[] getPlanets()
    {
        return planets_found_on.clone();
    }

    @Override
    @Deprecated
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return mapColor;
    }
}
