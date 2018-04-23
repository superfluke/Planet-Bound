package com.crypticmushroom.planetbound.world.planet;

import com.crypticmushroom.planetbound.init.PBBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public class PlanetRonne extends Planet {
    public PlanetRonne() {
        super(0.01F, 0.01F);
    }

    @Override
    public void generate(BlockPos chunkPos, Biome biome) {

    }

    @Override
    public IBlockState getTopBlock() {
        return PBBlocks.ronnian_sand.getDefaultState();
    }

    @Override
    public IBlockState getBottomBlock() {
        return PBBlocks.ronnian_sandstone.getDefaultState();
    }

    @Override
    public IBlockState getFillerBlock() {
        return PBBlocks.ronnian_stone.getDefaultState();
    }

}