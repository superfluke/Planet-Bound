package com.crypticmushroom.planetbound.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterRonne extends Teleporter
{
    public TeleporterRonne(WorldServer world)
    {
        super(world);
    }

    @Override
    public void placeInPortal(Entity entityIn, float rotationYaw)
    {
    	BlockPos pos = this.world.getTopSolidOrLiquidBlock(entityIn.getPosition()).up().east(2);

        entityIn.setLocationAndAngles((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), entityIn.rotationYaw, 0.0F);
        entityIn.motionX = 0.0D;
        entityIn.motionY = 0.0D;
        entityIn.motionZ = 0.0D;
    }

    @Override
    public boolean makePortal(Entity entity)
    {
        return true;
    }
}