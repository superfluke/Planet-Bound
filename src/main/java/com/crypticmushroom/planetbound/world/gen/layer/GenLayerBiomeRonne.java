package com.crypticmushroom.planetbound.world.gen.layer;

import com.crypticmushroom.planetbound.init.PBBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomeRonne extends GenLayer
{
    protected Biome[] allowedBiomes = {
            PBBiomes.redDesert,
            PBBiomes.emberwoodForest
    };

    public GenLayerBiomeRonne(long seed, GenLayer genlayer)
    {
        super(seed);
        parent = genlayer;
    }

    public GenLayerBiomeRonne(long seed)
    {
        super(seed);
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth)
    {
        int[] dest = IntCache.getIntCache(width * depth);

        for(int dz = 0; dz < depth; dz++)
            for(int dx = 0; dx < width; dx++)
            {
                initChunkSeed(dx + x, dz + z);
                dest[dx + dz * width] = Biome.getIdForBiome(allowedBiomes[nextInt(allowedBiomes.length)]);
            }

        return dest;
    }
}
