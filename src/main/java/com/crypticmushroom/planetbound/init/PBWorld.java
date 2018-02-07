package com.crypticmushroom.planetbound.init;

import com.crypticmushroom.planetbound.world.WorldProviderRonne;
import com.crypticmushroom.planetbound.world.gen.PBOreGenerator;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PBWorld
{
    public static final int RONNE_ID = 4; //TODO make this configurable
    
    public static final DimensionType RONNE = DimensionType.register("ronne", "ronne", RONNE_ID, WorldProviderRonne.class, false);
    
    public static void init()
    {
        GameRegistry.registerWorldGenerator(new PBOreGenerator(), 0);
        
        DimensionManager.registerDimension(RONNE_ID, RONNE);
    }
}