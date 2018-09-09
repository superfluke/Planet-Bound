package com.crypticmushroom.planetbound.world.gen;

import java.util.Random;

import com.crypticmushroom.planetbound.init.PBBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class PBGenStoneSpire extends WorldGenerator 
{
	private final IBlockState blockstate;
	private final int size;
	
	public PBGenStoneSpire(IBlockState blockstate, int size)
    {
        super(false);
        this.blockstate = blockstate;
        this.size = size;
    }
	
	@Override
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
		if (rand.nextInt(12) != 0) 
			return false;
		
		int height = 21 + rand.nextInt(10);
		
		IBlockState pillarblock = blockstate;
		int blockrand = rand.nextInt(4);
		if(blockrand == 0)
			pillarblock = PBBlocks.ronnian_coarse_dirt.getDefaultState();
		else if(blockrand == 1)
			pillarblock = Blocks.RED_SANDSTONE.getDefaultState();
		
		
		//start down 2 to prevent floating pillars
		for(int y=-2; y<height; y++)
		{
			double cosTwist = Math.cos(Math.toRadians((y+2)*10));
			double sinTwist = Math.sin(Math.toRadians((y+2)*10));
			
			//int taperedSize = (int) (MathHelper.clampedLerp(1, size, (height-y)/(height-10))+0.5);
			
			for(int x=-size; x<=size; x++)
			{
				for(int z=-size; z<=size; z++)
				{
					
					int twistX = (int) ((cosTwist*x - sinTwist*z)+0.5);
					int twistZ = (int) ((sinTwist*x + cosTwist*z)+0.5);
					//if(Math.abs(x)==size || Math.abs(z)==size)
						this.setBlockAndNotifyAdequately(worldIn, position.add(twistX, y, twistZ), pillarblock);

				}
			}
		}
		return true;
    }
	

}
