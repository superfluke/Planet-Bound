package com.crypticmushroom.planetbound.container;

import com.crypticmushroom.planetbound.crafting.InventorsForgeManager;
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
    public static final int OUTPUT_SLOT_1 = 4;
    public static final int OUTPUT_SLOT_2 = 5;
    public static final int OUTPUT_SLOT_3 = 6;
    
    private TileEntityInventorsForge tileEntity;
    
    private int cookTime;
    private int totalCookTime;
    private int furnaceBurnTime;
    private int currentItemBurnTime;
    private int smeltMode;
    
    public ContainerInventorsForge(InventoryPlayer inventory, TileEntityInventorsForge tileEntity)
    {
        this.tileEntity = tileEntity;
        
        this.addSlotToContainer(new Slot(tileEntity, INPUT_SLOT_1, 38, 17));
        this.addSlotToContainer(new Slot(tileEntity, INPUT_SLOT_2, 56, 17));
        this.addSlotToContainer(new Slot(tileEntity, INPUT_SLOT_3, 74, 17));
        this.addSlotToContainer(new SlotFurnaceFuel(tileEntity, FUEL_SLOT, 56, 53));
        this.addSlotToContainer(new SlotFurnaceOutput(inventory.player, tileEntity, OUTPUT_SLOT_1, 110, 17));
        this.addSlotToContainer(new SlotFurnaceOutput(inventory.player, tileEntity, OUTPUT_SLOT_2, 110, 35));
        this.addSlotToContainer(new SlotFurnaceOutput(inventory.player, tileEntity, OUTPUT_SLOT_3, 110, 53));
        
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
            
            if(this.smeltMode != tileEntity.getField(4))
            {
                icontainerlistener.sendWindowProperty(this, 4, tileEntity.getField(4));
            }
            
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
        }
        
        this.smeltMode = tileEntity.getField(4);
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
        ItemStack stack = ItemStack.EMPTY;
        
        Slot slot = this.inventorySlots.get(index);
        
        if(slot != null && slot.getHasStack())
        {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if(index == OUTPUT_SLOT_1 || index == OUTPUT_SLOT_2 || index == OUTPUT_SLOT_3)
            {
                if(!this.mergeItemStack(stack1, 7, 43, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(stack1, stack);
            }
            else if(index > 6)
            {
                if(!FurnaceRecipes.instance().getSmeltingResult(stack1).isEmpty())
                {
                    if(!this.mergeItemStack(stack1, INPUT_SLOT_1, INPUT_SLOT_3 + 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if(InventorsForgeManager.isIngredient(stack1))
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
                else if(index >= 7 && index <= 33)
                {
                    if(!this.mergeItemStack(stack1, 34, 43, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if(index >= 34 && index <= 42)
                {
                    if(!this.mergeItemStack(stack1, 7, 34, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            }
            else
            {
                if(!this.mergeItemStack(stack1, 7, 43, false))
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