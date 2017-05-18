package hu.obuda.uni.nik.labyrinthmaze;

import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

import hu.obuda.uni.nik.labyrinthmaze.model.Hole;

/**
 * Created by Heisuke on 2017.05.18..
 */

public class HoleGenerator {

    private List<Hole> holes;
    int w,h;

    public HoleGenerator(int sizewidth,int sizeheight){
        holes = new ArrayList<Hole>();
        this.w=sizewidth;
        this.h= sizeheight;
        GenerateHoles();

    }

    public void GenerateHoles(){
        for (int i = 0; i<10; i++){
            for (int j = 0; j < 10; j++) {
                if(Hole.rand.nextInt(100)<30){
                    if((i!=0 && j!=0) && (i!=9 && j !=9)) {
                        RectF rect = new RectF((i * w), j * h, (i * w) + 50, (j * h) + 50);
                        getHoles().add(new Hole(rect));
                    }

                }
            }
        }

    }


    public List<Hole> getHoles() {
        return holes;
    }
}
