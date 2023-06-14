package tankTitans;

import java.io.Serializable;

public class Highscore implements Serializable, Comparable<Highscore> {
    private String name;
    private double score;

    public Highscore(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return name + " - " + score + "s";
    }

    @Override
    public int compareTo(Highscore h) {
        return 0;
    }
}
