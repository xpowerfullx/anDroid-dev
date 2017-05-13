package hu.obuda.uni.nik.labyrinthmaze.model;

/**
 * Created by Budai Krisztián on 2017. 05. 13.
 */

public class Player {
    private String name;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName(){
        return this.name;
    }

    public int getScore(){
        return this.score;
    }
}
