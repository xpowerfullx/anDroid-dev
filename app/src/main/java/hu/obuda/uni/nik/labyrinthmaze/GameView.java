package hu.obuda.uni.nik.labyrinthmaze;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.Display;
import android.view.View;

import java.util.ArrayList;


/**
 * Created by Tomi on 2017. 04. 26..
 */

public class GameView extends View {



    private float xPos, xAccel, xVel = 0.0f;
    private float yPos, yAccel, yVel = 0.0f;
    private float xMax, yMax;
    private Bitmap ball;


        private Paint paint;
        private ArrayList<RectF> Rects;


        public GameView(Context context,Display disp) {
            super(context);

            Point size = new Point();
            disp.getSize(size);
            xMax = (float) size.x - 100;
            yMax = (float) size.y - 100;

            Bitmap ballSrc = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
            final int dstWidth = 100;
            final int dstHeight = 100;
            ball = Bitmap.createScaledBitmap(ballSrc, dstWidth, dstHeight, true);

        }



    public void updateBall(float xAccel,float yAccel) {
        float frameTime = 0.666f;
        xVel += (xAccel * frameTime);
        yVel += (yAccel * frameTime);

        float xS = (xVel / 2) * frameTime;
        float yS = (yVel / 2) * frameTime;

        xPos -= xS;
        yPos -= yS;

        if (xPos > xMax) {
            xPos = xMax;
        } else if (xPos < 0) {
            xPos = 0;
        }

        if (yPos > yMax) {
            yPos = yMax;
        } else if (yPos < 0) {
            yPos = 0;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(ball, xPos, yPos, null);
        invalidate();
    }



    private  void DrawMap(Canvas canvas)
    {
        int [] [] map= ReadLevelsClass.GetMap();

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









    }



