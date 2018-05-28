package com.crypticmushroom.planetbound.world;

import java.io.IOException;

import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class Colorizer {
	
	protected ResourceLocation colormapLoc;
	protected int[] buffer;
	
	public Colorizer() {}
	
	public Colorizer(ResourceLocation colormapLoc) {
		this.colormapLoc = colormapLoc;
	}

	public void setBiomeColorizer(int[] buffer) {
		this.buffer = buffer;
	}

	public void setBiomeColormap(IResourceManager resourceManager, ResourceLocation colormap_loc) {
		try {
			setBiomeColorizer(TextureUtil.readImageData(resourceManager, colormap_loc));
		}catch(IOException e) {
		}
	}
	
	public void reloadColormap(IResourceManager resourceManager) {
		setBiomeColormap(resourceManager, colormapLoc);
	}

	public int getFloraColor(double temperature, double humidity) {
		humidity = humidity * temperature;
		int i = (int)((1.0D - temperature) * 255.0D);
		int j = (int)((1.0D - humidity) * 255.0D);
		int k = j << 8 | i;
		return k > buffer.length? -65281 : buffer[k];
	}
}
