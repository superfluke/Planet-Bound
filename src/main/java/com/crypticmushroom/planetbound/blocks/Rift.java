package com.crypticmushroom.planetbound.blocks;

import java.util.Random;

import com.crypticmushroom.planetbound.init.PBWorld;
import com.crypticmushroom.planetbound.player.PBPlayer;
import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Rift extends BlockPortal implements PBBlock
{
	public Rift()
	{
		super();

		setHardness(-1.0F);
		setSoundType(SoundType.GLASS);
		setLightLevel(0.75F);
		this.setTickRandomly(true);
	}

	@Override
	public Planet[] getPlanets() {
		return new Planet[0];
	}

	@Override
	public int tickRate(World world) {
		return 50;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		removePortal(world, pos, state);
	}

	private void removePortal(World world, BlockPos pos, IBlockState state) {
		EnumFacing.Axis axis = state.getValue(AXIS);
		switch(axis) {
		case X:
			removePortalsVert(world, pos, axis);
			removePortals(world, pos, -1, 0, axis);
			removePortals(world, pos, 1, 0, axis);
			break;
		case Y:
			break;
		case Z:
			removePortalsVert(world, pos, axis);
			removePortals(world, pos, 0, -1, axis);
			removePortals(world, pos, 0, 1, axis);
			break;
		}
	}

	private void removePortals(World world, BlockPos pos, int dx, int dz, EnumFacing.Axis axis) {
		IBlockState state;
		while((state = world.getBlockState(pos = pos.add(dx, 0, dz))).getBlock() == this && state.getValue(AXIS) == axis) {
			//world.setBlockToAir(pos);
			removePortalsVert(world, pos, axis);
		}
	}

	private void removePortalsVert(World world, BlockPos pos, EnumFacing.Axis axis) {
		BlockPos OGpos = pos;
		IBlockState state;
		do{
			world.setBlockToAir(pos);
			pos = pos.up();
		}while((state = world.getBlockState(pos)).getBlock() == this && state.getValue(AXIS) == axis);
		pos = OGpos;
		while((state = world.getBlockState(pos = pos.down())).getBlock() == this && state.getValue(AXIS) == axis) {
			world.setBlockToAir(pos);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if(entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;

			PBPlayer.get(player).transferToDimension(player.dimension == PBWorld.RONNE_ID ? 0 : PBWorld.RONNE_ID);
		}
	}

	@Override
	public boolean trySpawnPortal(World worldIn, BlockPos pos)
	{
		return true;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{

	}
}