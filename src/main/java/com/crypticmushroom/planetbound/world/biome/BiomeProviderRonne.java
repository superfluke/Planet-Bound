package com.crypticmushroom.planetbound.world.biome;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeProviderSingle;

//TODO make this a more .. uh, 'appropriate' biome provider once we have a clearer vision of Ronne.
public class BiomeProviderRonne extends BiomeProviderSingle
{
    public BiomeProviderRonne()
    {
        super(Biomes.PLAINS);
    }
}