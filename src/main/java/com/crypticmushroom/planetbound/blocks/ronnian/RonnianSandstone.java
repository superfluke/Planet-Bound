package com.crypticmushroom.planetbound.blocks.ronnian;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class RonnianSandstone extends Block {
    public RonnianSandstone() {
        super(Material.ROCK);
        this.setHardness(2);
        setSoundType(SoundType.STONE);
    }
}
