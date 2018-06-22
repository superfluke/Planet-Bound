package com.crypticmushroom.planetbound.client;

import com.crypticmushroom.planetbound.CommonProxy;
import com.crypticmushroom.planetbound.init.PBPlanets;
import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.client.FMLClientHandler;
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

		IResourceManager manager0 = FMLClientHandler.instance().getClient().getResourceManager();
		if(manager0 !=null && manager0 instanceof IReloadableResourceManager){
			IReloadableResourceManager manager = (IReloadableResourceManager)manager0;

			for(Planet planet : PBPlanets.allPlanets()) {
				manager.registerReloadListener(planet);
			}
		}

	}

	@Override
	public void init(FMLInitializationEvent event)
	{

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

	@Override
	public void sendMessage(EntityPlayer reciever, String message)
	{
		if (this.thePlayer() == reciever)
		{
			Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(message));
		}
	}
}