package com.crypticmushroom.planetbound.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class GauntletCore extends Block
{
    public GauntletCore()
    {
        super(Material.ROCK, MapColor.GRAY);
        setUnlocalizedName("gauntlet_core");
        setRegistryName("gauntlet_core");
        setSoundType(SoundType.STONE);
    }
}
