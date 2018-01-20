package com.crypticmushroom.planetbound.client.gui;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.client.gui.button.SmeltModeButton;
import com.crypticmushroom.planetbound.container.ContainerInventorsForge;
import com.crypticmushroom.planetbound.networking.PBNetworkHandler;
import com.crypticmushroom.planetbound.networking.packet.PBPacketSmeltMode;
import com.crypticmushroom.planetbound.tileentity.TileEntityInventorsForge;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Pretty much a modified copy of {@link GuiFurnace}
 */
@SideOnly(Side.CLIENT)
public class GuiInventorsForge extends GuiContainer
{
    private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation(PBCore.MOD_ID + ":textures/gui/container/inventors_forge.png");

    private final InventoryPlayer inventory;
    
    private final TileEntityInventorsForge tileEntity;

    public GuiInventorsForge(InventoryPlayer inventory, TileEntityInventorsForge tileEntity)
    {
        super(new ContainerInventorsForge(inventory, tileEntity));
        
        this.inventory = inventory;
        this.tileEntity = tileEntity;
    }
    
    @Override
    public void initGui()
    {
        super.initGui();
        
        addButton(new SmeltModeButton(tileEntity, 0, guiLeft + 5, guiTop + 5));
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        
        super.drawScreen(mouseX, mouseY, partialTicks);
        
        renderHoveredToolTip(mouseX, mouseY);
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String displayName = tileEntity.getDisplayName().getUnformattedText();
        
        fontRenderer.drawString(displayName, xSize / 2 - fontRenderer.getStringWidth(displayName) / 2, 6, 4210752);
        fontRenderer.drawString(inventory.getDisplayName().getUnformattedText(), 8, ySize - 96 + 2, 4210752);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        
        mc.getTextureManager().bindTexture(FURNACE_GUI_TEXTURES);
        
        int i = (width - xSize) / 2;
        int j = (height - ySize) / 2;
        
        drawTexturedModalRect(i, j, 0, 0, xSize, ySize);

        if(TileEntityInventorsForge.isBurning(tileEntity))
        {
            int k = getBurnLeftScaled(13);
            
            drawTexturedModalRect(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = getCookProgressScaled(24);
        
        drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);
    }
    
    @Override
    protected void actionPerformed(GuiButton button)
    {
        if(button.id == 0)
        {
            PBNetworkHandler.sendToServer(new PBPacketSmeltMode(tileEntity));
        }
    }
    
    private int getCookProgressScaled(int pixels)
    {
        int i = tileEntity.getField(2);
        int j = tileEntity.getField(3);
        
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }
    
    private int getBurnLeftScaled(int pixels)
    {
        int i = tileEntity.getField(1);

        if(i == 0)
        {
            i = 200;
        }

        return tileEntity.getField(0) * pixels / i;
    }
}