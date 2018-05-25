package com.crypticmushroom.planetbound.world.gen;

import com.crypticmushroom.planetbound.blocks.PBTallgrass;
import com.crypticmushroom.planetbound.init.PBBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

//This only works with Enums, so the class will need to be an Enum
public class PBRonnianTallgrassGen extends WorldGenerator
{
    private final IBlockState tallGrassState;

    public PBRonnianTallgrassGen(PBTallgrass.TallgrassVariant var1)
    {
        this.tallGrassState = PBBlocks.ronnian_tallgrass.getDefaultState().withProperty(PBTallgrass.VARIANT, var1);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (IBlockState iblockstate = worldIn.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, position)) && position.getY() > 0; iblockstate = worldIn.getBlockState(position))
        {
            position = position.down();
        }

        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && PBBlocks.ronnian_tallgrass.canPlaceBlockAt(worldIn, blockpos))
            {
                worldIn.setBlockState(blockpos, this.tallGrassState, 2);
            }
        }
        return true;
    }
}
