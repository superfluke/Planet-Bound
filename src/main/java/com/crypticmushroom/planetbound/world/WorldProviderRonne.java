package com.crypticmushroom.planetbound.world;

import com.crypticmushroom.planetbound.init.PBPlanets;
import com.crypticmushroom.planetbound.init.PBWorld;
import com.crypticmushroom.planetbound.world.biome.BiomeProviderRonne;
import com.crypticmushroom.planetbound.world.gen.WorldProviderPlanet;
import com.crypticmushroom.planetbound.world.planet.Planet;

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
		return PBPlanets.RONNE;
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

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getCloudColor(float partialTicks) {
		float f = world.getCelestialAngle(partialTicks);
		float f1 = MathHelper.cos(f * ((float) Math.PI * 2F)) * 2.0F + 0.5F;
		f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
		float f2 = 0.4F;
		float f3 = 0.4F;
		float f4 = 0.4F;
		float f5 = world.getRainStrength(partialTicks);

		if (f5 > 0.0F) {
			float f6 = (f2 * 0.3F + f3 * 0.59F + f4 * 0.11F) * 0.6F;
			float f7 = 1.0F - f5 * 0.95F;
			f2 = f2 * f7 + f6 * (1.0F - f7);
			f3 = f3 * f7 + f6 * (1.0F - f7);
			f4 = f4 * f7 + f6 * (1.0F - f7);
		}

		f2 = f2 * (f1 * 0.9F + 0.1F);
		f3 = f3 * (f1 * 0.9F + 0.1F);
		f4 = f4 * (f1 * 0.85F + 0.15F);
		float f9 = world.getThunderStrength(partialTicks);

		if (f9 > 0.0F) {
			float f10 = (f2 * 0.3F + f3 * 0.59F + f4 * 0.11F) * 0.2F;
			float f8 = 1.0F - f9 * 0.95F;
			f2 = f2 * f8 + f10 * (1.0F - f8);
			f3 = f3 * f8 + f10 * (1.0F - f8);
			f4 = f4 * f8 + f10 * (1.0F - f8);
		}

		return new Vec3d(f2, f3, f4);
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