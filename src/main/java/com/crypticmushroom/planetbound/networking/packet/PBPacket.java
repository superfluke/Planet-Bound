package com.crypticmushroom.planetbound.networking.packet;

import com.crypticmushroom.planetbound.PBCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public abstract class PBPacket<Message extends IMessage> implements IMessage, IMessageHandler<Message, Message>
{
    @Override
    public Message onMessage(Message message, MessageContext context)
    {
        if(context.side == Side.CLIENT)
        {
            handleClient(message, PBCore.proxy.thePlayer());
        }
        else if(context.side == Side.SERVER)
        {
            handleServer(message, context.getServerHandler().player);
        }

        return null;
    }

    public abstract void handleClient(Message message, EntityPlayer player);

    public abstract void handleServer(Message message, EntityPlayer player);
}