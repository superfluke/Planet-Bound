package com.crypticmushroom.planetbound.world.biome;

import com.crypticmushroom.planetbound.blocks.ronnian.RonnianTallgrass;
import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.world.gen.PBEmberwoodTreeGen;
import com.crypticmushroom.planetbound.world.gen.PBNoTreeGen;
import com.crypticmushroom.planetbound.world.gen.PBRonnianTallgrassGen;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeRonneMountains extends PBBiomeRonne {

    private WorldGenTrees RonneGenEmberwoodTrees;

    public BiomeRonneMountains(BiomeProperties props)
    {
        super(props);

        RonneGenEmberwoodTrees = new PBEmberwoodTreeGen(false);

        topBlock = PBBlocks.ronnian_stone.getDefaultState();
        fillerBlock = PBBlocks.ronnian_stone.getDefaultState();

        decorator.treesPerChunk = 1;
        decorator.grassPerChunk = 1;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random)
    {
        return par1Random.nextInt(10) == 0 ? new PBNoTreeGen() : par1Random.nextInt(3) == 0 ? RonneGenEmberwoodTrees : new PBNoTreeGen();
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
        return new PBRonnianTallgrassGen(RonnianTallgrass.TallgrassVariant.NORMAL);
    }
}
