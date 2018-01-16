package com.crypticmushroom.planetbound.blocks.oreblock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class VerdaniteOreBlock extends Block
{
    public VerdaniteOreBlock()
    {
        super(Material.ROCK, MapColor.GRAY);
        setUnlocalizedName("verdanite_block");
        setRegistryName("verdanite_block");
        setSoundType(SoundType.STONE);
    }
}
