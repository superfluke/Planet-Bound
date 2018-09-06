package com.crypticmushroom.planetbound.init;

import com.crypticmushroom.planetbound.PBCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class PBCreativeTabs
{

    /*
     * //TODO temporary test tab; will be removed in the future public static final
     * CreativeTabs TAB_MAIN = new CreativeTabs("main") {
     *
     * @Override public ItemStack createIcon() { return new
     * ItemStack(PBBlocks.verdanite_block); } };
     */

    // Vague tabs made for development. Broader tabs will be made as new blocks are
    // added.
    public static final CreativeTabs TAB_BLOCKS = new CreativeTabs(PBCore.MOD_ID + ".pb_blocks.name")
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(PBBlocks.verdanite_block);
        }
    };

    public static final CreativeTabs TAB_ITEMS = new CreativeTabs(PBCore.MOD_ID + ".pb_items.name")
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(PBItems.verdanite_ingot);
        }
    };
}