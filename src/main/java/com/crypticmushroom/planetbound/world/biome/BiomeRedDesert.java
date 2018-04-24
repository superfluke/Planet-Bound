package com.crypticmushroom.planetbound.world.biome;

import com.crypticmushroom.planetbound.PBCore;
import net.minecraft.world.biome.Biome;

public class BiomeRedDesert extends Biome {
    public BiomeRedDesert() {
        super(new Properties());

        setRegistryName(PBCore.MOD_ID, "red_desert");
    }

    public static class Properties extends BiomeProperties {
        public Properties() {
            super("Red Desert");

            setRainfall(0.0F);
            setRainDisabled();
            setBaseBiome("red_desert");
        }
    }
}