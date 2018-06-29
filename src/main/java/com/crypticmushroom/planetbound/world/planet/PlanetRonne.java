package com.crypticmushroom.planetbound.world.planet;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public class PlanetRonne extends Planet
{
    public PlanetRonne()
    {
        super(0.01F, 0.01F, "ronne_grass.png", "ronne_foliage.png");
    }

    @Override
    public String getName()
    {
        return "Ronne";
    }

    @Override
    public void generate(BlockPos chunkPos, Biome biome)
    {

    }
    /*
     * @Override public IBlockState getTopBlock() { return
     * PBBlocks.ronnian_sand.getDefaultState(); }
     *
     * @Override public IBlockState getBottomBlock() { return
     * PBBlocks.ronnian_sandstone.getDefaultState(); }
     *
     * @Override public IBlockState getFillerBlock() { return
     * PBBlocks.ronnian_stone.getDefaultState(); }
     */
}