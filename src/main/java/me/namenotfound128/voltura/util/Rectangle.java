package me.namenotfound128.voltura.util;

public class Rectangle {
    public float x;
    public float y;
    public float width;
    public float height;

    public Rectangle(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle add(float x, float y, float width, float height) {
        this.x += x;
        this.y += y;
        this.width += width;
        this.height += height;
        return this;
    }

    public void applySize(Rectangle other) {
        this.width = other.width;
        this.height = other.height;
    }

    public void applySize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public boolean isHovered(float mouseX, float mouseY) {
        return mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX2() && mouseY < this.getY2();
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getX2() {
        return this.x + this.width;
    }

    public float getY2() {
        return this.y + this.height;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }
}
