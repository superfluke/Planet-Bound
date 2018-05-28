package com.crypticmushroom.planetbound.networking.packet;

import java.util.ArrayList;
import java.util.List;

import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.player.PBPlayer;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PBPacketSpawnRift extends PBPacket<PBPacketSpawnRift>
{
	private BlockPos pos;

	public PBPacketSpawnRift()
	{

	}

	public PBPacketSpawnRift(BlockPos pos)
	{
		this.pos = pos;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		int x = buf.readInt();
		int y = buf.readInt();
		int z = buf.readInt();

		pos = new BlockPos(x, y, z);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
	}

	@Override
	public void handleClient(PBPacketSpawnRift message, EntityPlayer player)
	{

	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void handleServer(PBPacketSpawnRift message, EntityPlayer player)
	{
		PBPlayer pbPlayer = PBPlayer.get(player);

		int cooldown = pbPlayer.getGauntletUseCooldown();

		if(cooldown <= 0)
		{
			BlockPos pos = message.pos;
			BlockPos up = pos.up();

			World world = player.world;

			Block block = world.getBlockState(pos).getBlock();

			EnumFacing facing = player.getHorizontalFacing();

			List<BlockPos> portalBlocks = freeBlocks(pos, facing);

			if(!block.equals(Blocks.AIR) && isAllAir(world, portalBlocks))
			{

				IBlockState state = PBBlocks.rift.getDefaultState();;
				switch(facing){
				case EAST:
				case WEST:
					state = state.withRotation(Rotation.CLOCKWISE_90);
				}

				for(BlockPos position : portalBlocks)
					world.setBlockState(position, state);

				pbPlayer.setGauntletUseCooldown(200);
			}
		}
		else
		{
			// player.sendMessage(new TextComponentString(String.format("Please wait (%s)",
			// cooldown / 40)));
		}
	}

	@SuppressWarnings("incomplete-switch")
	private List<BlockPos> freeBlocks(BlockPos pos, EnumFacing facing)
	{
		List<BlockPos> positions = new ArrayList<>();

		positions.add(pos.up());
		positions.add(pos.up(2));
		positions.add(pos.up(3));

		switch(facing)
		{
		case NORTH:
		case SOUTH:
			for(int i = 0; i < 3; i++)
				positions.add(positions.get(i).west());
			break;
		case EAST:
		case WEST:
			for(int i = 0; i < 3; i++)
				positions.add(positions.get(i).north());
		}

		return positions;
	}

	private boolean isAllAir(World world, List<BlockPos> blocks) {
		for(BlockPos pos : blocks)
			if(!world.isAirBlock(pos))
				return false;
		return true;
	}
}