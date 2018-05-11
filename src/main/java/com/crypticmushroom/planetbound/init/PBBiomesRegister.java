package com.crypticmushroom.planetbound.init;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.world.biome.BiomeRedDesert;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = PBCore.MOD_ID)
public final class PBBiomesRegister
{
    @SubscribeEvent
    public static void onRegisterBiomes(Register<Biome> event)
    {
        final BiomeRegistry biomes = new BiomeRegistry(event.getRegistry());

        biomes.register("red_desert", new BiomeRedDesert(new BiomeProperties("Red Desert").setRainDisabled().setRainfall(0).setTemperature(0.7F)), Type.DRY, Type.WASTELAND);
    }

    private static class BiomeRegistry
    {
        private final IForgeRegistry<Biome> registry;

        BiomeRegistry(IForgeRegistry<Biome> registry)
        {
            this.registry = registry;
        }

        public void register(String registryName, Biome biome, Type... biomeTypes)
        {
            biome.setRegistryName(PBCore.MOD_ID, registryName);
            registry.register(biome);
            BiomeDictionary.addTypes(biome, biomeTypes);
        }
    }
}
