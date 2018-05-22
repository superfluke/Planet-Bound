package com.crypticmushroom.planetbound.world;

import com.crypticmushroom.planetbound.init.PBWorld;
import com.crypticmushroom.planetbound.world.biome.BiomeProviderRonne;
import com.crypticmushroom.planetbound.world.gen.WorldProviderPlanet;
import com.crypticmushroom.planetbound.world.planet.Planet;
import com.crypticmushroom.planetbound.world.planet.PlanetRonne;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//TODO fill in world information for Ronne once we have a clearer vision of it.
public class WorldProviderRonne extends WorldProviderPlanet {
	@Override
	protected void init() {
		hasSkyLight = true;
		biomeProvider = new BiomeProviderRonne(world);
	}

	@Override
	public DimensionType getDimensionType() {
		return PBWorld.RONNE;
	}

	@Override
	public Planet getPlanet() {
		return new PlanetRonne();
	}

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}

	// daytime sky color should be a midnight blue
	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getSkyColor(net.minecraft.entity.Entity cameraEntity, float partialTicks) {
		return world.getSkyColorBody(cameraEntity, partialTicks);
	}

	// we may want to change the cloud color later
	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getCloudColor(float partialTicks) {
		return world.getCloudColorBody(partialTicks);
	}

	/**
	 * Return Vec3D with biome specific fog color
	 */
	// fog color also midnight blue but lighter than sky
	// rgb(97, 109, 170)
	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
		float f = MathHelper.cos(p_76562_1_ * ((float) Math.PI * 2F)) * 2.0F + 0.5F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		float red = 0.380392F; // 97 / 255
		float green = 0.427451F; // 109 / 255
		float blue = 0.666667F; // 170 / 255
		red = red * (f * 0.94F + 0.06F);
		green = green * (f * 0.94F + 0.06F);
		blue = blue * (f * 0.91F + 0.09F);
		return new Vec3d(red, green, blue);
	}

}