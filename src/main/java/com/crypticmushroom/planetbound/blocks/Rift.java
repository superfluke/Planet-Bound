package com.crypticmushroom.planetbound.blocks;

import com.crypticmushroom.planetbound.init.PBWorld;
import com.crypticmushroom.planetbound.player.PBPlayer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Rift extends BlockPortal {
    public Rift() {
        super();

        setHardness(-1.0F);
        setSoundType(SoundType.GLASS);
        setLightLevel(0.75F);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;

            PBPlayer.get(player).transferToDimension(player.dimension == PBWorld.RONNE_ID ? 0 : PBWorld.RONNE_ID);
        }
    }

    //

    @Override
    public boolean trySpawnPortal(World worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {

    }
}