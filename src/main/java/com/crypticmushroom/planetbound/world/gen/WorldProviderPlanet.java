package com.crypticmushroom.planetbound.world.gen;

import com.crypticmushroom.planetbound.world.planet.Planet;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;

public abstract class WorldProviderPlanet extends WorldProvider {
    public abstract Planet getPlanet();

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorPlanet(this.world, this.world.getSeed(), this.getPlanet());
    }

}