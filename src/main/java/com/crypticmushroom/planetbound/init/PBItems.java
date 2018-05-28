package com.crypticmushroom.planetbound.init;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.items.CoreFrame;
import com.crypticmushroom.planetbound.items.GauntletShell;
import com.crypticmushroom.planetbound.items.RendiumCore;
import com.crypticmushroom.planetbound.items.RiftGauntlet;
import com.crypticmushroom.planetbound.items.oreingot.BloodstoneShard;
import com.crypticmushroom.planetbound.items.oreingot.FortiumIngot;
import com.crypticmushroom.planetbound.items.oreingot.HalkirIngot;
import com.crypticmushroom.planetbound.items.oreingot.KybriteIngot;
import com.crypticmushroom.planetbound.items.oreingot.RendiumChunk;
import com.crypticmushroom.planetbound.items.oreingot.RendiumCrystal;
import com.crypticmushroom.planetbound.items.oreingot.VerdaniteIngot;
import com.crypticmushroom.planetbound.logger.PBLogDev;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(modid = PBCore.MOD_ID)
public class PBItems {
	private static final List<Item> items = new ArrayList<>();

	public static Item kybrite_ingot;
	public static Item verdanite_ingot;
	public static Item rendium_chunk;
	public static Item rendium_crystal;
	public static Item core_frame;
	public static Item rendium_core;
	public static Item rift_gauntlet;
	public static Item gauntlet_shell;
	public static Item fortium_ingot;
	public static Item halkir_ingot;
	public static Item bloodstone_shard;

	public static void init() {
		kybrite_ingot = registerItem(new KybriteIngot(), "kybrite_ingot");
		verdanite_ingot = registerItem(new VerdaniteIngot(), "verdanite_ingot");
		rendium_chunk = registerItem(new RendiumChunk(), "rendium_chunk");
		rendium_crystal = registerItem(new RendiumCrystal(), "rendium_crystal");
		core_frame = registerItem(new CoreFrame(), "core_frame");
		rendium_core = registerItem(new RendiumCore(), "rendium_core");
		rift_gauntlet = registerItem(new RiftGauntlet(), "rift_gauntlet");
		gauntlet_shell = registerItem(new GauntletShell(), "gauntlet_shell");
		fortium_ingot = registerItem(new FortiumIngot(), "fortium_ingot");
		halkir_ingot = registerItem(new HalkirIngot(), "halkir_ingot");
		bloodstone_shard = registerItem(new BloodstoneShard(), "bloodstone_shard");
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(PBItems.getItems());
		PBLogDev.printInfo("Registered PlanetBound items...");
	}
	
	protected static <T extends Item> T registerItem(T item, String name) {
		return registerItem(item, name, PBCreativeTabs.TAB_ITEMS);
	}

	protected static <T extends Item> T registerItem(T item, String name, CreativeTabs tab) {
		Validate.notNull(item, "item cannot be null");
		Validate.notNull(name, "name cannot be null");

		if (tab != null) {
			item.setCreativeTab(tab);
		}

		item.setUnlocalizedName(name);
		item.setRegistryName(PBCore.MOD_ID, name);

		items.add(item);

		return item;
	}
	
	/*

	protected static Item registerBlockAsItem(Item item, String name) {
		Validate.notNull(item, "item cannot be null");
		Validate.notNull(name, "name cannot be null");

		item.setUnlocalizedName(name);
		item.setRegistryName(PBCore.MOD_ID, name);

		items.add(item);

		return item;
	}
*/
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for (Item item : items) {
			registerModel(item);
		}
	}

	@SideOnly(Side.CLIENT)
	private static void registerModel(Item item) {
		registerModel(item, 0);
	}

	@SideOnly(Side.CLIENT)
	private static void registerModel(Item item, int metadata) {
		Validate.notNull(item, "item cannot be null");
		ModelLoader.setCustomModelResourceLocation(item, metadata,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	public static Item[] getItems() {
		return items.toArray(new Item[] {});
	}
}