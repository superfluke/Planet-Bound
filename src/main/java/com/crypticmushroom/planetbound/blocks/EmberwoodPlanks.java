package com.crypticmushroom.planetbound.blocks;

import jdk.nashorn.internal.ir.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class EmberwoodPlanks extends net.minecraft.block.Block {
    public EmberwoodPlanks() {
        super(Material.WOOD);
        this.setSoundType(SoundType.WOOD);
    }
}
