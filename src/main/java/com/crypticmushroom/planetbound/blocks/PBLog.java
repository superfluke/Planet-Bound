package com.crypticmushroom.planetbound.blocks;

import java.util.Arrays;

import com.crypticmushroom.planetbound.world.planet.Planet;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public class PBLog extends BlockLog implements PBBlock
{
    private final Planet[] planets_found_in;

    public PBLog(Planet... planets)
    {
        this.setSoundType(SoundType.WOOD);
        this.setDefaultState(this.getDefaultState().withProperty(LOG_AXIS, EnumAxis.Y));
        this.planets_found_in = planets;
    }

    @Override
    public Planet[] getPlanets()
    {
        return Arrays.copyOf(planets_found_in, planets_found_in.length);
    }

    @Override
    public BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, LOG_AXIS);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = getDefaultState();

        switch (meta & 12)
        {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
        }

        return iblockstate;
    }

    @Override
    @SuppressWarnings("incomplete-switch")
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        switch (state.getValue(LOG_AXIS))
        {
            case X:
                i |= 4;
                break;
            case Z:
                i |= 8;
                break;
            case NONE:
                i |= 12;
        }

        return i;
    }
}