package com.crypticmushroom.planetbound.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class PBCreativeTabs
{

    /*
    //TODO temporary test tab; will be removed in the future
    public static final CreativeTabs TAB_MAIN = new CreativeTabs("main")
    {
        @Override 
        public ItemStack getTabIconItem()
        {
            return new ItemStack(PBBlocks.verdanite_block);
        }
    };
    */

    //Vague tabs made for development. Broader tabs will be made as new blocks are added.
    public static final CreativeTabs TAB_BLOCKS = new CreativeTabs("pb_blocks")
    {
        @Override
        public ItemStack getTabIconItem()
        {
            return new ItemStack(PBBlocks.verdanite_block);
        }
    };

    public static final CreativeTabs TAB_ITEMS = new CreativeTabs("pb_items")
    {
        @Override
        public ItemStack getTabIconItem()
        {
            return new ItemStack(PBItems.verdanite_ingot);
        }
    };
}