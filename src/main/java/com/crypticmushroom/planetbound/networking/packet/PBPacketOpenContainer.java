package com.crypticmushroom.planetbound.networking.packet;

import com.crypticmushroom.planetbound.PBCore;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PBPacketOpenContainer extends PBPacket<PBPacketOpenContainer>
{
    private int id;

    public PBPacketOpenContainer()
    {
    }

    public PBPacketOpenContainer(int id)
    {
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
    }

    @Override
    public IMessage handleServer(PBPacketOpenContainer message, EntityPlayer player)
    {
        BlockPos pos = player.getPosition();
        player.openGui(PBCore.instance(), message.id, player.world, pos.getX(), pos.getY(), pos.getZ());
        return null;
    }
}