package com.crypticmushroom.planetbound.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class PBCreativeTabs
{
    //TODO temporary test tab; will be removed in the future
    public static final CreativeTabs TAB_MAIN = new CreativeTabs("main")
    {
        @Override 
        public ItemStack getTabIconItem()
        {
            return new ItemStack(PBBlocks.verdanite_block);
        }
    };
}