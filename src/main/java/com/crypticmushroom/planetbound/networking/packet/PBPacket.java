package com.crypticmushroom.planetbound.networking.packet;

import com.crypticmushroom.planetbound.PBCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public abstract class PBPacket<Message extends IMessage> implements IMessage, IMessageHandler<Message, IMessage>
{
    @Override
    public IMessage onMessage(Message message, MessageContext context)
    {
        switch (context.side)
        {
            case CLIENT:
                return handleClient(message, PBCore.proxy.thePlayer());
            case SERVER:
                return handleServer(message, context.getServerHandler().player);
        }
        return null;
    }

    public IMessage handleClient(Message message, EntityPlayer player)
    {
        return null;
    }

    public IMessage handleServer(Message message, EntityPlayer player)
    {
        return null;
    }
}