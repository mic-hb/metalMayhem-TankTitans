package tankTitans;

import java.awt.*;

public class GUIButton {
    private String label;
    private int x, y;
    private int width, height;
    private Color bg_color;

    public GUIButton(String label, int x, int y, int width, int height, Color bg_color) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bg_color = bg_color;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getBg_color() {
        return bg_color;
    }

    public void setBg_color(Color bg_color) {
        this.bg_color = bg_color;
    }
}
