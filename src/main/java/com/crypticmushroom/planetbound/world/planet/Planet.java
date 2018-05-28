package com.crypticmushroom.planetbound.world.planet;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.world.Colorizer;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Planet implements IResourceManagerReloadListener {
	private final float minHeight;

	private final float maxHeight;

	private final int xSize, ySize, zSize;

	//private final ColorReloader grassColorReloader, foliageColorReloader;

	public final Colorizer grassColorizer, foliageColorizer;
	
	public Planet(float minHeight, float maxHeight, String grassColormapName, String foliageColormapName) {
		this(5, 33, 5, minHeight, maxHeight, new Colorizer(new ResourceLocation(PBCore.MOD_ID, "textures/colormap/" + grassColormapName)), new Colorizer(new ResourceLocation(PBCore.MOD_ID, "textures/colormap/" + foliageColormapName)));
	}

	/*
	 * Use this if you know how to use it. I'm leaving this open just in case. ~Kino
	 */
	public Planet(int xSize, int ySize, int zSize, float minHeight, float maxHeight, Colorizer grassColorizer, Colorizer foliageColorizer) {
		this.grassColorizer = grassColorizer;
		this.foliageColorizer = foliageColorizer;

		this.xSize = xSize;
		this.ySize = ySize;
		this.zSize = zSize;

		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
	}

	public abstract void generate(BlockPos chunkPos, Biome biome);

	public abstract String getName();
	
	@Override
	public final int hashCode() {
		return getName().hashCode();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public final void onResourceManagerReload(IResourceManager resourceManager) {
		grassColorizer.reloadColormap(resourceManager);
		foliageColorizer.reloadColormap(resourceManager);
	}

	/*
	 * public abstract IBlockState getTopBlock();
	 * 
	 * public abstract IBlockState getBottomBlock();
	 * 
	 * public abstract IBlockState getFillerBlock();
	 */
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