package com.crypticmushroom.planetbound.init;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.blocks.*;
import com.crypticmushroom.planetbound.blocks.oreblock.FortiumOreBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.KybriteOreBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.RendiumOreBlock;
import com.crypticmushroom.planetbound.blocks.oreblock.VerdaniteOreBlock;
import com.crypticmushroom.planetbound.blocks.ores.KybriteOre;
import com.crypticmushroom.planetbound.blocks.ores.RendiumOre;
import com.crypticmushroom.planetbound.blocks.ores.VerdaniteOre;
import com.crypticmushroom.planetbound.blocks.ronnian.*;
import com.crypticmushroom.planetbound.logger.PBLogDev;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = PBCore.MOD_ID)
public class PBBlocks
{
    private static final List<Block> blocks = new ArrayList<>();

    // Kybrite
    public static Block kybrite_ore;
    public static Block kybrite_block;
    // Verdanite
    public static Block verdanite_ore;
    public static Block verdanite_block;
    // Rendium
    public static Block rendium_ore;
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
    public static Block ronnian_stone_polished;
    public static Block ronnian_sandstone_chiseled;
    public static Block ronnian_sandstone_smooth;
    // Emberwood
    public static EmberwoodLeaves emberwood_leaves;
    public static Block emberwood_planks;
    public static Block emberwood;

    public static void init()
    {
        // Kybirte
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
        // Ronian Blocks
        ronnian_sand = registerBlock(new RonnianSand(), "scarlet_sand", PBCreativeTabs.TAB_BLOCKS);
        ronnian_sandstone = registerBlock(new RonnianSandstone(), "ronnian_sandstone", PBCreativeTabs.TAB_BLOCKS);
        ronnian_stone = registerBlock(new RonnianStone(), "ronnian_stone", PBCreativeTabs.TAB_BLOCKS);
        ronnian_stone_polished = registerBlock(new RonnianStonePolished(), "ronnian_stone_polished", PBCreativeTabs.TAB_BLOCKS);
        ronnian_sandstone_chiseled = registerBlock(new RonnianSandstoneChiseled(), "ronnian_sandstone_chiseled", PBCreativeTabs.TAB_BLOCKS);
        ronnian_sandstone_smooth = registerBlock(new RonnianSandstoneSmooth(), "ronnian_sandstone_smooth", PBCreativeTabs.TAB_BLOCKS);
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