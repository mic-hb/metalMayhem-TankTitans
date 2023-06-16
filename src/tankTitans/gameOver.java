package tankTitans;

import processing.core.PApplet;
import processing.core.PImage;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * @author Michael
 */

public class gameOver extends PApplet {
    /* Default */
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS = 60;

    /* Game Over Menu */
    private boolean is_mainMenu = true;
    private String inputText = "";
    private double score;
    public List<Highscore> listHighscores = new ArrayList<>();
    private static final String FILE_PATH = "listhighscores.txt";

    private PImage bg_mainMenu;
    private PImage PlayButton;
    private PImage ExitButton;
    private GUIButton textbox = new GUIButton(640, 540, 461, 92);
    private GUIButton b_PLAY = new GUIButton(640, 450, 217, 96);
    private GUIButton b_EXIT = new GUIButton(640, 580, 217, 96);
    private boolean click_playGame = false;
    private boolean click_exitgame = false;
    private boolean enter_name = false;
    private int ctr = 0;

    /**
     *  Round: Game Over
     */

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
        PlayButton = loadImage("src/assets/button/PlayAgain.png");
        ExitButton = loadImage("src/assets/button/MainMenu.png");
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
            if (enter_name) {
                subLoadFile(listHighscores);
                System.out.println(listHighscores);
                listHighscores.add(new Highscore(inputText, score));
                subSaveFile(listHighscores);
                System.out.println(listHighscores);
                System.out.println("Berhasil save highscore!");
            }

            String[] args = {"mainMenu"};
            PApplet.runSketch(args, new tankTitans());
            surface.setVisible(false);
            stop();
        }
    }

    private void subLoadFile(List<Highscore> topHighscores) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            topHighscores.addAll((List<Highscore>) ois.readObject());
            System.out.println("Berhasil load file");
        } catch (IOException | ClassNotFoundException e) {
            // Ignore if the file doesn't exist or there's an error reading from it
        }
    }

    private void subSaveFile(List<Highscore> topHighscores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(topHighscores);
            System.out.println("Berhasil save file");
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void subMenu() {
        if (args[0].equals("gameOver")) {
            textSize(128);
//            fill(0, 408, 612);
            textAlign(CENTER);
            text("GAME OVER", WIDTH/2, 280);

            image(PlayButton, b_PLAY.getX() - (b_PLAY.getWidth() / 2), b_PLAY.getY() - (b_PLAY.getHeight() / 2));
            image(ExitButton, b_EXIT.getX() - (b_EXIT.getWidth() / 2), b_EXIT.getY() - (b_EXIT.getHeight() / 2));
        }
        if (args[0].equals("winnerChickenDinner")) {
            Formatter formatted = new Formatter();
            formatted.format("%.2f", score);
            score = Double.valueOf(formatted.toString());
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

                Highscore newHighscore = new Highscore(inputText, score);
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
            if (inputText.length() < 7) {
                inputText += key;
            }
        } else if (key == BACKSPACE && inputText.length() > 0) {
            inputText = inputText.substring(0, inputText.length() - 1);
        } else if (key == ENTER && inputText.length() > 0) {
            is_mainMenu = false;
            enter_name = true;
        }
    }

    public void keyReleased() {
    }

    public void mousePressed(){
        if (args.equals("WinnerChickenDinner")) {
            if (click_exitgame) {
                String[] args = {"mainMenu"};
                PApplet.runSketch(args, new tankTitans());
                surface.setVisible(false);
                stop();
            }
            if (click_playGame) {
                String[] args = {"runBattle"};
                PApplet.runSketch(args, new battleMain());
                surface.setVisible(false);
                stop();
            }
        }
    }

    void update(int x, int y, GUIButton b) {
        if (overRect(b_EXIT.getX(), b_EXIT.getY(), b_EXIT.getWidth(), b_EXIT.getHeight())) {
            click_exitgame = true;
        } else {
            click_exitgame = false;
        }
        if (overRect(b_PLAY.getX(), b_PLAY.getY(), b_PLAY.getWidth(), b_PLAY.getHeight())) {
            click_playGame = true;
        } else {
            click_playGame = false;
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
