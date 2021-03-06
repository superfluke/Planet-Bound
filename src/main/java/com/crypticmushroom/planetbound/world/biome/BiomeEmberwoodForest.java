package com.crypticmushroom.planetbound.world.biome;

import java.util.Random;

import com.crypticmushroom.planetbound.blocks.PBTallgrass;
import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.world.gen.PBAmberwoodTreeGen;
import com.crypticmushroom.planetbound.world.gen.PBEmberwoodTreeGen;
import com.crypticmushroom.planetbound.world.gen.PBNoTreeGen;
import com.crypticmushroom.planetbound.world.gen.PBRonnianTallgrassGen;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeEmberwoodForest extends PBBiomeRonne
{
    private WorldGenAbstractTree emberwoodTreeGen;
    private WorldGenAbstractTree amberwoodTreeGen;

    public BiomeEmberwoodForest(BiomeProperties props)
    {
        super(props);

        emberwoodTreeGen = new PBEmberwoodTreeGen(false);
        amberwoodTreeGen = new PBAmberwoodTreeGen(false);

        topBlock = PBBlocks.ronnian_grass.getDefaultState();
        fillerBlock = PBBlocks.ronnian_dirt.getDefaultState();

        decorator.treesPerChunk = 6;
        decorator.grassPerChunk = 3;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return rand.nextInt(10) == 0 ? amberwoodTreeGen : rand.nextInt(3) == 0 ? emberwoodTreeGen : new PBNoTreeGen();
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
        return new PBRonnianTallgrassGen(PBTallgrass.TallgrassVariant.NORMAL);
    }
}
