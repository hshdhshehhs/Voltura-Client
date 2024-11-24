package me.namenotfound128.voltura.mod.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MathHelper;

import java.util.function.Supplier;

import me.namenotfound128.voltura.animation.Animation;
import me.namenotfound128.voltura.animation.Easing;
import me.namenotfound128.voltura.mod.Mod;
import me.namenotfound128.voltura.util.Color;
import me.namenotfound128.voltura.util.VanillaRenderWidget;

public class KeystrokesMod extends Mod {
    private final KeystrokesMode mode = KeystrokesMode.WASD_JUMP_MOUSE;
    private final boolean settingAnimation = false;

    private float mouseX = 0.0f;
    private float mouseY = 0.0f;
    private float lastMouseX = 0.0f;
    private float lastMouseY = 0.0f;

    public KeystrokesMod() {
        super("Keystrokes", "Shows your input");
    }

    private final VanillaRenderWidget widget = new VanillaRenderWidget(this);

    @Override
    public void render() {
        beginRender();
        int width = 0;
        int height = 0;
        for (Key key : this.mode.getKeys()) {
            if (settingAnimation)
                key.updateAnimation();
            if (key.getX() + key.getWidth() > width) {
                width = key.getX() + key.getWidth();
            }
            if (key.getY() + key.getHeight() > height) {
                height = key.getY() + key.getHeight();
            }
            float textWidth = widget.stringWidth(key.getName());
            int animValue = settingAnimation ? (int) (255 * key.animation.getValue()) : (key.isDown() ? 255 : 0);
            widget.box(key.getX(), key.getY(), key.getWidth(), key.getHeight(), new Color(animValue, animValue, animValue, 80).getRGB());
            widget.string(key.getName(), (float) key.getX() + (float) key.getWidth() / 2.0f - textWidth / 2.0f, (float) key.getY() + (float) key.getHeight() / 2.0f - 4.0f, new Color(255 - animValue, 255 - animValue, 255 - animValue).getRGB());
        }
        float delta = this.mc.timer.renderPartialTicks;
        float calculatedMouseX = this.lastMouseX + (this.mouseX - this.lastMouseX) * delta;
        float calculatedMouseY = this.lastMouseY + (this.mouseY - this.lastMouseY) * delta;
        widget.box(1.0f, height + 2, width - 1, 34.0f, 0x50000000);
        widget.box((float) width / 2.0f + calculatedMouseX, (float) (height + 20) + calculatedMouseY, 1.0f, 1.0f, -1);
        this.pos.applySize(width, height + 36);
        endRender();
    }

    @Override
    public void tick() {
        this.lastMouseX = this.mouseX;
        this.lastMouseY = this.mouseY;
        this.mouseX *= 0.75f;
        this.mouseY *= 0.75f;
    }

    public void mouse(float yaw, float pitch) {
        this.mouseX += yaw / 40.0f;
        this.mouseY -= pitch / 40.0f;
        this.mouseX = MathHelper.clamp_float(this.mouseX, -(this.pos.width / 2.0f) + 1.0f, this.pos.width / 2.0f - 1.0f);
        this.mouseY = MathHelper.clamp_float(this.mouseY, -18.0f, 15.0f);
    }

    public static class Key {
        private static final Key W = new Key(() -> "W", Minecraft.getMinecraft().gameSettings.keyBindForward, 21, 1, 18, 18);
        private static final Key A = new Key(() -> "A", Minecraft.getMinecraft().gameSettings.keyBindLeft, 1, 21, 18, 18);
        private static final Key S = new Key(() -> "S", Minecraft.getMinecraft().gameSettings.keyBindBack, 21, 21, 18, 18);
        private static final Key D = new Key(() -> "D", Minecraft.getMinecraft().gameSettings.keyBindRight, 41, 21, 18, 18);
        private static final Key LMB = new Key(() -> "LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 1, 41, 28, 18);
        private static final Key RMB = new Key(() -> "RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 31, 41, 28, 18);
        private static final Key LMB_CPS = new Key(Key::getLMBText, Minecraft.getMinecraft().gameSettings.keyBindAttack, 1, 41, 28, 18);
        private static final Key RMB_CPS = new Key(Key::getRMBText, Minecraft.getMinecraft().gameSettings.keyBindUseItem, 31, 41, 28, 18);
        private final Supplier<String> name;
        private final KeyBinding keybind;
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        public Animation animation = new Animation(0.5f, 1, 0);

        private static String getLMBText() {
            return "LMB";
        }

        private static String getRMBText() {
            return "RMB";
        }

        public Key(Supplier<String> name, KeyBinding keybind, int x, int y, int width, int height) {
            this.name = name;
            this.keybind = keybind;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        private boolean lastPressed = true;

        public void updateAnimation() {
            if (lastPressed != isDown()) {
                animation = new Animation(.25f, animation.getValue(), isDown() ? 1 : 0, Easing.LINEAR);
            }

            lastPressed = isDown();
        }

        public boolean isDown() {
            return this.keybind.isKeyDown();
        }

        public int getHeight() {
            return this.height;
        }

        public String getName() {
            return this.name.get();
        }

        public int getWidth() {
            return this.width;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }

    public enum KeystrokesMode {
        WASD(Key.W, Key.A, Key.S, Key.D),
        WASD_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB),
        WASD_SPRINT(Key.W, Key.A, Key.S, Key.D, new Key(() -> "Sprint", Minecraft.getMinecraft().gameSettings.keyBindSprint, 1, 41, 58, 18)),
        WASD_SPRINT_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB, new Key(() -> "Sprint", Minecraft.getMinecraft().gameSettings.keyBindSprint, 1, 61, 58, 18)),
        WASD_JUMP(Key.W, Key.A, Key.S, Key.D, new Key(() -> "§m          §r", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 41, 58, 18)),
        WASD_JUMP_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB, new Key(() -> "§m          §r", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 61, 58, 18)),
        WASD_JUMP_MOUSE_CPS(Key.W, Key.A, Key.S, Key.D, Key.LMB_CPS, Key.RMB_CPS, new Key(() -> "§m          §r", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 61, 58, 18));

        private final Key[] keys;
        private int width = 0;
        private int height = 0;

        private KeystrokesMode(Key... keysIn) {
            for (Key key : this.keys = keysIn) {
                this.width = Math.max(this.width, key.getX() + key.getWidth());
                this.height = Math.max(this.height, key.getY() + key.getHeight());
            }
        }

        public int getHeight() {
            return this.height;
        }

        public int getWidth() {
            return this.width;
        }

        public Key[] getKeys() {
            return this.keys;
        }
    }
}
