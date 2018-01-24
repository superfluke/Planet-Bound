package com.crypticmushroom.planetbound.crafting;

import java.util.ArrayList;
import java.util.List;

import com.crypticmushroom.planetbound.container.ContainerInventorsForge;
import com.crypticmushroom.planetbound.tileentity.TileEntityInventorsForge;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.RecipeMatcher;

public class InventorsForgeRecipe
{
    private final NonNullList<Ingredient> input;
    
    private final ItemStack output;
    
    protected InventorsForgeRecipe(NonNullList<Ingredient> input, ItemStack output)
    {
        this.input = input;
        this.output = output;
    }
    
    public boolean matches(TileEntityInventorsForge inventory)
    {
        List<ItemStack> inputList = new ArrayList<>();
        
        int ingredientCount = 0;
        
        for(int i = ContainerInventorsForge.INPUT_SLOT_1; i <= ContainerInventorsForge.INPUT_SLOT_3; i++)
        {
            ItemStack stack = inventory.getStackInSlot(i);
            
            if(!stack.isEmpty())
            {
                ingredientCount++;
                
                inputList.add(stack);
            }
        }

        if(ingredientCount != input.size())
        {
            return false;
        }
        
        return RecipeMatcher.findMatches(inputList, input) != null;
    }
    
    public boolean matches(ItemStack... input)
    {
        List<ItemStack> inputList = new ArrayList<>();
        
        int ingredientCount = 0;
        
        for(ItemStack stack : input)
        {
            if(!stack.isEmpty())
            {
                ingredientCount++;
                
                inputList.add(stack);
            }
        }

        if(ingredientCount != this.input.size())
        {
            return false;
        }
        
        return RecipeMatcher.findMatches(inputList, this.input) != null;
    }
    
    public NonNullList<Ingredient> getIngredients()
    {
        return input;
    }
    
    public ItemStack getOutput()
    {
        return output;
    }
}