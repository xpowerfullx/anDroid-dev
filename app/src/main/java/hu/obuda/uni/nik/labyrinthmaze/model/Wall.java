package hu.obuda.uni.nik.labyrinthmaze.model;

/**
 * Created by Tomi on 2017. 05. 13..
 */

public class Wall {
private  int x;
    private  int y;

    public Wall(int X, int Y) {
      this.x=X;
        this.y=Y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
