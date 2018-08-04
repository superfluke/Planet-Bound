package com.crypticmushroom.planetbound.client;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.networking.PBGuiHandler;
import com.crypticmushroom.planetbound.networking.PBNetworkHandler;
import com.crypticmushroom.planetbound.networking.packet.PBPacketOpenContainer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = PBCore.MOD_ID, value = Side.CLIENT)
public class ClientEventHandler
{
    @SubscribeEvent
    public static void keyPressed(InputEvent.KeyInputEvent event)
    {
        if (PBKeyHandler.isInventoryPressed())
        {
            EntityPlayer player = Minecraft.getMinecraft().player;

            if (player.isCreative())
            {
                Minecraft.getMinecraft().displayGuiScreen(new GuiContainerCreative(player));
            } else
            {
                PBNetworkHandler.sendToServer(new PBPacketOpenContainer(PBGuiHandler.PLAYER_ID));
            }
        }
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event)
    {
        event.getMap().registerSprite(new ResourceLocation(PBCore.MOD_ID + ":items/empty_gauntlet_slot"));
    }
}