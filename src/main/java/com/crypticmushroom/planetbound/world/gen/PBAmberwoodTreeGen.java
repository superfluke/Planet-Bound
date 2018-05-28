package com.crypticmushroom.planetbound.world.gen;

import com.crypticmushroom.planetbound.init.PBBlocks;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;

//TODO: make custom Amberwood tree class
public class PBAmberwoodTreeGen extends WorldGenBigTree {

	public PBAmberwoodTreeGen(boolean notify) {
		super(notify);
	}
	
	@Override
	protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state) {
		if(state.getBlock() instanceof BlockLeaves) {
			state = PBBlocks.amberwood_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, state.getValue(BlockLeaves.CHECK_DECAY));
		}else if(state.getBlock() instanceof BlockLog) {
			state = PBBlocks.amberwood.getDefaultState().withProperty(BlockLog.LOG_AXIS, state.getValue(BlockLog.LOG_AXIS));
		}
		
		super.setBlockAndNotifyAdequately(worldIn, pos, state);
	}

}
