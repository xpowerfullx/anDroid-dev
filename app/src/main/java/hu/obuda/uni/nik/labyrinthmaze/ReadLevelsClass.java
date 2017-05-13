package hu.obuda.uni.nik.labyrinthmaze;

/**
 * Created by Tomi on 2017. 04. 27..
 */

public class ReadLevelsClass {
    public  static int [][] GetMap()
    {
        int[][] array = {
                { 0,0,0,0,0,0 },
                { 0,0,0,0,2,0 },
                { 1,1,1,1,1,0 },
                { 0,0,0,0,0,0 },
                { 0,1,0,0,0,0 },
                { 0,1,0,0,1,0 },
                { 0,1,0,0,1,0 },
                { 0,1,1,1,1,1 },
                { 0,0,0,2,2,2 },
                { 0,0,0,0,0,0 },
        };
        return  array;
    }

}