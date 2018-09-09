package com.crypticmushroom.planetbound.world.biome;

import java.util.Random;

import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.world.gen.PBGenStoneSpire;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BiomeRedDesert extends PBBiomeRonne
{
	private PBGenStoneSpire spireGen;
	
    public BiomeRedDesert(BiomeProperties props)
    {
        super(props);

        this.topBlock = PBBlocks.ronnian_sand.getDefaultState();
        this.fillerBlock = PBBlocks.ronnian_sand.getDefaultState();
        
        spireGen = new PBGenStoneSpire(PBBlocks.ronnian_sandstone.getDefaultState(), 5);
    }
    
    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
    	super.decorate(world, rand, pos);
    	
    	int x = rand.nextInt(16) + 8;
        int z = rand.nextInt(16) + 8;
        BlockPos genpos = world.getHeight(pos.add(x, 0, z)).up();
        spireGen.generate(world, rand, genpos);
    }

    /*
     * **Remove Block Comment when Ores are implemented **
     *
     * public void decorate(World world, Random rand, BlockPos pos) {
     * super.decorate(world, rand, pos);
     *
     * //Bloodstone Ore Gen for (int i = 0; i < 8; i++) { int Xcoord = pos.getX() +
     * rand.nextInt(16); int Zcoord = pos.getZ() + rand.nextInt(16); int Ycoord =
     * rand.nextInt(100); new
     * WorldGenMinable(PBBlocks.bloodstone_ore.getDefaultState(),
     * chunkProviderSettings.ironSize, input -> input ==
     * PBBlocks.ronnian_stone.getDefaultState()).generate(world, rand, new
     * BlockPos(Xcoord, Ycoord, Zcoord)); }
     *
     * //Halkir Ore Gen for (int i = 0; i < 8; i++) { int Xcoord = pos.getX() +
     * rand.nextInt(16); int Zcoord = pos.getZ() + rand.nextInt(16); int Ycoord =
     * rand.nextInt(100); new WorldGenMinable(PBBlocks.halkir_ore.getDefaultState(),
     * chunkProviderSettings.ironSize, input -> input ==
     * PBBlocks.ronnian_stone.getDefaultState()).generate(world, rand, new
     * BlockPos(Xcoord, Ycoord, Zcoord)); }
     *
     * //Cremsine Ore Gen for (int i = 0; i < 8; i++) { int Xcoord = pos.getX() +
     * rand.nextInt(16); int Zcoord = pos.getZ() + rand.nextInt(16); int Ycoord =
     * rand.nextInt(100); new
     * WorldGenMinable(PBBlocks.cremsine_ore.getDefaultState(),
     * chunkProviderSettings.ironSize, input -> input ==
     * PBBlocks.ronnian_stone.getDefaultState()).generate(world, rand, new
     * BlockPos(Xcoord, Ycoord, Zcoord)); } }
     */
}