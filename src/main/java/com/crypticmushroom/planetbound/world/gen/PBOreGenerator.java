package com.crypticmushroom.planetbound.world.gen;

import com.crypticmushroom.planetbound.init.PBBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import org.apache.commons.lang3.Validate;

import java.util.Random;

public class PBOreGenerator implements IWorldGenerator
{
    private final WorldGenMinable rendiumGenerator = new WorldGenMinable(PBBlocks.rendium_ore.getDefaultState(), 7); // Same
    // as
    // lapis
    private final WorldGenMinable verdaniteGenerator = new WorldGenMinable(PBBlocks.verdanite_ore.getDefaultState(), 9); // Same
    // as
    // iron
    private final WorldGenMinable kybriteGenerator = new WorldGenMinable(PBBlocks.kybrite_ore.getDefaultState(), 9); // Same
    // as
    // iron

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator generator, IChunkProvider provider)
    {
        if (world.provider.getDimensionType() == DimensionType.OVERWORLD)
        {
            generate(rendiumGenerator, random, chunkX, chunkZ, world, 20, 0, 128);
            generate(verdaniteGenerator, random, chunkX, chunkZ, world, 20, 0, 64);
            generate(kybriteGenerator, random, chunkX, chunkZ, world, 20, 0, 64);
        }
    }

    private void generate(WorldGenerator generator, Random random, int chunkX, int chunkZ, World world, int spawnTries, int minHeight, int maxHeight)
    {
        Validate.isTrue(minHeight >= 0 && maxHeight <= 256);

        for (int i = 0; i < spawnTries; i++)
        {
            int x = chunkX * 16 + random.nextInt(16);
            int y = minHeight + random.nextInt(maxHeight - minHeight + 1);
            int z = chunkZ * 16 + random.nextInt(16);

            generator.generate(world, random, new BlockPos(x, y, z));
        }
    }
}