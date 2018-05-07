package com.crypticmushroom.planetbound.inventory;

import com.crypticmushroom.planetbound.init.PBItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

public class InventoryGauntlet implements IInventory
{
    private final NonNullList<ItemStack> contents = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);

    @Override
    public String getName()
    {
        return "inventory_gauntlet";
    }

    @Override
    public boolean hasCustomName()
    {
        return false;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return null;
    }

    @Override
    public int getSizeInventory()
    {
        return contents.size();
    }

    @Override
    public boolean isEmpty()
    {
        return contents.isEmpty();
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return contents.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(contents, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(contents, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        contents.set(index, stack);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public void markDirty()
    {

    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player)
    {

    }

    @Override
    public void closeInventory(EntityPlayer player)
    {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return stack.getItem() == PBItems.rift_gauntlet;
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value)
    {

    }

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear()
    {
        contents.clear();
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        ItemStackHelper.loadAllItems(compound, contents);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        ItemStackHelper.saveAllItems(compound, contents);

        return compound;
    }
}