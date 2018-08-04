package com.crypticmushroom.planetbound.blocks;

import java.util.Random;

import net.minecraft.block.material.MapColor;
import org.apache.commons.lang3.Validate;

import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Base class for all planet's grass blocks
 */
public class PBGrass extends Block implements PBBlockTinted
{
    protected Block dirtBlock;
    private final Planet[] planets_found_on;

    public PBGrass(Block dirtBlock)
    {
        this(dirtBlock, Material.GRASS, Material.GRASS.getMaterialMapColor());
    }

    public PBGrass(Block dirtBlock, MapColor mapColorIn)
    {
        this(dirtBlock, Material.GRASS, mapColorIn);
    }

    public PBGrass(Block dirtBlock, Material materialIn, MapColor mapColorIn, Planet... planets)
    {
        super(materialIn, mapColorIn);
        this.planets_found_on = planets;
        this.setSoundType(SoundType.PLANT);
        this.setHardness(0.9F);
        this.setTickRandomly(true);

        Validate.notNull(dirtBlock);
        this.dirtBlock = dirtBlock;
    }

    @Override
    public Planet[] getPlanets()
    {
        return planets_found_on.clone();
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
            if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockState(pos.up()).getBlock()
                    .getLightOpacity(world.getBlockState(pos.up()), world, pos.up()) > 2)
                world.setBlockState(pos, this.dirtBlock.getDefaultState());
            else if (world.getLightFromNeighbors(pos.up()) >= 9)
                for (int i = 0; i < 4; ++i)
                {
                    BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                    Block block = world.getBlockState(blockpos.up()).getBlock();
                    IBlockState iblockstate = world.getBlockState(blockpos);

                    if (iblockstate.getBlock() == this.dirtBlock
                            && world.getLightFromNeighbors(blockpos.up()) >= 4
                            && block.getLightOpacity(world.getBlockState(blockpos.up()), world, blockpos.up()) <= 2)
                        world.setBlockState(blockpos, this.dirtBlock.getDefaultState());
                }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int j)
    {
        return PBBlocks.ronnian_dirt.getItemDropped(PBBlocks.ronnian_dirt.getDefaultState(), random, j);
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
                                   IPlantable plantable)
    {
        boolean hasWater = world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.south()).getMaterial() == Material.WATER;
        return plantable.getPlantType(world, pos.offset(direction)) == EnumPlantType.Plains ||
                plantable.getPlantType(world, pos.offset(direction)) == EnumPlantType.Beach && hasWater;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public TintType getTintType()
    {
        return TintType.GRASS;
    }
}
