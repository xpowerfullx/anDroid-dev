package hu.obuda.uni.nik.labyrinthmaze.model;

/**
 * Created by Roltown on 2017. 04. 26..
 */

public class WallSegmentModel {
    public static final int UNPROCESSED_SEGMENT = 0;
    public static final int PROCESSED_SEGMENT = 1;
    public static final int FINAL_SEGMENT = 2;
    public static final int START_SEGMENT = 0;
    public static final int FINISH_SEGMENT = 2;
    public static final int SIMPLE_SEGMENT = 1;

    private int segmentState;
    private boolean northWall;
    private boolean southWall;
    private boolean westWall;
    private boolean eastWall;
    private int segmentSize;
    private float segmentX;
    private float segmentY;
    private int segmentType;

    public WallSegmentModel(int segmentX, int segmentY) {
        this.segmentX = segmentX;
        this.segmentY = segmentY;
        segmentType = SIMPLE_SEGMENT;
    }

    public int getSegmentType() {
        return segmentType;
    }

    public void setSegmentType(int segmentType) {
        this.segmentType = segmentType;
    }

    public int getSegmentSize() {
        return segmentSize;
    }

    public float getSegmentX() {
        return segmentX;
    }

    public float getSegmentY() {
        return segmentY;
    }

    public int getSegmentState() {
        return segmentState;
    }

    public void setSegmentState(int segmentState) {
        this.segmentState = segmentState;
    }

    public boolean isNorthWall() {
        return northWall;
    }

    public void setNorthWall(boolean northWall) {
        this.northWall = northWall;
    }

    public boolean isSouthWall() {
        return southWall;
    }

    public void setSouthWall(boolean southWall) {
        this.southWall = southWall;
    }

    public boolean isWestWall() {
        return westWall;
    }

    public void setWestWall(boolean westWall) {
        this.westWall = westWall;
    }

    public boolean isEastWall() {
        return eastWall;
    }

    public void setEastWall(boolean eastWall) {
        this.eastWall = eastWall;
    }
}
