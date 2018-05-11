package com.crypticmushroom.planetbound.blocks;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.List;
import java.util.Random;

public class EmberwoodLeaves extends BlockLeaves
{
    public EmberwoodLeaves()
    {
        super();
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta)
    {
        return null;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
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
        int meta = state.getValue(CHECK_DECAY) ? 1 : 0;
        if(state.getValue(DECAYABLE)) meta |= 0b10;
        return meta;
    }

    @Deprecated
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(CHECK_DECAY, ((meta & 0b1) == 1)).withProperty(DECAYABLE, (meta & 0b10) == 1);
    }
}