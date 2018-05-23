package com.crypticmushroom.planetbound.client;

import java.io.IOException;

import com.crypticmushroom.planetbound.PBCore;

import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class ColorReloader{
	private final ResourceLocation colormap_loc;
	private final java.util.function.Consumer<int[]> setter;

	/**
	 * @param setter the reference to the static setter method, such as {@code ColorizerRonnianGrass::setGrassBiomeColorizer}
	 * @param colormap_loc the location of the colormap .png file, such as {@code planetbound:textures/colormap/ronne_grass.png}
	 */
	public ColorReloader(java.util.function.Consumer<int[]> setter, ResourceLocation colormap_loc) {
		this.colormap_loc = colormap_loc;
		this.setter = setter;
	}

	/**
	 * @param setter the reference to the static setter method, such as {@code ColorizerRonnianGrass::setGrassBiomeColorizer}
	 * @param colormap_name the name of the colormap .png file, such as {@code ronne_grass.png}
	 */
	public ColorReloader(java.util.function.Consumer<int[]> setter, String colormap_name) {
		this(setter, new ResourceLocation(PBCore.MOD_ID, "textures/colormap/" + colormap_name));
	}

	public void reload(IResourceManager resourceManager) {
		try {
			setter.accept(TextureUtil.readImageData(resourceManager, colormap_loc));
		}catch(IOException e) {

		}
	}
}