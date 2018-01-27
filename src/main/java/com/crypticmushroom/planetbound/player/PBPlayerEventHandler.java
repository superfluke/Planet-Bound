package com.crypticmushroom.planetbound.player;

import com.crypticmushroom.planetbound.PBCore;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PBPlayerEventHandler
{
    private static final ResourceLocation PLAYER_STORAGE = new ResourceLocation(PBCore.MOD_ID, "player_storage");

    @SubscribeEvent
    public void PlayerConstructingEvent(AttachCapabilitiesEvent<Entity> event)
    {
        if((event.getObject() instanceof EntityPlayer))
        {
            EntityPlayer player = (EntityPlayer)event.getObject();
            
            if(PBPlayer.get(player) == null)
            {
                event.addCapability(PLAYER_STORAGE,  new PBPlayer.Factory(new PBPlayer(player)));
            }
        }
    }

    @SubscribeEvent
    public void onPlayerCloned(Clone event)
    {
        PBPlayer original = PBPlayer.get(event.getOriginal());
        PBPlayer clone = PBPlayer.get(event.getEntityPlayer());        

        if(original != null)
        {
            NBTTagCompound compound = new NBTTagCompound();
            
            original.writeToNBT(compound);
            
            if(clone != null)
            {
                clone.readFromNBT(compound);
            }
        }
    }
}