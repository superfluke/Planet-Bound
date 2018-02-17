package com.crypticmushroom.planetbound.world.gen;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

import com.crypticmushroom.planetbound.init.PBBlocks;

public class ChunkGeneratorRonne implements IChunkGenerator
{
    private World world;

    public ChunkGeneratorRonne(World world)
    {
        this.world = world;
    }
    
    @Override
    public Chunk generateChunk(int x, int z)
    {
        //TODO generate stuff
        ChunkPrimer primer = new ChunkPrimer();
        
        for(int xPos = 0; xPos < 16; xPos++)
        {
            for(int yPos = 0; yPos < 64; yPos++)
            {
                for(int zPos = 0; zPos < 16; zPos++)
                {
                    primer.setBlockState(xPos, yPos, zPos, PBBlocks.scarlet_sand.getDefaultState());
                    
                    if(yPos <= 60)
                    {
                        primer.setBlockState(xPos, yPos, zPos, Blocks.RED_SANDSTONE.getDefaultState());
                    }
                    
                    if(yPos <= 58)
                    {
                        primer.setBlockState(xPos, yPos, zPos, Blocks.STONE.getDefaultState());
                    }
                }
            }
        }
        
        Chunk chunk = new Chunk(world, primer, x, z);
        chunk.generateSkylightMap();
        
        return chunk;
    }

    @Override
    public void populate(int x, int z)
    {
        //TODO Auto-generated method stub
    }

    @Override
    public boolean generateStructures(Chunk chunk, int x, int z)
    {
        //TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        //TODO add creatures to spawn
        
        return new ArrayList<>();
    }

    @Override
    public BlockPos getNearestStructurePos(World world, String structureName, BlockPos position, boolean findUnexplored)
    {
        //TODO Auto-generated method stub
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunk, int x, int z)
    {
        //TODO Auto-generated method stub
    }

    @Override
    public boolean isInsideStructure(World world, String structureName, BlockPos pos)
    {
        //TODO Auto-generated method stub
        return false;
    }

}