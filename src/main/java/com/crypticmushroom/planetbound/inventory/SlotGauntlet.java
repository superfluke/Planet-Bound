package com.crypticmushroom.planetbound.inventory;

import com.crypticmushroom.planetbound.init.PBItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SlotGauntlet extends Slot {
    public SlotGauntlet(IInventory inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() == PBItems.rift_gauntlet;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public String getSlotTexture() {
        return "planetbound:items/empty_gauntlet_slot";
    }
}