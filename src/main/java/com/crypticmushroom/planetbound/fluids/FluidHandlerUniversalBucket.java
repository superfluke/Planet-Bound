package com.crypticmushroom.planetbound.fluids;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple;

/**
 * Class based on:
 * https://github.com/Choonster-Minecraft-Mods/TestMod3/blob/099f84747156ce8d01f1276954e047d5ec6ce800/src/main/java/choonster/testmod3/fluids/FluidHandlerUniversalBucket.java
 *
 * This class acts like FluidBucketWrapper, but due to it's nature, we need our own
 */
public class FluidHandlerUniversalBucket extends FluidHandlerItemStackSimple
{
    public FluidHandlerUniversalBucket(ItemStack container, int capacity)
    {
        super(container, capacity);
    }

    @Override
    public boolean canFillFluidType(FluidStack fluid)
    {
        return fluid.getFluid() == FluidRegistry.WATER ||
                fluid.getFluid() == FluidRegistry.LAVA ||
                fluid.getFluid().getName().equals("milk") ||
                FluidRegistry.getBucketFluids().contains(fluid.getFluid());
    }
}
