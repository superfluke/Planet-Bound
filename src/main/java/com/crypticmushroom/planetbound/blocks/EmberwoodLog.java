package com.crypticmushroom.planetbound.blocks;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class EmberwoodLog extends BlockRotatedPillar {
    public EmberwoodLog() {
        super(Material.WOOD);
        this.setSoundType(SoundType.WOOD);
    }
}
