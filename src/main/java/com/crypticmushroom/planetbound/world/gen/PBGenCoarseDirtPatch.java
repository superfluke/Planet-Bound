package com.crypticmushroom.planetbound.world.gen;

import com.crypticmushroom.planetbound.init.PBBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class PBGenCoarseDirtPatch extends WorldGenerator
{
    private IBlockState blockState;
    private int numberOfBlocks;

    public PBGenCoarseDirtPatch(int i)
    {
        this(PBBlocks.ronnian_coarse_dirt, i);
    }

    public PBGenCoarseDirtPatch(Block block, int i)
    {
        blockState = block.getDefaultState();
        numberOfBlocks = i;
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        int range = random.nextInt(numberOfBlocks - 2) + 2;
        int yRange = 1;

        for (int dx = pos.getX() - range; dx <= pos.getX() + range; dx++)
        {
            for (int dz = pos.getZ() - range; dz <= pos.getZ() + range; dz++)
            {
                int l1 = dx - pos.getX();
                int i2 = dz - pos.getZ();

                if (l1 * l1 + i2 * i2 > range * range)
                {
                    continue;
                }

                for (int dy = pos.getY() - yRange; dy <= pos.getY() + yRange; dy++)
                {
                    BlockPos dPos = new BlockPos(dx, dy, dz);
                    Block blockThere = world.getBlockState(dPos).getBlock();
                    if (blockThere == PBBlocks.ronnian_stone && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
                    {
                        world.setBlockState(dPos, blockState, 2);
                    }
                }
            }
        }

        return true;
    }
}
