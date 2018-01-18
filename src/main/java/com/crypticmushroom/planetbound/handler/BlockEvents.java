package com.crypticmushroom.planetbound.handler;

import java.util.concurrent.ThreadLocalRandom;

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
            float max = 5 + event.getFortuneLevel() * 1.5F;
            int drops = ThreadLocalRandom.current().nextInt(3, (int)(max + 1));
            
            event.getDrops().add(new ItemStack(PBItems.rendium_chunk, drops));
        }
    }
}