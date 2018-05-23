package com.crypticmushroom.planetbound.init;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.world.biome.BiomeEmberwoodForest;
import com.crypticmushroom.planetbound.world.biome.BiomeRedDesert;

import com.crypticmushroom.planetbound.world.biome.BiomeRonneMountains;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

//If anyone can find a way to merge this and PGBiomeRegister without crashing, show me
// - Androsa

@SuppressWarnings("all")
@ObjectHolder(PBCore.MOD_ID)
public class PBBiomes
{
	@ObjectHolder("red_desert")
	public static final Biome redDesert;
	@ObjectHolder("emberwood_forest")
	public static final Biome emberwoodForest;
	@ObjectHolder("ronne_mountains")
    public static final Biome ronneMountains;

	static
	{
		redDesert = new BiomeRedDesert(new BiomeProperties("Red Desert")
				.setRainDisabled()
				.setRainfall(0)
				.setTemperature(0.7F)
                .setHeightVariation(0.1F)
                .setBaseHeight(0.05F));
		emberwoodForest = new BiomeEmberwoodForest(new BiomeProperties("Emberwood Forest")
				.setRainDisabled()
				.setRainfall(0)
				.setTemperature(0.7F)
                .setHeightVariation(0.1F)
                .setBaseHeight(0.05F));
		ronneMountains = new BiomeRonneMountains(new BiomeProperties("Ronne Mountains")
                .setRainDisabled()
                .setRainfall(0)
                .setTemperature(0.55F)
                .setHeightVariation(0.7F)
                .setBaseHeight(0.1F));
	}
}
