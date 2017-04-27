package hu.obuda.uni.nik.labyrinthmaze;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.KeyEvent;
import android.view.View;

import java.util.ArrayList;


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
        private ArrayList<RectF> Rects;


        public GameView(Context context) {
            super(context);
            ballBounds = new RectF();
            paint = new Paint();
            Rects=new ArrayList<RectF>();
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


    private  void DrawMap(Canvas canvas)
    {
        int [] [] map=ReadLevels.GetMap();

        for( int i = 0; i < map.length; i++ )
        {
            for( int j = 0; j < map[i].length; j++ )
            {
                if(map[i][j]==1) {
                    Rects.add(new RectF() );
                }


            }
        }

        //kirajzolás
        int counter=0;

        for( int i = 0; i < map.length; i++ )
        {
            for( int j = 0; j < map[i].length; j++ )
            {
                if(map[i][j]==1) {
                    Rects.get(counter).set(i*20, j*20, 20, 20);
                    paint.setColor(Color.BLACK);
                    canvas.drawRect(Rects.get(counter), paint);
                    counter++;
                }
            }
        }
    }

    @Override
        public void onSizeChanged(int w, int h, int oldW, int oldH) {

            xMax = w-1;
            yMax = h-1;
        }
    }



