package com.crypticmushroom.planetbound.world.gen;

import java.util.List;
import java.util.Random;

import com.crypticmushroom.planetbound.init.PBBlocks;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

import com.crypticmushroom.planetbound.world.planet.Planet;

/* If you were to ask me, I think it would be more effective if this was split up, so that
 * each planet can be more customizable than a single class. I'll keep this for now, but
 * if the other planets are implemented, this one should be just for Ronne
 *
 * @Androsa
 */
public class ChunkGeneratorPlanet implements IChunkGenerator
{

    private World world;
    private final Random random;

    private NoiseGeneratorOctaves minLimitPerlinNoise;
    private NoiseGeneratorOctaves maxLimitPerlinNoise;
    private NoiseGeneratorOctaves mainPerlinNoise;
    private NoiseGeneratorPerlin surfaceNoise;
    public NoiseGeneratorOctaves scaleNoise;
    public NoiseGeneratorOctaves depthNoise;

    private Biome biomesForGenerationRonne[];
    private double[] depthBuffer = new double[256];
    private final double[] heightMap;

    double[] mainNoiseRegion;
    double[] minLimitRegion;
    double[] maxLimitRegion;
    double[] depthRegion;

    private Planet planetInstance;
    private final PBGenCavesRonne caveGen = new PBGenCavesRonne();

    public ChunkGeneratorPlanet(World world, long seed, Planet planet) {
        this.heightMap = new double[planet.getXSize() * planet.getYSize() * planet.getZSize()];

        this.planetInstance = planet;

        this.world = world;
        this.random = new Random(seed);
        this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.random, 16);
        this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.random, 16);
        this.mainPerlinNoise = new NoiseGeneratorOctaves(this.random, 8);
        this.surfaceNoise = new NoiseGeneratorPerlin(this.random, 4);
        this.scaleNoise = new NoiseGeneratorOctaves(this.random, 10);
        this.depthNoise = new NoiseGeneratorOctaves(this.random, 16);
    }

	@Override
	public Chunk generateChunk(int x, int z) {
        random.setSeed(x * 0x4f9939f508L + z * 0x1ef1565bd5L);
        ChunkPrimer primer = new ChunkPrimer();
        setBlocksInChunk(x, z, primer);

        this.biomesForGenerationRonne = world.getBiomeProvider().getBiomes(biomesForGenerationRonne, x * 16, z * 16, 16, 16);
        replaceBiomeBlocks(x, z, primer, biomesForGenerationRonne);
        caveGen.generate(world, x, z, primer);

        Chunk chunk = new Chunk(world, primer, x, z);

        byte[] chunkBiomes = chunk.getBiomeArray();
        for (int i = 0; i < chunkBiomes.length; ++i) {
            chunkBiomes[i] = (byte) Biome.getIdForBiome(this.biomesForGenerationRonne[i]);
        }

        chunk.generateSkylightMap();

        return chunk;
	}

	@Override
	public void populate(int x, int z) {
        BlockFalling.fallInstantly = true;
        int i = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(i, 0, j);
        Biome biome = this.world.getBiome(blockpos.add(16, 0, 16));
        this.random.setSeed(this.world.getSeed());
        long k = this.random.nextLong() / 2L * 2L + 1L;
        long l = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed((long)x * k + (long)z * l ^ this.world.getSeed());

        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.random, x, z, false);

        biome.decorate(this.world, this.random, blockpos);

        this.planetInstance.generate(blockpos, biome);

        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.random, x, z, false);

        BlockFalling.fallInstantly = false;
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return null;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {

	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}

    public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
        byte seaLevel = 63;
        this.biomesForGenerationRonne = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGenerationRonne, x * 4 - 2, z * 4 - 2, 10, 10);
        this.generateHeightmap(x * 4, z * 4);

        for (int i = 0; i < 4; ++i) {
            int j = i * 5;
            int k = (i + 1) * 5;

            for (int l = 0; l < 4; ++l) {
                int i1 = (j + l) * 33;
                int j1 = (j + l + 1) * 33;
                int k1 = (k + l) * 33;
                int l1 = (k + l + 1) * 33;

                for (int i2 = 0; i2 < 32; ++i2) {
                    double d0 = 0.125D;
                    double d1 = this.heightMap[i1 + i2];
                    double d2 = this.heightMap[j1 + i2];
                    double d3 = this.heightMap[k1 + i2];
                    double d4 = this.heightMap[l1 + i2];
                    double d5 = (this.heightMap[i1 + i2 + 1] - d1) * d0;
                    double d6 = (this.heightMap[j1 + i2 + 1] - d2) * d0;
                    double d7 = (this.heightMap[k1 + i2 + 1] - d3) * d0;
                    double d8 = (this.heightMap[l1 + i2 + 1] - d4) * d0;

                    for (int j2 = 0; j2 < 8; ++j2) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int k2 = 0; k2 < 4; ++k2) {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double d15 = d10 - d16;

                            for (int l2 = 0; l2 < 4; ++l2) {
                                if ((d15 += d16) > 0.0D) {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, PBBlocks.ronnian_stone.getDefaultState());
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void generateHeightmap(int chunkX, int chunkZ) {
    	this.generateHeightmap(chunkX, 0, chunkZ);
    }

    private void generateHeightmap(int chunkX, int yOffset, int chunkZ) {
        this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, chunkX, chunkZ, this.planetInstance.getXSize(), this.planetInstance.getZSize(), 80.0D, 80.0D, 0.0D);
        float f = 684.412F;
        float f1 = 684.412F;
        this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, chunkX, yOffset, chunkZ, this.planetInstance.getXSize(), this.planetInstance.getYSize(), this.planetInstance.getZSize(), (double)(f / 80.0D), (double)(f1 / 160.0D), (double)(f / 80.0D));
        this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, chunkX, yOffset, chunkZ, this.planetInstance.getXSize(), this.planetInstance.getYSize(), this.planetInstance.getZSize(), (double)f, (double)f1, (double)f);
        this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, chunkX, yOffset, chunkZ, this.planetInstance.getXSize(), this.planetInstance.getYSize(), this.planetInstance.getZSize(), (double)f, (double)f1, (double)f);
        int i = 0;
        int j = 0;

        for (int k = 0; k < 5; ++k) {
            for (int l = 0; l < 5; ++l) {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                Biome biomegenbase = this.biomesForGenerationRonne[k + 2 + (l + 2) * 10];
 
                for (int j1 = -2; j1 <= 2; ++j1) {
                    for (int k1 = -2; k1 <= 2; ++k1) {
                        Biome biomegenbase1 = this.biomesForGenerationRonne[k + j1 + 2 + (l + k1 + 2) * 10];
                        float f5 = this.planetInstance.getMinHeight();
                        float f6 = this.planetInstance.getMaxHeight();

                        f2 += f6;
                        f3 += f5;
                        f4 += 1.0F;
                    }
                }

                f2 = f2 / f4;
                f3 = f3 / f4;
                f2 = f2 * 0.9F + 0.1F;
                f3 = (f3 * 4.0F - 1.0F) / 8.0F;
                double d7 = this.depthRegion[j] / 8000.0D;

                if (d7 < 0.0D) {
                    d7 = -d7 * 0.3D;
                }

                d7 = d7 * 3.0D - 2.0D;

                if (d7 < 0.0D) {
                    d7 = d7 / 2.0D;

                    if (d7 < -1.0D) {
                        d7 = -1.0D;
                    }

                    d7 = d7 / 1.4D;
                    d7 = d7 / 2.0D;
                } else {
                    if (d7 > 1.0D)
                    {
                        d7 = 1.0D;
                    }

                    d7 = d7 / 8.0D;
                }

                ++j;
                double d8 = (double)f3;
                double d9 = (double)f2;
                d8 = d8 + d7 * 0.2D;
                d8 = d8 * (double)8.5F / 8.0D;
                double d0 = (double)8.5F + d8 * 4.0D;

                for (int l1 = 0; l1 < 33; ++l1) {
                    double d1 = ((double)l1 - d0) * (double)12.0F * 128.0D / 256.0D / d9;

                    if (d1 < 0.0D) {
                        d1 *= 4.0D;
                    }

                    double d2 = this.minLimitRegion[i] / (double)512.0F;
                    double d3 = this.maxLimitRegion[i] / (double)512.0F;
                    double d4 = (this.mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
                    double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

                    if (l1 > 29) {
                        double d6 = (double)((float)(l1 - 29) / 3.0F);
                        d5 = d5 * (1.0D - d6) + -10.0D * d6;
                    }

                    this.heightMap[i] = d5;
                    ++i;
                }
            }
        }
    }

    public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomesIn) {
        if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, this.world)) return;
        double d0 = 0.03125D;
        this.depthBuffer = this.surfaceNoise.getRegion(this.depthBuffer, (double)(x * 16), (double)(z * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                Biome biome = biomesIn[j + i * 16];
                biome.genTerrainBlocks(this.world, this.random, primer, x * 16 + i, z * 16 + j, this.depthBuffer[j + i * 16]);
            }
        }
    }
}