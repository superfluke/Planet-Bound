package com.crypticmushroom.planetbound.init;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.blocks.GauntletCore;
import com.crypticmushroom.planetbound.blocks.GauntletCoreFrame;
import com.crypticmushroom.planetbound.blocks.GauntletShell;
import com.crypticmushroom.planetbound.blocks.oreblock.KybriteOreBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.RendiumOreBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.VerdaniteOreBlock;
import com.crypticmushroom.planetbound.blocks.ores.KybriteOre;
import com.crypticmushroom.planetbound.blocks.ores.RendiumOre;
import com.crypticmushroom.planetbound.blocks.ores.VerdaniteOre;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PBBlocks
{
	private static final List<Block> blocks = new ArrayList<>();
	
    public static Block kybrite_ore;
    public static Block kybrite_block;
    public static Block verdanite_ore;
    public static Block verdanite_block;
    public static Block rendium_ore;
    public static Block rendium_block;
    public static Block gauntlet_core;
    public static Block gauntlet_core_frame;
    public static Block gauntlet_shell;

    public static void init()
    {
        kybrite_ore = registerBlock(new KybriteOre(), "kybrite_ore");
        kybrite_block = registerBlock(new KybriteOreBlock(), "kybrite_block");
        verdanite_ore = registerBlock(new VerdaniteOre(), "verdanite_ore");
        verdanite_block = registerBlock(new VerdaniteOreBlock(), "verdanite_block");
        rendium_ore = registerBlock(new RendiumOre(), "rendium_ore");
        rendium_block = registerBlock(new RendiumOreBlock(), "rendium_block");
        gauntlet_core = registerBlock(new GauntletCore(), "gauntlet_core");
        gauntlet_core_frame = registerBlock(new GauntletCoreFrame(), "gauntlet_core_frame");
        gauntlet_shell = registerBlock(new GauntletShell(), "gauntlet_shell");
    }
    
    private static Block registerBlock(Block block, String name)
    {
    	Validate.notNull(block, "block cannot be null");
    	Validate.notNull(name, "name cannot be null");
    	
    	block.setCreativeTab(PBCreativeTabs.TAB_MAIN); //TODO for testing purposes!
    	
    	block.setUnlocalizedName(name);
    	block.setRegistryName(PBCore.MOD_ID, name);
    	
    	blocks.add(block);
    	
    	PBItems.registerItem(new ItemBlock(block), name);
    	
    	return block;
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerModels()
    {
    	for(Block block : blocks)
    	{
    		registerModel(block);
    	}
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
    	
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), metadata, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
    
    public static Block[] getBlocks()
    {
    	return blocks.toArray(new Block[]{});
    }
}