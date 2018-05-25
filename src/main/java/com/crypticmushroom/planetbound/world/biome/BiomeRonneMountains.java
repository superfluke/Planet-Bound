package com.crypticmushroom.planetbound.world.biome;

import com.crypticmushroom.planetbound.blocks.PBTallgrass;
import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.world.gen.PBEmberwoodTreeGen;
import com.crypticmushroom.planetbound.world.gen.PBGenCoarseDirtPatch;
import com.crypticmushroom.planetbound.world.gen.PBNoTreeGen;
import com.crypticmushroom.planetbound.world.gen.PBRonnianTallgrassGen;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeRonneMountains extends PBBiomeRonne {

    private WorldGenTrees RonneGenEmberwoodTrees;
    private PBGenCoarseDirtPatch dirtPatch = new PBGenCoarseDirtPatch(8);

    public BiomeRonneMountains(BiomeProperties props)
    {
        super(props);

        RonneGenEmberwoodTrees = new PBEmberwoodTreeGen(false);

        topBlock = PBBlocks.ronnian_stone.getDefaultState();
        fillerBlock = PBBlocks.ronnian_stone.getDefaultState();

        decorator.treesPerChunk = 5;
        decorator.grassPerChunk = 2;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        super.decorate(world, rand, pos);

        if(world.getHeight() < 80) {
            for (int i = 0; i < 10; i++) {
                int rx = pos.getX() + rand.nextInt(16) + 8;
                int rz = pos.getZ() + rand.nextInt(16) + 8;
                dirtPatch.generate(world, rand, world.getHeight(new BlockPos(rx, 0, rz)));
            }
        } else if(world.getHeight() > 79) {
            for (int i = 0; i < 3; i++) {
                int rx = pos.getX() + rand.nextInt(16) + 8;
                int rz = pos.getZ() + rand.nextInt(16) + 8;
                dirtPatch.generate(world, rand, world.getHeight(new BlockPos(rx, 0, rz)));
            }
        }
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
