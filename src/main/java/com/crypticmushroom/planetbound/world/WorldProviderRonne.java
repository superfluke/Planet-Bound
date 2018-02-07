package com.crypticmushroom.planetbound.world;

import com.crypticmushroom.planetbound.init.PBWorld;
import com.crypticmushroom.planetbound.world.gen.ChunkGeneratorRonne;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;

//TODO fill in world information for Ronne once we have a clearer vision of it.
public class WorldProviderRonne extends WorldProvider
{
    @Override
    protected void init()
    {
        hasSkyLight = true;
        biomeProvider = new BiomeProviderSingle(PBWorld.RED_DESERT);
    }
    
    @Override
    public DimensionType getDimensionType()
    {
        return PBWorld.RONNE;
    }
    
    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorRonne(world);
    }
    
    @Override
    public boolean isSurfaceWorld()
    {
        return false;
    }
}