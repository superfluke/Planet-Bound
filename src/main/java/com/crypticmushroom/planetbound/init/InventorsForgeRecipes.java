package com.crypticmushroom.planetbound.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.Validate;

import com.crypticmushroom.planetbound.recipe.InventorsForgeRecipe;
import com.crypticmushroom.planetbound.tileentity.TileEntityInventorsForge;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.Ingredient;

public class InventorsForgeRecipes
{
    private static final List<InventorsForgeRecipe> recipes = new ArrayList<>();
    
    public static void init()
    {
        registerRecipe(new ItemStack[]
                {
                        new ItemStack(PBItems.verdanite_ingot),
                        new ItemStack(PBItems.kybrite_ingot),
                        new ItemStack(PBItems.rendium_chunk),
                }
        , new ItemStack(PBItems.rendium_crystal, 1));
        
        registerRecipe(new ItemStack[]
                {
                        new ItemStack(Items.APPLE),
                        new ItemStack(Items.GOLD_INGOT),
                }
        , new ItemStack(Items.GOLDEN_APPLE, 1));
        
        for(Entry<ItemStack, ItemStack> entry : FurnaceRecipes.instance().getSmeltingList().entrySet())
        {
            registerRecipe(new ItemStack[]{entry.getKey()}, entry.getValue()); //register default furnace recipes
        }
    }
    
    private static void registerRecipe(ItemStack[] input, ItemStack output)
    {
        Validate.notNull(input, "input cannot be null");
        
        List<Ingredient> ingredients = new ArrayList<>();
        
        for(ItemStack stack : input)
        {
            ingredients.add(Ingredient.fromStacks(stack));
        }
        
        recipes.add(new InventorsForgeRecipe(ingredients, output));
    }
    
    public static boolean isIngredient(ItemStack stack)
    {
        boolean result = false;
        
        for(InventorsForgeRecipe recipe : recipes)
        {
            for(Ingredient ingredient : recipe.getIngredients())
            {
                for(ItemStack stack1 : ingredient.getMatchingStacks())
                {
                    if(stack1.isItemEqual(stack))
                    {
                        result = true;
                        
                        break;
                    }
                }
            }
        }
        
        return result;
    }
    
    public static ItemStack getSmeltingResult(TileEntityInventorsForge inventory)
    {
        for(InventorsForgeRecipe recipe : recipes)
        {
            if(recipe.compare(inventory))
            {
                return recipe.getOutput();
            }
        }
        
        return ItemStack.EMPTY;
    }
    
    public static ItemStack getSmeltingResult(ItemStack... input)
    {
        for(InventorsForgeRecipe recipe : recipes)
        {
            if(recipe.compare(input))
            {
                return recipe.getOutput();
            }
        }
        
        return ItemStack.EMPTY;
    }
}