package com.crypticmushroom.planetbound.items;

import com.crypticmushroom.planetbound.init.PBWorld;
import com.crypticmushroom.planetbound.player.PBPlayer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class RiftGauntlet extends Item
{
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        PBPlayer.get(player).transferToDimension(player.dimension == PBWorld.RONNE_ID ? 0 : PBWorld.RONNE_ID);
        
        return super.onItemRightClick(world, player, hand);
    }
}