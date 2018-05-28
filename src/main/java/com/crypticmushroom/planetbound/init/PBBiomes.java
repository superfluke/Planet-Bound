package com.crypticmushroom.planetbound.init;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.world.biome.BiomeEmberwoodForest;
import com.crypticmushroom.planetbound.world.biome.BiomeRedDesert;
import com.crypticmushroom.planetbound.world.biome.BiomeRonneMountains;
import com.crypticmushroom.planetbound.world.biome.PBBiome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

//If anyone can find a way to merge this and PGBiomeRegister without crashing, show me
// - Androsa

@SuppressWarnings("all")
@ObjectHolder(PBCore.MOD_ID)
public class PBBiomes
{
	public static PBBiome redDesert;
	public static Biome emberwoodForest;
    public static Biome ronneMountains;

	public static void init() {
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
                .setHeightVariation(1.3F)
                .setBaseHeight(1.05F));
	}
}
