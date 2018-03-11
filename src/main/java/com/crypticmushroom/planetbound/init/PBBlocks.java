package com.crypticmushroom.planetbound.init;

import java.util.ArrayList;
import java.util.List;

import com.crypticmushroom.planetbound.blocks.*;
import com.crypticmushroom.planetbound.blocks.ronnian.*;
import org.apache.commons.lang3.Validate;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.blocks.oreblock.FortiumOreBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.KybriteOreBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.RendiumOreBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.VerdaniteOreBlock;
import com.crypticmushroom.planetbound.blocks.ores.KybriteOre;
import com.crypticmushroom.planetbound.blocks.ores.RendiumOre;
import com.crypticmushroom.planetbound.blocks.ores.VerdaniteOre;
import com.crypticmushroom.planetbound.logger.PBLogDev;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class PBBlocks
{
    private static final List<Block> blocks = new ArrayList<>();

    //Kybrite
    public static Block kybrite_ore;
    public static Block kybrite_block;
    //Verdanite
    public static Block verdanite_ore;
    public static Block verdanite_block;
    //Rendium
    public static Block rendium_ore;
    public static Block rendium_block;
    //Other
    public static Block inventors_forge;
    public static Block lit_inventors_forge;
    public static Block fortium_block;
    public static Block rift;
    //Ronnian Blocks
    public static Block ronnian_sand;
    public static Block ronnian_sandstone;
    public static Block ronnian_stone;
    public static Block ronnian_stone_polished;
    public static Block ronnian_sandstone_chiseled;
    public static Block ronnian_sandstone_smooth;
    //Emberwood
    public static Block emberwood_leaves;
    public static Block emberwood_planks;
    public static Block emberwood;

    public static void init()
    {
        //Kybirte
        kybrite_ore = registerBlock(new KybriteOre(), "kybrite_ore");
        kybrite_block = registerBlock(new KybriteOreBlock(), "kybrite_block");
        //Verdanite
        verdanite_ore = registerBlock(new VerdaniteOre(), "verdanite_ore");
        verdanite_block = registerBlock(new VerdaniteOreBlock(), "verdanite_block");
        //Rendium
        rendium_ore = registerBlock(new RendiumOre(), "rendium_ore");
        rendium_block = registerBlock(new RendiumOreBlock(), "rendium_block");
        //Other
        inventors_forge = registerBlock(new InventorsForge(false), "inventors_forge");
        lit_inventors_forge = registerBlock(new InventorsForge(true), "lit_inventors_forge");
        fortium_block = registerBlock(new FortiumOreBlock(), "fortium_block");
        rift = registerBlock(new Rift(), "rift");
        //Ronian Blocks
        ronnian_sand = registerBlock(new RonnianSand(), "scarlet_sand");
        ronnian_sandstone = registerBlock(new RonnianSandstone(), "ronnian_sandstone");
        ronnian_stone = registerBlock(new RonnianStone(), "ronnian_stone");
        ronnian_stone_polished = registerBlock(new RonnianStonePolished(), "ronnian_stone_polished");
        ronnian_sandstone_chiseled = registerBlock(new RonnianSandstoneChiseled(), "ronnian_sandstone_chiseled");
        ronnian_sandstone_smooth = registerBlock(new RonnianSandstoneSmooth(), "ronnian_sandstone_smooth");
        //Emberwood
        emberwood_leaves = registerBlock(new EmberwoodLeaves(), "emberwood_leaves");
        emberwood_planks = registerBlock(new EmberwoodPlanks(), "emberwood_planks");
        emberwood = registerBlock(new EmberwoodLog(), "emberwood_log");
    }
    
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(PBBlocks.getBlocks());
        PBLogDev.printInfo("Registered PlanetBound blocks...");
    }
    
    private static Block registerBlock(Block block, String name)
    {
        Validate.notNull(block, "block cannot be null");
        Validate.notNull(name, "name cannot be null");
        
        block.setCreativeTab(PBCreativeTabs.TAB_MAIN); //TODO for testing purposes!
                                                       //Yeah so we should probably remove the things that shouldn't be in the tab after we're done with testing purposes.
        
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