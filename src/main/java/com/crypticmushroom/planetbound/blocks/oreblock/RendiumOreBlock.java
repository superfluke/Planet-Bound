package com.crypticmushroom.planetbound.blocks.oreblock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class RendiumOreBlock extends Block
{
    public RendiumOreBlock()
    {
        super(Material.ROCK, MapColor.GRAY);
        setUnlocalizedName("rendium_block");
        setRegistryName("rendium_block");
        setSoundType(SoundType.STONE);
    }
}
