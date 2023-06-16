package tankTitans;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
    private PImage BackButton;
    private int y1 = 150;
    private int y2 = 200;
    private int y3 = 250;
    private GUIButton b_BACK;
    private boolean click_backButton = false;
    private static final String FILE_PATH = "listhighscores.txt";
    private static final int MAX_TOP_SCORERS = 3;
    public List<Highscore> topHighscores = new ArrayList<>();
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
        loadTopScorers(topHighscores);
        b_BACK = new GUIButton(640, 720 - 180, 368, 96);
        BackButton = loadImage("src/assets/button/BackButton_.png");
    }

    /**
     * Main program
     */

    public void draw() {
        if (is_highScore) {
            update(mouseX, mouseY, b_BACK);
            background(bg_highScore);

            backButton();

            textSize(25);
            fill(0);
            textAlign(CENTER,CENTER);
            text("Highscore!!!", width/2, 100);

            topHighscores.sort(Comparator.comparingDouble(Highscore::getScore));
            if (topHighscores.size() > MAX_TOP_SCORERS) {
                topHighscores = topHighscores.subList(0, MAX_TOP_SCORERS);
            }
            if (topHighscores.size() == 1) {
                text("1. " + topHighscores.get(0).toString(),width/2,y1);
                text("2. -",width/2,y2);
                text("3. -",width/2,y3);
            } else if (topHighscores.size() == 2) {
                text("1. " + topHighscores.get(0).toString(),width/2,y1);
                text("2. " + topHighscores.get(1).toString(),width/2,y2);
                text("3. -",width/2,y3);
            } else if (topHighscores.size() >= 3) {
                text("1. " + topHighscores.get(0).toString(), width / 2, y1);
                text("2. " + topHighscores.get(1).toString(), width / 2, y2);
                text("3. " + topHighscores.get(2).toString(), width / 2, y3);
            } else if (topHighscores.size() == 0) {
                text("1. -",width/2,y1);
                text("2. -",width/2,y2);
                text("3. -",width/2,y3);
                System.out.println("NUll");
            }
        }
    }

    private void backButton() {
        fill(25, 24, 24);
        stroke(255, 245, 258);
//        rect(b_BACK.getX() - (b_BACK.getWidth() / 2), b_BACK.getY() - (b_BACK.getHeight() / 2), b_BACK.getWidth(), b_BACK.getHeight());
        image(BackButton, b_BACK.getX() - (b_BACK.getWidth() / 2), b_BACK.getY() - (b_BACK.getHeight() / 2));
    }

    public void keyPressed() {
    }

    public void keyReleased() {
    }

    public void mousePressed(){
        if (click_backButton) {
            String[] args = {"runHighscore"};
            PApplet.runSketch(args, new tankTitans());
            surface.setVisible(false);
            stop();
        }
    }

    void update(int x, int y, GUIButton b) {
        if ( overRect(b_BACK.getX(), b_BACK.getY(), b_BACK.getWidth(), b_BACK.getHeight()) ) {
            click_backButton = true;
        } else {
            click_backButton = false;
        }
    }

    private boolean overRect(int x, int y, int width, int height){
        if (mouseX >= x - (width / 2) && mouseX <= x + (width / 2) && mouseY >= y - (height / 2) && mouseY <= y + (height / 2)) {
            return true;
        } else {
            return false;
        }
    }

    private void loadTopScorers(List<Highscore> topHighscores) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            topHighscores.addAll((List<Highscore>) ois.readObject());
            System.out.println(this.topHighscores);
        } catch (IOException | ClassNotFoundException e) {
            // Ignore if the file doesn't exist or there's an error reading from it
        }
    }

    private void saveTopScorers(List<Highscore> topHighscores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(topHighscores);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
