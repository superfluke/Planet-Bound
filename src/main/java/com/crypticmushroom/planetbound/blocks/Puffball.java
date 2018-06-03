package com.crypticmushroom.planetbound.blocks;

import java.util.Arrays;
import java.util.Random;

import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Puffball extends Block implements PBBlock {
	public static final PropertyEnum<Puffball.EnumType> VARIANT = PropertyEnum.<Puffball.EnumType>create("variant",
			Puffball.EnumType.class);
	public static final PropertyBool DOWN = PropertyBool.create("down");
	private final Item smallBlock;
	private final Planet[] planets_found_on;

	public Puffball(Material materialIn, MapColor color, Item smallBlockIn, Planet... planets) {
		super(materialIn, color);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, Puffball.EnumType.ALL_OUTSIDE).withProperty(DOWN, Boolean.TRUE));
		this.smallBlock = smallBlockIn;
		this.setSoundType(SoundType.WOOD);
		this.setHardness(0.2F);
		this.planets_found_on = planets;
	}

	@Override
	public Planet[] getPlanets() {
		return planets_found_on.clone();
	}


	private static final double PI_2 = 2.0 * Math.PI;
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if(stateIn.getValue(VARIANT) == EnumType.ALL_INSIDE)
			return;

		if(rand.nextInt(10) > 0) return;

		int count = rand.nextInt(3);

		for(int i = 0; i < count; i++) {
			double theta = rand.nextDouble() * PI_2;
			double epsilon = rand.nextDouble() * PI_2;

			double sinTheta = Math.sin(theta);

			double radius = rand.nextDouble() * 0.25;

			double xSpeed = radius * sinTheta * Math.cos(epsilon);
			double ySpeed = radius * sinTheta * Math.sin(epsilon);
			if(ySpeed > 0)
				ySpeed *= 1.25;
			double zSpeed = radius * Math.cos(epsilon);

			worldIn.spawnParticle(EnumParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, xSpeed, ySpeed, zSpeed, Block.getStateId(Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.ALL_INSIDE)));
		}
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random random) {
		return Math.max(0, random.nextInt(10) - 7);
	}

	/**
	 * Get the MapColor for this Block and the given BlockState
	 */
	@Override
	@Deprecated
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		switch (state.getValue(VARIANT)) {
		case ALL_INSIDE:
			return MapColor.SAND;
		default:
			return super.getMapColor(state, worldIn, pos);
		}
	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 */
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return this.smallBlock;
	}

	@Override
    @Deprecated
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this.smallBlock);
	}

	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to
	 * allow for adjustments to the IBlockstate
	 */
	@Override
    @Deprecated
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState();
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
    @Deprecated
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, Puffball.EnumType.byMetadata(meta));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(VARIANT).getMetadata();
	}

	/**
	 * Returns the blockstate with the given rotation from the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 */
	@Override
    @Deprecated
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		switch (rot) {
		case CLOCKWISE_180:

			switch (state.getValue(VARIANT)) {
			case NORTH_WEST:
				return state.withProperty(VARIANT, Puffball.EnumType.SOUTH_EAST);
			case NORTH:
				return state.withProperty(VARIANT, Puffball.EnumType.SOUTH);
			case NORTH_EAST:
				return state.withProperty(VARIANT, Puffball.EnumType.SOUTH_WEST);
			case WEST:
				return state.withProperty(VARIANT, Puffball.EnumType.EAST);
			case EAST:
				return state.withProperty(VARIANT, Puffball.EnumType.WEST);
			case SOUTH_WEST:
				return state.withProperty(VARIANT, Puffball.EnumType.NORTH_EAST);
			case SOUTH:
				return state.withProperty(VARIANT, Puffball.EnumType.NORTH);
			case SOUTH_EAST:
				return state.withProperty(VARIANT, Puffball.EnumType.NORTH_WEST);
			default:
				return state;
			}

		case COUNTERCLOCKWISE_90:

			switch (state.getValue(VARIANT)) {
			case NORTH_WEST:
				return state.withProperty(VARIANT, Puffball.EnumType.SOUTH_WEST);
			case NORTH:
				return state.withProperty(VARIANT, Puffball.EnumType.WEST);
			case NORTH_EAST:
				return state.withProperty(VARIANT, Puffball.EnumType.NORTH_WEST);
			case WEST:
				return state.withProperty(VARIANT, Puffball.EnumType.SOUTH);
			case EAST:
				return state.withProperty(VARIANT, Puffball.EnumType.NORTH);
			case SOUTH_WEST:
				return state.withProperty(VARIANT, Puffball.EnumType.SOUTH_EAST);
			case SOUTH:
				return state.withProperty(VARIANT, Puffball.EnumType.EAST);
			case SOUTH_EAST:
				return state.withProperty(VARIANT, Puffball.EnumType.NORTH_EAST);
			default:
				return state;
			}

		case CLOCKWISE_90:

			switch (state.getValue(VARIANT)) {
			case NORTH_WEST:
				return state.withProperty(VARIANT, Puffball.EnumType.NORTH_EAST);
			case NORTH:
				return state.withProperty(VARIANT, Puffball.EnumType.EAST);
			case NORTH_EAST:
				return state.withProperty(VARIANT, Puffball.EnumType.SOUTH_EAST);
			case WEST:
				return state.withProperty(VARIANT, Puffball.EnumType.NORTH);
			case EAST:
				return state.withProperty(VARIANT, Puffball.EnumType.SOUTH);
			case SOUTH_WEST:
				return state.withProperty(VARIANT, Puffball.EnumType.NORTH_WEST);
			case SOUTH:
				return state.withProperty(VARIANT, Puffball.EnumType.WEST);
			case SOUTH_EAST:
				return state.withProperty(VARIANT, Puffball.EnumType.SOUTH_WEST);
			default:
				return state;
			}

		default:
			return state;
		}
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 */
	@Override
    @Deprecated
	@SuppressWarnings("incomplete-switch")
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		Puffball.EnumType blockhugemushroom$enumtype = state.getValue(VARIANT);

		switch (mirrorIn) {
		case LEFT_RIGHT:

			switch (blockhugemushroom$enumtype) {
			case NORTH_WEST:
				return state.withProperty(VARIANT, Puffball.EnumType.SOUTH_WEST);
			case NORTH:
				return state.withProperty(VARIANT, Puffball.EnumType.SOUTH);
			case NORTH_EAST:
				return state.withProperty(VARIANT, Puffball.EnumType.SOUTH_EAST);
			case WEST:
			case EAST:
			default:
				return super.withMirror(state, mirrorIn);
			case SOUTH_WEST:
				return state.withProperty(VARIANT, Puffball.EnumType.NORTH_WEST);
			case SOUTH:
				return state.withProperty(VARIANT, Puffball.EnumType.NORTH);
			case SOUTH_EAST:
				return state.withProperty(VARIANT, Puffball.EnumType.NORTH_EAST);
			}

		case FRONT_BACK:

			switch (blockhugemushroom$enumtype) {
			case NORTH_WEST:
				return state.withProperty(VARIANT, Puffball.EnumType.NORTH_EAST);
			case NORTH:
			case SOUTH:
			default:
				break;
			case NORTH_EAST:
				return state.withProperty(VARIANT, Puffball.EnumType.NORTH_WEST);
			case WEST:
				return state.withProperty(VARIANT, Puffball.EnumType.EAST);
			case EAST:
				return state.withProperty(VARIANT, Puffball.EnumType.WEST);
			case SOUTH_WEST:
				return state.withProperty(VARIANT, Puffball.EnumType.SOUTH_EAST);
			case SOUTH_EAST:
				return state.withProperty(VARIANT, Puffball.EnumType.SOUTH_WEST);
			}
		}

		return super.withMirror(state, mirrorIn);
	}

	@Override
    @Deprecated
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		BlockPos pos2 = pos.down();
		IBlockState state2 = worldIn.getBlockState(pos2);
		if(state2.getBlock() != this) {
			boolean b = false;
			for(EnumFacing facing : EnumFacing.Plane.HORIZONTAL) {
				b |= worldIn.getBlockState(pos.offset(facing)).getBlock() != this;
			}
			return state.withProperty(DOWN, b);
		}
		return state.withProperty(DOWN, Boolean.FALSE);
	}

	@Override
	protected BlockStateContainer createBlockState() {

		return new BlockStateContainer(this, new IProperty[] { VARIANT, DOWN });
	}

	@Override
    @SuppressWarnings("unchecked")
	public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
		IBlockState state = world.getBlockState(pos);
		for (IProperty prop : (java.util.Set<IProperty<?>>) state.getProperties().keySet()) {
			if (prop.getName().equals("variant")) {
				world.setBlockState(pos, state.cycleProperty(prop));
				return true;
			}
		}
		return false;
	}

	public static enum EnumType implements IStringSerializable {
		NORTH_WEST(1, "north_west"),
		NORTH(2, "north"),
		NORTH_EAST(3, "north_east"),
		WEST(4, "west"),
		CENTER(5, "center"),
		EAST(6, "east"),
		SOUTH_WEST(7, "south_west"),
		SOUTH(8, "south"),
		SOUTH_EAST(9, "south_east"),
		ALL_INSIDE(10, "all_inside"),
		ALL_OUTSIDE(11, "all_outside");

		private static final Puffball.EnumType[] META_LOOKUP = new Puffball.EnumType[16];
		private final int meta;
		private final String name;

		private EnumType(int meta, String name) {
			this.meta = meta;
			this.name = name;
		}

		public int getMetadata() {
			return this.meta;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static Puffball.EnumType byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			Puffball.EnumType blockhugemushroom$enumtype = META_LOOKUP[meta];
			return blockhugemushroom$enumtype == null ? META_LOOKUP[0] : blockhugemushroom$enumtype;
		}

		@Override
		public String getName() {
			return this.name;
		}

		static {
			Arrays.fill(META_LOOKUP, ALL_OUTSIDE);
			for (Puffball.EnumType type : values()) {
				META_LOOKUP[type.getMetadata()] = type;
			}
		}
	}
}