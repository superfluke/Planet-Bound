package com.crypticmushroom.planetbound.container;

import com.crypticmushroom.planetbound.inventory.SlotGauntlet;
import com.crypticmushroom.planetbound.player.PBPlayer;

public class ContainerPlayer extends net.minecraft.inventory.ContainerPlayer
{
    public ContainerPlayer(PBPlayer player)
    {
        super(player.getPlayer().inventory, !player.getPlayer().world.isRemote, player.getPlayer());

        addSlotToContainer(new SlotGauntlet(player.getInventoryGauntlet(), 0, 77, 44));
    }
}