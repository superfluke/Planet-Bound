package com.crypticmushroom.planetbound.container;

import com.crypticmushroom.planetbound.init.InventorsForgeRecipes;
import com.crypticmushroom.planetbound.tileentity.TileEntityInventorsForge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Pretty much a modified copy of {@link ContainerFurnace}
 */
public class ContainerInventorsForge extends Container
{
    public static final int INPUT_SLOT_1 = 0;
    public static final int INPUT_SLOT_2 = 1;
    public static final int INPUT_SLOT_3 = 2;
    public static final int FUEL_SLOT = 3;
    public static final int OUTPUT_SLOT = 4;
    
    private TileEntityInventorsForge tileEntity;
    
    private int cookTime;
    private int totalCookTime;
    private int furnaceBurnTime;
    private int currentItemBurnTime;
    
    public ContainerInventorsForge(InventoryPlayer inventory, TileEntityInventorsForge tileEntity)
    {
        this.tileEntity = tileEntity;
        
        this.addSlotToContainer(new Slot(tileEntity, INPUT_SLOT_1, 38, 17));
        this.addSlotToContainer(new Slot(tileEntity, INPUT_SLOT_2, 56, 17));
        this.addSlotToContainer(new Slot(tileEntity, INPUT_SLOT_3, 74, 17));
        this.addSlotToContainer(new SlotFurnaceFuel(tileEntity, FUEL_SLOT, 56, 53));
        this.addSlotToContainer(new SlotFurnaceOutput(inventory.player, tileEntity, OUTPUT_SLOT, 116, 35));

        for(int i = 0; i < 3; ++i)
        {
            for(int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(inventory, k, 8 + k * 18, 142));
        }
    }
    
    @Override
    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        
        listener.sendAllWindowProperties(this, tileEntity);
    }
    
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for(int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = this.listeners.get(i);

            if(this.cookTime != tileEntity.getField(2))
            {
                icontainerlistener.sendWindowProperty(this, 2, tileEntity.getField(2));
            }

            if(this.furnaceBurnTime != tileEntity.getField(0))
            {
                icontainerlistener.sendWindowProperty(this, 0, tileEntity.getField(0));
            }

            if(this.currentItemBurnTime != tileEntity.getField(1))
            {
                icontainerlistener.sendWindowProperty(this, 1, tileEntity.getField(1));
            }

            if(this.totalCookTime != tileEntity.getField(3))
            {
                icontainerlistener.sendWindowProperty(this, 3, tileEntity.getField(3));
            }
            
            if(this.totalCookTime != tileEntity.getField(3))
            {
                icontainerlistener.sendWindowProperty(this, 3, tileEntity.getField(3));
            }
        }

        this.cookTime = tileEntity.getField(2);
        this.furnaceBurnTime = tileEntity.getField(0);
        this.currentItemBurnTime = tileEntity.getField(1);
        this.totalCookTime = tileEntity.getField(3);       
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        tileEntity.setField(id, data);
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return tileEntity.isUsableByPlayer(playerIn);
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        //Slot 0-2 = input
        //Slot 3 = fuel
        //Slot 4 = output
        //Slot 5-31 = player inventory
        //Slot 32-40 = hotbar
        ItemStack stack = ItemStack.EMPTY;
        
        Slot slot = this.inventorySlots.get(index);
        
        if(slot != null && slot.getHasStack())
        {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if(index == OUTPUT_SLOT)
            {
                if(!this.mergeItemStack(stack1, 5, 41, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(stack1, stack);
            }
            else if(index > 4)
            {
                if(InventorsForgeRecipes.isIngredient(stack1))
                {
                    if(!this.mergeItemStack(stack1, INPUT_SLOT_1, INPUT_SLOT_3 + 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if(TileEntityFurnace.isItemFuel(stack1))
                {
                    if(!this.mergeItemStack(stack1, 3, 4, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if(index >= 5 && index <= 31)
                {
                    if(!this.mergeItemStack(stack1, 32, 40, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if(index >= 32 && index <= 40)
                {
                    if(!this.mergeItemStack(stack1, 5, 31, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            }
            else
            {
                if(!this.mergeItemStack(stack1, 5, 40, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            
            if(stack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
            
            if(stack1.getCount() == stack1.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack1);
        }

        return stack;
    }
}