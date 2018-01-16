package com.crypticmushroom.planetbound.blocks.oreblock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class KybriteOreBlock extends Block
{
    public KybriteOreBlock()
    {
        super(Material.ROCK, MapColor.GRAY);
        setUnlocalizedName("kybrite_block");
        setRegistryName("kybrite_block");
        setSoundType(SoundType.STONE);
    }
}
