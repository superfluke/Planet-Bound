package com.crypticmushroom.planetbound.world;

import com.crypticmushroom.planetbound.init.PBWorld;
import com.crypticmushroom.planetbound.world.gen.WorldProviderPlanet;
import com.crypticmushroom.planetbound.world.planet.Planet;
import com.crypticmushroom.planetbound.world.planet.PlanetRonne;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProviderSingle;

//TODO fill in world information for Ronne once we have a clearer vision of it.
public class WorldProviderRonne extends WorldProviderPlanet {

    @Override
    protected void init() {
        hasSkyLight = true;
        biomeProvider = new BiomeProviderSingle(PBWorld.RED_DESERT);
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

}