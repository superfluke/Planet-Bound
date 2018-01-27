package com.crypticmushroom.planetbound.networking;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.networking.packet.PBPacketOpenContainer;
import com.crypticmushroom.planetbound.networking.packet.PBPacketSmeltMode;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PBNetworkHandler
{
    private static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(PBCore.MOD_ID);

    private static int packetId;
    
    public static void init()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(PBCore.instance(), new PBGuiHandler());
        
        INSTANCE.registerMessage(PBPacketSmeltMode.class, PBPacketSmeltMode.class, packetId++, Side.SERVER);
        INSTANCE.registerMessage(PBPacketOpenContainer.class, PBPacketOpenContainer.class, packetId++, Side.SERVER);
    }
    
    public static void sendToAll(IMessage message)
    {
        INSTANCE.sendToAll(message);
    }
    
    @SideOnly(Side.CLIENT)
    public static void sendToServer(IMessage message)
    {
        INSTANCE.sendToServer(message);
    }
    
    public static void sendTo(IMessage message, EntityPlayerMP player)
    {
        INSTANCE.sendTo(message, player);
    }
}