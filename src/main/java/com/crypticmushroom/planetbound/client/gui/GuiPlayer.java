package com.crypticmushroom.planetbound.client.gui;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.container.ContainerPlayer;
import com.crypticmushroom.planetbound.player.PBPlayer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiPlayer extends GuiInventory
{
    private static final ResourceLocation INVENTORY_BACKGROUND = new ResourceLocation(PBCore.MOD_ID + ":textures/gui/container/inventory.png");

    public GuiPlayer(PBPlayer player)
    {
        super(player.getPlayer());

        inventorySlots = new ContainerPlayer(player);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(INVENTORY_BACKGROUND);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        drawEntityOnScreen(i + 51, j + 75, 30, (float) (i + 51) - mouseX, (float) (j + 75 - 50) - mouseY, this.mc.player);
    }
}