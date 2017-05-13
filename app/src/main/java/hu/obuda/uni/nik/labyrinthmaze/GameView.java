package hu.obuda.uni.nik.labyrinthmaze;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.Display;
import android.view.View;


import java.util.ArrayList;

import hu.obuda.uni.nik.labyrinthmaze.model.WallSegment;


/**
 * Created by Tomi on 2017. 04. 26..
 */

public class GameView extends View {

    private float xPos, xAccel, xVel = 0.0f;
    private float yPos, yAccel, yVel = 0.0f;
    private float xMax, yMax;
    private Bitmap ball;


   // private ArrayList<DrawLine> Lines;

    private Paint paint;
    private ArrayList<RectF> Rects;

    public GameView(Context context, Display disp) {
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

    public void updateBall(float xAccel, float yAccel) {
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
        DrawMap(canvas);
        invalidate();
    }

    private  void DrawMap(Canvas canvas)
    {
        MapGenerator mapgen= new MapGenerator();


        WallSegment[] [] map=new WallSegment[10][10];

        for( int i = 0; i < map.length; i++ )
        {
            for( int j = 0; j < map[i].length; j++ )
            {
                WallSegment model = new WallSegment(i,j);
                model.setNorthWall(true);
               // model.setWestWall(true);
               // model.setSouthWall(true);
               // model.setSouthWall(true);

               // model.setEastWall(true);
                //model.se(true);
                //model.setSouthWall(true);
                map[i][j]=model;

            }
        }

        int valami=0;

        //kirajzolás
        int counter=0;

        paint = new Paint();
        paint.setColor(Color.GREEN);

        int sizewidth=getWidth()/10;
        int sizehieght=getHeight()/10;

        for( int i = 0; i < map.length; i++ )
        {
            for( int j = 0; j < map[i].length; j++ )
            {

                if(map[i][j].isNorthWall()==true) {

                   // Rect line= new Rect(sizewidth*i, sizehieght*j, sizewidth, 2);

                    //canvas.drawRect(line, paint);
                    canvas.drawLine(sizewidth*i, sizehieght*j, sizewidth*i+sizewidth, sizehieght*j+sizehieght, paint);
                    //canvas.drawRect(10, 10, 20, 20, paint);

                    //  canvas.drawLine(20, 0, 0, 20, paint);

                }
                if(map[i][j].isSouthWall()==true)
                {

                    //canvas.drawRect(10, 10, 20, 20, paint);
                    //Rect line= new Rect(sizewidth*i, sizehieght*j+sizehieght, sizewidth*i+sizewidth, sizehieght*j+sizehieght);
                    canvas.drawLine(sizewidth*i, sizehieght*j+sizehieght, sizewidth*i+sizewidth, sizehieght*j+sizehieght, paint);
                }
                if(map[i][j].isEastWall()==true)
                {
                    //Rect line= new Rect(sizewidth*i+sizewidth, sizehieght*j, sizewidth*i+sizewidth, sizehieght*j+sizehieght);
                   // canvas.drawRect(line, paint);
                    //canvas.drawRect(10, 10, 20, 20, paint);
                    canvas.drawLine(sizewidth*i+sizewidth, sizehieght*j, sizewidth*i+sizewidth, sizehieght*j+sizehieght, paint);
                }
                if(map[i][j].isWestWall()==true)
                {
                    //Rect line= new Rect(sizewidth*i, sizehieght*j, sizewidth*i, sizehieght*j+sizehieght);
                    //canvas.drawRect(line, paint);
                    //canvas.drawRect(10, 10, 20, 20, paint);
                    canvas.drawLine(sizewidth*i, sizehieght*j, sizewidth*i, sizehieght*j+sizehieght, paint);
                }


            }
        }
    }
}