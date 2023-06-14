package tankTitans;

import processing.core.PApplet;
import processing.core.PImage;
import java.awt.*;
import java.util.Formatter;

/**
 * @author Michael
 */

public class gameOver extends PApplet {
    /* Default */
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS = 60;

    /* Rounds */
    private boolean is_mainMenu = true;
    private double score;
    private String inputText = "";

    /**
     *  Round: Main Menu
     */
    private PImage bg_mainMenu;
    private GUIButton textbox = new GUIButton("inputText", 640, 540, 461, 92, Color.WHITE);
//    private GUIButton b_highscore = new GUIButton("", 620, 400, 100, 75, Color.CYAN);
//    private GUIButton b_exit = new GUIButton("", 620, 500, 100, 75, Color.CYAN);
    private boolean click_playGame = false;
    private int ctr = 0;

//    public static void main(String[] args) {
//        // TODO code application logic here
//        PApplet.main("tankTitans.tankTitans");
//    }

    public gameOver(String[] args, double score){
        this.args = args;
        this.score = score;
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
        bg_mainMenu = loadImage("src/assets/background/background_1.png");
    }

    /**
     * Main program
     */
    public void draw() {
        if (is_mainMenu) {
            update(mouseX, mouseY, textbox);
            background(0, 0, 0);
            fill(255, 245, 248);
            stroke(255, 245, 258);

            subMenu();
            ctr++;

            if (ctr == 120) {
//                is_mainMenu = false;
            }
        } else {
            String[] args = {"mainMenu"};
            PApplet.runSketch(args, new tankTitans());
            surface.setVisible(false);
            stop();
        }
    }

    private void subMenu() {
        if (args[0].equals("gameOver")) {
            textSize(128);
//            fill(0, 408, 612);
            textAlign(CENTER);
            text("GAME OVER", 640, 360);
        }
        if (args[0].equals("winnerChickenDinner")) {
            Formatter formatted = new Formatter();
            formatted.format("%.2f", score);
            textSize(128);
            textAlign(CENTER, CENTER);
            text("YOU WIN", 640, 256);
            textSize(64);
            text("Your score : " + formatted.toString() + "s", 640, 392);

            rect(textbox.getX() - textbox.getWidth() / 2, textbox.getY() - textbox.getHeight() / 2, textbox.getWidth(), textbox.getHeight());

            float text_size = (float) (textbox.getHeight() / 1.8);
            if (inputText.length() > 0) {
                fill(0);
                textSize(text_size);
                textAlign(CENTER, CENTER);
                text(inputText, textbox.getX(), textbox.getY() - 8);
            } else {
                fill(100);
                textSize((float) (text_size * 0.8));
                textAlign(CENTER, CENTER);
                text("Input your name", textbox.getX(), textbox.getY() - 8);
            }
        }
    }

    public void keyPressed() {
        if (key != ENTER && key != BACKSPACE && key != SHIFT && key != CONTROL && key != ALT) {
            if (inputText.length() < 5) {
                inputText += key;
            }
        } else if (key == BACKSPACE && inputText.length() > 0) {
            inputText = inputText.substring(0, inputText.length() - 1);
        }
    }

    public void keyReleased() {
    }

    public void mousePressed(){
        if (click_playGame) {

        }
    }

    void update(int x, int y, GUIButton b) {
        if ( overRect(textbox.getX(), textbox.getY(), textbox.getWidth(), textbox.getHeight()) ) {
//            click_playGame = true;
        } else {
//            click_playGame = false;
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
