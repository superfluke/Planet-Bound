package com.crypticmushroom.planetbound.world.gen.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerRonne extends GenLayer
{
    public GenLayerRonne(long seed)
    {
        super(seed);
    }

    public static GenLayer[] makeWorld(long seed)
    {

        GenLayer biomes = new GenLayerBiomeRonne(1L);

        biomes = new GenLayerZoom(1000L, biomes);
        biomes = new GenLayerZoom(1001L, biomes);
        biomes = new GenLayerZoom(1002L, biomes);
        biomes = new GenLayerZoom(1003L, biomes);
        biomes = new GenLayerZoom(1004L, biomes);

        biomes = new GenLayerBiomeBorderRonne(700L, biomes);
        GenLayer borderLayer = new GenLayerFoothillsRonne(1L, biomes);
        borderLayer = new GenLayerSmooth(7000L, borderLayer);
        biomes = new GenLayerFoothillsMix(100L, biomes, borderLayer);

        GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);

        biomes.initWorldGenSeed(seed);
        genlayervoronoizoom.initWorldGenSeed(seed);

        return new GenLayer[]{biomes, genlayervoronoizoom};
    }
}
