package hu.obuda.uni.nik.labyrinthmaze.model;

/**
 * Created by Budai Krisztián on 2017. 04. 26.
 */

public class HighScore {
    private int rank;
    private String name;
    private int score;

    public HighScore(int rank, String name, int score) {
        this.rank = rank;
        this.name = name;
        this.score = score;
    }

    public int getRank(){
        return this.rank;
    }

    public String getName(){
        return this.name;
    }

    public int getScore(){
        return this.score;
    }
}
