package crypticmushroom.planetbound;

import crypticmushroom.planetbound.init.PBBlocks;
import crypticmushroom.planetbound.init.PBItems;
import crypticmushroom.planetbound.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.DimensionType;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = PlanetBound.MODID, 
		name = "PlanetBound", 
		version = PlanetBound.VERSION)

// I see potential in this mod, but you gotta make sure you iron out the basics before doing anything with dimensions.
// Dimensions can get pretty complicated when it comes to modding this stuff. Take it from me and Bailey.
// If you need help with anything in particular talk to me or Dennisz.
// - Jonathan
public class PlanetBound {
	
	public static final String MODID = "planetbound";
	public static final String VERSION = "1.0";

	public static final PBItems items = new PBItems();
	public static final PBBlocks blocks = new PBBlocks();
	public static final String ARMOR_DIR = "planetbound:textures/armor/";
	
	@SidedProxy(clientSide = "crypticmushroom.planetbound.proxy.ClientProxy", serverSide = "crypticmushroom.planetbound.proxy.ServerProxy")
	public static CommonProxy proxy;
	
	public static DimensionType dimType;
	public static int backupdimensionID = -290;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		System.out.println("DIRT BLOCK >> "+Blocks.DIRT.getUnlocalizedName());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}