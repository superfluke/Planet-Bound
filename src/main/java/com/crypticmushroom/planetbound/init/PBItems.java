package com.crypticmushroom.planetbound.init;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.items.oreingot.KybriteIngot;
import com.crypticmushroom.planetbound.items.oreingot.RendiumCrystal;
import com.crypticmushroom.planetbound.items.oreingot.VerdaniteIngot;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PBItems
{
	private static final List<Item> items = new ArrayList<>();
	
    public static Item kybrite_ingot;
    public static Item verdanite_ingot;
    //public static Item rendium_chunk;
    //public static Item rendium_crystal;

    public static void init()
    {
        kybrite_ingot = registerItem(new KybriteIngot(), "kybrite_ingot");
        verdanite_ingot = registerItem(new VerdaniteIngot(), "verdanite_ingot");
        //rendium_chunk = new RendiumCrystal(CrystalType.CHUNK);
        //rendium_crystal = new RendiumCrystal(CrystalType.CRYSTAL);
    }
    
    protected static Item registerItem(Item item, String name)
    {
    	Validate.notNull(item, "item cannot be null");
    	Validate.notNull(name, "name cannot be null");
    	
    	item.setCreativeTab(CreativeTabs.MISC); //TODO for testing purposes!
    	
    	item.setUnlocalizedName(name);
    	item.setRegistryName(PBCore.MOD_ID, name);
    	
    	items.add(item);
    	
    	return item;
    }
    
    public static Item[] getItems()
    {
    	return items.toArray(new Item[]{});
    }
}
