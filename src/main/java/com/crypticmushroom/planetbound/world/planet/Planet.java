package com.crypticmushroom.planetbound.world.planet;

import org.apache.commons.lang3.Validate;

import com.crypticmushroom.planetbound.client.ColorReloader;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Planet implements IResourceManagerReloadListener
{
	private final float minHeight;

	private final float maxHeight;

	private final int xSize, ySize, zSize;

	private final ColorReloader[] colorReloaders;

	public Planet(float minHeight, float maxHeight, ColorReloader... reloaders)
	{
		this(5, 33, 5, minHeight, maxHeight, reloaders);
	}

	/*
	 * Use this if you know how to use it. I'm leaving this open just in case. ~Kino
	 */
	public Planet(int xSize, int ySize, int zSize, float minHeight, float maxHeight, ColorReloader... reloaders)
	{
		this.xSize = xSize;
		this.ySize = ySize;
		this.zSize = zSize;

		this.minHeight = minHeight;
		this.maxHeight = maxHeight;

		Validate.noNullElements(reloaders);

		this.colorReloaders = reloaders;
	}

	public abstract void generate(BlockPos chunkPos, Biome biome);

	public abstract String getName();

	@Override
	@SideOnly(Side.CLIENT)
	public final void onResourceManagerReload(IResourceManager resourceManager) {
		for(ColorReloader reloader : this.colorReloaders) {
			reloader.reload(resourceManager);
		}
	}

	/*
	 * public abstract IBlockState getTopBlock();
	 * 
	 * public abstract IBlockState getBottomBlock();
	 * 
	 * public abstract IBlockState getFillerBlock();
	 */
	public float getMinHeight()
	{
		return this.minHeight;
	}

	public float getMaxHeight()
	{
		return this.maxHeight;
	}

	public int getXSize()
	{
		return this.xSize;
	}

	public int getYSize()
	{
		return this.ySize;
	}

	public int getZSize()
	{
		return this.zSize;
	}

}