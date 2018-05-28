package com.crypticmushroom.planetbound.blocks;

import java.util.Random;

import com.crypticmushroom.planetbound.init.PBWorld;
import com.crypticmushroom.planetbound.player.PBPlayer;
import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Rift extends BlockPortal implements PBBlock {

	private Block frameBlock;

	public Rift(Block frame) {
		frameBlock = frame;

		setHardness(-1.0F);
		setSoundType(SoundType.GLASS);
		setLightLevel(0.75F);
		this.setTickRandomly(true);
	}

	@Override
	public Planet[] getPlanets() {
		return new Planet[0];
	}

	@Override
	public int tickRate(World world) {
		return 50;
	}

	@Override
	public void randomTick(World world, BlockPos pos, IBlockState state, Random rand) {
		EnumFacing.Axis axis = state.getValue(AXIS);

		Rift.Size size = new Rift.Size(world, pos, axis);

		com.crypticmushroom.planetbound.logger.PBLogDev.logDev.info("Checking portal");
		
		if (!size.isValid()) {
			BlockPos.getAllInBox(size.bottomLeft, size.topRight)
					.forEach(world::setBlockToAir);
		}

		// removePortal(world, pos, state);
	}

	/**
	 * This has possibly the greatest particle effect of almost any block
	 * in any mod; certainly the best one ive ever created :)
	 * - Raptor
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {

		final EnumParticleTypes PARTICLE = EnumParticleTypes.END_ROD;
		final int[] PARAMS = {};// {Block.getStateId(stateIn)};
		
		// Plays the portal sound
		if (rand.nextInt(100) == 0) {
			worldIn.playSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT,
					SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
		}
		
		// Random particles within the middle of the portal
		int count = rand.nextInt(5);

		for (int i = 0; i < count; i++) {
			double x = pos.getX() + rand.nextDouble();
			double y = pos.getY() + rand.nextDouble();
			double z = pos.getZ() + rand.nextDouble();

			worldIn.spawnParticle(PARTICLE, x, y, z, 0, 0, 0, PARAMS);
		}

		// manages the edges
		BlockPos posLeft, posRight, posUp = pos.up(), posDown = pos.down();

		EnumFacing.Axis axis = stateIn.getValue(AXIS);

		double xRight, zLeft, xLeft, zRight;

		switch (axis) {
		case X:
			posLeft = pos.east();
			posRight = pos.west();
			xLeft = (xRight = pos.getX()) + 1;
			zLeft = zRight = pos.getZ() + 0.5;
			break;
		case Z:
			posLeft = pos.north();
			posRight = pos.south();
			zRight = (zLeft = pos.getZ()) + 1;
			xRight = xLeft = pos.getX() + 0.5;
			break;
		default:
			return;
		}

		IBlockState left, right, up, down;
		left = worldIn.getBlockState(posLeft);
		right = worldIn.getBlockState(posRight);
		up = worldIn.getBlockState(posUp);
		down = worldIn.getBlockState(posDown);

		boolean doLeft = left.getMaterial() == Material.AIR || left.getBlock() == this && left.getValue(AXIS) != axis;
		boolean doRight = right.getMaterial() == Material.AIR || right.getBlock() == this && right.getValue(AXIS) != axis;
		boolean doUp = up.getMaterial() == Material.AIR || up.getBlock() == this && up.getValue(AXIS) != axis;
		boolean doDown = down.getMaterial() == Material.AIR || down.getBlock() == this && down.getValue(AXIS) != axis;
		boolean portalUp = up.getBlock() != this;
		double increment = 0.1;

		// particles on left and right sides
		for (double y = pos.getY(); y < pos.getY() + 1; y += increment) {
			if (doLeft)
				worldIn.spawnParticle(PARTICLE, xLeft, y, zLeft, 0, 0, 0, PARAMS);
			if (doRight)
				worldIn.spawnParticle(PARTICLE, xRight, y, zRight, 0, 0, 0, PARAMS);
		}

		// particles on top and bottom
		if (doUp || doDown || portalUp) {
			double yUp = pos.getY() + 1;
			switch (axis) {
			case X:
				for (double x = xRight; x < xLeft; x += increment) {
					if (doUp) 
						worldIn.spawnParticle(PARTICLE, x, yUp, zLeft, 0, 0, 0, PARAMS);
					if (portalUp) //the particles falling on the outside
						worldIn.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, x, yUp, zLeft, 0, 0, 0, PARAMS);
					if (doDown)
						worldIn.spawnParticle(PARTICLE, x, pos.getY(), zLeft, 0, 0, 0, PARAMS);
				}
				break;
			case Z:
				for (double z = zLeft; z < zRight; z += increment) {
					if (doUp)
						worldIn.spawnParticle(PARTICLE, xLeft, yUp, z, 0, 0, 0, PARAMS);
					if (portalUp) //the particles falling on the outside
						worldIn.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, xLeft, yUp, z, 0, 0, 0, PARAMS);
					if (doDown)
						worldIn.spawnParticle(PARTICLE, xLeft, pos.getY(), z, 0, 0, 0, PARAMS);
				}
			default:
			}
		}
	}

	/**
	 * This code is from {@link BlockPortal} but is probably
	 * useless because the model for the Rift block is 2D
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess world, BlockPos pos,
			EnumFacing side) {
		pos = pos.offset(side);
		EnumFacing.Axis axis = null;

		if (blockState.getBlock() == this) {
			axis = blockState.getValue(AXIS);

			if (axis == null) {
				return false;
			}

			if (axis == EnumFacing.Axis.Z && side != EnumFacing.EAST && side != EnumFacing.WEST) {
				// IBlockState state = world.getBlockState(pos.offset(side));
				// return !state.isOpaqueCube() && state.getBlock() != this;
				return false;
			}

			if (axis == EnumFacing.Axis.X && side != EnumFacing.SOUTH && side != EnumFacing.NORTH) {
				// IBlockState state = world.getBlockState(pos.offset(side));
				// return !state.isOpaqueCube() && (state.getBlock() != this);
				return false;
			}
		}

		boolean flag = world.getBlockState(pos.west()).getBlock() == this
				&& world.getBlockState(pos.west(2)).getBlock() != this;
		boolean flag1 = world.getBlockState(pos.east()).getBlock() == this
				&& world.getBlockState(pos.east(2)).getBlock() != this;
		boolean flag2 = world.getBlockState(pos.north()).getBlock() == this
				&& world.getBlockState(pos.north(2)).getBlock() != this;
		boolean flag3 = world.getBlockState(pos.south()).getBlock() == this
				&& world.getBlockState(pos.south(2)).getBlock() != this;
		boolean flag4 = flag || flag1 || axis == EnumFacing.Axis.X;
		boolean flag5 = flag2 || flag3 || axis == EnumFacing.Axis.Z;

		if (flag4 && side == EnumFacing.WEST) {
			return true;
		} else if (flag4 && side == EnumFacing.EAST) {
			return true;
		} else if (flag5 && side == EnumFacing.NORTH) {
			return true;
		} else {
			return flag5 && side == EnumFacing.SOUTH;
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;

			PBPlayer.get(player).transferToDimension(player.dimension == PBWorld.RONNE_ID ? 0 : PBWorld.RONNE_ID);
		}
	}

	@Override
	public boolean trySpawnPortal(World worldIn, BlockPos pos) {
		//Rifts don't spawn other side portals
		return true;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		EnumFacing.Axis axis = state.getValue(AXIS);

		Rift.Size size = new Rift.Size(worldIn, pos, axis);

		if (size.portalBlockCount < size.width * size.height)
			worldIn.setBlockToAir(pos);
	}

	public class Size {
		private final World world;
		private final EnumFacing.Axis axis;
		private final EnumFacing rightDir;
		private final EnumFacing leftDir;
		private int portalBlockCount;
		private BlockPos bottomLeft, topRight;
		private int height;
		private int width;
		private boolean valid = true;

		public Size(World worldIn, BlockPos pos, EnumFacing.Axis axis) {
			this.world = worldIn;
			this.axis = axis;

			if (axis == EnumFacing.Axis.X) {
				this.leftDir = EnumFacing.EAST;
				this.rightDir = EnumFacing.WEST;
			} else {
				this.leftDir = EnumFacing.NORTH;
				this.rightDir = EnumFacing.SOUTH;
			}

			for (BlockPos blockpos = pos; pos.getY() > blockpos.getY() - 21 && pos.getY() > 0
					&& worldIn.getBlockState(pos.down()).getBlock() == Rift.this; pos = pos.down())
				;
			// pos is now position lowest portal block

			int i = this.getDistanceUntilEdge(pos, this.leftDir) - 1;

			if (i >= 0) {
				this.bottomLeft = pos.offset(this.leftDir, i);

				this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);

				if (/*this.width < 2 ||*/ this.width > 21) {
					this.bottomLeft = null;
					this.width = 0;
				}
			}

			if (this.bottomLeft != null) {
				this.height = this.calculatePortalHeight();

				if(this.height > 0)
					this.topRight = this.bottomLeft.offset(this.rightDir, this.width - 1).up(this.height - 1);
			}

		}

		protected int getDistanceUntilEdge(BlockPos start, EnumFacing direction) {
			int i;

			for (i = 0; i < 22; ++i) {
				BlockPos blockpos = start.offset(direction, i);

				/*if (!this.isEmptyBlock(this.world.getBlockState(blockpos)) ||
						this.world.getBlockState(blockpos.down()).getBlock() != Rift.this.frameBlock) {
					break;
				}*/
				if (this.world.getBlockState(blockpos).getBlock() != Rift.this)
					break;
			}

			return i;
		}

		public int getHeight() {
			return this.height;
		}

		public int getWidth() {
			return this.width;
		}

		protected int calculatePortalHeight() {
			this.portalBlockCount = 0;
			
			label0: for (this.height = 0; this.height < 21; ++this.height) {
				for (int i = 0; i < this.width; ++i) {
					BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
					IBlockState state = this.world.getBlockState(blockpos);
					Block block = state.getBlock();

					if (block == Rift.this) {
						++this.portalBlockCount;
					} else {
						if(i == 0) {
							state = this.world.getBlockState(blockpos.offset(this.leftDir));
							
							if(state.getBlock() != Rift.this.frameBlock)
								this.valid = false;
							
						}else if(i == this.width - 1) {
							state = this.world.getBlockState(blockpos.offset(this.rightDir));
							
							if(state.getBlock() != Rift.this.frameBlock)
								this.valid = false;
						}
						
						break label0;
					}
				}
			}
			
			for(int i = 0; i < this.width; i++) {
				BlockPos pos = this.bottomLeft.offset(this.rightDir, i);
				IBlockState stateBottom = this.world.getBlockState(pos.down());
				IBlockState stateTop = this.world.getBlockState(pos.up(this.height));
				
				if(stateBottom.getBlock() != Rift.this.frameBlock || stateTop.getBlock() != Rift.this.frameBlock) {
					this.valid = false;
					break;
				}
			}
			

			if (this.height <= 21/* && this.height >= 3*/) {
				return this.height;
			} else {
				this.bottomLeft = null;
				this.width = 0;
				this.height = 0;
				this.valid = false;
				return 0;
			}
		}

		protected boolean isEmptyBlock(IBlockState stateIn) {
			if (stateIn.getMaterial() == Material.AIR)
				return true;
			Block blockIn = stateIn.getBlock();
			return blockIn == Blocks.FIRE || blockIn == Rift.this;
		}

		public boolean isValid() {
			return this.bottomLeft != null && valid;
		}

		/*public void placePortalBlocks() {
			for (int i = 0; i < this.width; ++i) {
				BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);

				for (int j = 0; j < this.height; ++j) {
					this.world.setBlockState(blockpos.up(j),
							Rift.this.getDefaultState().withProperty(AXIS, this.axis), 2);
				}
			}
		}*/
	}
}