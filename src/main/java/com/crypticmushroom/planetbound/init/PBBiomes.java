package com.crypticmushroom.planetbound.init;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.logger.PBLogDev;
import com.crypticmushroom.planetbound.world.biome.*;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = PBCore.MOD_ID)
@ObjectHolder(PBCore.MOD_ID)
public class PBBiomes
{
    public static final Biome red_desert = new BiomeRedDesert(new BiomeProperties("Red Desert")
            .setRainDisabled()
            .setRainfall(0)
            .setTemperature(0.7F)
            .setHeightVariation(0.1F)
            .setBaseHeight(0.05F));
    public static final Biome emberwood_forest = new BiomeEmberwoodForest(new BiomeProperties("Emberwood Forest")
            .setRainDisabled()
            .setRainfall(0)
            .setTemperature(0.7F)
            .setHeightVariation(0.1F)
            .setBaseHeight(0.05F));
    public static final Biome ronne_mountains = new BiomeRonneMountains(new BiomeProperties("Ronne Mountains")
            .setRainDisabled()
            .setRainfall(0)
            .setTemperature(0.55F)
            .setHeightVariation(1.3F)
            .setBaseHeight(1.05F));
    public static final Biome ronne_foothills = new BiomeRonneMountains(new BiomeProperties("Ronne Foothills")
            .setRainDisabled()
            .setRainfall(0)
            .setTemperature(0.55F)
            .setHeightVariation(0.2F)
            .setBaseHeight(0.5F));
    public static final Biome ronne_grasslands = new BiomeRonneGrasslands(new BiomeProperties("Ronne Grasslands")
            .setRainDisabled()
            .setRainfall(0)
            .setTemperature(0.6F)
            .setHeightVariation(0.1F)
            .setBaseHeight(0.05F));
    public static final Biome ronne_plains = new BiomeRonnePlains(new BiomeProperties("Ronne Plains")
            .setRainDisabled()
            .setRainfall(0)
            .setTemperature(0.65F)
            .setHeightVariation(0.05F)
            .setBaseHeight(0.05F));

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event)
    {
        final BiomeRegistry biomes = new BiomeRegistry(event.getRegistry());

        biomes.register("red_desert", red_desert, BiomeDictionary.Type.DRY, BiomeDictionary.Type.WASTELAND);
        biomes.register("emberwood_forest", emberwood_forest, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HOT);
        biomes.register("ronne_mountains", ronne_mountains, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.MOUNTAIN);
        biomes.register("ronne_foothills", ronne_foothills, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.HILLS);
        biomes.register("ronne_grasslands", ronne_grasslands, BiomeDictionary.Type.DRY, BiomeDictionary.Type.PLAINS);
        biomes.register("ronne_plains", ronne_plains, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SPARSE);

        PBLogDev.printInfo("Registered PlanetBound biomes...");
    }

    private static class BiomeRegistry
    {
        private final IForgeRegistry<Biome> registry;

        BiomeRegistry(IForgeRegistry<Biome> registry)
        {
            this.registry = registry;
        }

        /**
         * @param name The name of the biome.
         * @param biome The biome field. Before the biome can be used in this method, it must be declared.
         * @param biomeTypes The type or types this biome falls under. If there is nothing here, it will be assumed.
         *                   Still, best to have something here than have it as nothing
         */
        public void register(String name, Biome biome, BiomeDictionary.Type... biomeTypes)
        {
            biome.setRegistryName(PBCore.MOD_ID, name);
            registry.register(biome);
            BiomeDictionary.addTypes(biome, biomeTypes);
        }
    }
}
