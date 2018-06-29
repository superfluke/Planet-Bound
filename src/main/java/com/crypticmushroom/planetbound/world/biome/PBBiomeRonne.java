package com.crypticmushroom.planetbound.world.biome;

import java.util.Random;

import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.init.PBPlanets;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PBBiomeRonne extends PBBiome
{
    /*
     * This class should be used for any biomes used in Ronne Use this class by
     * extending this, instead of Biome
     *
     * - Androsa
     *
     */

    public PBBiomeRonne(BiomeProperties props)
    {
        super(props, PBPlanets.RONNE);

        decorator.flowersPerChunk = -1; // This disables Poppies and Dandelions
        decorator.reedsPerChunk = -1; // For some reason, reeds cannot be disabled, but this looks better
        decorator.treesPerChunk = -1; // Disabled by default. Tweaking this number alongside the tree generator makes it more decorated
        decorator.grassPerChunk = -1; // Disabled by default. Tweaking this number alongside the grass generator makes it more decorated
    }

    // Starts the Decorator
    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn, rand, pos);

        for (int i = 0; i < 8; i++)
        {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(100);

            new WorldGenMinable(PBBlocks.halkir_ore.getDefaultState(), 9, input -> input == PBBlocks.ronnian_stone.getDefaultState()).generate(worldIn, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        for (int i = 0; i < 8; i++)
        {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(100);
            new WorldGenMinable(PBBlocks.bloodstone_ore.getDefaultState(), 9, input -> input == PBBlocks.ronnian_stone.getDefaultState()).generate(worldIn, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }

        /*
         * //Cremsine Ore Gen for (int i = 0; i < 8; i++) { int Xcoord = pos.getX() +
         * rand.nextInt(16); int Zcoord = pos.getZ() + rand.nextInt(16); int Ycoord =
         * rand.nextInt(100); new
         * WorldGenMinable(PBBlocks.cremsine_ore.getDefaultState(),
         * chunkProviderSettings.ironSize, input -> input ==
         * PBBlocks.ronnian_stone.getDefaultState()).generate(world, rand, new
         * BlockPos(Xcoord, Ycoord, Zcoord)); } }
         */
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noiseVal)
    {
        this.genRonneBiomeTerrain(world, rand, primer, x, z, noiseVal);
    }

    public final void genRonneBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
    {
        int i = worldIn.getSeaLevel();
        IBlockState iblockstate = this.topBlock;
        IBlockState iblockstate1 = this.fillerBlock;
        int j = -1;
        int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int l = x & 15;
        int i1 = z & 15;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int j1 = 255; j1 >= 0; --j1)
        {
            if (j1 <= rand.nextInt(5))
            {
                chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
            } else
            {
                IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

                if (iblockstate2.getMaterial() == Material.AIR)
                {
                    j = -1;
                } else if (iblockstate2.getBlock() == PBBlocks.ronnian_stone)
                {
                    if (j == -1)
                    {
                        if (k <= 0)
                        {
                            iblockstate = AIR;
                            iblockstate1 = PBBlocks.ronnian_stone.getDefaultState();
                        } else if (j1 >= i - 4 && j1 <= i + 1)
                        {
                            iblockstate = this.topBlock;
                            iblockstate1 = this.fillerBlock;
                        }

                        if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR))
                        {
                            if (this.getTemperature(blockpos$mutableblockpos.setPos(x, j1, z)) < 0.15F)
                            {
                                iblockstate = ICE;
                            } else
                            {
                                iblockstate = WATER;
                            }
                        }

                        j = k;

                        if (j1 >= i - 1)
                        {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
                        } else if (j1 < i - 7 - k)
                        {
                            iblockstate = AIR;
                            iblockstate1 = PBBlocks.ronnian_stone.getDefaultState();
                            chunkPrimerIn.setBlockState(i1, j1, l, PBBlocks.ronnian_stone.getDefaultState());
                        } else
                        {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
                        }
                    } else if (j > 0)
                    {
                        --j;
                        chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);

                        if (j == 0 && iblockstate1.getBlock() == PBBlocks.ronnian_sand && k > 1)
                        {
                            j = rand.nextInt(4) + Math.max(0, j1 - 63);
                            iblockstate1 = PBBlocks.ronnian_sandstone.getDefaultState();
                        }
                    }
                }
            }
        }
    }


    /**
     * takes temperature, returns color
     */
    // midnight blue color in Ronne
    // returns rgb(34, 43, 89) when currentTemperature = 0.7f
    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        currentTemperature = currentTemperature / 3.0F;
        currentTemperature = MathHelper.clamp(currentTemperature, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.651061F - currentTemperature * 0.05F, 0.594644F + currentTemperature * 0.1F,
                0.349020F);
    }

    @Override
    public int getPBGrassColorAtPos(BlockPos pos)
    {
        double d0 = MathHelper.clamp(this.getTemperature(pos), 0.0F, 1.0F);
        double d1 = MathHelper.clamp(this.getRainfall(), 0.0F, 1.0F);
        return getModdedBiomeGrassColor(getPlanet().grassColorizer.getFloraColor(d0, d1));
    }

    @Override
    public int getPBFoliageColorAtPos(BlockPos pos)
    {
        double d0 = MathHelper.clamp(this.getTemperature(pos), 0.0F, 1.0F);
        double d1 = MathHelper.clamp(this.getRainfall(), 0.0F, 1.0F);
        return getModdedBiomeFoliageColor(getPlanet().foliageColorizer.getFloraColor(d0, d1));
    }
}
