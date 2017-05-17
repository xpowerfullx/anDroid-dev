package hu.obuda.uni.nik.labyrinthmaze.model;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Random;

/**
 * Created by Adam on 2017. 04. 27..
 */

public class Hole {

    public  static Random rand = new Random();
    private RectF rect;
    private Paint paintHole;


    public Hole(RectF rect) {
        this.setRect(rect);
        setPaintHole(new Paint());
        getPaintHole().setColor(Color.BLUE);
        getPaintHole().setStrokeWidth(3);
        getPaintHole().setStyle(Paint.Style.FILL);

    }




    public Paint getPaintHole() {
        return paintHole;
    }

    public void setPaintHole(Paint paintHole) {
        this.paintHole = paintHole;
    }

    public RectF getRect() {
        return rect;
    }

    public void setRect(RectF rect) {
        this.rect = rect;
    }
}
