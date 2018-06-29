package com.crypticmushroom.planetbound.world.gen.layer;

import com.crypticmushroom.planetbound.init.PBBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

//TODO: Will need experimenting
public class GenLayerBiomeBorderRonne extends GenLayer
{

    public GenLayerBiomeBorderRonne(long l, GenLayer genlayer)
    {
        super(l);
        parent = genlayer;
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth)
    {
        int nx = x - 1;
        int nz = z - 1;
        int nwidth = width + 2;
        int ndepth = depth + 2;
        int input[] = parent.getInts(nx, nz, nwidth, ndepth);
        int output[] = IntCache.getIntCache(width * depth);

        int offX = x & 3;
        int offZ = z & 3;

        for (int dz = 0; dz < depth; dz++)
        {
            for (int dx = 0; dx < width; dx++)
            {
                int centerX = ((dx + offX + 1) & 0xFFFFFFFC) - offX;
                int centerZ = ((dz + offZ + 1) & 0xFFFFFFFC) - offZ;

                if (dx <= centerX + 1 && dx >= centerX - 1 && dz <= centerZ + 1 && dz >= centerZ - 1)
                {
                    output[dx + dz * width] = input[centerX + 1 + (centerZ + 1) * nwidth];
                } else
                {
                    output[dx + dz * width] = input[dx + 1 + (dz + 1) * nwidth];
                }
            }
        }

        return output;
    }
}
