package com.crypticmushroom.planetbound.handler;

import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.init.PBItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockEvents
{
	@SubscribeEvent
	public void onHarvestDrops(HarvestDropsEvent event)
	{
		if(event.getState().getBlock().equals(PBBlocks.rendium_ore))
		{
			int drops = 3 + (int)(Math.random() * 4);
			
			event.getDrops().add(new ItemStack(PBItems.rendium_chunk, drops));
		}
	}
}