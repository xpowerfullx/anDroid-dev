package hu.obuda.uni.nik.labyrinthmaze.model;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Adam on 2017. 04. 27..
 */

public class HoleModel {

    private float holeX;
    private float holeY;
    private int holeRadius;
    private Paint holeColor;

    public HoleModel(float holeX, float holeY) {
        this.holeX = holeX;
        this.holeY = holeY;
        holeRadius = 50;
        holeColor.setColor(Color.GRAY);
    }

    public float getHoleX() {
        return holeX;
    }

    public float getHoleY() {
        return holeY;
    }

    public int getHoleRadius() {
        return holeRadius;
    }

    public Paint getHoleColor() {
        return holeColor;
    }
}
