package com.sdu.com.sdu.ga;

import com.sdu.pic.ColorFormat;

import java.awt.*;
import java.util.*;

/**
 * Created by J on 2017/5/19.
 */
public class Chromosome {

    private Color color;
    private boolean[] gene;
    private double score;


    public Chromosome() {
        gene = new boolean[24];
        for (int i = 0; i < 24; i++) {
            gene[i] = Math.random() >= 0.5;
        }
    }

    public Chromosome(Color color) {
        this.color = color;
        try {
            this.gene = ColorFormat.toBinaryGene(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean[] getGene() {
        return gene;
    }

    public void setGene(boolean[] gene) {
        this.gene = gene;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void mutation(int num) {
        int size = gene.length;
        for (int i = 0; i < num; i++) {
            //寻找变异位置
            int at = ((int) (Math.random() * size)) % size;
            //变异后的值
            boolean bool = !gene[at];
            gene[at] = bool;
        }
    }

    public static Chromosome clone(final Chromosome c) {
        if (c == null || c.gene == null) {
            return null;
        }
        Chromosome copy = new Chromosome();
        for (int i = 0; i < 24; i++) {
            copy.gene[i] = c.gene[i];
        }
        return copy;
    }

    public static ArrayList<Chromosome> crossover(Chromosome p1, Chromosome p2) {
        if (p1 == null || p2 == null) {
            return null;
        }
        if (p1.gene == null || p2.gene == null) {
            return null;
        }
        if (p1.gene.length != p2.gene.length) {
            return null;
        }
        Chromosome c1 = clone(p1);
        Chromosome c2 = clone(p2);
        //随机产生交叉互换位置
        int size = c1.gene.length;
        int a = ((int) (Math.random() * size)) % size;
        int b = ((int) (Math.random() * size)) % size;
        int min = a > b ? b : a;
        int max = a > b ? a : b;
        //对位置上的基因进行交叉互换
        for (int i = min; i <= max; i++) {
            boolean t = c1.gene[i];
            c1.gene[i] = c2.gene[i];
            c2.gene[i] = t;
        }
        ArrayList<Chromosome> list = new ArrayList<Chromosome>();
        list.add(c1);
        list.add(c2);
        return list;
    }


}
