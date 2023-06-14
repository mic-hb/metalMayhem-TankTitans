package tankTitans;

import java.io.Serializable;

public class Scorer implements Serializable {
    private double score;

    public Scorer(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return score + "s";
    }
}
