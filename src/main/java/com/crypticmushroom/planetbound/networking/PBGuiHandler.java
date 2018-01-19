package com.crypticmushroom.planetbound.networking;

import com.crypticmushroom.planetbound.client.gui.GuiInventorsForge;
import com.crypticmushroom.planetbound.container.ContainerInventorsForge;
import com.crypticmushroom.planetbound.tileentity.TileEntityInventorsForge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PBGuiHandler implements IGuiHandler
{
    public static final int INVENTORS_FORGE_ID = 20; //should be configurable in the future to avoid compatibility issues with other mods
    
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        if(id == INVENTORS_FORGE_ID)
        {
            return new ContainerInventorsForge(player.inventory, (TileEntityInventorsForge)world.getTileEntity(new BlockPos(x, y, z)));
        }
        
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        if(id == INVENTORS_FORGE_ID)
        {
            return new GuiInventorsForge(player.inventory, (TileEntityInventorsForge)world.getTileEntity(new BlockPos(x, y, z)));       
        }
    
        return null;
    }
}