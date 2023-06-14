package tankTitans;

import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;

public class highscoreMenu extends PApplet {
    /* Default */
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS = 60;
    /* Rounds */
    private boolean is_highScore = true;

    /**
     *  Round: High Score
     */
    private PImage bg_highScore;
    private GUIButton b_highscore = new GUIButton("Back",620, 400, 100, 75, Color.CYAN);
    private boolean click_highScore = false;

    public static void main(String[] args) {

    }

    /**
     * Inisialisasi objek
     * seperti karakter, player, npc, menu, image, dll.
     */

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        /* Backgrounds */
        frameRate(FPS);
        bg_highScore = loadImage("src/assets/background/Main_Menu-1.png");
    }

    /**
     * Main program
     */

    public void draw() {
        textSize(100);
        fill(255);
        textAlign(CENTER,CENTER);
        text("Kontol!!!", 200, 200);
        if (is_highScore) {
            update(mouseX, mouseY, b_highscore);
            background(bg_highScore);
            fill(255, 245, 248);
            stroke(255, 245, 258);
            rect(b_highscore.getX(), b_highscore.getY(), b_highscore.getWidth(), b_highscore.getHeight());
        }
    }

    public void keyPressed() {
    }

    public void keyReleased() {
    }

    public void mousePressed(){
        if (click_highScore) {
            String[] args = {"runHighscore"};
            PApplet.runSketch(args, new tankTitans());
            surface.setVisible(false);
        }
    }

    void update(int x, int y, GUIButton b) {
        if ( overRect(b_highscore.getX(), b_highscore.getY(), b_highscore.getWidth(), b_highscore.getHeight()) ) {
            click_highScore = true;
        } else {
            click_highScore = false;
        }
    }

    private boolean overRect(int x, int y, int width, int height){
        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
            return true;
        } else {
            return false;
        }
    }
}
