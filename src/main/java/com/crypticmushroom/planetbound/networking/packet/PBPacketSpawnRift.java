package com.crypticmushroom.planetbound.networking.packet;

import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.player.PBPlayer;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
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
            
            if(!block.equals(Blocks.AIR) && world.getBlockState(up).getBlock().equals(Blocks.AIR))
            {
                world.setBlockState(up, PBBlocks.rift.getDefaultState());
                world.setBlockState(up.west(), PBBlocks.rift.getDefaultState());
                world.setBlockState(up.up(), PBBlocks.rift.getDefaultState());
                world.setBlockState(up.up().west(), PBBlocks.rift.getDefaultState());
                world.setBlockState(up.up().up(), PBBlocks.rift.getDefaultState());
                world.setBlockState(up.up().up().west(), PBBlocks.rift.getDefaultState());
                
                pbPlayer.setGauntletUseCooldown(200);
            }
        }
        else
        {
            //player.sendMessage(new TextComponentString(String.format("Please wait (%s)", cooldown)));
        }
    }
}