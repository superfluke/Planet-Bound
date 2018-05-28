package com.crypticmushroom.planetbound.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PBTallgrass extends BlockBush implements IShearable, PBBlockTinted {
	public static final PropertyEnum<TallgrassVariant> VARIANT = PropertyEnum.create("variant", TallgrassVariant.class);

	protected static final AxisAlignedBB PLANT_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D,
			0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

	private final Planet[] planets_found_on;

	public PBTallgrass(Planet... planets) {
		super(Material.PLANTS);

		this.setTickRandomly(true);
		this.setSoundType(SoundType.PLANT);
		this.getDefaultState().withProperty(VARIANT, TallgrassVariant.NORMAL);
		this.planets_found_on = planets;
	}

	@Override
	public Planet[] getPlanets() {
		return planets_found_on.clone();
	}
	
	@Override
	public TintType getTintType() {
		return TintType.GRASS;
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(VARIANT).ordinal();
	}

	@Override
	@Deprecated
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, TallgrassVariant.values()[meta]);
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for(int i = 0; i < TallgrassVariant.values().length; i++)
			items.add(new ItemStack(this, 1, i));
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).ordinal());
	}

	@Override
	public int damageDropped(IBlockState state) {
		TallgrassVariant leafType = state.getValue(VARIANT);
		return leafType.ordinal();
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		List<ItemStack> ret = new ArrayList<>();
		ret.add(new ItemStack(this));
		return ret;
	}

	public boolean canPlaceBlockAt(IBlockState state) {
		return state.getBlock() == PBBlocks.ronnian_grass || state.getBlock() == PBBlocks.ronnian_dirt
				|| state.getBlock() == PBBlocks.ronnian_coarse_dirt;
	}

	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		return super.canBlockStay(worldIn, pos, state);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return PLANT_AABB;
	}

	/* TRANSLUCENT should only be for blocks like ice and stained glass.
	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}*/

	@SideOnly(Side.CLIENT)
	public void registerModel() {
		for(int i = 0; i < TallgrassVariant.values().length; i++) {
			// String variant = "inventory_" + TallgrassVariant.values()[i].getName();
			String name = getRegistryName().toString();
			TallgrassVariant variant = TallgrassVariant.values()[i];
			if(variant != TallgrassVariant.NORMAL) {
				name += "_" + variant.getName();
			}
			ModelResourceLocation mrl = new ModelResourceLocation(name, "inventory");
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), i, mrl);
		}
	}

	public enum TallgrassVariant implements IStringSerializable {
		NORMAL;

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ROOT);
		}
	}
}
