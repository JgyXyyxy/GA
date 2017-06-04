package com.sdu.com.sdu.ga;


import java.util.*;

/**
 * Created by J on 2017/5/19.
 */
public class Population {


    private List<Chromosome> population;
    private int popSize = 100;//种群数量

    private double bestFitness;
    private double worstFitness;
    private double totalFitness;
    private double averageFitness;
    private Chromosome bestChromosome;
    private Fitness fitness;

    public Population(int popsize,Fitness fitness) {
        this.fitness = fitness;
        this.popSize = popsize;
        this.init();
    }

    public Population(List<Chromosome> population,Fitness fitness) {
        this.population = population;
        this.popSize = population.size();
        this.fitness = fitness;
        caculteScore();
    }

    public List<Chromosome> getPopulation() {
        return population;
    }

    public void setPopulation(List<Chromosome> population) {
        this.population = population;
    }

    public int getPopSize() {
        return popSize;
    }

    public void setPopSize(int popSize) {
        this.popSize = popSize;
    }

    public double getBestFitness() {
        return bestFitness;
    }

    public void setBestFitness(double bestFitness) {
        this.bestFitness = bestFitness;
    }

    public double getWorstFitness() {
        return worstFitness;
    }

    public void setWorstFitness(double worstFitness) {
        this.worstFitness = worstFitness;
    }

    public double getTotalFitness() {
        return totalFitness;
    }

    public void setTotalFitness(double totalFitness) {
        this.totalFitness = totalFitness;
    }

    public double getAverageFitness() {
        return averageFitness;
    }

    public void setAverageFitness(double averageFitness) {
        this.averageFitness = averageFitness;
    }

    public Chromosome getBestChromosome() {
        return bestChromosome;
    }

    public void setBestChromosome(Chromosome bestChromosome) {
        this.bestChromosome = bestChromosome;
    }

    public void init() {
        population = new ArrayList<Chromosome>();
        for (int i = 0; i < popSize; i++) {
            Chromosome chro = new Chromosome();
            population.add(chro);
        }
        caculteScore();
    }

    public void caculteScore() {
        for (Chromosome chromosome:population){
            chromosome.setScore(fitness.calculateFittness(chromosome.getGene()));
        }
        bestFitness = population.get(0).getScore();
        worstFitness = population.get(0).getScore();
        totalFitness= 0;
        for (Chromosome chro : population) {
            if (chro.getScore() >= bestFitness) { //设置最好基因值
                bestFitness = chro.getScore();
                bestChromosome = chro;
            }
            if (chro.getScore() < worstFitness) { //设置最坏基因值
                worstFitness = chro.getScore();
            }
            totalFitness += chro.getScore();
        }
        averageFitness = totalFitness / popSize;
        //因为精度问题导致的平均值大于最好值，将平均值设置成最好值
        averageFitness = averageFitness > bestFitness ? bestFitness : averageFitness;
    }



}
