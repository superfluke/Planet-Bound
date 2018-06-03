package com.crypticmushroom.planetbound.blocks;

import java.util.List;
import java.util.Random;

import com.crypticmushroom.planetbound.world.planet.Planet;
import com.google.common.collect.Lists;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class PBLeaves extends BlockLeaves implements PBBlockTinted {

	private final Planet[] planets_found_on;
	protected final PBSapling sapling;
	protected final Item appleDrop;

	public PBLeaves(PBSapling sapling, Item appleDrop, Planet... planets) {
		this.planets_found_on = planets;
		this.sapling = sapling;
		this.appleDrop = appleDrop;
		this.setDefaultState(blockState.getBaseState().withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
	}

	public PBLeaves(PBSapling sapling, Planet... planets) {
		this(sapling, null, planets);
	}

	@Override
	public Planet[] getPlanets() {
		return planets_found_on.clone();
	}

	@Override
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
		if(appleDrop != null && worldIn.rand.nextInt(chance) == 0) {
			spawnAsEntity(worldIn, pos, new ItemStack(appleDrop));
		}
	}

	@Override
	public BlockPlanks.EnumType getWoodType(int meta) {
		return null;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return sapling == null? null : Item.getItemFromBlock(sapling);
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return Lists.newArrayList(new ItemStack(this));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;

		if(!state.getValue(DECAYABLE)) {
			i |= 4;
		}

		if(state.getValue(CHECK_DECAY)) {
			i |= 8;
		}
		return i;
	}

	@Deprecated
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY,
				(meta & 8) > 0);
	}

	@Override
	public TintType getTintType() {
		return TintType.FOLIAGE;
	}
}