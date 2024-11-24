package me.namenotfound128.voltura.util;

public interface IRenderWidget {

    Rectangle renderRectangle();

    void beginRender();

    void endRender();

    void fillBackground(int color);

    Rectangle string(String text, float x, float y, int color);

    float stringWidth(String text);

    float stringHeight(String text);

    void box(float x, float y, float width, float height, int color);

}
