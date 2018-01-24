package com.crypticmushroom.planetbound.tileentity;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.blocks.InventorsForge;
import com.crypticmushroom.planetbound.container.ContainerInventorsForge;
import com.crypticmushroom.planetbound.crafting.InventorsForgeManager;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Pretty much a modified copy of {@link TileEntityFurnace}
 */
public class TileEntityInventorsForge extends TileEntityLockable implements ITickable, ISidedInventory
{
    private static final int[] SLOTS_TOP = new int[]{ContainerInventorsForge.INPUT_SLOT_1, ContainerInventorsForge.INPUT_SLOT_2, ContainerInventorsForge.INPUT_SLOT_3};
    private static final int[] SLOTS_BOTTOM = new int[]{ContainerInventorsForge.OUTPUT_SLOT_1, ContainerInventorsForge.OUTPUT_SLOT_2, ContainerInventorsForge.OUTPUT_SLOT_3};
    private static final int[] SLOTS_SIDES = new int[]{ContainerInventorsForge.FUEL_SLOT};
    
    private NonNullList<ItemStack> contents = NonNullList.<ItemStack>withSize(7, ItemStack.EMPTY);
    private int furnaceBurnTime;
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    private int smeltMode; //0 = alloy mode, 1 = per slot mode
    //are you going to make a json file or java class for the alloy recipies as well? sorry if you already did, I’m making these comments on my phone.
    
    @Override
    public int getSizeInventory()
    {
        return this.contents.size();
    }
    
    @Override
    public boolean isEmpty()
    {
        for(ItemStack stack : contents)
        {
            if(!stack.isEmpty())
            {
                return false;
            }
        }

        return true;
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
        ItemStack target = contents.get(index);
        
        boolean flag = !stack.isEmpty() && stack.isItemEqual(target) && ItemStack.areItemStackTagsEqual(stack, target);
        
        contents.set(index, stack);

        if(stack.getCount() > getInventoryStackLimit())
        {
            stack.setCount(getInventoryStackLimit());
        }

        if((index == ContainerInventorsForge.INPUT_SLOT_1 || index == ContainerInventorsForge.INPUT_SLOT_2 || index == ContainerInventorsForge.INPUT_SLOT_3) && !flag)
        {
            totalCookTime = getCookTime(stack);
            //cookTime = 0; No longer reset cook time due to there being 3 input slots
            
            markDirty();
        }
    }
    
    @Override
    public String getName()
    {
        return "container.inventors_forge";
    }
    
    @Override
    public boolean hasCustomName()
    {
        return false;
    }
    
    public static void registerFixesFurnace(DataFixer fixer)
    {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityInventorsForge.class, new String[]{"Items"}));
    }
    
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        
        contents = NonNullList.<ItemStack>withSize(getSizeInventory(), ItemStack.EMPTY);
        
        ItemStackHelper.loadAllItems(compound, contents);
        
        furnaceBurnTime = compound.getInteger("BurnTime");
        cookTime = compound.getInteger("CookTime");
        totalCookTime = compound.getInteger("CookTimeTotal");
        currentItemBurnTime = getItemBurnTime(contents.get(ContainerInventorsForge.FUEL_SLOT));
        smeltMode = compound.getInteger("SmeltMode");
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        
        compound.setInteger("BurnTime", (short)furnaceBurnTime);
        compound.setInteger("CookTime", (short)cookTime);
        compound.setInteger("CookTimeTotal", (short)totalCookTime);
        ItemStackHelper.saveAllItems(compound, contents);
        compound.setInteger("SmeltMode", smeltMode);

        return compound;
    }
    
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }
    
    public boolean isBurning()
    {
        return furnaceBurnTime > 0;
    }
    
    private boolean hasInput()
    {
        ItemStack input1 = contents.get(ContainerInventorsForge.INPUT_SLOT_1);
        ItemStack input2 = contents.get(ContainerInventorsForge.INPUT_SLOT_2);
        ItemStack input3 = contents.get(ContainerInventorsForge.INPUT_SLOT_3);
        
        return !input1.isEmpty() || !input2.isEmpty() || !input3.isEmpty();
    }
    
    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }
    
    @Override
    public void update()
    {
        boolean flag = isBurning();
        boolean flag1 = false;
        
        if(isBurning())
        {
            --furnaceBurnTime;
        }
        
        if(!world.isRemote)
        {
            ItemStack fuel = contents.get(ContainerInventorsForge.FUEL_SLOT);
            
            if(isBurning() || !fuel.isEmpty() && hasInput())
            {
                if(!isBurning() && canSmelt())
                {
                    furnaceBurnTime = getItemBurnTime(fuel);
                    currentItemBurnTime = furnaceBurnTime;

                    if(isBurning())
                    {
                        flag1 = true;

                        if(!fuel.isEmpty())
                        {
                            Item fuelItem = fuel.getItem();
                            fuel.shrink(1);

                            if(fuel.isEmpty())
                            {
                                ItemStack fuelItem1 = fuelItem.getContainerItem(fuel);
                                contents.set(ContainerInventorsForge.FUEL_SLOT, fuelItem1);
                            }
                        }
                    }
                }
                
                if(isBurning() && canSmelt())
                {
                    ++cookTime;

                    if(cookTime == totalCookTime)
                    {
                        cookTime = 0;
                        totalCookTime = getCookTime(contents.get(ContainerInventorsForge.INPUT_SLOT_1));
                        
                        if(smeltMode == 0)
                        {
                            smeltItem();
                        }
                        else if(smeltMode == 1)
                        {
                            smeltItem(contents.get(ContainerInventorsForge.INPUT_SLOT_1));
                            smeltItem(contents.get(ContainerInventorsForge.INPUT_SLOT_2));
                            smeltItem(contents.get(ContainerInventorsForge.INPUT_SLOT_3));
                        }
                        
                        flag1 = true;
                    }
                }
                else
                {
                    cookTime = 0;
                }
            }
            else if(!isBurning() && cookTime > 0)
            {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }
            
            if(flag != this.isBurning())
            {
                flag1 = true;
                
                InventorsForge.setState(this.isBurning(), this.world, this.pos);
            }
        }
        
        if(flag1)
        {
            this.markDirty();
        }
    }
    
    public int getCookTime(ItemStack stack)
    {
        return 200;
    }
    
    private boolean canSmelt()
    {
        if(!hasInput())
        {
            return false;
        }
        else
        {
            if(smeltMode == 0)
            {
                ItemStack result = InventorsForgeManager.getSmeltingResult(this);
                
                if(result.isEmpty())
                {
                    return false;
                }
                else
                {
                    return getFirstFreeOutput(result) != -1;
                }
            }
            else if(smeltMode == 1)
            {
                ItemStack result1 = FurnaceRecipes.instance().getSmeltingResult(contents.get(ContainerInventorsForge.INPUT_SLOT_1));
                ItemStack result2 = FurnaceRecipes.instance().getSmeltingResult(contents.get(ContainerInventorsForge.INPUT_SLOT_2));
                ItemStack result3 = FurnaceRecipes.instance().getSmeltingResult(contents.get(ContainerInventorsForge.INPUT_SLOT_3));
                
                if(result1.isEmpty() && result2.isEmpty() && result3.isEmpty())
                {
                    return false;
                }
                else
                {
                    return getFirstFreeOutput(result1) != -1 || getFirstFreeOutput(result2) != -1 || getFirstFreeOutput(result3) != -1;
                }
            }
        }
        
        return false;
    }
    
    private int getFirstFreeOutput(ItemStack result)
    {
        ItemStack output1 = contents.get(ContainerInventorsForge.OUTPUT_SLOT_1);
        ItemStack output2 = contents.get(ContainerInventorsForge.OUTPUT_SLOT_2);
        ItemStack output3 = contents.get(ContainerInventorsForge.OUTPUT_SLOT_3);

        if(outputValid(output1, result))
        {
            return ContainerInventorsForge.OUTPUT_SLOT_1;
        }
        else if(outputValid(output2, result))
        {
            return ContainerInventorsForge.OUTPUT_SLOT_2;
        }
        else if(outputValid(output3, result))
        {
            return ContainerInventorsForge.OUTPUT_SLOT_3;
        }
        
        return -1;
    }
    
    private boolean outputValid(ItemStack output, ItemStack result)
    {
        if(output.isEmpty())
        {
            return true;
        }
        else if(!output.isItemEqual(result))
        {
            return false;
        }
        else if(output.getCount() + result.getCount() <= getInventoryStackLimit() && output.getCount() + result.getCount() <= output.getMaxStackSize())  // Forge fix: make furnace respect stack sizes in furnace recipes
        {   
            return true;
        }
        else
        {
            return output.getCount() + result.getCount() <= result.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
        }
    }
    
    public void smeltItem(ItemStack input) //smeltItem for per slot smelt mode!!
    {
        if(canSmelt())
        {
            ItemStack result = InventorsForgeManager.getSmeltingResult(input);
            
            if(!result.isEmpty())
            {
                int outputSlot = getFirstFreeOutput(result);
                
                if(outputSlot != -1)
                {
                   ItemStack output = contents.get(outputSlot);
                   
                   if(output.isEmpty())
                   {
                       contents.set(outputSlot, result.copy());
                   }
                   else if(output.getItem() == result.getItem())
                   {
                       output.grow(result.getCount());
                   }
                   
                   input.shrink(1);
                   
                   extractWaterFromSponge(input);
                }
            }
        }
    }
    
    public void smeltItem() //smeltItem for alloy smelt mode!!
    {
        if(canSmelt())
        {
            ItemStack result = InventorsForgeManager.getSmeltingResult(this);
            
            if(!result.isEmpty())
            {
                int outputSlot = getFirstFreeOutput(result);
                
                if(outputSlot != -1)
                {
                   ItemStack output = contents.get(outputSlot);
                   
                   if(output.isEmpty())
                   {
                       contents.set(outputSlot, result.copy());
                   }
                   else if(output.getItem() == result.getItem())
                   {
                       output.grow(result.getCount());
                   }
                   
                   for(int i = ContainerInventorsForge.INPUT_SLOT_1; i <= ContainerInventorsForge.INPUT_SLOT_3; i++)
                   {
                       ItemStack stack = contents.get(i);
                       
                       if(!stack.isEmpty())
                       {
                           stack.shrink(1);
                       }
                   }
                   
                   extractWaterFromSponge(contents.get(ContainerInventorsForge.INPUT_SLOT_1));
                   extractWaterFromSponge(contents.get(ContainerInventorsForge.INPUT_SLOT_2));
                   extractWaterFromSponge(contents.get(ContainerInventorsForge.INPUT_SLOT_3));
                }
            }
        }
    }
    
    private void extractWaterFromSponge(ItemStack sponge)
    {
        if(sponge.getItem() == Item.getItemFromBlock(Blocks.SPONGE) && sponge.getMetadata() == 1 && !((ItemStack)this.contents.get(ContainerInventorsForge.FUEL_SLOT)).isEmpty() && ((ItemStack)this.contents.get(ContainerInventorsForge.FUEL_SLOT)).getItem() == Items.BUCKET)
        {
            contents.set(ContainerInventorsForge.FUEL_SLOT, new ItemStack(Items.WATER_BUCKET));
        }
    }
    
    public static int getItemBurnTime(ItemStack stack)
    {
        if(stack.isEmpty())
        {
            return 0;
        }
        else
        {
            int burnTime = net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack);
            
            if(burnTime >= 0) 
            {
                return burnTime;
            }
            
            Item item = stack.getItem();

            if(item == Item.getItemFromBlock(Blocks.WOODEN_SLAB))
            {
                return 150;
            }
            else if(item == Item.getItemFromBlock(Blocks.WOOL))
            {
                return 100;
            }
            else if(item == Item.getItemFromBlock(Blocks.CARPET))
            {
                return 67;
            }
            else if(item == Item.getItemFromBlock(Blocks.LADDER))
            {
                return 300;
            }
            else if(item == Item.getItemFromBlock(Blocks.WOODEN_BUTTON))
            {
                return 100;
            }
            else if(Block.getBlockFromItem(item).getDefaultState().getMaterial() == Material.WOOD)
            {
                return 300;
            }
            else if(item == Item.getItemFromBlock(Blocks.COAL_BLOCK))
            {
                return 16000;
            }
            else if(item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName()))
            {
                return 200;
            }
            else if(item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName()))
            {
                return 200;
            }
            else if(item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName()))
            {
                return 200;
            }
            else if(item == Items.STICK)
            {
                return 100;
            }
            else if(item != Items.BOW && item != Items.FISHING_ROD)
            {
                if(item == Items.SIGN)
                {
                    return 200;
                }
                else if(item == Items.COAL)
                {
                    return 1600;
                }
                else if(item == Items.LAVA_BUCKET)
                {
                    return 20000;
                }
                else if(item != Item.getItemFromBlock(Blocks.SAPLING) && item != Items.BOWL)
                {
                    if(item == Items.BLAZE_ROD)
                    {
                        return 2400;
                    }
                    else if(item instanceof ItemDoor && item != Items.IRON_DOOR)
                    {
                        return 200;
                    }
                    else
                    {
                        return item instanceof ItemBoat ? 400 : 0;
                    }
                }
                else
                {
                    return 100;
                }
            }
            else
            {
                return 300;
            }
        }
    }
    
    public static boolean isItemFuel(ItemStack stack)
    {
        return getItemBurnTime(stack) > 0;
    }
    
    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if(world.getTileEntity(pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D) <= 64.0D;
        }
    }
    
    @Override
    public void openInventory(EntityPlayer player){}
    
    @Override
    public void closeInventory(EntityPlayer player){}
    
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        if(index == ContainerInventorsForge.OUTPUT_SLOT_1)
        {
            return false;
        }
        else if(index != ContainerInventorsForge.FUEL_SLOT)
        {
            return true;
        }
        else
        {
            ItemStack fuel = contents.get(ContainerInventorsForge.FUEL_SLOT);
            
            return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && fuel.getItem() != Items.BUCKET;
        }
    }
    
    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        if(side == EnumFacing.DOWN)
        {
            return SLOTS_BOTTOM;
        }
        else
        {
            return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
        }
    }
    
    @Override
    public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction)
    {
        return isItemValidForSlot(index, stack);
    }
    
    @Override //TODO
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if(direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();

            if (item != Items.WATER_BUCKET && item != Items.BUCKET)
            {
                return false;
            }
        }

        return true;
    }
    
    @Override
    public String getGuiID()
    {
        return PBCore.MOD_ID + ":inventors_forge";
    }
    
    @Override //I'm pretty sure this is never even called
    public Container createContainer(InventoryPlayer inventory, EntityPlayer player)
    {
        return new ContainerInventorsForge(inventory, this);
    }
    
    @Override
    public int getField(int id)
    {
        switch(id)
        {
            case 0:
                return furnaceBurnTime;
            case 1:
                return currentItemBurnTime;
            case 2:
                return cookTime;
            case 3:
                return totalCookTime;
            case 4:
                return smeltMode;
            default:
                return 0;
        }
    }
    
    @Override
    public void setField(int id, int value)
    {
        switch(id)
        {
            case 0:
                furnaceBurnTime = value;
                break;
            case 1:
                currentItemBurnTime = value;
                break;
            case 2:
                cookTime = value;
                break;
            case 3:
                totalCookTime = value;
                break;
            case 4:
                smeltMode = value;
        }
    }
    
    @Override
    public int getFieldCount()
    {
        return 5;
    }
    
    @Override
    public void clear()
    {
        contents.clear();
    }
    
    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }
}