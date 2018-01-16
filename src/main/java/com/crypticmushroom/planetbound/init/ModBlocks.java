package com.crypticmushroom.planetbound.init;

import com.crypticmushroom.planetbound.blocks.GauntletCore;
import com.crypticmushroom.planetbound.blocks.GauntletCoreFrame;
import com.crypticmushroom.planetbound.blocks.GauntletRift;
import com.crypticmushroom.planetbound.blocks.GauntletShell;
import com.crypticmushroom.planetbound.blocks.oreblock.KybriteOreBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.RendiumOreBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.VerdaniteOreBlock;
import com.crypticmushroom.planetbound.blocks.ores.KybriteOre;
import com.crypticmushroom.planetbound.blocks.ores.RendiumOre;
import com.crypticmushroom.planetbound.blocks.ores.VerdaniteOre;

public class ModBlocks
{
    //All the blocks
    public static KybriteOre kybrite_ore;
    public static KybriteOreBlock kybrite_block;
    public static VerdaniteOre verdanite_ore;
    public static VerdaniteOreBlock verdanite_block;
    public static RendiumOre rendium_ore;
    public static RendiumOreBlock rendium_block;
    public static GauntletCore gauntlet_core;
    public static GauntletCoreFrame gauntlet_core_frame;
    public static GauntletShell gauntlet_shell;
    public static GauntletRift rift_gauntlet;
    public static void init()
    {
        kybrite_ore = new KybriteOre();
        kybrite_block = new KybriteOreBlock();
        verdanite_ore = new VerdaniteOre();
        verdanite_block = new VerdaniteOreBlock();
        rendium_ore = new RendiumOre();
    }
}
