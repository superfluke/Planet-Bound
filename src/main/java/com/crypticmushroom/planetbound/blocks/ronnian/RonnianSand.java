package com.crypticmushroom.planetbound.blocks.ronnian;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;

public class RonnianSand extends BlockFalling {
    public RonnianSand() {
        setHardness(0.5F);
        setSoundType(SoundType.SAND);
    }
}