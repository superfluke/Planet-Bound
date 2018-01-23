package com.crypticmushroom.planetbound.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.Validate;

import com.crypticmushroom.planetbound.recipe.InventorsForgeRecipe;
import com.crypticmushroom.planetbound.tileentity.TileEntityInventorsForge;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class InventorsForgeRecipes
{
    private static final List<InventorsForgeRecipe> recipes = new ArrayList<>();
    
    public static void init()
    {
        registerRecipe(new ItemStack[]
                {
                        new ItemStack(PBItems.verdanite_ingot),
                        new ItemStack(PBItems.kybrite_ingot)
                }
        , new ItemStack(PBItems.fortium_ingot, 1), 0.7F);
        
        registerRecipe(new ItemStack[]
                {
                        new ItemStack(Items.APPLE),
                        new ItemStack(Items.GOLD_INGOT),
                }
        , new ItemStack(Items.GOLDEN_APPLE, 1), 0);
        
        for(Entry<ItemStack, ItemStack> entry : FurnaceRecipes.instance().getSmeltingList().entrySet())
        {
            registerRecipe(new ItemStack[]{entry.getKey()}, entry.getValue()); //register default furnace recipes
        }
    }
    
    private static void registerRecipe(ItemStack[] input, ItemStack output)
    {
        Validate.notNull(input, "input cannot be null");
        Validate.notNull(output, "output cannot be null");
        
        List<Ingredient> ingredients = new ArrayList<>();
        
        for(ItemStack stack : input)
        {
            ingredients.add(Ingredient.fromStacks(stack));
        }
        
        recipes.add(new InventorsForgeRecipe(ingredients, output));
    }
    
    private static void registerRecipe(ItemStack[] input, ItemStack output, float xp)
    {
        Validate.notNull(input, "input cannot be null");
        Validate.notNull(output, "output cannot be null");
        
        List<Ingredient> ingredients = new ArrayList<>();
        
        for(ItemStack stack : input)
        {
            ingredients.add(Ingredient.fromStacks(stack));
        }
        
        recipes.add(new InventorsForgeRecipe(ingredients, output));
        
        //hacking into furnace xp values is much easier than creating my own output slots
        Map<ItemStack, Float> experienceList = ReflectionHelper.getPrivateValue(FurnaceRecipes.class, FurnaceRecipes.instance(), 2);
        experienceList.put(output, xp);
        
        ReflectionHelper.setPrivateValue(FurnaceRecipes.class, FurnaceRecipes.instance(), experienceList, 2);
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