/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tankTitans;

import processing.core.PApplet;
import processing.core.PImage;
import java.awt.*;

public class tankTitans extends PApplet {
    /* Default */
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS = 60;

    /* Rounds */
    private boolean is_mainMenu = true;

    /**
     *  Round: Main Menu
     */
    private PImage bg_mainMenu;
    private PImage PlayGameButton;
    private PImage HighScoreButton;
    private PImage ExitButton;
    private PImage logo;
    private int buttonX, buttonY, buttonWidth, buttonHeight;
    private GUIButton b_playGame = new GUIButton(640, 350, 221, 64);
    private GUIButton b_highscore = new GUIButton(640, 450, 221, 64);
    private GUIButton b_exit = new GUIButton(640, 550, 221, 64);
    private boolean click_playGame = false;
    private boolean click_highScore = false;
    private boolean click_exit = false;

    public static void main(String[] args) {
        // TODO code application logic here
        PApplet.main("tankTitans.tankTitans");
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
        bg_mainMenu = loadImage("src/assets/background/Main_Menu-1.png");
        PlayGameButton = loadImage("src/assets/button/PlayGameButton.png");
        HighScoreButton = loadImage("src/assets/button/HighScoreButton.png");
        ExitButton = loadImage("src/assets/button/ExitButton.png");
        logo = loadImage("src/assets/button/logo_1.png");
    }

    /**
     * Main program
     */
    public void draw() {
        if (is_mainMenu) {
            update(mouseX, mouseY, b_playGame);
            background(bg_mainMenu);
            fill(255, 245, 248);
            stroke(255, 245, 258);

            image(PlayGameButton, b_playGame.getX() - (b_playGame.getWidth() / 2), b_playGame.getY() - (b_playGame.getHeight() / 2));
            image(HighScoreButton, b_highscore.getX() - (b_highscore.getWidth() / 2), b_highscore.getY() - (b_playGame.getHeight() / 2));
            image(ExitButton, b_exit.getX() - (b_exit.getWidth() / 2), b_exit.getY() - (b_playGame.getHeight() / 2));
            image(logo, 640 - (640 / 2), 150 - (201 / 2));
        }
    }

    public void keyPressed() {
    }

    public void keyReleased() {
    }

    public void mousePressed(){

        if (click_playGame) {
            String[] args = {"runBattle"};
            PApplet.runSketch(args, new battleMain());
            surface.setVisible(false);
            stop();
        }
        if (click_highScore) {
            String[] args = {"runHighscore"};
            PApplet.runSketch(args, new highscoreMenu());
            surface.setVisible(false);
            stop();
        }
        if (click_exit) {
            surface.setVisible(false);
            exit();
        }
    }

    void update(int x, int y, GUIButton b) {
        if ( overRect(b_playGame.getX(), b_playGame.getY(), b_playGame.getWidth(), b_playGame.getHeight()) ) {
            click_playGame = true;
        } else {
            click_playGame = false;
        }

        if ( overRect(b_highscore.getX(), b_highscore.getY(), b_highscore.getWidth(), b_highscore.getHeight()) ) {
            click_highScore = true;
        } else {
            click_highScore = false;
        }

        if ( overRect(b_exit.getX(), b_exit.getY(), b_exit.getWidth(), b_exit.getHeight()) ) {
            click_exit = true;
        } else {
            click_exit = false;
        }
    }

    private boolean overRect(int x, int y, int width, int height){
        if (mouseX >= x - (width / 2) && mouseX <= x + (width / 2) && mouseY >= y - (height / 2) && mouseY <= y + (height / 2)) {
            return true;
        } else {
            return false;
        }
    }
}
