package hu.obuda.uni.nik.labyrinthmaze;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.KeyEvent;
import android.view.View;

/**
 * Created by Tomi on 2017. 04. 26..
 */

public class GameView extends View {



        private int xMin = 0;
        private int xMax;
        private int yMin = 0;
        private int yMax;
        private float ballRadius = 40;
        private float ballX = ballRadius + 20;
        private float ballY = ballRadius + 40;
        private float ballSpeedX = 5;
        private float ballSpeedY = 3;
        private RectF ballBounds;
        private Paint paint;


        public GameView(Context context) {
            super(context);
            ballBounds = new RectF();
            paint = new Paint();

            //to enable keypad
            this.setFocusable(true);
            this.requestFocus();
        }


        @Override
        protected void onDraw(Canvas canvas) {

            ballBounds.set(ballX-ballRadius, ballY-ballRadius, ballX+ballRadius, ballY+ballRadius);
            paint.setColor(Color.GREEN);
            canvas.drawOval(ballBounds, paint);



            update();


            try {
                Thread.sleep(60);
            } catch (InterruptedException e) { }

            invalidate();  
        }


        private void update() {

            ballY += ballSpeedY;

            if (ballX + ballRadius > xMax) {
                ballSpeedX = -ballSpeedX;
                ballX = xMax-ballRadius;
            } else if (ballX - ballRadius < xMin) {
                ballSpeedX = -ballSpeedX;
                ballX = xMin+ballRadius;
            }
            if (ballY + ballRadius > yMax) {
                ballSpeedY = -ballSpeedY;
                ballY = yMax - ballRadius;
            } else if (ballY - ballRadius < yMin) {
                ballSpeedY = -ballSpeedY;
                ballY = yMin + ballRadius;
            }
        }


        @Override
        public void onSizeChanged(int w, int h, int oldW, int oldH) {

            xMax = w-1;
            yMax = h-1;
        }


        @Override
        public boolean onKeyUp(int keyCode, KeyEvent event) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    ballSpeedX++;
                    break;
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    ballSpeedX--;
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    ballSpeedY--;
                    break;
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    ballSpeedY++;
                    break;
                case KeyEvent.KEYCODE_DPAD_CENTER:
                    ballSpeedX = 0;
                    ballSpeedY = 0;
                    break;
                case KeyEvent.KEYCODE_A:

                    float maxRadius = (xMax > yMax) ? yMax / 2 * 0.9f  : xMax / 2 * 0.9f;
                    if (ballRadius < maxRadius) {
                        ballRadius *= 1.05;
                    }
                    break;
                case KeyEvent.KEYCODE_Z:
                    if (ballRadius > 20) {
                        ballRadius *= 0.95;
                    }
                    break;
            }
            return true;
        }


    }



