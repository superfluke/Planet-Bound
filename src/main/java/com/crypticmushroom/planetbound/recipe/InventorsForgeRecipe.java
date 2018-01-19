package com.crypticmushroom.planetbound.recipe;

import java.util.ArrayList;
import java.util.List;

import com.crypticmushroom.planetbound.container.ContainerInventorsForge;
import com.crypticmushroom.planetbound.tileentity.TileEntityInventorsForge;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.util.RecipeMatcher;

public class InventorsForgeRecipe
{
    private final List<Ingredient> input;
    
    private final ItemStack output;
    
    public InventorsForgeRecipe(List<Ingredient> input, ItemStack output)
    {
        if(input.size() > 3)
        {
            this.input = input.subList(0, 3);
        }
        else
        {
            this.input = input;
        }
        
        this.output = output;
    }
    
    public boolean compare(TileEntityInventorsForge inventory)
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
        System.out.println(output);
        return RecipeMatcher.findMatches(inputList, input) != null;
    }
    
    public ItemStack getOutput()
    {
        return output;
    }
}