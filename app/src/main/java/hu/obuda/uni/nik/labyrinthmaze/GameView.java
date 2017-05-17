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

    private float xPos = 10, xVel = 0.0f;
    private float yPos = 10, yVel = 0.0f;
    private MapGenerator mapgen;
    private WallSegment[][] map;
    private Path path;
    private Paint paintWall;
    private boolean utkozes;
    private Paint p = new Paint(Color.BLUE);
    private Rect endZone;
    private Region clip;

    private Path path2;
    private Paint redpaint;
    private ArrayList<Rect> falak;
    private int WallType = 0;
    private int sizewidth;
    private int sizeheight;
    private int screenWidth;
    private int screenHeight;

    public GameView(Context context, Display disp) {
        super(context);

        Point size = new Point();
        disp.getSize(size);

        path2 = new Path();

        clip = new Region(0, 0, getWidth(), getHeight());

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


        sizewidth = (displayMetrics.widthPixels) / 10;
        sizeheight = (displayMetrics.heightPixels) / 10;
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        endZone = new Rect(screenWidth - sizewidth, screenHeight - sizeheight, screenWidth, screenHeight);

        redpaint = new Paint();
        redpaint.setColor(Color.RED);
        redpaint.setStrokeWidth(2);
        redpaint.setStyle(Paint.Style.STROKE);
        falak = new ArrayList<Rect>();
        getWallLinesForPath();
    }

    private void getWallLinesForPath() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if (map[j][i].isNorthWall()) {
                    path.moveTo(i * sizewidth, j * sizeheight);
                    path.lineTo(i * sizewidth, (j + 1) * sizeheight);
                    Rect r = new Rect(i * sizewidth, j * sizeheight, i * sizewidth, (j + 1) * sizeheight);
                    falak.add(r);

                }
                if (map[j][i].isSouthWall()) {
                    path.moveTo((i + 1) * sizewidth, j * sizeheight);
                    path.lineTo((i + 1) * sizewidth, (j + 1) * sizeheight);
                    Rect r = new Rect((i + 1) * sizewidth, j * sizeheight, (i + 1) * sizewidth, (j + 1) * sizeheight);
                    falak.add(r);

                }
                if (map[j][i].isWestWall()) {
                    path.moveTo(i * sizewidth, j * sizeheight);
                    path.lineTo((i + 1) * sizewidth, j * sizeheight);
                    Rect r = new Rect(i * sizewidth, j * sizeheight, (i + 1) * sizewidth, j * sizeheight);
                    falak.add(r);

                }
                if (map[j][i].isEastWall()) {
                    path.moveTo(i * sizewidth, (j + 1) * sizeheight);
                    path.lineTo((i + 1) * sizewidth, (j + 1) * sizeheight);
                    Rect r = new Rect(i * sizewidth, (j + 1) * sizeheight, (i + 1) * sizewidth, (j + 1) * sizeheight);
                    falak.add(r);
                }

            }
        }
    }



    public void updateBall(float xAccel, float yAccel) {
        float frameTime = 0.666f;
        xVel = (xAccel * frameTime);
        yVel = (yAccel * frameTime);


        float xS = (xVel / 2) * frameTime;
        float yS = (yVel / 2) * frameTime;
        Rect aktualisFal = new Rect();
        for (int i = 0; i < falak.size(); i++) {
            {
                aktualisFal = falak.get(i);
                if (Math.abs(yPos - aktualisFal.top) < 5 && (aktualisFal.left < xPos && aktualisFal.right > xPos)) {
                    utkozes = true;
                    p = new Paint(Color.GREEN);
                    if (aktualisFal.top == aktualisFal.bottom) {
                        WallType = 1;
                    } else {
                        WallType = 0;
                    }


                    break;
                }

                if (Math.abs(xPos - aktualisFal.left) < 5 && (aktualisFal.top < yPos && aktualisFal.bottom > yPos)) {
                    utkozes = true;
                    p = new Paint(Color.GREEN);
                    if (aktualisFal.top + 10 == aktualisFal.bottom) {
                        WallType = 1;
                    } else {
                        WallType = 0;
                    }


                    break;
                }
            }


        }


        if ((utkozes)) {
            if (WallType == 1) {
                if (yS > 0)
                    yPos += 5;
                else
                    yPos -= 5;
                yS = 0;


            } else {
                if (xS > 0)
                    xPos += 5;
                else
                    xPos -= 5;

                xS = 0;
            }
        }


        xPos -= xS;
        yPos -= yS;


        xVel = (xAccel * frameTime);
        yVel = (yAccel * frameTime);


        xS = (xVel / 2) * frameTime;
        yS = (yVel / 2) * frameTime;


        if (utkozes) {

            if (WallType == 0) {
                if ((aktualisFal.left - xPos) > 0) {
                    // balrol
                    if (xS > 0) {
                        utkozes = false;
                    } else {
                        xS = 0;
                    }

                } else {
                    // jobbrol
                    if (xS < 0) {
                        utkozes = false;
                    } else {
                        xS = 0;
                    }
                }
            }
            if (WallType == 1) {
                if ((aktualisFal.top - yPos) < 0) {
                    //lentrol
                    if (yS < 0) {
                        utkozes = false;
                    } else {
                        yS = 0;
                    }
                } else if ((aktualisFal.bottom - yPos) > 0) {

                    //  fentrol
                    if (yS > 0) {
                        utkozes = false;
                    } else {
                        yS = 0;
                    }


                }
            }


        }

        xPos -= xS;
        yPos -= yS;


        if (utkozes) {
            utkozes = false;

        }
        if (Math.abs((int) xPos - endZone.centerX()) <= 5 && Math.abs((int) yPos - endZone.centerY()) <= 5) {

            map = mapgen.generateNewMap();
            path = new Path();
            falak.clear();
            getWallLinesForPath();
            xPos = 50;
            yPos = 50;

        }
        System.out.println("X : " + xPos + " " + " Y" + yPos);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paintWall);
        for (int i = 0; i < falak.size(); i++) {
            canvas.drawRect(falak.get(i), redpaint);
        }
        endZone = new Rect(screenWidth - sizewidth, screenHeight - sizeheight, screenWidth, screenHeight);

        Paint endZonePaint = new Paint();
        endZonePaint.setColor(Color.GREEN);
        endZonePaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(endZone, endZonePaint);
        path2 = new Path();
        path2.addCircle(xPos, yPos, 20, Path.Direction.CW);

        canvas.drawPath(path2, p);
        invalidate();
    }
}