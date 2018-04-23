package com.crypticmushroom.planetbound.world.planet;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public abstract class Planet {
    private float minHeight;

    private float maxHeight;

    private int xSize, ySize, zSize;

    public Planet(float minHeight, float maxHeight) {
        this(5, 33, 5, minHeight, maxHeight);
    }

    /*
     * Use this if you know how to use it.
     * I'm leaving this open just in case.
     * ~Kino
     */
    public Planet(int xSize, int ySize, int zSize, float minHeight, float maxHeight) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;

        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    public abstract void generate(BlockPos chunkPos, Biome biome);

    public abstract IBlockState getTopBlock();

    public abstract IBlockState getBottomBlock();

    public abstract IBlockState getFillerBlock();

    public float getMinHeight() {
        return this.minHeight;
    }

    public float getMaxHeight() {
        return this.maxHeight;
    }

    public int getXSize() {
        return this.xSize;
    }

    public int getYSize() {
        return this.ySize;
    }

    public int getZSize() {
        return this.zSize;
    }

}