package crypticmushroom.planetbound;

import org.apache.logging.log4j.core.Logger;

import crypticmushroom.planetbound.init.PBBlocks;
import crypticmushroom.planetbound.init.PBItems;
import crypticmushroom.planetbound.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = PlanetBound.MODID, name = PlanetBound.MODNAME, version = PlanetBound.MODVERSION, dependencies = "required-after:forge@[14.23.1.2555,)", useMetadata = true)
public class PlanetBound {
	
	public static final String MODID = "PlanetBound";
	public static final String MODNAME = "PlanetBound";
	public static final String MODVERSION = "0.0.1";
	
	@SidedProxy(clientSide = "crypticmushroom.planetbound.proxy.ClientProxy", serverSide = "crypticmushroom.planetbound.proxy.ServerProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance
	public static PlanetBound instance;
	
	public static Logger logger;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = (Logger) event.getModLog();
		proxy.preInit(event);
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
	
	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			PBBlocks.register(event.getRegistry());
		}
		
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			PBItems.register(event.getRegistry());
			PBBlocks.registerItemBlocks(event.getRegistry());
		}
		
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			PBItems.registerItemModel();
			PBBlocks.registerItemModels();
		}
	}

}
