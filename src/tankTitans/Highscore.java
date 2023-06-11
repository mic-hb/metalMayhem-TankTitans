package tankTitans;

import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Highscore extends PApplet {
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
    private GUIButton b_highscore = new GUIButton(620, 400, 100, 75, Color.CYAN);
    private boolean click_highScore = false;
    private static final String FILE_PATH = "top_scorers.txt";
    private static final int MAX_TOP_SCORERS = 3;
    public List<Scorer> topScorers = new ArrayList<>();
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
        battleMain bm = new battleMain();
        loadTopScorers(topScorers);
        Scorer newScorer = new Scorer(bm.score);
        topScorers.add(newScorer);
        topScorers.sort(Comparator.comparingDouble(Scorer::getScore).reversed());
        if (topScorers.size() > MAX_TOP_SCORERS) {
            topScorers = topScorers.subList(0, MAX_TOP_SCORERS);
        }
        if (topScorers.size() == 1) {
            text("1. " + topScorers.get(0).toString(),width/2,150);
        } else if (topScorers.size() == 2) {
            text("1. " + topScorers.get(0).toString(),width/2,150);
            text("2. " + topScorers.get(1).toString(),width/2,200);
        } else {
            text("1. " + topScorers.get(0).toString(),width/2,150);
            text("2. " + topScorers.get(1).toString(),width/2,200);
            text("3. " + topScorers.get(2).toString(),width/2,250);
        }
        saveTopScorers(topScorers);
    }

    /**
     * Main program
     */

    public void draw() {
        textSize(25);
        fill(0);
        textAlign(CENTER,CENTER);
        text("Highscore niqqa", width/2, 100);

        if (is_highScore) {
            update(mouseX, mouseY, b_highscore);
//            background(bg_highScore);
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

    private static void loadTopScorers(List<Scorer> topScorers) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            topScorers.addAll((List<Scorer>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            // Ignore if the file doesn't exist or there's an error reading from it
        }
    }

    private static void saveTopScorers(List<Scorer> topScorers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(topScorers);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
