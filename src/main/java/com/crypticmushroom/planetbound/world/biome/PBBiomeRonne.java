package com.crypticmushroom.planetbound.world.biome;

import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.init.PBWorld;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

public class PBBiomeRonne extends Biome {
    /*
    * This class should be used for any biomes used in Ronne
    * Use this class by extending this, instead of Biome
    *
    * - Androsa
    *
     */

    public PBBiomeRonne(BiomeProperties props) {
        super(props);

        decorator.flowersPerChunk = -1; //This disables Poppies and Dandelions
        decorator.reedsPerChunk = -1; //For some reason, reeds cannot be disabled, but this looks better
        decorator.treesPerChunk = -1; //Disables default trees. Another method can be used to gen trees
        decorator.grassPerChunk = -1; //Again, another method can be used to gen Grass
    }

    //Starts the Decorator
    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        this.decorator.decorate(worldIn, rand, this, pos);
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noiseVal) {
        this.genRonneBiomeTerrain(world, rand, primer, x, z, noiseVal);
    }

    public final void genRonneBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        int i = worldIn.getSeaLevel();
        IBlockState iblockstate = this.topBlock;
        IBlockState iblockstate1 = this.fillerBlock;
        int j = -1;
        int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int l = x & 15;
        int i1 = z & 15;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int j1 = 255; j1 >= 0; --j1) {
            if (j1 <= rand.nextInt(5)) {
                chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
            } else {
                IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

                if (iblockstate2.getMaterial() == Material.AIR) {
                    j = -1;
                } else if (iblockstate2.getBlock() == PBBlocks.ronnian_stone) {
                    if (j == -1) {
                        if (k <= 0) {
                            iblockstate = AIR;
                            iblockstate1 = PBBlocks.ronnian_stone.getDefaultState();
                        } else if (j1 >= i - 4 && j1 <= i + 1) {
                            iblockstate = this.topBlock;
                            iblockstate1 = this.fillerBlock;
                        }

                        if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
                            if (this.getTemperature(blockpos$mutableblockpos.setPos(x, j1, z)) < 0.15F) {
                                iblockstate = ICE;
                            } else {
                                iblockstate = WATER;
                            }
                        }

                        j = k;

                        if (j1 >= i - 1) {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
                        } else if (j1 < i - 7 - k) {
                            iblockstate = AIR;
                            iblockstate1 = PBBlocks.ronnian_stone.getDefaultState();
                            chunkPrimerIn.setBlockState(i1, j1, l, PBBlocks.ronnian_sand.getDefaultState());
                        } else {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
                        }
                    } else if (j > 0) {
                        --j;
                        chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);

                        if (j == 0 && iblockstate1.getBlock() == PBBlocks.ronnian_sand && k > 1) {
                            j = rand.nextInt(4) + Math.max(0, j1 - 63);
                            iblockstate1 = PBBlocks.ronnian_sandstone.getDefaultState();
                        }
                    }
                }
            }
        }
    }
}
