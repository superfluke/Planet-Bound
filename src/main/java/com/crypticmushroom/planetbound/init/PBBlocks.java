package com.crypticmushroom.planetbound.init;

import static com.crypticmushroom.planetbound.init.PBCreativeTabs.TAB_BLOCKS;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.apache.commons.lang3.Validate;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.blocks.EmberwoodLeaves;
import com.crypticmushroom.planetbound.blocks.EmberwoodLog;
import com.crypticmushroom.planetbound.blocks.EmberwoodPlanks;
import com.crypticmushroom.planetbound.blocks.InventorsForge;
import com.crypticmushroom.planetbound.blocks.Puffball;
import com.crypticmushroom.planetbound.blocks.Rift;
import com.crypticmushroom.planetbound.blocks.oreblock.BloodstoneBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.FortiumBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.HalkirBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.KybriteBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.RendiumBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.VerdaniteBlock;
import com.crypticmushroom.planetbound.blocks.ores.BloodstoneOre;
import com.crypticmushroom.planetbound.blocks.ores.HalkirOre;
import com.crypticmushroom.planetbound.blocks.ores.KybriteOre;
import com.crypticmushroom.planetbound.blocks.ores.KybriteOreRonnian;
import com.crypticmushroom.planetbound.blocks.ores.RendiumOre;
import com.crypticmushroom.planetbound.blocks.ores.VerdaniteOre;
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
import com.crypticmushroom.planetbound.world.ColorizerRonnianFoliage;
import com.crypticmushroom.planetbound.world.ColorizerRonnianGrass;
import com.crypticmushroom.planetbound.world.biome.PBBiomeColorHelper;

import net.minecraft.block.Block;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
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
	// Misc
	public static Block puffball_block;

	public static void init()
	{
		// Kybrite
		kybrite_ore = registerBlock(new KybriteOre(), "kybrite_ore");
		kybrite_block = registerBlock(new KybriteBlock(), "kybrite_block");
		// Verdanite
		verdanite_ore = registerBlock(new VerdaniteOre(), "verdanite_ore");
		verdanite_block = registerBlock(new VerdaniteBlock(), "verdanite_block");
		// Rendium
		rendium_ore = registerBlock(new RendiumOre(), "rendium_ore");
		rendium_block = registerBlock(new RendiumBlock(), "rendium_block");
		// Other
		inventors_forge = registerBlock(new InventorsForge(false), "inventors_forge");
		lit_inventors_forge = registerBlock(new InventorsForge(true), "lit_inventors_forge", (CreativeTabs)null);
		fortium_block = registerBlock(new FortiumBlock(), "fortium_block");
		rift = registerBlock(new Rift(), "rift", null);
		// Ronnian Blocks
		ronnian_sand = registerBlock(new RonnianSand(), "scarlet_sand");
		ronnian_sandstone = registerBlock(new RonnianSandstone(), "ronnian_sandstone");
		ronnian_stone = registerBlock(new RonnianStone(), "ronnian_stone");
		ronnian_stone_smooth = registerBlock(new RonnianStoneSmooth(), "ronnian_stone_smooth");
		ronnian_stone_chiseled = registerBlock(new RonnianStoneChiseled(), "ronnian_stone_chiseled");
		ronnian_sandstone_chiseled = registerBlock(new RonnianSandstoneChiseled(), "ronnian_sandstone_chiseled");
		ronnian_sandstone_smooth = registerBlock(new RonnianSandstoneSmooth(), "ronnian_sandstone_smooth");
		ronnian_dirt = registerBlock(new RonnianDirt(), "ronnian_dirt");
		ronnian_coarse_dirt = registerBlock(new RonnianCoarseDirt(), "ronnian_coarse_dirt");
		ronnian_grass = registerBlock(new RonnianGrass(), "ronnian_grass");
		ronnian_tallgrass = registerBlock(new RonnianTallgrass(), "ronnian_tallgrass");
		halkir_ore = registerBlock(new HalkirOre(), "halkir_ore");
		halkir_block = registerBlock(new HalkirBlock(), "halkir_block");
		bloodstone_ore = registerBlock(new BloodstoneOre(), "bloodstone_ore");
		bloodstone_block = registerBlock(new BloodstoneBlock(), "bloodstone_block");
		verdanite_ore_ronnian = registerBlock(new VerdaniteOre(), "verdanite_ore_ronnian");
		kybrite_ore_ronnian = registerBlock(new KybriteOreRonnian(), "kybrite_ore_ronnian");
		rendium_ore_ronnian = registerBlock(new RendiumOre(), "rendium_ore_ronnian");
		// Emberwood
		emberwood_leaves = registerBlock(new EmberwoodLeaves(), "emberwood_leaves");
		emberwood_planks = registerBlock(new EmberwoodPlanks(), "emberwood_planks");
		emberwood = registerBlock(new EmberwoodLog(), "emberwood_log");
		// Misc
		puffball_block = registerBlock(new Puffball(Material.WOOD, MapColor.BLUE, /* TODO add small puffball */ null, SoundType.WOOD).setHardness(0.2F), "blue_puffball_block").setUnlocalizedName("puffball");
	}

	public static void setupColors() {
		final BlockColors bc = Minecraft.getMinecraft().getBlockColors();
		final ItemColors ic = Minecraft.getMinecraft().getItemColors();
		//to be used one day if we want grass to have custom
		//tinting per biome
		//blocks
		bc.registerBlockColorHandler(new IBlockColor() {

			@Override
			public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
				return worldIn != null && pos != null ? PBBiomeColorHelper.getPBGrassColorAtPos(worldIn, pos) : ColorizerRonnianGrass.getGrassColor(0.7D, 0.0D);
			}

		}, ronnian_grass, ronnian_tallgrass);
		bc.registerBlockColorHandler(new IBlockColor()
		{
			@Override
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
			{
				return worldIn != null && pos != null ? PBBiomeColorHelper.getPBFoliageColorAtPos(worldIn, pos) : ColorizerRonnianFoliage.getFoliageColor(0.7D, 0.0D);
			}

		}, emberwood_leaves);

		//items of blocks
		ic.registerItemColorHandler(new IItemColor() {

			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				IBlockState iblockstate = ((ItemBlock)stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
				return bc.colorMultiplier(iblockstate, (IBlockAccess)null, (BlockPos)null, tintIndex);
			}

		}, ronnian_grass, ronnian_tallgrass, emberwood_leaves);
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(PBBlocks.getBlocks());
		PBLogDev.printInfo("Registered PlanetBound blocks...");
	}

	private static <T extends Block> T registerBlock(T block, String name) {
		return registerBlock(block, name, TAB_BLOCKS);
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