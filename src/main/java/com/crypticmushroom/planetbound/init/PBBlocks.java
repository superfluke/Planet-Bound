package com.crypticmushroom.planetbound.init;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.blocks.EmberwoodLeaves;
import com.crypticmushroom.planetbound.blocks.EmberwoodLog;
import com.crypticmushroom.planetbound.blocks.EmberwoodPlanks;
import com.crypticmushroom.planetbound.blocks.InventorsForge;
import com.crypticmushroom.planetbound.blocks.Rift;
import com.crypticmushroom.planetbound.blocks.oreblock.FortiumOreBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.KybriteOreBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.RendiumOreBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.VerdaniteOreBlock;
import com.crypticmushroom.planetbound.blocks.ores.BloodstoneOre;
import com.crypticmushroom.planetbound.blocks.ores.HalkirOre;
import com.crypticmushroom.planetbound.blocks.ores.KybriteOre;
import com.crypticmushroom.planetbound.blocks.ores.KybriteOreRonnian;
import com.crypticmushroom.planetbound.blocks.ores.RendiumOre;
import com.crypticmushroom.planetbound.blocks.ores.RendiumOreRonnian;
import com.crypticmushroom.planetbound.blocks.ores.VerdaniteOre;
import com.crypticmushroom.planetbound.blocks.ores.VerdaniteOreRonnian;
import com.crypticmushroom.planetbound.blocks.ronnian.RonnianCoarseDirt;
import com.crypticmushroom.planetbound.blocks.ronnian.RonnianDirt;
import com.crypticmushroom.planetbound.blocks.ronnian.RonnianGrass;
import com.crypticmushroom.planetbound.blocks.ronnian.RonnianSand;
import com.crypticmushroom.planetbound.blocks.ronnian.RonnianSandstone;
import com.crypticmushroom.planetbound.blocks.ronnian.RonnianSandstoneChiseled;
import com.crypticmushroom.planetbound.blocks.ronnian.RonnianSandstoneSmooth;
import com.crypticmushroom.planetbound.blocks.ronnian.RonnianStone;
import com.crypticmushroom.planetbound.blocks.ronnian.RonnianStoneChiseled;
import com.crypticmushroom.planetbound.blocks.ronnian.RonnianStoneSmooth;
import com.crypticmushroom.planetbound.blocks.ronnian.RonnianTallgrass;
import com.crypticmushroom.planetbound.logger.PBLogDev;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(modid = PBCore.MOD_ID)
public class PBBlocks
{
	private static final List<Block> blocks = new ArrayList<>();

	// Kybrite
	public static Block kybrite_ore;
	public static Block kybrite_ore_ronnian;
	public static Block kybrite_block;
	// Verdanite
	public static Block verdanite_ore;
	public static Block verdanite_ore_ronnian;
	public static Block verdanite_block;
	// Rendium
	public static Block rendium_ore;
	public static Block rendium_ore_ronnian;
	public static Block rendium_block;
	// Other
	public static Block inventors_forge;
	public static Block lit_inventors_forge;
	public static Block fortium_block;
	public static Block rift;
	// Ronnian Blocks
	public static Block ronnian_sand;
	public static Block ronnian_sandstone;
	public static Block ronnian_stone;
	public static Block ronnian_stone_smooth;
	public static Block ronnian_stone_chiseled;
	public static Block ronnian_sandstone_chiseled;
	public static Block ronnian_sandstone_smooth;
	public static Block ronnian_dirt;
	public static Block ronnian_coarse_dirt;
	public static Block ronnian_grass;
	public static Block ronnian_tallgrass;
	// Halkir
	public static Block halkir_ore;
	public static Block halkir_block;
	//Bloodstone
	public static Block bloodstone_ore;
	public static Block bloodstone_block;
	// Emberwood
	public static EmberwoodLeaves emberwood_leaves;
	public static Block emberwood_planks;
	public static Block emberwood;

	public static void init()
	{
		// Kybrite
		kybrite_ore = registerBlock(new KybriteOre(), "kybrite_ore", PBCreativeTabs.TAB_BLOCKS);
		kybrite_block = registerBlock(new KybriteOreBlock(), "kybrite_block", PBCreativeTabs.TAB_BLOCKS);
		// Verdanite
		verdanite_ore = registerBlock(new VerdaniteOre(), "verdanite_ore", PBCreativeTabs.TAB_BLOCKS);
		verdanite_block = registerBlock(new VerdaniteOreBlock(), "verdanite_block", PBCreativeTabs.TAB_BLOCKS);
		// Rendium
		rendium_ore = registerBlock(new RendiumOre(), "rendium_ore", PBCreativeTabs.TAB_BLOCKS);
		rendium_block = registerBlock(new RendiumOreBlock(), "rendium_block", PBCreativeTabs.TAB_BLOCKS);
		// Other
		inventors_forge = registerBlock(new InventorsForge(false), "inventors_forge", PBCreativeTabs.TAB_BLOCKS);
		lit_inventors_forge = registerBlock(new InventorsForge(true), "lit_inventors_forge", null);
		fortium_block = registerBlock(new FortiumOreBlock(), "fortium_block", PBCreativeTabs.TAB_BLOCKS);
		rift = registerBlock(new Rift(), "rift", null);
		// Ronnian Blocks
		ronnian_sand = registerBlock(new RonnianSand(), "scarlet_sand", PBCreativeTabs.TAB_BLOCKS);
		ronnian_sandstone = registerBlock(new RonnianSandstone(), "ronnian_sandstone", PBCreativeTabs.TAB_BLOCKS);
		ronnian_stone = registerBlock(new RonnianStone(), "ronnian_stone", PBCreativeTabs.TAB_BLOCKS);
		ronnian_stone_smooth = registerBlock(new RonnianStoneSmooth(), "ronnian_stone_smooth", PBCreativeTabs.TAB_BLOCKS);
		ronnian_stone_chiseled = registerBlock(new RonnianStoneChiseled(), "ronnian_stone_chiseled", PBCreativeTabs.TAB_BLOCKS);
		ronnian_sandstone_chiseled = registerBlock(new RonnianSandstoneChiseled(), "ronnian_sandstone_chiseled", PBCreativeTabs.TAB_BLOCKS);
		ronnian_sandstone_smooth = registerBlock(new RonnianSandstoneSmooth(), "ronnian_sandstone_smooth", PBCreativeTabs.TAB_BLOCKS);
		ronnian_dirt = registerBlock(new RonnianDirt(), "ronnian_dirt", PBCreativeTabs.TAB_BLOCKS);
		ronnian_coarse_dirt = registerBlock(new RonnianCoarseDirt(), "ronnian_coarse_dirt", PBCreativeTabs.TAB_BLOCKS);
		ronnian_grass = registerBlock(new RonnianGrass(), "ronnian_grass", PBCreativeTabs.TAB_BLOCKS);
		ronnian_tallgrass = registerBlock(new RonnianTallgrass(), "ronnian_tallgrass", PBCreativeTabs.TAB_BLOCKS);
		halkir_ore = registerBlock(new HalkirOre(), "halkir_ore", PBCreativeTabs.TAB_BLOCKS);
		bloodstone_ore = registerBlock(new BloodstoneOre(), "bloodstone_ore", PBCreativeTabs.TAB_BLOCKS);
		verdanite_ore_ronnian = registerBlock(new VerdaniteOreRonnian(), "verdanite_ore_ronnian", PBCreativeTabs.TAB_BLOCKS);
		kybrite_ore_ronnian = registerBlock(new KybriteOreRonnian(), "kybrite_ore_ronnian", PBCreativeTabs.TAB_BLOCKS);
		rendium_ore_ronnian = registerBlock(new RendiumOreRonnian(), "rendium_ore_ronnian", PBCreativeTabs.TAB_BLOCKS);
		// Emberwood
		emberwood_leaves = registerBlock(new EmberwoodLeaves(), "emberwood_leaves", PBCreativeTabs.TAB_BLOCKS);
		emberwood_planks = registerBlock(new EmberwoodPlanks(), "emberwood_planks", PBCreativeTabs.TAB_BLOCKS);
		emberwood = registerBlock(new EmberwoodLog(), "emberwood_log", PBCreativeTabs.TAB_BLOCKS);
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(PBBlocks.getBlocks());
		PBLogDev.printInfo("Registered PlanetBound blocks...");
	}

	private static <T extends Block> T registerBlock(T block, String name, CreativeTabs tab)
	{
		Validate.notNull(block, "block cannot be null");
		Validate.notNull(name, "name cannot be null");

		if(tab != null)
		{
			block.setCreativeTab(tab);
		}

		block.setUnlocalizedName(name);
		block.setRegistryName(PBCore.MOD_ID, name);

		blocks.add(block);

		PBItems.registerBlockAsItem(new ItemBlock(block), name);

		return block;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		for(Block block : blocks)
		{
			registerModel(block);
		}
		ModelLoader.setCustomStateMapper(emberwood_leaves, new StateMap.Builder().ignore(EmberwoodLeaves.CHECK_DECAY, EmberwoodLeaves.DECAYABLE).build());
	}

	@SideOnly(Side.CLIENT)
	private static void registerModel(Block block)
	{
		registerModel(block, 0);
	}

	@SideOnly(Side.CLIENT)
	private static void registerModel(Block block, int metadata)
	{
		Validate.notNull(block, "block cannot be null");

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), metadata, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

	public static Block[] getBlocks()
	{
		return blocks.toArray(new Block[]{});
	}
}