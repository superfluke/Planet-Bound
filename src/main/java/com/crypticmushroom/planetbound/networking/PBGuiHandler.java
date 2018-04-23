package com.crypticmushroom.planetbound.networking;

import com.crypticmushroom.planetbound.client.gui.GuiInventorsForge;
import com.crypticmushroom.planetbound.client.gui.GuiPlayer;
import com.crypticmushroom.planetbound.client.gui.GuiWorkbench;
import com.crypticmushroom.planetbound.container.ContainerInventorsForge;
import com.crypticmushroom.planetbound.container.ContainerPlayer;
import com.crypticmushroom.planetbound.container.ContainerWorkbench;
import com.crypticmushroom.planetbound.player.PBPlayer;
import com.crypticmushroom.planetbound.tileentity.TileEntityInventorsForge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PBGuiHandler implements IGuiHandler {
    public static final int INVENTORS_FORGE_ID = 20;
    public static final int WORKBENCH_ID = 21;
    public static final int PLAYER_ID = 22;

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == INVENTORS_FORGE_ID) {
            return new ContainerInventorsForge(player.inventory, (TileEntityInventorsForge) world.getTileEntity(new BlockPos(x, y, z)));
        } else if (id == WORKBENCH_ID) {
            return new ContainerWorkbench(player.inventory, world, new BlockPos(x, y, z));
        } else if (id == PLAYER_ID) {
            return new ContainerPlayer(PBPlayer.get(player));
        }

        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == INVENTORS_FORGE_ID) {
            return new GuiInventorsForge(player.inventory, (TileEntityInventorsForge) world.getTileEntity(new BlockPos(x, y, z)));
        } else if (id == WORKBENCH_ID) {
            return new GuiWorkbench(player.inventory, world, new BlockPos(x, y, z));
        } else if (id == PLAYER_ID) {
            return new GuiPlayer(PBPlayer.get(player));
        }

        return null;
    }
}