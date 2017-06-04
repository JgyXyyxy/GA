package com.sdu.com.sdu.ga;


import com.sdu.pic.ColorFormat;
import com.sdu.pic.PicAtri;
import com.sdu.swing.Drawing;
import com.sdu.swing.ShowImage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by J on 2017/5/19.
 */
public class GeneticAlgorithm {

    private Population population;
    private int maxIterNum = 50000;//最大迭代次数
    private double mutationRate = 0.01;//基因变异的概率
    private int maxMutationNum = 3;//最大变异步长
    private int generation = 1;//当前遗传到第几代
    private double bestScore = 0;
    private Drawing drawing ;
    private ShowImage showImage;

    public GeneticAlgorithm(Population population ,ShowImage showImage) {
        this.population = population;
//        this.drawing = drawing;
        this.showImage = showImage;
    }

    private Chromosome getParentChromosome (){
        double slice = Math.random() * population.getTotalFitness();
        double sum = 0;
        for (Chromosome chro : population.getPopulation()) {
            sum += chro.getScore();
            //转到对应的位置并且适应度不小于平均适应度
            if (sum > slice && chro.getScore() >= population.getAverageFitness()) {
                return chro;
            }
        }
        return null;
    }

    public void evolve(PicAtri atri, int x, int y) {
        List<Chromosome> childChromosomeList = new ArrayList<Chromosome>();
        while (childChromosomeList.size() < population.getPopSize()) {
            Chromosome p1 = getParentChromosome();
            Chromosome p2 = getParentChromosome();
            List<Chromosome> children = Chromosome.crossover(p1, p2);
            if (children != null) {
                for (Chromosome chro : children) {
                    if (childChromosomeList.size()<population.getPopSize()) {
                        childChromosomeList.add(chro);
                    }else{
                        break;
                    }
                }
            }
        }


        population.setPopulation(childChromosomeList);
        mutation();
        population.caculteScore();
        Chromosome bestChromosome = population.getBestChromosome();
        bestScore = bestChromosome.getScore();

//        Color[] colors = new Color[population.getPopSize()];
//        for (int i = 0; i < childChromosomeList.size(); i++) {
//            Chromosome chromosome = childChromosomeList.get(i);
//            colors[i] = ColorFormat.binary2Color(chromosome.getGene());
//        }
//        atri.setAlpha(atri.getImageSrc(),colors);
//        drawing.getGImage();
        Color color = ColorFormat.binary2Color(bestChromosome.getGene());
        atri.setPointAlpha(x,y,color);
//        showImage.updatePic();
    }

    public void mutation() {
        for (Chromosome chro : population.getPopulation()) {
            if (Math.random() < mutationRate) { //发生基因突变
                int mutationNum = (int) (Math.random() * maxMutationNum);
                chro.mutation(mutationNum);
            }
        }
    }

//    public void  caculte(){
//        generation = 1;
//        while (generation < maxIterNum) {
//            //种群遗传
//            evolve();
//            print();
//            generation++;
//        }
//
//    }


    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    public int getMaxIterNum() {
        return maxIterNum;
    }

    public void setMaxIterNum(int maxIterNum) {
        this.maxIterNum = maxIterNum;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public int getMaxMutationNum() {
        return maxMutationNum;
    }

    public void setMaxMutationNum(int maxMutationNum) {
        this.maxMutationNum = maxMutationNum;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public Drawing getDrawing() {
        return drawing;
    }

    public double getBestScore() {
        return bestScore;
    }

    public void setBestScore(double bestScore) {
        this.bestScore = bestScore;
    }

    public ShowImage getShowImage() {
        return showImage;
    }

    public void setShowImage(ShowImage showImage) {
        this.showImage = showImage;
    }

    public void setDrawing(Drawing drawing) {
        this.drawing = drawing;
    }
}
