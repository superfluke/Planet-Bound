package com.crypticmushroom.planetbound.world.gen;

import java.util.List;
import java.util.Random;

import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

/* If you were to ask me, I think it would be more effective if this was split up, so that
 * each planet can be more customizable than a single class. I'll keep this for now, but
 * if the other planets are implemented, this one should be just for Ronne
 *
 * @Androsa
 */
public class ChunkGeneratorPlanet implements IChunkGenerator
{
    private final Random random;

    private NoiseGeneratorOctaves minLimitPerlinNoise;
    private NoiseGeneratorOctaves maxLimitPerlinNoise;
    private NoiseGeneratorOctaves mainPerlinNoise;

    private NoiseGeneratorPerlin surfaceNoise;
    private NoiseGeneratorOctaves scaleNoise;
    private NoiseGeneratorOctaves depthNoise;

    private final World world;
    private WorldType terrainType;
    private final double[] heightMap;
    private final float[] biomeWeights;
    private double[] depthBuffer = new double[256];
    private Biome biomesForGenerationRonne[];

    private double[] mainNoiseRegion;
    private double[] minLimitRegion;
    private double[] maxLimitRegion;
    private double[] depthRegion;

    private Planet planetInstance;

    private final PBGenCavesRonne caveGen = new PBGenCavesRonne();

    public ChunkGeneratorPlanet(World world, long seed, Planet planet)
    {
        this.heightMap = new double[825];

        this.planetInstance = planet;

        this.world = world;
        this.terrainType = world.getWorldInfo().getTerrainType();
        this.random = new Random(seed);
        this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.random, 16);
        this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.random, 16);
        this.mainPerlinNoise = new NoiseGeneratorOctaves(this.random, 8);
        this.surfaceNoise = new NoiseGeneratorPerlin(this.random, 4);
        this.scaleNoise = new NoiseGeneratorOctaves(this.random, 10);
        this.depthNoise = new NoiseGeneratorOctaves(this.random, 16);
        this.biomeWeights = new float[25];

        for (int j = -2; j <= 2; ++j)
        {
            for (int k = -2; k <= 2; ++k)
            {
                float f = 10.0F / MathHelper.sqrt((float) (j * j + k * k) + 0.2F);
                this.biomeWeights[j + 2 + (k + 2) * 5] = f;
            }
        }
    }

    @Override
    public Chunk generateChunk(int x, int z)
    {
        random.setSeed(x * 0x4f9939f508L + z * 0x1ef1565bd5L);
        ChunkPrimer primer = new ChunkPrimer();
        setBlocksInChunk(x, z, primer);

        this.biomesForGenerationRonne = world.getBiomeProvider().getBiomes(biomesForGenerationRonne, x * 16, z * 16, 16, 16);
        replaceBiomeBlocks(x, z, primer, biomesForGenerationRonne);
        caveGen.generate(world, x, z, primer);

        Chunk chunk = new Chunk(world, primer, x, z);

        byte[] chunkBiomes = chunk.getBiomeArray();
        for (int i = 0; i < chunkBiomes.length; ++i)
        {
            chunkBiomes[i] = (byte) Biome.getIdForBiome(this.biomesForGenerationRonne[i]);
        }

        chunk.generateSkylightMap();

        return chunk;
    }

    @Override
    public void populate(int x, int z)
    {
        BlockFalling.fallInstantly = true;
        int i = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(i, 0, j);
        Biome biome = this.world.getBiome(blockpos.add(16, 0, 16));
        this.random.setSeed(this.world.getSeed());
        long k = this.random.nextLong() / 2L * 2L + 1L;
        long l = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed((long) x * k + (long) z * l ^ this.world.getSeed());

        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.random, x, z, false);

        biome.decorate(this.world, this.random, blockpos);

        this.planetInstance.generate(blockpos, biome);

        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.random, x, z, false);

        BlockFalling.fallInstantly = false;
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z)
    {
        return false;
    }

    @Override
    public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        return null;
    }

    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored)
    {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z)
    {

    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos)
    {
        return false;
    }

    public void setBlocksInChunk(int x, int z, ChunkPrimer primer)
    {
        byte seaLevel = 63;
        this.biomesForGenerationRonne = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGenerationRonne, x * 4 - 2, z * 4 - 2, 10, 10);
        this.generateHeightmap(x * 4, 0, z * 4);

        for (int i = 0; i < 4; ++i)
        {
            int j = i * 5;
            int k = (i + 1) * 5;

            for (int l = 0; l < 4; ++l)
            {
                int i1 = (j + l) * 33;
                int j1 = (j + l + 1) * 33;
                int k1 = (k + l) * 33;
                int l1 = (k + l + 1) * 33;

                for (int i2 = 0; i2 < 32; ++i2)
                {
                    double d0 = 0.125D;
                    double d1 = this.heightMap[i1 + i2];
                    double d2 = this.heightMap[j1 + i2];
                    double d3 = this.heightMap[k1 + i2];
                    double d4 = this.heightMap[l1 + i2];
                    double d5 = (this.heightMap[i1 + i2 + 1] - d1) * d0;
                    double d6 = (this.heightMap[j1 + i2 + 1] - d2) * d0;
                    double d7 = (this.heightMap[k1 + i2 + 1] - d3) * d0;
                    double d8 = (this.heightMap[l1 + i2 + 1] - d4) * d0;

                    for (int j2 = 0; j2 < 8; ++j2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int k2 = 0; k2 < 4; ++k2)
                        {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double d15 = d10 - d16;

                            for (int l2 = 0; l2 < 4; ++l2)
                            {
                                if ((d15 += d16) > 0.0D)
                                {
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

    public void generateHeightmap(int chunkX, int zero, int chunkZ)
    {
        this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, chunkX, chunkZ, 5, 5, 200.0D, 200.0D, 0.5D);
        double coordinateScale = 848.0; //684.412 MC default
        double heightScale = 57.57; //684.412 MC default
        double noiseScaleX = 2255.1; //80.0 MC default
        double noiseScaleY = 1255.0; //160 MC default
        double noiseScaleZ = 2255.3; //80 MC default
        this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, chunkX, zero, chunkZ, 5, 33, 5, (coordinateScale / noiseScaleX), (heightScale / noiseScaleY), (coordinateScale / noiseScaleZ));
        this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, chunkX, zero, chunkZ, 5, 33, 5, coordinateScale, heightScale, coordinateScale);
        this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, chunkX, zero, chunkZ, 5, 33, 5, coordinateScale, heightScale, coordinateScale);
        int terrainIndex = 0;
        int noiseIndex = 0;

        for (int ax = 0; ax < 5; ++ax)
        {
            for (int az = 0; az < 5; ++az)
            {
                float totalVariation = 0.0F;
                float totalHeight = 0.0F;
                float totalFactor = 0.0F;
                byte two = 2;
                Biome biomegenbase = this.biomesForGenerationRonne[ax + 2 + (az + 2) * 10];

                for (int ox = -two; ox <= two; ++ox)
                {
                    for (int oz = -two; oz <= two; ++oz)
                    {
                        Biome biomegenbase1 = this.biomesForGenerationRonne[ax + ox + 2 + (az + oz + 2) * 10];
                        float rootHeight = biomegenbase1.getBaseHeight();
                        float heightVariation = biomegenbase1.getHeightVariation();

                        if (this.terrainType == WorldType.AMPLIFIED && rootHeight > 0.0F)
                        {
                            rootHeight = 1.0F + rootHeight * 2.0F;
                            heightVariation = 1.0F + heightVariation * 4.0F;
                        }

                        float heightFactor = this.biomeWeights[ox + 2 + (oz + 2) * 5] / (rootHeight + 2.0F);

                        if (biomegenbase1.getBaseHeight() > biomegenbase.getBaseHeight())
                        {
                            heightFactor /= 2.0F;
                        }

                        totalVariation += heightVariation * heightFactor;
                        totalHeight += rootHeight * heightFactor;
                        totalFactor += heightFactor;
                    }
                }

                totalVariation /= totalFactor;
                totalHeight /= totalFactor;
                totalVariation = totalVariation * 0.9F + 0.1F;
                totalHeight = (totalHeight * 4.0F - 1.0F) / 8.0F;
                double terrainNoise = this.depthRegion[noiseIndex] / 8000.0D;

                if (terrainNoise < 0.0D)
                {
                    terrainNoise = -terrainNoise * 0.3D;
                }

                terrainNoise = terrainNoise * 3.0D - 2.0D;

                if (terrainNoise < 0.0D)
                {
                    terrainNoise /= 2.0D;

                    if (terrainNoise < -1.0D)
                    {
                        terrainNoise = -1.0D;
                    }

                    terrainNoise /= 1.4D;
                    terrainNoise /= 2.0D;
                } else
                {
                    if (terrainNoise > 1.0D)
                    {
                        terrainNoise = 1.0D;
                    }

                    terrainNoise /= 8.0D;
                }

                ++noiseIndex;
                double heightCalc = (double) totalHeight;
                double variationCalc = (double) totalVariation;
                heightCalc += terrainNoise * 0.2D;
                heightCalc = heightCalc * 8.5D / 8.0D;
                double d5 = 8.5D + heightCalc * 4.0D;

                for (int ay = 0; ay < 33; ++ay)
                {
                	double stretchY = 10.725; //12.0 MC default
                    double d6 = ((double) ay - d5) * stretchY * 128.0D / 256.0D / variationCalc;

                    if (d6 < 0.0D)
                    {
                        d6 *= 4.0D;
                    }

                    double d7 = this.minLimitRegion[terrainIndex] / 512.0D;
                    double d8 = this.maxLimitRegion[terrainIndex] / 512.0D;
                    double d9 = (this.mainNoiseRegion[terrainIndex] / 10.0D + 1.0D) / 2.0D;
                    double terrainCalc = MathHelper.clampedLerp(d7, d8, d9) - d6;

                    if (ay > 29)
                    {
                        double d11 = (double) ((float) (ay - 29) / 3.0F);
                        terrainCalc = terrainCalc * (1.0D - d11) + -10.0D * d11;
                    }

                    this.heightMap[terrainIndex] = terrainCalc;
                    ++terrainIndex;
                }
            }
        }
    }

    public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomesIn)
    {
        if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, this.world))
            return;
        double d0 = 0.03125D;
        this.depthBuffer = this.surfaceNoise.getRegion(this.depthBuffer, (double) (x * 16), (double) (z * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);

        for (int i = 0; i < 16; ++i)
        {
            for (int j = 0; j < 16; ++j)
            {
                Biome biome = biomesIn[j + i * 16];
                biome.genTerrainBlocks(this.world, this.random, primer, x * 16 + i, z * 16 + j, this.depthBuffer[j + i * 16]);
            }
        }
    }
}