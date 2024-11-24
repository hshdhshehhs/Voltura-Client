package me.namenotfound128.voltura.mod;

import me.namenotfound128.voltura.util.Rectangle;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.Minecraft;

public class Mod {

    public final String name;
    public final String description;
    public Rectangle pos = new Rectangle(50.0f, 60.0f, 20.0f, 20.0f);
    public boolean enabled;
    protected Minecraft mc;

    public Mod(String name, String description) {
        this.name = name;
        this.description = description;
        this.enabled = true;
        this.mc = Minecraft.getMinecraft();
    }

    public void tick() {
    }

    public void key(int key) {
    }

    public void mouse(int button) {
    }

    public void render() {
    }

    public void renderPreview() {
        this.render();
    }

    protected void beginRender() {
        GlStateManager.pushMatrix();
        GlStateManager.translate(pos.x, pos.y, 0.0f);
    }

    protected void endRender() {
        GlStateManager.translate(-pos.x, -pos.y, 0.0f);
        GlStateManager.popMatrix();
    }
    
}
