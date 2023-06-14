package tankTitans;

public class Highscore implements Comparable {
    protected String name;
    protected double score;

    public Highscore(String name, double score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
