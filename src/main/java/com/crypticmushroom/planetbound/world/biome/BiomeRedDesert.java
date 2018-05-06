package com.crypticmushroom.planetbound.world.biome;

import com.crypticmushroom.planetbound.init.PBBlocks;

public class BiomeRedDesert extends PBBiomeRonne {
    public BiomeRedDesert(BiomeProperties props) {
        super(props);

        this.topBlock = PBBlocks.ronnian_sand.getDefaultState();
        this.fillerBlock = PBBlocks.ronnian_sand.getDefaultState();
    }

/* **Remove Block Comment when Ores are implemented **

    public void decorate(World world, Random rand, BlockPos pos) {
        super.decorate(world, rand, pos);

        //Bloodstone Ore Gen
        for (int i = 0; i < 8; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(100);
            new WorldGenMinable(PBBlocks.bloodstone_ore.getDefaultState(), chunkProviderSettings.ironSize, input -> input == PBBlocks.ronnian_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }

        //Halkir Ore Gen
        for (int i = 0; i < 8; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(100);
            new WorldGenMinable(PBBlocks.halkir_ore.getDefaultState(), chunkProviderSettings.ironSize, input -> input == PBBlocks.ronnian_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }

        //Cremsine Ore Gen
        for (int i = 0; i < 8; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(100);
            new WorldGenMinable(PBBlocks.cremsine_ore.getDefaultState(), chunkProviderSettings.ironSize, input -> input == PBBlocks.ronnian_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
    } */
}