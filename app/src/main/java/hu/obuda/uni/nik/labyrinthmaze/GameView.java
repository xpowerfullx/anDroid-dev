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
import java.util.List;
import java.util.Random;

import hu.obuda.uni.nik.labyrinthmaze.model.Hole;
import hu.obuda.uni.nik.labyrinthmaze.model.WallSegment;


/**
 * Created by Tomi on 2017. 04. 26..
 */

public class GameView extends View {


    private float xPos=10, xAccel, xVel = 0.0f;
    private float yPos=10, yAccel, yVel = 0.0f;
    private float xMax, yMax;
    private Bitmap ball;
    private float wall = 25.0f;
    MapGenerator mapgen;
    WallSegment[][] map;
    Path path;
    Paint paintWall;
     HoleGenerator hl;
    boolean utkozes;
    Paint p = new Paint(Color.BLUE);


    Region clip;
    int sizewidth;
    int sizeheight;
    Path path2;
    private Paint paint;
    private Paint redpaint;
    private ArrayList<RectF> Rects;
    private ArrayList<Rect> falak;
    private  int WallType=0;
    List<RectF> recktek = new ArrayList<RectF>();

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
        sizewidth= (displayMetrics.widthPixels - 10)/10;
        sizeheight= (displayMetrics.heightPixels - 50)/10;

        hl = new HoleGenerator(sizewidth,sizeheight);





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
                    Rect r = new Rect(i * sizewidth  ,j * sizeheight,  i * sizewidth ,(j+1) * sizeheight);
                    falak.add(r);
                    System.out.println("LEFT: " + r.left + " ||TOP: " + r.top + " ||Right: " + r.right + " ||Bottom: " + r.bottom);

                }
                if (map[j][i].isSouthWall()) {
                    path.moveTo((i + 1) * sizewidth, j * sizeheight);
                    path.lineTo((i + 1) * sizewidth, (j + 1) * sizeheight);
                    //System.out.println("PATH: " + "X " + (i + 1) * sizewidth + " " + " ||Y " + j * sizeheight +" ||X' " +(i + 1) * sizewidth + " ||Y' " + (j + 1) * sizeheight);

                    Rect r = new Rect( (i+1) * sizewidth,j * sizeheight,  (i + 1) * sizewidth,(j + 1) * sizeheight);
                    //System.out.println("LEFT: " + r.left + " ||TOP: " + r.top + " ||Right: " + r.right + " ||Bottom: " + r.bottom);

                    falak.add(r);

                }
                if (map[j][i].isWestWall()) {
                    path.moveTo(i * sizewidth, j * sizeheight);
                    path.lineTo((i + 1) * sizewidth, j * sizeheight);
                    //falak.add(new Rect(i * sizewidth, j * sizeheight, i * sizewidth, (j + 1) * sizeheight));
                    //System.out.println("PATH: " + "X " + i * sizewidth + " " + " ||Y " + j * sizeheight + " ||X' " + i * sizewidth + " ||Y' " + (j + 1) * sizeheight);
                    Rect r = new Rect( i * sizewidth,j * sizeheight ,  (i + 1) * sizewidth,j * sizeheight);
                    //System.out.println("LEFT: " + r.left + " ||TOP: " + r.top + " ||Right: " + r.right + " ||Bottom: " + r.bottom);
                    falak.add(r);

                }
                if (map[j][i].isEastWall()) {
                    path.moveTo(i * sizewidth, (j + 1) * sizeheight);
                    path.lineTo((i + 1) * sizewidth, (j + 1) * sizeheight);
                    //System.out.println("PATH: " +"X "+ i*sizewidth+ " "+  " ||Y " + (j+1)*sizeheight + " ||X' "+ (i+1)*sizewidth + " ||Y' "+ (j+1)*sizeheight);
                    Rect r = new Rect( i * sizewidth, (j+1) * sizeheight, (i + 1) * sizewidth,(j + 1) * sizeheight);
                    //System.out.println("LEFT: " + r.left + " ||TOP: " + r.top + " ||Right: " +r. right + " ||Bottom: " + r.bottom);
                    falak.add(r);
                }

            }
        }









    }
    boolean jobbrol=false;
    boolean balrol=false;
    boolean fentrol=false;
    boolean lentrol=false;

    Rect ballB;
    public void updateBall(float xAccel, float yAccel) {
        float frameTime = 0.666f;
        xVel = (xAccel * frameTime);
        yVel = (yAccel * frameTime);

        float lastXpos=xPos;

        float lastYpos=xPos;

        float xS = (xVel / 2) * frameTime;
        float yS = (yVel / 2) * frameTime;
        // ballB = new Rect((int)xPos,(int)yPos,ball.getWidth(),ball.getHeight());
        Rect aktualisFal=new Rect();
        for (int i=0;i<falak.size();i++) {
            {
                aktualisFal = falak.get(i);
                if (Math.abs(yPos - aktualisFal.top) < 5 && (aktualisFal.left < xPos && aktualisFal.right > xPos)) {
                    utkozes = true;
                    p = new Paint(Color.GREEN);
                    //paintWall=new Paint(Color.GREEN);
                    if(aktualisFal.top==aktualisFal.bottom)
                    {
                        WallType=1;
                    }
                    else
                    {
                        WallType=0;
                    }


                    break;
                }

                if (Math.abs(xPos - aktualisFal.left) < 5 && (aktualisFal.top < yPos && aktualisFal.bottom > yPos)) {
                    utkozes = true;
                    p = new Paint(Color.GREEN);
                    //paintWall=new Paint(Color.GREEN);
                    if(aktualisFal.top+10==aktualisFal.bottom)
                    {
                        WallType=1;
                    }
                    else
                    {
                        WallType=0;
                    }


                    break;
                }
            }


        }








        if((utkozes) )
        {




            //xPos=50;
            //yPos=50;

            // yS=0;
            // xS=0;
            if(WallType==1) {
                if(yS>0)
                    yPos += 5;
                else
                yPos-=5;
                yS=0;


            }
            else
            {
                if(xS>0)
                    xPos+=5;
                else
                  xPos-=5;

                xS=0;
            }



            //xPos--;
            //yPos--;
        }


        xPos -= xS;
        yPos -= yS;



        xVel = (xAccel * frameTime);
        yVel = (yAccel * frameTime);





        xS = (xVel / 2) * frameTime;
        yS = (yVel / 2) * frameTime;


        if(utkozes)
        {

            if(WallType==0)
            {
                if((aktualisFal.left-xPos)>0)
                {
                   // balrol
                    if(xS>0)
                    {
                        utkozes=false;
                    }
                    else {
                        xS=0;
                    }

                }
                else
                {
                   // jobbrol
                    if (xS<0)
                    {
                        utkozes=false;
                    }
                    else
                    {
                        xS=0;
                    }
                }
            }
            if(WallType==1)
            {
                if((aktualisFal.top-yPos)<0)
                {
                    //lentrol
                    if (yS<0)
                    {
                        utkozes=false;
                    }
                    else
                    {
                        yS=0;
                    }
                }
                else if((aktualisFal.bottom-yPos)>0)
                {

                  //  fentrol
                    if(yS>0)
                    {
                        utkozes=false;
                    }
                    else
                    {
                        yS=0;
                    }


                }
            }


        }

        xPos -= xS;
        yPos -= yS;





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

        for(int i =0; i<hl.getHoles().size();i++){

            canvas.drawOval(hl.getHoles().get(i).getRect(),hl.getHoles().get(i).getPaintHole());

        }






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