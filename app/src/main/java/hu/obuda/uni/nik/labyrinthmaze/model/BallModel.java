package hu.obuda.uni.nik.labyrinthmaze.model;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Adam on 2017. 04. 26..
 */

public class BallModel {
    private float ballRadius;
    private float ballX;
    private float ballY;
    private float ballSpeedX;
    private float ballSpeedY;
    private Paint paint;

    public BallModel() {
        ballRadius = 40;
        ballX = 0;
        ballY = 0;
        ballSpeedX = 0;
        ballSpeedY = 0;
        paint.setColor(Color.GREEN);
    }

    public float getBallRadius() {
        return ballRadius;
    }

    public Paint getPaint() {
        return paint;
    }

    public float getBallX() {
        return ballX;
    }

    public void setBallX(float ballX) {
        this.ballX = ballX;
    }

    public float getBallY() {
        return ballY;
    }

    public void setBallY(float ballY) {
        this.ballY = ballY;
    }

    public float getBallSpeedX() {
        return ballSpeedX;
    }

    public void setBallSpeedX(float ballSpeedX) {
        this.ballSpeedX = ballSpeedX;
    }

    public float getBallSpeedY() {
        return ballSpeedY;
    }

    public void setBallSpeedY(float ballSpeedY) {
        this.ballSpeedY = ballSpeedY;
    }
}
