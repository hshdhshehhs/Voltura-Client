package me.namenotfound128.voltura;

import me.namenotfound128.voltura.mod.ModManager;
import me.namenotfound128.voltura.screen.GuiEditModPositions;
import net.lax1dude.eaglercraft.v1_8.EaglercraftVersion;
import net.minecraft.client.Minecraft;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;

public class Voltura {

    public static final Logger log = LogManager.getLogger(EaglercraftVersion.projectForkName);

    public static void init() {
        log.info("Starting " + EaglercraftVersion.projectForkName + " v" + EaglercraftVersion.projectForkVersion);
        ModManager.addMods();
    }

    public static void tick() {
        if (Minecraft.getMinecraft().thePlayer != null) {
            if(Minecraft.getMinecraft().currentScreen == null && Minecraft.getMinecraft().gameSettings.keyOpenModPositionEditor.isPressed()) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiEditModPositions());
            }
            ModManager.tick();
        }
    }
    
}
