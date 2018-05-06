package com.crypticmushroom.planetbound.init;

import com.crypticmushroom.planetbound.PBCore;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

//If anyone can find a way to merge this and PGBiomeRegister without crashing, show me
// - Androsa

@SuppressWarnings("all")
@ObjectHolder(PBCore.MOD_ID)
public class PBBiomes {
    @ObjectHolder("red_desert")
    public static final Biome redDesert;

    //This *needs* to be done, or it will error
    static {
        redDesert = null;
    }
}
