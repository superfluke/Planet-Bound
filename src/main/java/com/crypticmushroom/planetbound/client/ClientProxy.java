package com.crypticmushroom.planetbound.client;

import com.crypticmushroom.planetbound.CommonProxy;
import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.init.PBItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
// Client side only stuff in CLIENT package.
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        PBKeyHandler.registerKeyBindings();
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        PBItems.registerModels();
        PBBlocks.registerModels();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {

    }

    @Override
    public EntityPlayer thePlayer()
    {
        return Minecraft.getMinecraft().player;
    }
}