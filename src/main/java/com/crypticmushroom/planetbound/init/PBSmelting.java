package com.crypticmushroom.planetbound.init;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class PBSmelting
{
    //TODO add xp values for smelting
    public static void init()
    {
        FurnaceRecipes.instance().addSmeltingRecipeForBlock(PBBlocks.kybrite_ore, new ItemStack(PBItems.kybrite_ingot), 0.7F);
        FurnaceRecipes.instance().addSmeltingRecipeForBlock(PBBlocks.verdanite_ore, new ItemStack(PBItems.verdanite_ingot), 0.7F);
        FurnaceRecipes.instance().addSmelting(PBItems.rendium_chunk, new ItemStack(PBItems.rendium_crystal), 0.7F);
    }
}