package hu.obuda.uni.nik.labyrinthmaze.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.obuda.uni.nik.labyrinthmaze.model.WallSegment;

/**
 * Created by Adam on 2017. 05. 13..
 */

public class MapGenerator {
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int WEST = 2;
    public static final int EAST = 3;

    private Random R = new Random();
    private WallSegment[][] mapMatrix;
    private List<WallSegment> roadMap;
    private List<WallSegment> finalRoadMap;

    public MapGenerator() {
        mapMatrix = new WallSegment[10][10];
        roadMap = new ArrayList<WallSegment>();
        finalRoadMap = new ArrayList<WallSegment>();
    }

    public WallSegment[][] generateNewMap(){
        for (int i = 0; i<10; i++){
            for (int j = 0; j < 10; j++) {
                mapMatrix[i][j] = new WallSegment(i, j);
            }
        }

        int coordX = R.nextInt(10);
        int coordY = R.nextInt(10);
        roadMap.add(mapMatrix[coordX][coordY]);

        //Road generation loop
        while (!roadMap.isEmpty()){
            if (hasNextTile(coordX, coordY)) {
                int dir = R.nextInt(4);
                carveWall(coordX, coordY, dir);
                coordX = roadMap.get(roadMap.size() -1).getSegmentX();
                coordY = roadMap.get(roadMap.size() -1).getSegmentY();
            }
            else {
                if (!roadMap.isEmpty()) {
                    finalRoadMap.add(roadMap.get(roadMap.size() - 1));
                    roadMap.remove(roadMap.size() -1 );
                    if (!roadMap.isEmpty()) {
                        coordX=roadMap.get(roadMap.size() -1).getSegmentX();
                        coordY=roadMap.get(roadMap.size() -1).getSegmentY();
                    }
                }
            }
        }

        return mapMatrix;
    }
    private boolean checkValidCoordinates(int newC){
        if (newC >= 0 && newC < 10){
            return true;
        }
        return false;
    }

    private boolean hasNextTile(int x, int y){
        int freeTiles = 0;
        if (x-1 >= 0 && !roadMap.contains(mapMatrix[x-1][y]) && !finalRoadMap.contains(mapMatrix[x-1][y])){
            freeTiles++;
        }
        if (x+1 <10 && !roadMap.contains(mapMatrix[x+1][y]) && !finalRoadMap.contains(mapMatrix[x+1][y]))
        {
            freeTiles++;
        }
        if (y-1 >= 0 && !roadMap.contains(mapMatrix[x][y-1]) && !finalRoadMap.contains(mapMatrix[x][y-1])){
            freeTiles++;
        }
        if (y+1 < 10 && !roadMap.contains(mapMatrix[x][y+1]) && !finalRoadMap.contains(mapMatrix[x][y+1])){
            freeTiles++;
        }
        if (freeTiles>0) {
            return true;
        }
        return false;
    }
    private void carveWall(int coordX, int coordY, int dir){
        //NORTH
        if (dir == NORTH && checkValidCoordinates(coordY - 1) && !roadMap.contains(mapMatrix[coordX][coordY-1]) && !finalRoadMap.contains(mapMatrix[coordX][coordY-1])) {
            mapMatrix[coordX][coordY].setNorthWall(false);
            coordY = coordY - 1;
            mapMatrix[coordX][coordY].setSouthWall(false);
            roadMap.add(mapMatrix[coordX][coordY]);
            return;
        }
        //SOUTH
        if (dir == SOUTH && checkValidCoordinates(coordY + 1) && !roadMap.contains(mapMatrix[coordX][coordY+1]) && !finalRoadMap.contains(mapMatrix[coordX][coordY+1])) {
            mapMatrix[coordX][coordY].setSouthWall(false);
            coordY = coordY + 1;
            mapMatrix[coordX][coordY].setNorthWall(false);
            roadMap.add(mapMatrix[coordX][coordY]);
            return;
        }
        //WEST
        if (dir == WEST && checkValidCoordinates(coordX - 1) && !roadMap.contains(mapMatrix[coordX-1][coordY]) && !finalRoadMap.contains(mapMatrix[coordX-1][coordY])) {
            mapMatrix[coordX][coordY].setWestWall(false);
            coordX = coordX - 1;
            mapMatrix[coordX][coordY].setEastWall(false);
            roadMap.add(mapMatrix[coordX][coordY]);
            return;
        }
        //EAST
        if (dir == EAST && checkValidCoordinates(coordX + 1) && !roadMap.contains(mapMatrix[coordX+1][coordY]) && !finalRoadMap.contains(mapMatrix[coordX+1][coordY])) {
            mapMatrix[coordX][coordY].setEastWall(false);
            coordX = coordX + 1;
            mapMatrix[coordX][coordY].setWestWall(false);
            roadMap.add(mapMatrix[coordX][coordY]);
            return;
        }
    }
}