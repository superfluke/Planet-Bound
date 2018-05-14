package com.crypticmushroom.planetbound.world.gen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

import java.util.Random;

//This is used to not generate anything.
//Shuddup, I know what I'm doing
public class PBNoTreeGen extends WorldGenTrees
{
    public PBNoTreeGen()
    {
        super(false);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        return false;
    }
}
