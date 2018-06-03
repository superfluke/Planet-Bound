package com.crypticmushroom.planetbound.blocks;

import java.util.Random;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.init.PBBlocks;
import com.crypticmushroom.planetbound.networking.PBGuiHandler;
import com.crypticmushroom.planetbound.tileentity.TileEntityInventorsForge;
import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Pretty much a modified copy of {@link BlockFurnace}
 */
public class InventorsForge extends BlockContainer implements PBBlock
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	private static boolean keepInventory;
	private final boolean isBurning;

	public InventorsForge(boolean isBurning)
	{
		super(Material.ROCK);

		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setHarvestLevel("pickaxe", 0);
		setHardness(3.5F);
		setSoundType(SoundType.STONE);

		if(isBurning)
		{
			setLightLevel(0.875F);
		}

		this.isBurning = isBurning;
	}

	@Override
	public Planet[] getPlanets() {
		return new Planet[0];
	}

	public static void setState(boolean active, World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		TileEntity tileEntity = world.getTileEntity(pos);

		keepInventory = true;

		if(active)
		{
			world.setBlockState(pos, PBBlocks.lit_inventors_forge.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
			world.setBlockState(pos, PBBlocks.lit_inventors_forge.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
		}
		else
		{
			world.setBlockState(pos, PBBlocks.inventors_forge.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
			world.setBlockState(pos, PBBlocks.inventors_forge.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
		}

		keepInventory = false;

		if(tileEntity != null)
		{
			tileEntity.validate();

			world.setTileEntity(pos, tileEntity);
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune)
	{
		return Item.getItemFromBlock(PBBlocks.inventors_forge);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		this.setDefaultFacing(world, pos, state);
	}

	private void setDefaultFacing(World world, BlockPos pos, IBlockState state)
	{
		if(!world.isRemote)
		{
			IBlockState northState = world.getBlockState(pos.north());
			IBlockState southState = world.getBlockState(pos.south());
			IBlockState westState = world.getBlockState(pos.west());
			IBlockState eastState = world.getBlockState(pos.east());

			EnumFacing facing = state.getValue(FACING);

			if(facing == EnumFacing.NORTH && northState.isFullBlock() && !southState.isFullBlock())
			{
				facing = EnumFacing.SOUTH;
			}
			else if(facing == EnumFacing.SOUTH && southState.isFullBlock() && !northState.isFullBlock())
			{
				facing = EnumFacing.NORTH;
			}
			else if(facing == EnumFacing.WEST && westState.isFullBlock() && !eastState.isFullBlock())
			{
				facing = EnumFacing.EAST;
			}
			else if(facing == EnumFacing.EAST && eastState.isFullBlock() && !westState.isFullBlock())
			{
				facing = EnumFacing.WEST;
			}

			world.setBlockState(pos, state.withProperty(FACING, facing), 2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("incomplete-switch")
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random)
	{
		if(this.isBurning)
		{
			EnumFacing facing = state.getValue(FACING);

			double d0 = pos.getX() + 0.5D;
			double d1 = pos.getY() + random.nextDouble() * 6.0D / 16.0D;
			double d2 = pos.getZ() + 0.5D;
			//double d3 = 0.52D;
			double d4 = random.nextDouble() * 0.6D - 0.3D;

			if(random.nextDouble() < 0.1D)
			{
				world.playSound(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			}

			switch(facing)
			{
			case WEST:
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
				world.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
				break;
			case EAST:
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
				world.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
				break;
			case NORTH:
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
				world.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
				break;
			case SOUTH:
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
				world.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
				break;
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(world.isRemote)
		{
			return true;
		}
		else
		{
			TileEntity tileEntity = world.getTileEntity(pos);

			if(tileEntity instanceof TileEntityInventorsForge)
			{
				switch (facing)
				{
				case UP:
					player.openGui(PBCore.instance(), PBGuiHandler.WORKBENCH_ID, world, pos.getX(), pos.getY(), pos.getZ());
					break;
				default:
					player.openGui(PBCore.instance(), PBGuiHandler.INVENTORS_FORGE_ID, world, pos.getX(), pos.getY(), pos.getZ());
					break;
				}
			}

			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityInventorsForge();
	}

	@Override
	@Deprecated
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int metadata, EntityLivingBase placer)
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		if(!keepInventory)
		{
			TileEntity tileEntity = world.getTileEntity(pos);

			if(tileEntity instanceof TileEntityInventorsForge)
			{
				InventoryHelper.dropInventoryItems(world, pos, (TileEntityInventorsForge)tileEntity);

				world.updateComparatorOutputLevel(pos, this);
			}
		}

		super.breakBlock(world, pos, state);
	}

	@Override
    @Deprecated
	public boolean hasComparatorInputOverride(IBlockState state)
	{
		return true;
	}

	@Override
    @Deprecated
	public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos)
	{
		return Container.calcRedstone(world.getTileEntity(pos));
	}

	@Override
    @Deprecated
	public ItemStack getItem(World world, BlockPos pos, IBlockState state)
	{
		return new ItemStack(PBBlocks.inventors_forge);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}

	@Override
    @Deprecated
	public IBlockState getStateFromMeta(int metadata)
	{
		EnumFacing facing = EnumFacing.getFront(metadata);

		if(facing.getAxis() == EnumFacing.Axis.Y)
		{
			facing = EnumFacing.NORTH;
		}

		return getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(FACING).getIndex();
	}

	@Override
    @Deprecated
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
    @Deprecated
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	{
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[]{FACING});
	}
}