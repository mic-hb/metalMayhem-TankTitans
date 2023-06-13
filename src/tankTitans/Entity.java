/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tankTitans;

import processing.core.PImage;

/**
 * @author Michael
 */
public abstract class Entity {
    /* Properti Dasar */
    protected int HP, ATK, DEF;
    protected int x, y;
    protected int res;
    protected boolean is_broken;

    /* Properti untuk display (termasuk animasi) */
    protected PImage[] idle;
    protected PImage[] broken;
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
        this.is_broken = false;
    }

    public void brokeDown() {
        is_broken = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRes() {
        return res;
    }

    public int getHP() {
        return HP;
    }

    public int getATK() {
        return ATK;
    }

    public void getHit(int atk) {
        this.HP -= atk;
    }

    public boolean is_broken() {
        return is_broken;
    }
}
