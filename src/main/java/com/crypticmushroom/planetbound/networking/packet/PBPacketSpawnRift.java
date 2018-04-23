package com.crypticmushroom.planetbound.networking.packet;

import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.player.PBPlayer;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class PBPacketSpawnRift extends PBPacket<PBPacketSpawnRift> {
    private BlockPos pos;

    public PBPacketSpawnRift() {

    }

    public PBPacketSpawnRift(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();

        pos = new BlockPos(x, y, z);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
    }

    @Override
    public void handleClient(PBPacketSpawnRift message, EntityPlayer player) {

    }

    @SuppressWarnings("incomplete-switch")
    @Override
    public void handleServer(PBPacketSpawnRift message, EntityPlayer player) {
        PBPlayer pbPlayer = PBPlayer.get(player);

        int cooldown = pbPlayer.getGauntletUseCooldown();

        if (cooldown <= 0) {
            BlockPos pos = message.pos;
            BlockPos up = pos.up();

            World world = player.world;

            Block block = world.getBlockState(pos).getBlock();

            EnumFacing facing = player.getHorizontalFacing();

            if (!block.equals(Blocks.AIR) && canSpawnPortal(world, up, facing)) {
                switch (facing) {
                    case NORTH:
                    case SOUTH:
                        world.setBlockState(up, PBBlocks.rift.getDefaultState());
                        world.setBlockState(up.west(), PBBlocks.rift.getDefaultState());
                        world.setBlockState(up.up(), PBBlocks.rift.getDefaultState());
                        world.setBlockState(up.up().west(), PBBlocks.rift.getDefaultState());
                        world.setBlockState(up.up().up(), PBBlocks.rift.getDefaultState());
                        world.setBlockState(up.up().up().west(), PBBlocks.rift.getDefaultState());
                        break;
                    case EAST:
                    case WEST:
                        world.setBlockState(up, PBBlocks.rift.getDefaultState().withRotation(Rotation.CLOCKWISE_90));
                        world.setBlockState(up.north(), PBBlocks.rift.getDefaultState().withRotation(Rotation.CLOCKWISE_90));
                        world.setBlockState(up.up(), PBBlocks.rift.getDefaultState().withRotation(Rotation.CLOCKWISE_90));
                        world.setBlockState(up.up().north(), PBBlocks.rift.getDefaultState().withRotation(Rotation.CLOCKWISE_90));
                        world.setBlockState(up.up().up(), PBBlocks.rift.getDefaultState().withRotation(Rotation.CLOCKWISE_90));
                        world.setBlockState(up.up().up().north(), PBBlocks.rift.getDefaultState().withRotation(Rotation.CLOCKWISE_90));
                        break;
                }

                pbPlayer.setGauntletUseCooldown(200);
            }
        } else {
            //player.sendMessage(new TextComponentString(String.format("Please wait (%s)", cooldown / 40)));
        }
    }

    @SuppressWarnings("incomplete-switch")
    private List<BlockPos> freeBlocks(BlockPos pos, EnumFacing facing) {
        List<BlockPos> positions = new ArrayList<BlockPos>();

        switch (facing) {
            case NORTH:
                positions.add(pos.up());
                positions.add(pos.up().up());
                positions.add(pos.up().up().up());
                positions.add(pos.up().west());
                positions.add(pos.up().up().west());
                positions.add(pos.up().up().up().west());
                break;
            case EAST:
                positions.add(pos.up());
                positions.add(pos.up().up());
                positions.add(pos.up().up().up());
                positions.add(pos.up().north());
                positions.add(pos.up().up().north());
                positions.add(pos.up().up().up().north());
                break;
            case SOUTH:
                positions.add(pos.up());
                positions.add(pos.up().up());
                positions.add(pos.up().up().up());
                positions.add(pos.up().west());
                positions.add(pos.up().up().west());
                positions.add(pos.up().up().up().west());
                break;
            case WEST:
                positions.add(pos.up());
                positions.add(pos.up().up());
                positions.add(pos.up().up().up());
                positions.add(pos.up().north());
                positions.add(pos.up().up().north());
                positions.add(pos.up().up().up().north());
                break;
        }

        return positions;
    }

    private boolean canSpawnPortal(World world, BlockPos pos, EnumFacing facing) {
        for (BlockPos position : freeBlocks(pos, facing)) {
            if (!world.getBlockState(position).getBlock().equals(Blocks.AIR)) {
                return false;
            }
        }

        return true;
    }
}