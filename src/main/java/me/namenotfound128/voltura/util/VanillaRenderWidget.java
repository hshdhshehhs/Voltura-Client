package me.namenotfound128.voltura.util;

import me.namenotfound128.voltura.mod.Mod;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class VanillaRenderWidget
        implements IRenderWidget {
    private final Mod mod;

    private Minecraft mc() { return Minecraft.getMinecraft(); }

    public VanillaRenderWidget(Mod mod) {
        this.mod = mod;
    }

    @Override
    public Rectangle renderRectangle() {
        return this.mod.pos;
    }

    @Override
    public void beginRender() {
        GlStateManager.translate(this.renderRectangle().x, this.renderRectangle().y, 0.0f);
    }

    @Override
    public void endRender() {
        GlStateManager.translate(-this.renderRectangle().x, -this.renderRectangle().y, 0.0f);
    }

    @Override
    public void fillBackground(int color) {
        GlStateManager.translate(0, 0, -5);
        Gui.drawRect(0, 0, this.renderRectangle().getWidth(), this.renderRectangle().getHeight(), color);
        GlStateManager.translate(0, 0, 5);
    }

    @Override
    public Rectangle string(String text, float x, float y, int color) {
        this.mc().fontRendererObj.drawStringWithShadow(text, x, y, color);
        return new Rectangle(x, y, this.mc().fontRendererObj.getStringWidth(text), this.mc().fontRendererObj.FONT_HEIGHT);
    }

    @Override
    public float stringWidth(String text) {
        return this.mc().fontRendererObj.getStringWidth(text);
    }

    @Override
    public float stringHeight(String text) {
        return this.mc().fontRendererObj.FONT_HEIGHT;
    }

    @Override
    public void box(float x, float y, float width, float height, int color) {
        Gui.drawRect(x, y, x + width, y + height, color);
        GlStateManager.color(1, 1, 1, 1);
    }

}
