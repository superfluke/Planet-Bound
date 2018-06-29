package com.crypticmushroom.planetbound.world.biome;

import org.apache.commons.lang3.Validate;

import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public abstract class PBBiome extends Biome
{

    protected final Planet planet;

    /*
     * ALL our custom biomes should extend this to some degree
     */
    public PBBiome(BiomeProperties properties, Planet planet)
    {
        super(properties);

        Validate.notNull(planet);
        this.planet = planet;
    }

    public Planet getPlanet()
    {
        return planet;
    }

    public abstract int getPBGrassColorAtPos(BlockPos pos);

    public abstract int getPBFoliageColorAtPos(BlockPos pos);

}
