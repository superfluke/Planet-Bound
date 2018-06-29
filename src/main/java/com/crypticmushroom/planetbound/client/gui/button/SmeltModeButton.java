package com.crypticmushroom.planetbound.client.gui.button;

import com.crypticmushroom.planetbound.PBCore;
import com.crypticmushroom.planetbound.tileentity.TileEntityInventorsForge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.Display;

import java.util.Arrays;

//ugli code. dennis sad.
//no u
@SideOnly(Side.CLIENT)
public class SmeltModeButton extends GuiButton
{
    private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation(PBCore.MOD_ID + ":textures/gui/container/inventors_forge.png");

    private TileEntityInventorsForge listener;

    public SmeltModeButton(TileEntityInventorsForge listener, int id, int x, int y)
    {
        super(id, x, y, 9, 10, "");

        this.listener = listener;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(FURNACE_GUI_TEXTURES);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

            this.drawTexturedModalRect(this.x, this.y, 176, 31, 9, 10);

            if (hovered)
            {
                this.drawTexturedModalRect(this.x, this.y, 176, 41, 9, 10);
            }

            this.mouseDragged(mc, mouseX, mouseY);

            if (hovered)
            {
                GuiUtils.drawHoveringText(Arrays.asList("Smelt Mode: " + (listener.getField(4) == 0 ? "Alloys" : "Per slot")), mouseX - 100, mouseY - 2, Display.getWidth(), Display.getHeight(), -1, fontrenderer);
            } else
            {
                displayString = "";
            }
        }
    }
}