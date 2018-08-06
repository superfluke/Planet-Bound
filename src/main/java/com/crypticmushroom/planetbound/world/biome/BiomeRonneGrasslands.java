package com.crypticmushroom.planetbound.world.biome;

import com.crypticmushroom.planetbound.blocks.PBTallgrass;
import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.world.gen.PBRonnianTallgrassGen;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeRonneGrasslands extends PBBiomeRonne
{
    public BiomeRonneGrasslands(BiomeProperties props)
        {
            super(props);

            topBlock = PBBlocks.ronnian_grass.getDefaultState();
            fillerBlock = PBBlocks.ronnian_dirt.getDefaultState();

            decorator.treesPerChunk = -10;
            decorator.grassPerChunk = 8;
        }

        @Override
        public WorldGenerator getRandomWorldGenForGrass(Random rand)
        {
            return new PBRonnianTallgrassGen(PBTallgrass.TallgrassVariant.NORMAL);
        }
}
