package com.crypticmushroom.planetbound.client;

import com.crypticmushroom.planetbound.CommonProxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
//Server side only stuff in CLIENT package.
public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		
	}
	
	@Override
	public void init(FMLInitializationEvent event)
	{
		
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}