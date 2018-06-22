package com.crypticmushroom.planetbound.networking.packet;

import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.init.PBItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	public IMessage handleServer(PBPacketSpawnRift message, EntityPlayer player)
	{
		if(!player.getCooldownTracker().hasCooldown(PBItems.rift_gauntlet))
		{
			BlockPos pos = message.pos;
			World world = player.world;
			Block block = world.getBlockState(pos).getBlock();
			EnumFacing facing = player.getHorizontalFacing();
			List<BlockPos> portalBlocks = freeBlocks(pos, facing);

			if(!block.equals(Blocks.AIR) && isAllAir(world, portalBlocks))
			{
				IBlockState state = PBBlocks.rift.getDefaultState();
				if(facing == EnumFacing.EAST || facing == EnumFacing.WEST)
				{
					state = state.withRotation(Rotation.CLOCKWISE_90);
				}
				final IBlockState iBlockState = state;
				portalBlocks.forEach(blockPos -> world.setBlockState(blockPos, iBlockState));
				player.getCooldownTracker().setCooldown(PBItems.rift_gauntlet, 200);
			}
		}
		else player.sendStatusMessage(new TextComponentString("you cannot use this yet!").setStyle(new Style().setColor(TextFormatting.RED)), true); //should actually never happen
		return null;
	}

	private List<BlockPos> freeBlocks(BlockPos pos, EnumFacing facing)
	{
        List<BlockPos> positions = new ArrayList<>();
	    EnumFacing offsetFacing;
	    switch (facing) {
            case NORTH:
            case SOUTH:
                offsetFacing = EnumFacing.WEST;
                break;
            case WEST:
            case EAST:
                default: //actually never used
                offsetFacing = EnumFacing.NORTH;
        }
        for(int i = 1; i <= 3; i++) {
            positions.add(pos.up(i));
            positions.add(pos.offset(offsetFacing, i));
        }
		return positions;
	}

	private boolean isAllAir(World world, List<BlockPos> blocks) {
		return blocks.stream().filter(pos -> !world.isAirBlock(pos)).collect(Collectors.toList()).size() == 0;
	}
}