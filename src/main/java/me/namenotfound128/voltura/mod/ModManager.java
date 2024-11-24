package me.namenotfound128.voltura.mod;

import java.util.ArrayList;
import java.util.List;

import me.namenotfound128.voltura.Voltura;
import me.namenotfound128.voltura.mod.impl.KeystrokesMod;
import me.namenotfound128.voltura.screen.GuiConfiguring;
import net.lax1dude.eaglercraft.v1_8.EagRuntime;
import net.lax1dude.eaglercraft.v1_8.EaglerInputStream;
import net.lax1dude.eaglercraft.v1_8.EaglerOutputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ModManager {

    public static final List<Mod> mods = new ArrayList<>();

    public static KeystrokesMod keystrokesMod;

    public static void addMods() {
        mods.add(keystrokesMod = new KeystrokesMod());
    }

    public static void render() {
        if(Minecraft.getMinecraft().currentScreen instanceof GuiConfiguring)
            return;

        for (int i = 0; i < mods.size(); i++) {
            Mod mod = mods.get(i);
            if(mod.enabled)
                mod.render();
        }
    }

    public static void renderPreview() {
        for (int i = 0; i < mods.size(); i++) {
            Mod mod = mods.get(i);
            mod.renderPreview();
        }
    }

    public static void mouseClicked(int button) {
        for (int i = 0; i < mods.size(); i++) {
            Mod mod = mods.get(i);
            if(mod.enabled)
                mod.mouse(button);
        }
    }

    public static void key(int key) {
        for (int i = 0; i < mods.size(); i++) {
            Mod mod = mods.get(i);
            if(mod.enabled)
                mod.key(key);
        }
    }

    public static void tick() {
        for (int i = 0; i < mods.size(); i++) {
            Mod mod = mods.get(i);
            if(mod.enabled)
                mod.tick();
        }
    }

    public static Mod getMod(String name) {
        for (int i = 0; i < mods.size(); i++) {
            Mod mod = mods.get(i);
            if (mod.name.equals(name))
                return mod;
        }
        return null;
    }

    public static void load() {
        NBTTagList list;
        byte[] storedData = EagRuntime.getStorage("voltura");
        if (storedData == null) {
            return;
        }
        try {
            NBTTagCompound base = CompressedStreamTools.readCompressed(new EaglerInputStream(storedData));
            list = base.getTagList("modConfig", 10);
        } catch (Exception e) {
            Voltura.log.error(e);
            return;
        }
        for (int i = 0; i < list.tagCount(); ++i) {
            Mod mod;
            NBTTagCompound modTag = list.getCompoundTagAt(i);
            if (!modTag.hasKey("name", 8) || !modTag.hasKey("enabled", 1) || !modTag.hasKey("x", 5) || !modTag.hasKey("y", 5) || (mod = getMod(modTag.getString("name"))) == null)
                continue;
            mod.enabled = modTag.getBoolean("enabled");
            mod.pos.x = modTag.getFloat("x");
            mod.pos.y = modTag.getFloat("y");

            Voltura.log.info("Loaded settings for " + mod.name);
        }
    }

    public static void save() {
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < mods.size(); i++) {
            Mod mod = mods.get(i);
            NBTTagCompound modTag = new NBTTagCompound();
            modTag.setString("name", mod.name);
            modTag.setBoolean("enabled", mod.enabled);
            modTag.setFloat("x", mod.pos.x);
            modTag.setFloat("y", mod.pos.y);

            list.appendTag(modTag);
        }
        NBTTagCompound base = new NBTTagCompound();
        base.setTag("modConfig", list);
        try {
            EaglerOutputStream eos = new EaglerOutputStream();
            CompressedStreamTools.writeCompressed(base, eos);
            EagRuntime.setStorage("voltura", eos.toByteArray());
        } catch (Exception e) {
            Voltura.log.error(e);
        }
    }
    
}
