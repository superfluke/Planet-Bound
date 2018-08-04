package com.crypticmushroom.planetbound.blocks;

import java.util.List;
import java.util.Random;

import com.crypticmushroom.planetbound.world.planet.Planet;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PBLeaves extends BlockLeaves implements PBBlockTinted
{

    private final Planet[] planets_found_on;
    protected final Block sapling;
    protected final Item appleDrop;
    protected MapColor mapColor;

    public PBLeaves(Block sapling, Planet... planets)
    {
        this(sapling, null, Material.LEAVES.getMaterialMapColor(), planets);
    }

    public PBLeaves(Block sapling, Item appleDrop, Planet... planets)
    {
        this(sapling, appleDrop, Material.LEAVES.getMaterialMapColor(), planets);
    }

    public PBLeaves(Block sapling, MapColor mapColorIn, Planet... planets)
    {
        this(sapling, null, mapColorIn, planets);
    }

    public PBLeaves(Block sapling, Item appleDrop, MapColor mapColorIn, Planet... planets)
    {
        this.planets_found_on = planets;
        this.sapling = sapling;
        this.appleDrop = appleDrop;
        this.mapColor = mapColorIn;
        this.setDefaultState(blockState.getBaseState().withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
    }

    @Override
    public Planet[] getPlanets()
    {
        return planets_found_on.clone();
    }

    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
    {
        if (appleDrop != null && worldIn.rand.nextInt(chance) == 0)
        {
            spawnAsEntity(worldIn, pos, new ItemStack(appleDrop));
        }
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta)
    {
        return null;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return sapling == null ? null : Item.getItemFromBlock(sapling);
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        return Lists.newArrayList(new ItemStack(this));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (!state.getValue(DECAYABLE))
        {
            i |= 4;
        }

        if (state.getValue(CHECK_DECAY))
        {
            i |= 8;
        }
        return i;
    }

    @Deprecated
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY,
                (meta & 8) > 0);
    }

    @Override
    public TintType getTintType()
    {
        return TintType.FOLIAGE;
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return mapColor;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @Deprecated
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        return Blocks.LEAVES.shouldSideBeRendered(state, world, pos, side);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return Blocks.LEAVES.getBlockLayer();
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state)
    {
        return Blocks.LEAVES.isOpaqueCube(state);
    }
}