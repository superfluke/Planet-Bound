package com.crypticmushroom.planetbound.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * So since minecraft is a jerk and won't open a workbench gui for us unless there's actually a workbench at BlockPos,
 * i'll have to create my own.
 */
public class ContainerWorkbench extends net.minecraft.inventory.ContainerWorkbench {
    private BlockPos pos;

    public ContainerWorkbench(InventoryPlayer inventory, World world, BlockPos pos) {
        super(inventory, world, pos);

        this.pos = pos;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return player.getDistanceSq((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D) <= 64.0D;
    }
}