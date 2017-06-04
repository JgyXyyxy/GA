package com.sdu.com.sdu.ga;

import com.sdu.pic.ColorFormat;

import java.awt.*;

/**
 * Created by J on 2017/5/19.
 */
public class Fitness {

    Color target = Color.yellow;

    public Fitness() {
    }

    public Fitness(Color target) {
        this.target = target;
    }


    public double calculateFittness(boolean[] srcGene) {

        double fit = 0;
        try {
            boolean[] targetGene = ColorFormat.toBinaryGene(target);
            for (int i = 0;i<24;i++){
                if (srcGene[i] == targetGene[i])
                    fit=fit+1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fit;
    }

}
