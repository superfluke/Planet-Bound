package com.crypticmushroom.planetbound.blocks;

import java.util.Arrays;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.networking.PBGuiHandler;
import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PBWorkbench extends BlockWorkbench implements PBBlock {

	private final Planet[] planets_found_on;

	public PBWorkbench(Planet... planets) {
		planets_found_on = planets;
		setHardness(2.5F);
		setSoundType(SoundType.WOOD);
	}

	@Override
	public Planet[] getPlanets() {
		return planets_found_on.clone();
	}

	/**
	 * Called when the block is right clicked by a player.
	 */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)
		{
			return true;
		}
		else
		{
			playerIn.openGui(PBCore.instance(), PBGuiHandler.WORKBENCH_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
			playerIn.addStat(StatList.CRAFTING_TABLE_INTERACTION);
			return true;
		}
	}
}
