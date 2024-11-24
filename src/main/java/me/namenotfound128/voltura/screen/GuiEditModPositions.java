package me.namenotfound128.voltura.screen;

import me.namenotfound128.voltura.mod.Mod;
import me.namenotfound128.voltura.mod.ModManager;
import me.namenotfound128.voltura.util.ListUtil;
import me.namenotfound128.voltura.util.Rectangle;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.gui.GuiButton;

public class GuiEditModPositions
        extends GuiConfiguring {
    private Mod draggingMod = null;
    private float clickX = 0.0f;
    private float clickY = 0.0f;

    private void outline(Rectangle rectangle, int color) {
        this.drawVerticalLine(rectangle.x, rectangle.y, rectangle.getY2(), color);
        this.drawVerticalLine(rectangle.getX2(), rectangle.y, rectangle.getY2(), color);
        this.drawHorizontalLine(rectangle.x, rectangle.getX2(), rectangle.y, color);
        this.drawHorizontalLine(rectangle.x, rectangle.getX2(), rectangle.getY2(), color);
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float delta) {
        super.drawScreen(mouseX, mouseY, delta);
        ModManager.renderPreview();
        if (this.draggingMod != null) {
            this.draggingMod.pos.x = (float) mouseX - this.clickX;
            this.draggingMod.pos.y = (float) mouseY - this.clickY;
        }
        for (int i = 0; i < ModManager.mods.size(); i++) {
            Mod mod = ModManager.mods.get(i);
            if (!mod.pos.isHovered(mouseX, mouseY)) continue;
            this.outline(mod.pos, -1);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            this.drawHoveringText(ListUtil.of("§l" + mod.name, mod.description, "Left Click: Move", "Right Click: " + (mod.enabled ? "§cDisable" : "§aEnable")), mouseX + 2, mouseY - 2);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        for (int i = 0; i < ModManager.mods.size(); i++) {
            Mod mod = ModManager.mods.get(i);
            if (!mod.pos.isHovered(mouseX, mouseY)) continue;
            this.clickX = (float) mouseX - mod.pos.x;
            this.clickY = (float) mouseY - mod.pos.y;
            if (button == 0) {
                this.draggingMod = mod;
                break;
            }
            if (button != 1) break;
            mod.enabled = !mod.enabled;
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int button) {
        super.mouseReleased(mouseX, mouseY, button);
        if (button == 0) {
            this.draggingMod = null;
            this.clickX = 0.0f;
            this.clickY = 0.0f;
        }
    }

    @Override
    public void onGuiClosed() {
        ModManager.save();
    }
}
