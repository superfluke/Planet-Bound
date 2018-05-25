package com.crypticmushroom.planetbound.world.biome;

import com.crypticmushroom.planetbound.blocks.PBTallgrass;
import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.world.gen.PBEmberwoodTreeGen;
import com.crypticmushroom.planetbound.world.gen.PBNoTreeGen;
import com.crypticmushroom.planetbound.world.gen.PBRonnianTallgrassGen;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeEmberwoodForest extends PBBiomeRonne
{
    private WorldGenTrees RonneGenEmberwoodTrees;

    public BiomeEmberwoodForest(BiomeProperties props)
    {
        super(props);

        RonneGenEmberwoodTrees = new PBEmberwoodTreeGen(false);

        topBlock = PBBlocks.ronnian_grass.getDefaultState();
        fillerBlock = PBBlocks.ronnian_dirt.getDefaultState();

        decorator.treesPerChunk = 6;
        decorator.grassPerChunk = 3;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random)
    {
        return par1Random.nextInt(10) == 0 ? new PBNoTreeGen() : par1Random.nextInt(3) == 0 ? RonneGenEmberwoodTrees : new PBNoTreeGen();
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
        return new PBRonnianTallgrassGen(PBTallgrass.TallgrassVariant.NORMAL);
    }
}
