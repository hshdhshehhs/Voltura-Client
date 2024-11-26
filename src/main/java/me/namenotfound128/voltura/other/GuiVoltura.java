package me.namenotfound128.voltura.other;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

public class GuiVoltura extends GuiScreen {

   @Override
    public void drawScreen(int mx, int my, float pt) {
        this.drawDefaultBackground();
this.drawCenteredString(this.fontRendererObj, "We got left hand, Keystrokes and more to come.", this.width / 2, this.height / 15, -1);

    }
    

}