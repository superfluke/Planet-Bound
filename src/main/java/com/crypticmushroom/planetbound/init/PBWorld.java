package com.crypticmushroom.planetbound.init;

import com.crypticmushroom.planetbound.config.ConfigHandler;
import com.crypticmushroom.planetbound.world.WorldProviderRonne;
import com.crypticmushroom.planetbound.world.biome.BiomeRedDesert;
import com.crypticmushroom.planetbound.world.gen.PBOreGenerator;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class PBWorld {
    public static final int RONNE_ID = ConfigHandler.dimension.dimensionIDRonne;

    public static final DimensionType RONNE = DimensionType.register("ronne", "ronne", RONNE_ID, WorldProviderRonne.class, false);

    public static void init() {
        GameRegistry.registerWorldGenerator(new PBOreGenerator(), 0);

        DimensionManager.registerDimension(RONNE_ID, RONNE);
    }
}