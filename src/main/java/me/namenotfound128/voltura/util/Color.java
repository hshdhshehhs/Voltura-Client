package me.namenotfound128.voltura.util;

public class Color {
    private int value;
    private static final double FACTOR = 0.7;

    public Color(int value) {
        this.value = value;
    }

    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }

    public Color(int r, int g, int b, int a) {
        this.value = (a & 0xFF) << 24 | (r & 0xFF) << 16 | (g & 0xFF) << 8 | (b & 0xFF) << 0;
        Color.testColorValueRange(r, g, b, a);
    }

    private static void testColorValueRange(int r, int g, int b, int a) {
        boolean rangeError = false;
        Object badComponentString = "";
        if (a < 0 || a > 255) {
            rangeError = true;
            badComponentString = (String) badComponentString + " Alpha";
        }
        if (r < 0 || r > 255) {
            rangeError = true;
            badComponentString = (String) badComponentString + " Red";
        }
        if (g < 0 || g > 255) {
            rangeError = true;
            badComponentString = (String) badComponentString + " Green";
        }
        if (b < 0 || b > 255) {
            rangeError = true;
            badComponentString = (String) badComponentString + " Blue";
        }
        if (rangeError) {
            throw new IllegalArgumentException("Color parameter outside of expected range:" + (String) badComponentString);
        }
    }

    public int getRed() {
        return this.getRGB() >> 16 & 0xFF;
    }

    public int getGreen() {
        return this.getRGB() >> 8 & 0xFF;
    }

    public int getBlue() {
        return this.getRGB() >> 0 & 0xFF;
    }

    public int getAlpha() {
        return this.getRGB() >> 24 & 0xFF;
    }

    public int getRGB() {
        return this.value;
    }

    public Color darker() {
        return new Color(Math.max((int) ((double) this.getRed() * 0.7), 0), Math.max((int) ((double) this.getGreen() * 0.7), 0), Math.max((int) ((double) this.getBlue() * 0.7), 0), this.getAlpha());
    }

    public Color brighter() {
        int r = this.getRed();
        int g = this.getGreen();
        int b = this.getBlue();
        int alpha = this.getAlpha();
        int i = 3;
        if (r == 0 && g == 0 && b == 0) {
            return new Color(i, i, i, alpha);
        }
        if (r > 0 && r < i) {
            r = i;
        }
        if (g > 0 && g < i) {
            g = i;
        }
        if (b > 0 && b < i) {
            b = i;
        }
        return new Color(Math.min((int) ((double) r / 0.7), 255), Math.min((int) ((double) g / 0.7), 255), Math.min((int) ((double) b / 0.7), 255), alpha);
    }
}