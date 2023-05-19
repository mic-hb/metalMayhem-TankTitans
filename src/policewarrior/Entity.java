/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package policewarrior;

import processing.core.PImage;

/**
 * @author Michael
 */
public abstract class Entity {
    /* Properti Dasar */
    protected int HP, ATK, DEF;
    protected int x, y;

    /* Properti untuk display (termasuk animasi) */
    protected PImage[] idle;
    protected int timing;
    protected int frame;
    protected int total_frame;
    protected int reset_frame;

    public Entity(int HP, int ATK, int DEF, int x, int y) {
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
