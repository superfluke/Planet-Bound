package com.crypticmushroom.planetbound.init;

import static com.crypticmushroom.planetbound.init.PBCreativeTabs.TAB_BLOCKS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.crypticmushroom.planetbound.blocks.*;
import org.apache.commons.lang3.Validate;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.client.ClientEventHandler;
import com.crypticmushroom.planetbound.logger.PBLogDev;
import com.crypticmushroom.planetbound.world.biome.PBBiomeColorHelper;
import com.crypticmushroom.planetbound.world.gen.PBAmberwoodTreeGen;
import com.crypticmushroom.planetbound.world.gen.PBEmberwoodTreeGen;
import com.crypticmushroom.planetbound.world.planet.Planet;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(modid = PBCore.MOD_ID)
public class PBBlocks {
	private static final List<Block> blocks = new ArrayList<>();

	// Kybrite
	public static PBOre kybrite_ore;
	public static PBOre kybrite_ore_ronnian;
	public static PBStorageBlock kybrite_block;
	// Verdanite
	public static PBOre verdanite_ore;
	public static PBOre verdanite_ore_ronnian;
	public static PBStorageBlock verdanite_block;
	// Rendium
	public static PBOre rendium_ore;
	public static PBOre rendium_ore_ronnian;
	public static PBStorageBlock rendium_block;
	// Other
	public static Block inventors_forge;
	public static Block lit_inventors_forge;
	public static PBStorageBlock fortium_block;
	public static Block rift;
	public static Puffball puffball_block;
	// Ronnian Blocks
	public static PBSand ronnian_sand;
	public static Block ronnian_sandstone;
	public static Block ronnian_stone;
	public static Block ronnian_stone_smooth;
	public static Block ronnian_stone_chiseled;
	public static Block ronnian_sandstone_chiseled;
	public static Block ronnian_sandstone_smooth;
	public static PBDirt ronnian_dirt;
	public static PBDirt ronnian_coarse_dirt;
	public static PBGrass ronnian_grass;
	public static PBTallgrass ronnian_tallgrass;
	// Halkir
	public static PBOre halkir_ore;
	public static PBStorageBlock halkir_block;
	// Bloodstone
	public static PBOre bloodstone_ore;
	public static PBStorageBlock bloodstone_block;
	// Emberwood
	public static PBSapling emberwood_sapling;
	public static PBLeaves emberwood_leaves;
	public static Block emberwood_planks;
	public static Block emberwood;
	public static Block emberwood_crafting_table;
	// Amberwood
	public static PBSapling amberwood_sapling;
	public static PBLeaves amberwood_leaves;
	public static Block amberwood_planks;
	public static Block amberwood;

	public static void init() {
		// Kybrite
		kybrite_ore = registerBlock(new PBOre(), "kybrite_ore");
		kybrite_block = registerBlock(new PBStorageBlock(MapColor.BLACK), "kybrite_block");
		// Verdanite
		verdanite_ore = registerBlock(new PBOre(), "verdanite_ore");
		verdanite_block = registerBlock(
				new PBStorageBlock(/* TODO maybe add custom map color for this block */MapColor.LIME),
				"verdanite_block");
		// Rendium
		rendium_ore = registerBlock(new RendiumOre(), "rendium_ore");
		rendium_block = registerBlock(new PBStorageBlock(MapColor.YELLOW), "rendium_block");
		// Other
		inventors_forge = registerBlock(new InventorsForge(false), "inventors_forge");
		lit_inventors_forge = registerBlock(new InventorsForge(true), "lit_inventors_forge", (CreativeTabs)null, false);
		fortium_block = registerBlock(new PBStorageBlock(MapColor.GREEN_STAINED_HARDENED_CLAY), "fortium_block");
		rift = registerBlock(new Rift(Blocks.IRON_BLOCK), "rift", (CreativeTabs)null, false);
		puffball_block = (Puffball)registerBlock(new Puffball(Material.WOOD, MapColor.BLUE,
				/* TODO add small puffball */ Item.getItemFromBlock(Blocks.BROWN_MUSHROOM)),
				"blue_puffball_block").setUnlocalizedName("puffball");
		// Ronnian Blocks
		ronnian_sand = registerBlock(new PBSand(MapColor.RED, PBPlanets.RONNE), "scarlet_sand");
		ronnian_sandstone = registerBlock(createStone(PBPlanets.RONNE), "ronnian_sandstone");
		ronnian_stone = registerBlock(createStone(PBPlanets.RONNE), "ronnian_stone");
		ronnian_stone_smooth = registerBlock(createStone(PBPlanets.RONNE), "ronnian_stone_smooth");
		ronnian_stone_chiseled = registerBlock(createStone(PBPlanets.RONNE), "ronnian_stone_chiseled");
		ronnian_sandstone_chiseled = registerBlock(createStone(PBPlanets.RONNE), "ronnian_sandstone_chiseled");
		ronnian_sandstone_smooth = registerBlock(createStone(PBPlanets.RONNE), "ronnian_sandstone_smooth");
		ronnian_dirt = registerBlock(new PBDirt(MapColor.RED_STAINED_HARDENED_CLAY, PBPlanets.RONNE), "ronnian_dirt");
		ronnian_coarse_dirt = registerBlock(new PBDirt(MapColor.RED, PBPlanets.RONNE),
				"ronnian_coarse_dirt");
		ronnian_grass = registerBlock(new PBGrass(ronnian_dirt, MapColor.RED_STAINED_HARDENED_CLAY), "ronnian_grass");
		ronnian_tallgrass = registerBlock(new PBTallgrass(MapColor.RED_STAINED_HARDENED_CLAY, PBPlanets.RONNE), "ronnian_tallgrass");
		halkir_ore = registerBlock(new PBOre(), "halkir_ore");
		halkir_block = registerBlock(new PBStorageBlock(MapColor.GRAY), "halkir_block");
		bloodstone_ore = registerBlock(new BloodstoneOre(PBPlanets.RONNE), "bloodstone_ore");
		bloodstone_block = registerBlock(new PBStorageBlock(MapColor.RED, PBPlanets.RONNE), "bloodstone_block");
		verdanite_ore_ronnian = registerBlock(new PBOre(PBPlanets.RONNE), "verdanite_ore_ronnian");
		kybrite_ore_ronnian = registerBlock(new PBOre(PBPlanets.RONNE), "kybrite_ore_ronnian");
		rendium_ore_ronnian = registerBlock(new RendiumOre(PBPlanets.RONNE), "rendium_ore_ronnian");
		// Emberwood
		emberwood_sapling = registerBlock(new PBSapling(new PBEmberwoodTreeGen(true), PBPlanets.RONNE), "emberwood_sapling");
		emberwood_leaves = registerBlock(new PBLeaves(emberwood_sapling, PBPlanets.RONNE), "emberwood_leaves");
		emberwood_planks = registerBlock(createPlanks(PBPlanets.RONNE), "emberwood_planks");
		emberwood = registerBlock(new PBLog(PBPlanets.RONNE), "emberwood_log");
		emberwood_crafting_table = registerBlock(new PBWorkbench(PBPlanets.RONNE), "emberwood_crafting_table");
		// Amberwood
		amberwood_sapling = registerBlock(new PBSapling(new PBAmberwoodTreeGen(true), PBPlanets.RONNE), "amberwood_sapling");
		amberwood_leaves = registerBlock(new PBLeaves(amberwood_sapling, PBPlanets.RONNE), "amberwood_leaves");
		amberwood_planks = registerBlock(createPlanks(PBPlanets.RONNE), "amberwood_planks");
		amberwood = registerBlock(new PBLog(PBPlanets.RONNE), "amberwood_log");
	}

	private static PBBlockBasic createStone(Planet... planetsIn) {
		return (PBBlockBasic)new PBBlockBasic(Material.ROCK, planetsIn).setHardness(2.0f);
	}

	private static PBBlockBasic createPlanks(Planet... planetsIn) {
		return (PBBlockBasic)new PBBlockBasic(Material.WOOD).setSoundType(SoundType.WOOD).setHardness(2.0F)
				.setResistance(5.0F);
	}

	public static void setupColors() {
		final BlockColors bc = Minecraft.getMinecraft().getBlockColors();
		final ItemColors ic = Minecraft.getMinecraft().getItemColors();
		
		class GrassBlockColor implements IBlockColor {
			private Planet planet;
			
			GrassBlockColor(Planet planet){
				this.planet = planet;
			}
			
			@Override
			public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
				return worldIn != null && pos != null? PBBiomeColorHelper.getPBGrassColorAtPos(worldIn, pos)
						: planet != null? planet.grassColorizer.getFloraColor(0.7, 0.0)
						: ColorizerGrass.getGrassColor(0.7, 0.0);
			}

		}
		
		class FoliageBlockColor implements IBlockColor {
			private Planet planet;
			
			FoliageBlockColor(Planet planet){
				this.planet = planet;
			}
			@Override
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos,
					int tintIndex) {
				return worldIn != null && pos != null? PBBiomeColorHelper.getPBFoliageColorAtPos(worldIn, pos)
						: planet != null? planet.foliageColorizer.getFloraColor(0.7, 0.0)
						: ColorizerFoliage.getFoliageColor(0.7, 0.0);
			}

		}
		
		final IItemColor itemColor = new IItemColor() {

			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				IBlockState iblockstate = ((ItemBlock)stack.getItem()).getBlock()
						.getStateFromMeta(stack.getMetadata());
				return bc.colorMultiplier(iblockstate, (IBlockAccess)null, (BlockPos)null, tintIndex);
			}

		};
		
		Map<Planet, IBlockColor> definedGrassColors = Maps.newHashMap(),
								definedFoliageColors = Maps.newHashMap();
		
		for(Block block : blocks) {
			label0: if(block instanceof PBBlockTinted) {
				PBBlockTinted pbBlock = (PBBlockTinted)block;
				IBlockColor blockColor;
				Planet[] planets = pbBlock.getPlanets();
				if(planets.length > 0) {
					Planet planet = planets[0];
		
					switch(pbBlock.getTintType()) {
					case GRASS:
						if((blockColor = definedGrassColors.get(planet)) == null) {
							blockColor = new GrassBlockColor(planet);
							definedGrassColors.put(planet, blockColor);
						}
						break;
					case FOLIAGE:
						if((blockColor = definedFoliageColors.get(planet)) == null) {
							blockColor = new FoliageBlockColor(planet);
							definedFoliageColors.put(planet, blockColor);
						}
						break;
					default:
						break label0;
					}					
				}else {
					break label0;
				}
				
				bc.registerBlockColorHandler(blockColor, block);
				ic.registerItemColorHandler(itemColor, block);
			}
		}
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(PBBlocks.getBlocks());
		PBLogDev.printInfo("Registered PlanetBound blocks...");
	}

	private static <T extends Block> T registerBlock(T block, String name) {
		return registerBlock(block, name, TAB_BLOCKS);
	}
	
	private static <T extends Block> T registerBlock(T block, String name, CreativeTabs tab) {
		return registerBlock(block, name, tab, true);
	}

	private static <T extends Block> T registerBlock(T block, String name, CreativeTabs tab, boolean registerItem) {
		Validate.notNull(block, "block cannot be null");
		Validate.notNull(name, "name cannot be null");

		if(tab != null) {
			block.setCreativeTab(tab);
		}

		block.setUnlocalizedName(name);
		block.setRegistryName(PBCore.MOD_ID, name);

		blocks.add(block);
		
		if(block instanceof BlockLeaves) {
			ClientEventHandler.LEAF_BLOX.add((BlockLeaves)block);
		}

		if(registerItem)
			PBItems.registerItem(new ItemBlock(block), name, (CreativeTabs)null);

		return block;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		StateMap leavesStateMap = new StateMap.Builder().ignore(PBLeaves.CHECK_DECAY, PBLeaves.DECAYABLE).build();
		StateMap saplingStateMap = new StateMap.Builder().ignore(PBSapling.STAGE).build();
		for(Block block : blocks) {
			registerModel(block);
			
			if(block instanceof PBLeaves)
				ModelLoader.setCustomStateMapper(block, leavesStateMap);
			else if(block instanceof PBSapling)
				ModelLoader.setCustomStateMapper(block, saplingStateMap);
		}
	}

	@SideOnly(Side.CLIENT)
	private static void registerModel(Block block) {
		registerModel(block, 0);
	}

	@SideOnly(Side.CLIENT)
	private static void registerModel(Block block, int metadata) {
		Validate.notNull(block, "block cannot be null");

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), metadata,
				new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

	public static Block[] getBlocks() {
		return blocks.toArray(new Block[blocks.size()]);
	}
}