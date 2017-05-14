package hu.obuda.uni.nik.labyrinthmaze;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

import java.lang.reflect.Array;
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
    private float wall = 25.0f;
    MapGenerator mapgen;
    WallSegment[][] map;
    Path path;
    Paint paintWall;
    boolean utkozes;
    Paint p = new Paint(Color.BLUE);

    Region clip;

    Path path2;
    private Paint paint;
    private Paint redpaint;
    private ArrayList<RectF> Rects;
    private ArrayList<Rect> falak;
    private  int WallType=0;

    public GameView(Context context, Display disp) {
        super(context);


        Point size = new Point();
        disp.getSize(size);

         path2 = new Path();

        clip= new Region(0, 0, getWidth(), getHeight());

        mapgen = new MapGenerator();
        map = mapgen.generateNewMap();
        path = new Path();
        paintWall = new Paint();
        paintWall.setColor(Color.BLACK);
        paintWall.setStrokeWidth(2);
        paintWall.setStyle(Paint.Style.STROKE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);


        int sizewidth= (displayMetrics.widthPixels - 10)/10;
        int sizeheight= (displayMetrics.heightPixels - 50)/10;

        Bitmap ballSrc = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        final int dstWidth = (displayMetrics.widthPixels - 10)/11;
        final int dstHeight = (displayMetrics.widthPixels - 10)/11;

        xMax = (displayMetrics.widthPixels - 50);
        yMax =(displayMetrics.heightPixels - 50);
        ball = Bitmap.createScaledBitmap(ballSrc, dstWidth, dstHeight, true);

        redpaint = new Paint();
        redpaint.setColor(Color.RED);
        redpaint.setStrokeWidth(2);
        redpaint.setStyle(Paint.Style.STROKE);
        falak = new ArrayList<Rect>();


        //get wall lines for path
        for (int i = 0; i <10; i++) {
            for (int j = 0; j < 10; j++) {

                if (map[j][i].isNorthWall()) {
                    path.moveTo(i * sizewidth, j * sizeheight);
                    path.lineTo(i * sizewidth, (j + 1) * sizeheight);
                    System.out.println("PATH: " + "X " + i * sizewidth + " " + " ||Y " + j * sizeheight + " ||X' " + i * sizewidth + " ||Y' " + (j + 1) * sizeheight);
                    Rect r = new Rect(i * sizewidth -1 ,j * sizeheight,  i * sizewidth +1,(j+1) * sizeheight);
                    falak.add(r);
                    System.out.println("LEFT: " + r.left + " ||TOP: " + r.top + " ||Right: " + r.right + " ||Bottom: " + r.bottom);

                }
                if (map[j][i].isSouthWall()) {
                    path.moveTo((i + 1) * sizewidth, j * sizeheight);
                    path.lineTo((i + 1) * sizewidth, (j + 1) * sizeheight);
                    //System.out.println("PATH: " + "X " + (i + 1) * sizewidth + " " + " ||Y " + j * sizeheight +" ||X' " +(i + 1) * sizewidth + " ||Y' " + (j + 1) * sizeheight);

                    Rect r = new Rect( (i+1) * sizewidth-1,j * sizeheight,  (i + 1) * sizewidth+1,(j + 1) * sizeheight);
                    //System.out.println("LEFT: " + r.left + " ||TOP: " + r.top + " ||Right: " + r.right + " ||Bottom: " + r.bottom);

                    falak.add(r);

                }
                if (map[j][i].isWestWall()) {
                    path.moveTo(i * sizewidth, j * sizeheight);
                    path.lineTo((i + 1) * sizewidth, j * sizeheight);
                    //falak.add(new Rect(i * sizewidth, j * sizeheight, i * sizewidth, (j + 1) * sizeheight));
                    //System.out.println("PATH: " + "X " + i * sizewidth + " " + " ||Y " + j * sizeheight + " ||X' " + i * sizewidth + " ||Y' " + (j + 1) * sizeheight);
                    Rect r = new Rect( i * sizewidth,j * sizeheight -1,  (i + 1) * sizewidth,j * sizeheight+1);
                    //System.out.println("LEFT: " + r.left + " ||TOP: " + r.top + " ||Right: " + r.right + " ||Bottom: " + r.bottom);
                    falak.add(r);

                }
                if (map[j][i].isEastWall()) {
                    path.moveTo(i * sizewidth, (j + 1) * sizeheight);
                    path.lineTo((i + 1) * sizewidth, (j + 1) * sizeheight);
                    //System.out.println("PATH: " +"X "+ i*sizewidth+ " "+  " ||Y " + (j+1)*sizeheight + " ||X' "+ (i+1)*sizewidth + " ||Y' "+ (j+1)*sizeheight);
                    Rect r = new Rect( i * sizewidth, (j+1) * sizeheight-1, (i + 1) * sizewidth,(j + 1) * sizeheight+1);
                    //System.out.println("LEFT: " + r.left + " ||TOP: " + r.top + " ||Right: " +r. right + " ||Bottom: " + r.bottom);
                    falak.add(r);
                }

            }
        }

        xPos=50;
        yPos=50;



    }

    Rect ballB;
    public void updateBall(float xAccel, float yAccel) {
        float frameTime = 0.666f;
        xVel = (xAccel * frameTime);
        yVel = (yAccel * frameTime);



        float xS = (xVel / 2) * frameTime;
        float yS = (yVel / 2) * frameTime;
       // ballB = new Rect((int)xPos,(int)yPos,ball.getWidth(),ball.getHeight());

        for (int i=0;i<falak.size();i++) {
            {
                Rect aktualisFal = falak.get(i);
                if (Math.abs(yPos - aktualisFal.top) < 5 && (aktualisFal.left < xPos && aktualisFal.right > xPos)) {
                    utkozes = true;
                    p = new Paint(Color.GREEN);
                    //paintWall=new Paint(Color.GREEN);
                    if(aktualisFal.left==aktualisFal.right)
                    {
                        WallType=0;
                    }
                    else
                    {
                        WallType=1;
                    }

                    break;
                }

                if (Math.abs(xPos - aktualisFal.left) < 5 && (aktualisFal.top < yPos && aktualisFal.bottom > yPos)) {
                    utkozes = true;
                    p = new Paint(Color.RED);
                    break;
                }
            }


            }



        if(utkozes)
        {
            xPos=50;
            yPos=50;

            // yS=0;
           // xS=0;
            if(WallType==0) {
                yS=0;
            }

            else
            {
                xS=0;
            }



            //xPos--;
            //yPos--;
        }


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

        if(utkozes)
        {
            utkozes=false;
        }

System.out.println("X : " +xPos +" "+ " Y"+yPos);
    }

    @Override
    protected void onDraw(Canvas canvas) {
      // canvas.drawBitmap(ball, xPos, yPos, null);
        canvas.drawPath(path, paintWall);
        //DrawMap(canvas);
        for(int i = 0; i < falak.size(); i++){
            canvas.drawRect(falak.get(i), redpaint);
        }


        path2=new Path();
        path2.addCircle(xPos, yPos,20 , Path.Direction.CW);

        canvas.drawPath(path2, p);

        Region region1 = new Region();
        region1.setPath(path, clip);
        Region region2 = new Region();
        region2.setPath(path2, clip);

        region1.op(region2, Region.Op.INTERSECT);
        if (!region1.quickReject(region2) && region1.op(region2, Region.Op.INTERSECT)) {

           // utkozes=true;

            // Collision!
        }


      //  path2=new Path();
        //path2.addCircle(xPos, yPos,2 , Path.Direction.CW);
        //canvas.drawPath(path2, paintWall);

        invalidate();
    }




    private  void DrawMap(Canvas canvas)
    {
        /*

        WallSegmentModel[][] map=new WallSegmentModel[10][10];

        for( int i = 0; i < map.length; i++ )
        {
            for( int j = 0; j < map[i].length; j++ )
            {
                WallSegmentModel model = new WallSegmentModel(i,j);
                model.setNorthWall(true);
                model.setSouthWall(false);
                model.setSouthWall(false);
                model.setSouthWall(false);
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


                    //canvas.drawRect(10, 10, 20, 20, paint);
                    canvas.drawLine(sizewidth*i, sizehieght*j, sizewidth*i+sizewidth, sizehieght*j, paint);
                    //  canvas.drawLine(20, 0, 0, 20, paint);

                }
                if(map[i][j].isSouthWall()==true)
                {

                    //canvas.drawRect(10, 10, 20, 20, paint);
                    canvas.drawLine(sizewidth*i, sizehieght*j+sizehieght, sizewidth*i+sizewidth, sizehieght*j+sizehieght, paint);
                }
                if(map[i][j].isEastWall()==true)
                {

                    //canvas.drawRect(10, 10, 20, 20, paint);
                    canvas.drawLine(sizewidth*i+sizewidth, sizehieght*j, sizewidth*i+sizewidth, sizehieght*j+sizehieght, paint);
                }
                if(map[i][j].isWestWall()==true)
                {

                    //canvas.drawRect(10, 10, 20, 20, paint);
                    canvas.drawLine(sizewidth*i, sizehieght*j, sizewidth*i, sizehieght*j+sizehieght, paint);
                }


            }
        }

        */
    }
}