package crypticmushroom.planetbound.common.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPBRendiumOre {
	
	 public BlockPBRendiumOre(boolean isOn)
	    {
	        super();

	        if (isOn)
	        {
	            this.setTickRandomly(true);
	        }
	    }
	
	private void setTickRandomly(boolean b) {
			
		}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        this.activate(worldIn, pos);
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    private void activate(World worldIn, BlockPos pos)
    {
        this.spawnParticles(worldIn, pos);

        if (this == Blocks.RENDIUM_ORE)
        {
            worldIn.setBlockState(pos, Blocks.LIT_RENDIUM_ORE.getDefaultState());
        }
    }

    private void spawnParticles(World worldIn, BlockPos pos) {
		
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (this == Blocks.LIT_RENDIUM_ORE)
        {
            worldIn.setBlockState(pos, Blocks.RENDIUM_ORE.getDefaultState());
        }
    }


}
