package com.crypticmushroom.planetbound.world.gen.layer;

import com.crypticmushroom.planetbound.init.PBBiomes;
import com.crypticmushroom.planetbound.world.biome.BiomeEmberwoodForest;
import com.crypticmushroom.planetbound.world.biome.PBBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerFoothillsRonne extends GenLayer
{
    public GenLayerFoothillsRonne(long l, GenLayer genlayer)
    {
        super(l);
        super.parent = genlayer;
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
        int border = Biome.getIdForBiome(PBBiomes.ronne_foothills);
        for (int dz = 0; dz < depth; dz++)
        {
            for (int dx = 0; dx < width; dx++)
            {
                int left = input[dx + 0 + (dz + 1) * nwidth];
                int right = input[dx + 2 + (dz + 1) * nwidth];
                int down = input[dx + 1 + (dz + 0) * nwidth];
                int up = input[dx + 1 + (dz + 2) * nwidth];
                int mid = input[dx + 1 + (dz + 1) * nwidth];

                if (shouldBorder(mid, left, down, right, up))
                {
                    output[dx + dz * width] = border;
                }
                else {
                    output[dx + dz * width] = -1;
                }
            }
        }

        return output;
    }

    boolean shouldBorder(int mid, int left, int down, int right, int up)
    {
        if (shouldBorder(mid, left))
        {
            return true;
        }
        else if (shouldBorder(mid, right))
        {
            return true;
        }
        else if (shouldBorder(mid, down))
        {
            return true;
        }
        else if (shouldBorder(mid, up))
        {
            return true;
        }
        else {
            return false;
        }
    }

    boolean shouldBorder(int id1, int id2)
    {
        Biome biome1 = Biome.getBiomeForId(id1);
        Biome biome2 = Biome.getBiomeForId(id2);

        //This is the Mountain Border. It must only be around it
        if (biome1 == PBBiomes.ronne_mountains && biome2 != PBBiomes.ronne_mountains)
            return true;
        if (biome1 != PBBiomes.ronne_mountains && biome2 == PBBiomes.ronne_mountains)
            return true;
        return false;
    }
}
