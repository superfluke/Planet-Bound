package com.crypticmushroom.planetbound.init;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.items.CoreFrame;
import com.crypticmushroom.planetbound.items.RendiumCore;
import com.crypticmushroom.planetbound.items.oreingot.KybriteIngot;
import com.crypticmushroom.planetbound.items.oreingot.RendiumChunk;
import com.crypticmushroom.planetbound.items.oreingot.RendiumCrystal;
import com.crypticmushroom.planetbound.items.oreingot.VerdaniteIngot;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PBItems
{
    private static final List<Item> items = new ArrayList<>();
    
    public static Item kybrite_ingot;
    public static Item verdanite_ingot;
    public static Item rendium_chunk;
    public static Item rendium_crystal;
    public static Item core_frame;
    public static Item rendium_core;
    
    public static void init()
    {
        kybrite_ingot = registerItem(new KybriteIngot(), "kybrite_ingot");
        verdanite_ingot = registerItem(new VerdaniteIngot(), "verdanite_ingot");
        rendium_chunk = registerItem(new RendiumChunk(), "rendium_chunk");
        rendium_crystal = registerItem(new RendiumCrystal(), "rendium_crystal");
        core_frame = registerItem(new CoreFrame(), "core_frame");
        rendium_core = registerItem(new RendiumCore(), "rendium_core");
    }
    
    protected static Item registerItem(Item item, String name)
    {
        Validate.notNull(item, "item cannot be null");
        Validate.notNull(name, "name cannot be null");
        
        item.setCreativeTab(PBCreativeTabs.TAB_MAIN); //TODO for testing purposes!
        
        item.setUnlocalizedName(name);
        item.setRegistryName(PBCore.MOD_ID, name);
        
        items.add(item);
        
        return item;
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerModels()
    {
        for(Item item : items)
        {
            registerModel(item);
        }
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerModel(Item item)
    {
        registerModel(item, 0);
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerModel(Item item, int metadata)
    {
        Validate.notNull(item, "item cannot be null");
        
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
    
    public static Item[] getItems()
    {
        return items.toArray(new Item[]{});
    }
}